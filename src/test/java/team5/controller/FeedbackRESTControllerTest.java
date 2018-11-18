package team5.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import team5.dao.Feedback.FeedbackDAO;
import team5.dao.Skill.SkillDao;
import team5.models.Feedback;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FeedbackRESTControllerTest {

    @Mock
    private FeedbackDAO feedbackDAO;
    @InjectMocks
    FeedbackRESTController controller;

    @Test
    public void setFeedbackView() {
//        List<Feedback> feedbackList = new ArrayList<>();
//        feedbackList.add(new Feedback(4,5,"JobOffer","Pepe"));
//        feedbackList.add(new Feedback(1,2,"NonJobOffer","Pepe"));
//        feedbackList.add(new Feedback(5,2,"JobOffer","Yes"));
//        feedbackList.add(new Feedback(11,7,"JobOffer","Yes"));
//
//        when(feedbackDAO.getSortedEntitiesByPage())
    }

    @Test
    public void viewFeedback() {
        Feedback feedback = new Feedback(4,5,"JobOffer","Yes");
        when(feedbackDAO.getByIds(4,5))
                .thenReturn(feedback);
        Feedback returnFeedback = controller.viewFeedback(4,5);
        assertEquals(feedback,returnFeedback);
    }

    @Test
    public void addNewFeedback() {

    }

    @Test
    public void updateFeedback() {
    }

    @Test
    public void deleteFeedback() {
    }

    @Test
    public void skillSetFilter() {
    }
}