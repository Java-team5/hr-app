package team5.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team5.dao.Feedback.FeedbackCRUDDAO;
import team5.models.Feedback;
import team5.models.FeedbackFilter;
import team5.utils.Utils;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/json/feedback/")
public class FeedbackRESTController {
    @Autowired
    FeedbackCRUDDAO feedbackDAO;

    FeedbackFilter filter = new FeedbackFilter();
    ObjectMapper mapper = new ObjectMapper();

    @GetMapping(value = "/view/{page}/**")
    public List<Feedback> setFeedbackView(@PathVariable int page, String sort) {
        int total = 5;

        int offset = Utils.getPageOffset(page, total);

        List<Feedback> feedbacks;
        if (sort != null && !sort.equals("none")) {
            feedbacks = feedbackDAO.getSortedEntitiesByPage(filter, sort, offset, total);
        } else {
            feedbacks = feedbackDAO.getEntitiesByPage(filter, offset, total);
        }


        String response;
        try {
            response = mapper.writeValueAsString(feedbacks);
        } catch (JsonProcessingException e) {
            return null;
        }

        int[] pages = Utils.getPagesIndexArray(feedbackDAO, total);

        return feedbacks;
    }


    @PostMapping(value = "/add")
    public ResponseEntity addNewFeedback(
            @RequestBody @Valid final Feedback feedback) {
        feedbackDAO.save(feedback);
        return new ResponseEntity(feedback, HttpStatus.CREATED);
    }

    @PostMapping(value = "/update")
    public ResponseEntity updateFeedback(
            @RequestBody @Valid final Feedback feedback
    ) {
        feedbackDAO.update(feedback);
        return new ResponseEntity(feedback, HttpStatus.OK);
    }

    @GetMapping(value = "/account/{id1}/{id2}")
    public ResponseEntity openUserAccount(@PathVariable long id1, @PathVariable long id2) {
        Object feedback = feedbackDAO.getByIds(id1, id2);
        return new ResponseEntity(feedback, HttpStatus.OK);
    }

}
