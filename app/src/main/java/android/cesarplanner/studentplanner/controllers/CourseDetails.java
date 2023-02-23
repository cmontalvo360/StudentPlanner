package android.cesarplanner.studentplanner.controllers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.cesarplanner.studentplanner.R;
import android.cesarplanner.studentplanner.database.Repository;
import android.cesarplanner.studentplanner.entities.Assessment;
import android.cesarplanner.studentplanner.entities.Course;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CourseDetails extends AppCompatActivity {
    EditText editTitle;
    EditText editStart;
    EditText editEnd;
    EditText editStatus;
    EditText editInstructorName;
    EditText editInstructorPhone;
    EditText editInstructorEmail;
    EditText editNote;
    LinearLayout noteLayout;
    DatePickerDialog.OnDateSetListener startDate;
    final Calendar myCalendarStart = Calendar.getInstance();
    int id, termID, count;
    String title, start, end, status, name, phone, email, note;
    boolean isChecked;
    Course course;
    Repository repository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        editTitle = findViewById(R.id.title);
        editStart = findViewById(R.id.startDate);
        editEnd = findViewById(R.id.endDate);
        editStatus = findViewById(R.id.status);
        editInstructorName = findViewById(R.id.name);
        editInstructorPhone = findViewById(R.id.phone);
        editInstructorEmail = findViewById(R.id.email);
        editNote = findViewById(R.id.note);
        id = getIntent().getIntExtra("id", -1);
        title = getIntent().getStringExtra("title");
        start = getIntent().getStringExtra("start");
        end = getIntent().getStringExtra("end");
        status = getIntent().getStringExtra("status");
        name = getIntent().getStringExtra("name");
        phone = getIntent().getStringExtra("phone");
        email = getIntent().getStringExtra("email");
        note = getIntent().getStringExtra("note");
        termID = getIntent().getIntExtra("termID", -1);
        editTitle.setText(title);
        editStart.setText(start);
        editEnd.setText(end);
        editStatus.setText(status);
        editInstructorName.setText(name);
        editInstructorPhone.setText(phone);
        editInstructorEmail.setText(email);
        editNote.setText(note);
        noteLayout = (LinearLayout) findViewById(R.id.noteLayout);
        noteLayout.setVisibility(View.INVISIBLE);
        repository = new Repository(getApplication());
        isChecked = false;

        System.out.println("Course Details: term id " + termID);

        RecyclerView recyclerView = findViewById(R.id.assessmentRecyclerView);
        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Assessment> assessmentList = new ArrayList<>();
        for(Assessment assessment : repository.getAllAssessments()) {
            if(assessment.getCourseID() == id) {
                assessmentList.add(assessment);
            }
        }
        assessmentAdapter.setAssessments(assessmentList);

        ToggleButton toggle = (ToggleButton) findViewById(R.id.toggleNoteBtn);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    noteLayout.setVisibility(View.VISIBLE);
                    System.out.println("Button is On!");
                } else {
                    noteLayout.setVisibility(View.INVISIBLE);
                    System.out.println("Button is Off!");
                }
            }
        });

        Button button = findViewById(R.id.saveCourse);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (id == -1) {
                    course = new Course(0, editTitle.getText().toString(), editStart.getText().toString(), editEnd.getText().toString(),
                            editStatus.getText().toString(), editInstructorName.getText().toString(), editInstructorPhone.getText().toString(),
                            editInstructorEmail.getText().toString(), editNote.getText().toString(), termID);
                    repository.insert(course);
                } else {
                    course = new Course(id, editTitle.getText().toString(), editStart.getText().toString(), editEnd.getText().toString(),
                            editStatus.getText().toString(), editInstructorName.getText().toString(), editInstructorPhone.getText().toString(),
                            editInstructorEmail.getText().toString(), editNote.getText().toString(), termID);
                    repository.update(course);
                }
            }
        });

        FloatingActionButton fab = findViewById(R.id.floatingActionButton3);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CourseDetails.this, AssessmentDetails.class);
                intent.putExtra("courseID", id);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        RecyclerView recyclerView = findViewById(R.id.assessmentRecyclerView);
        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Assessment> assessmentList = new ArrayList<>();
        for(Assessment assessment : repository.getAllAssessments()) {
            if(assessment.getCourseID() == id) {
                assessmentList.add(assessment);
            }
        }

        assessmentAdapter.setAssessments(assessmentList);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_coursedetails, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.delete:

                count = 0;
                for(Assessment a : repository.getAllAssessments()) {
                    if(a.getCourseID() == id) {
                        ++count;
                    }
                }
                if(count == 0) {
                    Toast.makeText(CourseDetails.this, course.getTitle() + " was deleted", Toast.LENGTH_LONG).show();
                    repository.delete(course);
                } else {
                    Toast.makeText(CourseDetails.this, "Can't delete a course with assessments", Toast.LENGTH_LONG).show();
                }

                return true;
            case R.id.share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, editNote.getText().toString());
                sendIntent.putExtra(Intent.EXTRA_TITLE, "Check out my note!");
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                return true;
            case R.id.notifyStart:
                setAlarm(editStart.getText().toString());
                return true;
            case R.id.notifyEnd:
                setAlarm(editEnd.getText().toString());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setAlarm(String string) {
        String dateFromScreen = string;
        String format = "MM/dd/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
        Date date = null;
        try {
            date = sdf.parse(dateFromScreen);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Long trigger = date.getTime();
        Intent intent = new Intent(CourseDetails.this, MyReceiver.class);
        intent.putExtra("key",dateFromScreen + " should trigger");
        PendingIntent sender = PendingIntent.getBroadcast(CourseDetails.this, ++MainActivity.numAlert,
                intent, PendingIntent.FLAG_IMMUTABLE);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
    }
}