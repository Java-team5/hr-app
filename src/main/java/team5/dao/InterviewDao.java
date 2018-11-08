package team5.dao;

import team5.dao.interfaces.FilteredEntityDao;
import team5.dao.utils.DBConnector;
import team5.models.Interview;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class InterviewDao implements FilteredEntityDao<Interview> {

    private Statement statement;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public InterviewDao() {
        try {
            Connection connection = DBConnector.getConnection();
            statement = connection.createStatement();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Interview> getFilteredEntitiesByPage(String field, String filter, int offset, int total) {
        String sql = "SELECT * FROM interview WHERE ("
                + field + " LIKE '%" + filter
                + "%') LIMIT " + (offset - 1) + "," + total;
        return createListEntitiesFromQueryResult(sql);
    }

    @Override
    public List<Interview> getFilteredSortedEntitiesByPage(String field, String filter, String sortBy, int pageId, int total) {
        String sql = "SELECT * FROM interview WHERE ("
                + field + " LIKE '%" + filter
                + "%') ORDER BY " + sortBy + " LIMIT " + (pageId - 1) + "," + total;
        return createListEntitiesFromQueryResult(sql);
    }

    @Override
    public void save(Interview model) {
        String sql = "INSERT INTO interview(idVacancy, idCandidate, planDate, factDate) VALUES ('"
                + model.getIdVacancy()
                + "', '" + model.getIdCandidate()
                + "', '" + format.format(model.getPlanDate())
                + "', '" + format.format(model.getFactDate()) + "')";
        executDatabaseQuery(sql);
    }

    @Override
    public Interview getById(long id) {
        String sql = String.format("SELECT * FROM interview WHERE id=%d", id);
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            return new Interview(
                    resultSet.getLong(1),
                    resultSet.getLong(2),
                    resultSet.getLong(3),
                    format.parse(resultSet.getString(4)),
                    format.parse(resultSet.getString(5))
            );
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Interview> getAll() {
        String sql = "SELECT * FROM interview";
        return createListEntitiesFromQueryResult(sql);
    }

    @Override
    public int count() {
        return getAll().size();
    }

    @Override
    public void update(Interview model) {
        String sql = "UPDATE interview SET " +
                "idVacancy='" + model.getIdVacancy()
                + "', idCandidate='" + model.getIdCandidate()
                + "', planDate='" + format.format(model.getPlanDate())
                + "', factDate='" + format.format(model.getFactDate())
                + "' WHERE id=" + model.getId();
        executDatabaseQuery(sql);
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM interview WHERE id=" + id + "";
        executDatabaseQuery(sql);
    }

    /**
     * Performs sql query.
     * @param sql query.
     */
    private void executDatabaseQuery(final String sql) {
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Interview> createListEntitiesFromQueryResult(String sql) {
        ResultSet resultSet;
        List<Interview> interviews = new ArrayList<>();
        try {
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                interviews.add(new Interview(
                        resultSet.getLong(1),
                        resultSet.getLong(2),
                        resultSet.getLong(3),
                        format.parse(resultSet.getString(4)),
                        format.parse(resultSet.getString(5))
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return interviews;
    }
}
