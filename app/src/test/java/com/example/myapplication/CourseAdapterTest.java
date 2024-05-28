package com.example.myapplication;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;
import com.example.myapplication.domain.model.Course;
import com.example.myapplication.view.adapter.CourseAdapter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28)
public class CourseAdapterTest {

    private CourseAdapter adapter;
    private List<Course> courses;

    @Mock
    private Map<Integer, Boolean> enrolledCoursesMap;

    @Before
    public void setUp() {
        courses = new ArrayList<>();
        courses.add(new Course(1, "TOEIC (cơ bản)", 0));
        courses.add(new Course(2, "IELTS (nâng cao)", 1000000));
        enrolledCoursesMap = new HashMap<>();
        adapter = new CourseAdapter(courses);
    }

    /**
     * test số khóa trong list courses
     * -> 2 như set up
     */
    @Test
    public void testGetItemCount() {
        assertEquals(2, adapter.getItemCount());
    }

    /**
     * Dự kiến
     * test Map không Null
     * Kích thước của Map -> 2
     * Key 1 -> true, 2 -> false
     */
    @Test
    public void testSetEnrolled(){
        enrolledCoursesMap.put(1, true);
        enrolledCoursesMap.put(2, false);
        adapter.setEnrolledCoursesMap(enrolledCoursesMap);
        assertNotNull(enrolledCoursesMap); //
        assertEquals(2, enrolledCoursesMap.size()); //
        assertTrue(enrolledCoursesMap.get(1)); //
        assertFalse(enrolledCoursesMap.get(2)); //
    }
}
