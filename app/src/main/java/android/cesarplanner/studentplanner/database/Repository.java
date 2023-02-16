package android.cesarplanner.studentplanner.database;

import android.app.Application;
import android.cesarplanner.studentplanner.dao.AssessmentDAO;
import android.cesarplanner.studentplanner.dao.CourseDAO;
import android.cesarplanner.studentplanner.dao.TermDAO;
import android.cesarplanner.studentplanner.entities.Assessment;
import android.cesarplanner.studentplanner.entities.Course;
import android.cesarplanner.studentplanner.entities.Term;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private TermDAO rTermDao;
    private CourseDAO rCourseDao;
    private AssessmentDAO rAssessmentDao;
    private List<Term> rAllTerms;
    private List<Course> rAllCourses;
    private List<Assessment> rAllAssessments;

    private static int NUMBER_OF_THREADS=4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application) {
        StudentPlannerDbBuilder db = StudentPlannerDbBuilder.getDatabase(application);
        rTermDao = db.termDAO();
        rCourseDao = db.courseDAO();
        rAssessmentDao = db.assessmentDAO();
    }

    // TERMS
    public List<Term> getAllTerms() {
        databaseExecutor.execute(() -> {
            rAllTerms = rTermDao.getAllTerms();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return rAllTerms;
    }

    public void insert(Term term) {
        databaseExecutor.execute(() -> {
            rTermDao.insert(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(Term term) {
        databaseExecutor.execute(() -> {
            rTermDao.update(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Term term) {
        databaseExecutor.execute(() -> {
            rTermDao.delete(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // COURSE
    public List<Course> getAllCourses() {
        databaseExecutor.execute(() -> {
            rAllCourses = rCourseDao.getAllCourses();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return rAllCourses;
    }

    public void insert(Course course) {
        databaseExecutor.execute(() -> {
            rCourseDao.insert(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(Course course) {
        databaseExecutor.execute(() -> {
            rCourseDao.update(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Course course) {
        databaseExecutor.execute(() -> {
            rCourseDao.delete(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Assessments
    public List<Assessment> getAllAssessments() {
        databaseExecutor.execute(() -> {
            rAllAssessments = rAssessmentDao.getAllAssessments();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return rAllAssessments;
    }

    public void insert(Assessment assessment) {
        databaseExecutor.execute(() -> {
            rAssessmentDao.insert(assessment);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(Assessment assessment) {
        databaseExecutor.execute(() -> {
            rAssessmentDao.update(assessment);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Assessment assessment) {
        databaseExecutor.execute(() -> {
            rAssessmentDao.delete(assessment);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
