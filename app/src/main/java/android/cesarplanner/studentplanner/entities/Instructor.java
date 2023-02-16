package android.cesarplanner.studentplanner.entities;

public class Instructor {
    private int instructorID;
    private String name;
    private String phone;
    private String email;

    public Instructor(int instructorID, String name, String phone, String email) {
        this.instructorID = instructorID;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public int getInstructorID() {
        return instructorID;
    }

    public void setInstructorID(int instructorID) {
        this.instructorID = instructorID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
