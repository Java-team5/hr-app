package team5.dao.FeedbackDAO;

import team5.dao.DBConnector;
import team5.dao.EntityDao;
import team5.models.Feedback;
import team5.models.Skill;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAO implements EntityDao<Feedback> {
    @Override
    public List<Feedback> getEntitiesByPage(int pageid, int total) {
        String sql="SELECT * FROM interviewfeedback LIMIT "+(pageid-1)+","+total;
        return createListEntitiesFromQueryResult(sql);
    }

    private List<Feedback> createListEntitiesFromQueryResult(String sql){
        ResultSet resultSet;
        List<Feedback> feedbacks = new ArrayList<>();

        try {
            Connection connection = DBConnector.getConnection();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                feedbacks.add(new Feedback(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return feedbacks;
    }

    @Override
    public Feedback find(long id) {
        return null;
    }

    @Override
    public void save(Feedback model) {

    }

    @Override
    public void update(Feedback model) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<Feedback> findAll() {
        String sql="SELECT * FROM interview";
        return createListEntitiesFromQueryResult(sql);
    }

    public int getCount(){
        return findAll().size();
    }
}
