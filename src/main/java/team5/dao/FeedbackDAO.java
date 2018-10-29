package team5.dao;

import team5.dao.utils.DBConnector;
import team5.dao.interfaces.EntityDao;
import team5.models.Feedback;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAO implements EntityDao<Feedback> {

    @Override
    public Feedback getById(long id) {
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
    public List<Feedback> getAll() {
        String sql="SELECT * FROM interview";
        return createListEntitiesFromQueryResult(sql);
    }

    @Override
    public int count(){
        return getAll().size();
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
    public List<Feedback> getSortedEntitiesByPage(String sortBy, int pageid, int total) {
        return null;
    }

    @Override
    public List<Feedback> getEntitiesByPage(int offset, int total) {
        String sql="SELECT * FROM interviewfeedback LIMIT "+(offset -1)+","+total;
        return createListEntitiesFromQueryResult(sql);
    }


}
