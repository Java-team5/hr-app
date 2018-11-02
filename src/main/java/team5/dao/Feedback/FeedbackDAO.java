package team5.dao.Feedback;

import team5.dao.utils.DBConnector;
import team5.dao.utils.DBUtils;
import team5.models.Feedback;
import team5.models.FeedbackFilter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAO implements FeedbackCRUDDAO {

    private Connection connection;
    private Statement statement;

    public FeedbackDAO() {
        try {
            connection = DBConnector.getConnection();
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Feedback getById(long id) {
        return null;
    }

    public Feedback getByIds(long id1, long id2) {
        String sql = "SELECT * FROM interviewfeedback WHERE (idInterview=" + id1 + " AND  idInterviewer=" + id2 + ")";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();

            return new Feedback(
                    resultSet.getInt("idInterview"),
                    resultSet.getInt("idInterviewer"),
                    resultSet.getString("feedbackState"),
                    resultSet.getString("reason")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Feedback model) {
        String sql = "INSERT INTO interviewfeedback (idInterview, idInterviewer, feedbackState, reason) VALUES ('" +
                model.getIdInterview() +
                "', '" + model.getIdInterviewer() +
                "', '" + model.getFeedbackState() +
                "', '" + model.getReason() + "');";
        DBUtils.updateByQuery(sql);
    }

    @Override
    public void update(Feedback model) {
        String sql = "UPDATE interviewfeedback SET idInterview='" + model.getIdInterview()
                + "', idInterviewer='" + model.getIdInterviewer()
                + "', feedbackState='" + model.getFeedbackState()
                + "', reason='" + model.getReason()
                + "' WHERE (idInterview=" + model.getIdInterview() + " AND " + "idInterviewer=" + model.getIdInterviewer() + ")";
        DBUtils.updateByQuery(sql);
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
        String sql = "SELECT * FROM candidate ORDER BY " + sortBy + " LIMIT " + (pageid - 1) + "," + total;
        return createListEntitiesFromQueryResult(sql);
    }


    @Override
    public List<Feedback> getEntitiesByPage(int offset, int total) {
        String sql="SELECT * FROM interviewfeedback LIMIT "+(offset -1)+","+total;
        return createListEntitiesFromQueryResult(sql);
    }


    @Override
    public List<Feedback> getEntitiesByPage(FeedbackFilter filter, int offset, int total) {
        String sql = "SELECT * FROM interviewfeedback WHERE (feedbackState LIKE '%" + filter.getState() + "%'" +
                " AND reason LIKE '%" + filter.getReason() + "%')  LIMIT " + (offset - 1) + "," + total + ";";

        return createListEntitiesFromQueryResult(sql);
    }

    @Override
    public List<Feedback> getSortedEntitiesByPage(FeedbackFilter filter, String sortBy, int offset, int total) {
        String sql = "SELECT * FROM interviewfeedback WHERE (feedbackState LIKE '%" + filter.getState() + "%'" +
                " AND reason LIKE '%" + filter.getReason() + "%')" +
                " ORDER BY " + sortBy +
                " LIMIT " + (offset - 1) + "," + total + ";";

        return createListEntitiesFromQueryResult(sql);
    }

}
