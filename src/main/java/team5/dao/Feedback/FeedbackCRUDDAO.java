package team5.dao.Feedback;

import team5.dao.interfaces.EntityDao;
import team5.models.Feedback;
import team5.models.FeedbackFilter;

import java.util.List;

public interface FeedbackCRUDDAO extends EntityDao<Feedback> {
    public Feedback getByIds(long id1, long id2);

    List<Feedback> getEntitiesByPage(FeedbackFilter filter, int offset, int total);

    List<Feedback> getSortedEntitiesByPage(FeedbackFilter filter, String sortBy, int offset, int total);
}
