package android.cesarplanner.studentplanner.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity(tableName = "courses")
public class Course {
    @PrimaryKey(autoGenerate = true)
    private int courseID;
    private String title;
    private String startDate;
    private String endDate;
    private String status;
    private String instructorsName;
    private String instructorsPhone;
    private String instructorsEmail;
    private String note;
    private int termID;

    public Course(int courseID, String title, String startDate, String endDate, String status, String instructorsName, String instructorsPhone, String instructorsEmail, String note, int termID) {
        this.courseID = courseID;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.instructorsName = instructorsName;
        this.instructorsPhone = instructorsPhone;
        this.instructorsEmail = instructorsEmail;
        this.note = note;
        this.termID = termID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    public String getInstructorsName() {
        return instructorsName;
    }

    public void setInstructorsName(String instructorsName) {
        this.instructorsName = instructorsName;
    }

    public String getInstructorsPhone() {
        return instructorsPhone;
    }

    public void setInstructorsPhone(String instructorsPhone) {
        this.instructorsPhone = instructorsPhone;
    }

    public String getInstructorsEmail() {
        return instructorsEmail;
    }

    public void setInstructorsEmail(String instructorsEmail) {
        this.instructorsEmail = instructorsEmail;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
