package team5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team5.dao.Feedback.FeedbackDAO;
import team5.models.Feedback;
import team5.models.FeedbackFilter;
import team5.utils.Utils;

import javax.validation.Valid;
import java.util.List;

import static java.lang.Math.ceil;

@RestController
@RequestMapping(value = "/json/feedback/")
public class FeedbackRESTController {
    @Autowired
    private FeedbackDAO feedbackDAO;

    private FeedbackFilter filter = new FeedbackFilter();

    @GetMapping(value = "/view/{page}/**")
    public List<Feedback> setFeedbackView(@PathVariable int page, String sort) {
        int total = 5;

        int offset = Utils.getPageOffset(page, total);

        List<Feedback> feedbackList;
        if (sort != null && !sort.equals("none")) {
            feedbackList = feedbackDAO.getSortedEntitiesByPage(filter, sort, offset, total);
        } else {
            feedbackList = feedbackDAO.getEntitiesByPage(filter, offset, total);
        }

        float pagesCount = (float) feedbackDAO.count() / total;
        int[] pages = new int[(int) ceil(pagesCount)];
        for(int i=0; i<pages.length; i++){
            pages[i] = i + 1;
        }


        return feedbackList;
    }

    @GetMapping(value = "/view/{id1}/{id2}/**")
    public Feedback viewFeedback(@PathVariable long id1, @PathVariable long id2) {
        Feedback feedback = feedbackDAO.getByIds(id1, id2);
        return feedback;
    }

    @PostMapping(value = "/add")
    public Feedback addNewFeedback(
            @RequestBody @Valid final Feedback feedback) {
        feedbackDAO.save(feedback);
        return feedback;
    }

    @PutMapping(value = "/update")
    public Feedback updateFeedback(
            @RequestBody @Valid final Feedback feedback
    ) {
        feedbackDAO.update(feedback);
        return feedback;
    }

    @DeleteMapping(value = "/view/{id1}/{id2}")
    public ResponseEntity deleteFeedback(@PathVariable final long id1, @PathVariable final long id2) {
        feedbackDAO.delete(id1,id2);
        return new ResponseEntity(HttpStatus.OK);
    }



    @PostMapping(value = "/filter")
    public FeedbackFilter skillSetFilter(@RequestBody final FeedbackFilter filterInput) {
        filter.setReason(filterInput.getReason());
        filter.setState(filterInput.getState());
        return filter;
    }

}
