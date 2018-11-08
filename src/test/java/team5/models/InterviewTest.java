package team5.models;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class InterviewTest {

    @Test
    public void getSetTest() {
        Interview interview = new Interview();
        Date date = new Date();
        interview.setId(1);
        interview.setIdCandidate(2);
        interview.setIdVacancy(3);
        interview.setPlanDate(date);
        interview.setFactDate(date);
        assertEquals(date, interview.getFactDate());
        assertEquals(date, interview.getPlanDate());
        assertEquals(1, interview.getId());
        assertEquals(2, interview.getIdCandidate());
        assertEquals(3, interview.getIdVacancy());

        Interview newInterview = new Interview(1, 3, 2, date, date);
        assertEquals(date, newInterview.getFactDate());
        assertEquals(date, newInterview.getPlanDate());
        assertEquals(1, newInterview.getId());
        assertEquals(2, newInterview.getIdCandidate());
        assertEquals(3, newInterview.getIdVacancy());
    }
}