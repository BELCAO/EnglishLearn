package com.example.myapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.myapplication.domain.model.Course;
import com.example.myapplication.domain.service.dao.impl.CourseDAO;
import com.example.myapplication.domain.service.database.DatabaseHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class CourseDAOTest {

    private CourseDAO DAO;
    private DatabaseHelper db;
    private static boolean isCreatedData;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db = DatabaseHelper.getInstance(context);
        if (!isCreatedData) {
            db.createData();
            isCreatedData = true;
        }
        DAO = CourseDAO.getInstance(db);
    }



    @Test
    public void testInsertCourse() {
        Course course = new Course(1, "TOEIC (cơ bản)", 0);
        DAO.insert(course);

        List<Course> courses = DAO.findAll();

        assertNotNull(courses);
        assertEquals(5, courses.size());

        Course insertedCourse = courses.get(0);
        assertEquals(course.getId(), insertedCourse.getId());
        assertEquals(course.getName(), insertedCourse.getName());
        assertEquals(course.getPrice(), insertedCourse.getPrice());
    }

    @After
    public void closeDb() {
        db.close();
    }

}
