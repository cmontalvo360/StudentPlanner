package android.cesarplanner.studentplanner.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.cesarplanner.studentplanner.R;
import android.cesarplanner.studentplanner.database.Repository;
import android.cesarplanner.studentplanner.entities.Assessment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AssessmentDetails extends AppCompatActivity {
    EditText editTitle;
    EditText editType;
    EditText editStart;
    EditText editEnd;
    int id, courseID;
    String title, type, start, end;
    Assessment assessment;
    Repository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_details);

        editTitle = findViewById(R.id.title);
        editType = findViewById(R.id.type);
        editStart = findViewById(R.id.startDate);
        editEnd = findViewById(R.id.endDate);
        id = getIntent().getIntExtra("id", -1);
        title = getIntent().getStringExtra("title");
        type = getIntent().getStringExtra("type");
        start = getIntent().getStringExtra("start");
        end = getIntent().getStringExtra("end");
        courseID = getIntent().getIntExtra("courseID", -1);
        editTitle.setText(title);
        editType.setText(type);
        editStart.setText(start);
        editEnd.setText(end);
        repository = new Repository(getApplication());

        System.out.println("Assessment Details: course id " + courseID);

        Button button = findViewById(R.id.saveAssessment);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (id == -1) {
                    assessment = new Assessment(0, editTitle.getText().toString(), editType.getText().toString(),editStart.getText().toString(), editEnd.getText().toString(),
                             courseID);
                    repository.insert(assessment);
                    Toast.makeText(AssessmentDetails.this, assessment.getTitle() + " was created", Toast.LENGTH_LONG).show();
                } else {
                    assessment = new Assessment(id, editTitle.getText().toString(), editType.getText().toString(),editStart.getText().toString(), editEnd.getText().toString(),
                            courseID);
                    repository.update(assessment);
                    Toast.makeText(AssessmentDetails.this, assessment.getTitle() + " was updated", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_assessmentdetails, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.delete:
                for(Assessment a : repository.getAllAssessments()) {
                    if(a.getAssessmentID() == id) {
                        assessment = a;
                    }
                }

                Toast.makeText(AssessmentDetails.this, assessment.getTitle() + " was deleted", Toast.LENGTH_LONG).show();
                repository.delete(assessment);
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
        Intent intent = new Intent(AssessmentDetails.this, MyReceiver.class);
        intent.putExtra("key",dateFromScreen + "should trigger");
        PendingIntent sender = PendingIntent.getBroadcast(AssessmentDetails.this, ++MainActivity.numAlert,
                intent, PendingIntent.FLAG_IMMUTABLE);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
    }
}