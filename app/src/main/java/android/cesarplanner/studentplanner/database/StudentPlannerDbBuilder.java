package android.cesarplanner.studentplanner.database;

import android.cesarplanner.studentplanner.dao.AssessmentDAO;
import android.cesarplanner.studentplanner.dao.CourseDAO;
import android.cesarplanner.studentplanner.dao.TermDAO;
import android.cesarplanner.studentplanner.entities.Assessment;
import android.cesarplanner.studentplanner.entities.Course;
import android.cesarplanner.studentplanner.entities.Term;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Assessment.class, Course.class, Term.class}, version=1, exportSchema = false)
public abstract class StudentPlannerDbBuilder extends RoomDatabase {
    public abstract AssessmentDAO assessmentDAO();
    public abstract CourseDAO courseDAO();
    public abstract TermDAO termDAO();

    private static volatile StudentPlannerDbBuilder INSTANCE;

    static StudentPlannerDbBuilder getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (StudentPlannerDbBuilder.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), StudentPlannerDbBuilder.class, "SPDatabase.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
