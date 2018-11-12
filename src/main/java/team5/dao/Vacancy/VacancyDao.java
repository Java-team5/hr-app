package team5.dao.Vacancy;

import team5.dao.exceptions.DeleteException;
import team5.dao.interfaces.SortFilterCrudDao;
import team5.dao.utils.DBConnector;
import team5.models.Vacancy;
import team5.utils.SqlFilter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VacancyDao implements SortFilterCrudDao<Vacancy, SqlFilter> {

    //language=SQL
    private final String SQL_SELECT_ALL = "SELECT * FROM vacancy";
    //language=SQL
    private final String SQL_SELECT_BY_ID =
            "SELECT * FROM vacancy WHERE id = ?";
    //language=SQL
    private final String SQL_INSERT_VACANCY =
            "INSERT INTO vacancy (idDeveloper, position,"
            + " salaryFrom, salaryTo, vacancyState, experienceYearsRequire)"
            + " VALUES (?,?,?,?,?,?)";
    //language=SQL
    private final String SQL_UPDATE_VACANCY_BY_ID = "UPDATE vacancy SET"
            + " idDeveloper = ?, position = ?, salaryFrom = ?,"
            + " salaryTo = ?, vacancyState = ?, experienceYearsRequire = ?"
            + " WHERE id = ?";
    //language=SQL
    private final String SQL_DELETE_VACANCY_BY_ID =
            "DELETE FROM vacancy WHERE id = ?";
    //language=SQL
    private final String SQL_SELECT_SORTED_FILTERED_ENTITIES_BY_PAGE =
            "SELECT * FROM vacancy WHERE ? LIKE ? ORDER BY ? LIMIT ?,?";

    private Connection connection;

    public VacancyDao() {
        try {
            this.connection = DBConnector.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(final Vacancy model) {
        try {
            querySave(model, this.SQL_INSERT_VACANCY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Vacancy getById(final long id) {
        try {
            PreparedStatement pStatement = this.connection.prepareStatement(
                    this.SQL_SELECT_BY_ID);
            pStatement.setLong(1, id);
            List<Vacancy> list = getListOfQueryResult(pStatement.executeQuery());
            return list.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  null;
    }

    @Override
    public List<Vacancy> getAll() {
        try {
            return query(this.SQL_SELECT_ALL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int count() {
        return getAll().size();
    }

    @Override
    public void update(final Vacancy model) {
        try {
            PreparedStatement pStatement = this.connection.prepareStatement(this.SQL_UPDATE_VACANCY_BY_ID);
            pStatement.setLong(1, model.getIdDeveloper());
            pStatement.setString(2, model.getPosition());
            pStatement.setDouble(3, model.getSalaryFrom());
            pStatement.setDouble(4, model.getSalaryTo());
            pStatement.setString(5, model.getVacancyState());
            pStatement.setDouble(6, model.getExperienceYearsRequire());
            pStatement.setLong(7, model.getId());
            pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(final long id) throws DeleteException {
        try {
            queryDelete(id, this.SQL_DELETE_VACANCY_BY_ID);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DeleteException();
        }
    }

    @Override
    public List<Vacancy> getFilteredSortedEntitiesByPage(
            final SqlFilter filter,
            final int pageId,
            final int total) {
        String sql = "SELECT * FROM vacancy " + filter.getEmbeddedLine()
                + " LIMIT " + (pageId - 1) + "," + total;
        return createListEntitiesFromQueryResult(sql);
    }

    private List<Vacancy> createListEntitiesFromQueryResult(final String sql) {
        List<Vacancy> vacancyList = new ArrayList<>();
        try {
            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                vacancyList.add(new Vacancy(
                        resultSet.getLong("id"),
                        resultSet.getLong("idDeveloper"),
                        resultSet.getString("position"),
                        resultSet.getDouble("salaryFrom"),
                        resultSet.getDouble("salaryTo"),
                        resultSet.getString("vacancyState"),
                        resultSet.getDouble("experienceYearsRequire")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vacancyList;
    }

    private void querySave(final Vacancy model, final String sqlQuery)throws SQLException {
        try {
            PreparedStatement pStatement = this.connection.
                    prepareStatement(sqlQuery);
            pStatement.setLong(1, model.getIdDeveloper());
            pStatement.setString(2, model.getPosition());
            pStatement.setDouble(3, model.getSalaryFrom());
            pStatement.setDouble(4, model.getSalaryTo());
            pStatement.setString(5, model.getVacancyState());
            pStatement.setDouble(6, model.getExperienceYearsRequire());
            pStatement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    private void queryDelete(final long id, final String sqlQuery) throws SQLException {
        try {
            PreparedStatement pStatement = this.connection.
                    prepareStatement(sqlQuery);
            pStatement.setLong(1, id);
            pStatement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    private List<Vacancy> query(final String sqlQuery) throws SQLException{
        try {
            PreparedStatement pStatement = this.connection.
                    prepareStatement(sqlQuery);
            return getListOfQueryResult(pStatement.executeQuery());
        } catch (SQLException e) {
            throw e;
        }
    }

    private List<Vacancy> getListOfQueryResult(final ResultSet resultSet) throws SQLException {
        try {
            List<Vacancy> vacancyList = new ArrayList<>();
            if (resultSet.next()) {
                vacancyList.add(new Vacancy(
                        resultSet.getLong("id"),
                        resultSet.getLong("idDeveloper"),
                        resultSet.getString("position"),
                        resultSet.getDouble("salaryFrom"),
                        resultSet.getDouble("salaryTo"),
                        resultSet.getString("vacancyState"),
                        resultSet.getDouble("experienceYearsRequire")
                ));
            }
            return vacancyList;
        } catch (SQLException e) {
            throw e;
        }
    }
}
