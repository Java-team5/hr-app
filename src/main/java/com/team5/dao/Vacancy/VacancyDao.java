package com.team5.dao.Vacancy;

import com.team5.dao.interfaces.SortFilterCrudDao;
import com.team5.dao.utils.DBConnector;
import com.team5.models.Vacancy;
import com.team5.utils.SqlFilter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Realizes all date access operations with Vacancy table from Database.
 */
public class VacancyDao implements SortFilterCrudDao<Vacancy, SqlFilter> {

    /**
     * Select operation for db.
     */
    //language=SQL
    private final String SQL_SELECT_ALL = "SELECT * FROM vacancy";

    /**
     * Select by id operation for db.
     */
    //language=SQL
    private final String SQL_SELECT_BY_ID =
            "SELECT * FROM vacancy WHERE id = ?";

    /**
     * Insert(add) operation for db.
     */
    //language=SQL
    private final String SQL_INSERT_VACANCY =
            "INSERT INTO vacancy (idDeveloper, position,"
            + " salaryFrom, salaryTo, vacancyState, experienceYearsRequire)"
            + " VALUES (?,?,?,?,?,?)";

    /**
     * Modifier operation for concrete vacancy in db.
     */
    //language=SQL
    private final String SQL_UPDATE_VACANCY_BY_ID = "UPDATE vacancy SET"
            + " idDeveloper = ?, position = ?, salaryFrom = ?,"
            + " salaryTo = ?, vacancyState = ?, experienceYearsRequire = ?"
            + " WHERE id = ?";

    /**
     * Delete operation for concrete vacancy in db.
     */
    //language=SQL
    private final String SQL_DELETE_VACANCY_BY_ID =
            "DELETE FROM vacancy WHERE id = ?";

    /**
     * Connection object for db.
     */
    private Connection connection;

    /**
     * Сonstructor, which is connected to the database.
     */
    public VacancyDao() {
        try {
            this.connection = DBConnector.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Realization of insert(ad or save) sql operation.
     * @param model vacancy that will add.
     */
    @Override
    public void save(final Vacancy model) {
        try {
            querySave(model, this.SQL_INSERT_VACANCY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Realization of get by id sql operation.
     * @param id unique record identifier.
     * @return Vacancy.
     */
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

    /**
     * Realization of select all sql operation.
     * @return All  vacancyes from database.
     */
    @Override
    public List<Vacancy> getAll() {
        try {
            return query(this.SQL_SELECT_ALL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Count of vacancyes in database.
     * @return
     */
    @Override
    public int count() {
        return getAll().size();
    }

    /**
     * Realization of modifier all sql operation.
     * @param model Modifier vacancy in database.
     */
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

    /**
     * Realization of delete sql operation.
     * @param id unique record identifier.
     */
    @Override
    public void delete(final long id) {
        try {
            queryDelete(id, this.SQL_DELETE_VACANCY_BY_ID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Composite query to database.
     * @param filter sql filter object that return sql query part.
     * @param pageId number of page.
     * @param total count of need records.
     * @return list of vacancies.
     */
    @Override
    public List<Vacancy> getFilteredSortedEntitiesByPage(
            final SqlFilter filter,
            final int pageId,
            final int total) {
        String sql = "SELECT * FROM vacancy " + filter.getEmbeddedLine()
                + " LIMIT " + (pageId - 1) + "," + total;
        return createListEntitiesFromQueryResult(sql);
    }

    /**
     * Create vacancies list.
     * @param sql Query.
     * @return vacancies list.
     */
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

    /**
     * Save vacancy in database.
     * @param model New vacancy.
     * @param sqlQuery No comment.
     * @throws SQLException No comment.
     */
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

    /**
     * Delete exist vacancy from database.
     * @param id id of vacancy that will be delete
     * @param sqlQuery No comment.
     * @throws SQLException No comment.
     */
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

    /**
     * Query.
     * @param sqlQuery No comment.
     * @return Vacancies list.
     * @throws SQLException No comment.
     */
    private List<Vacancy> query(final String sqlQuery) throws SQLException {
        try {
            PreparedStatement pStatement = this.connection.
                    prepareStatement(sqlQuery);
            return getListOfQueryResult(pStatement.executeQuery());
        } catch (SQLException e) {
            throw e;
        }
    }

    /**
     * getListOfQueryResult.
     * @param resultSet No comment.
     * @return vacancies list
     * @throws SQLException No comment.
     */
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
