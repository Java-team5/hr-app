package team5.dao.Feedback;

import team5.dao.interfaces.EntityDao;
import team5.models.Feedback;

public interface FeedbackCRUDDAO extends EntityDao<Feedback> {
    public Feedback getByIds(long id1, long id2);
}
