package team5.dao;

import team5.dao.interfaces.EntityDao;
import team5.dao.utils.DBConnector;
import team5.models.Vacancy;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VacancyDAO implements EntityDao<Vacancy> {

    //language=SQL
    private final String SQL_SELECT_ALL = "SELECT * FROM vacancy";
    //language=SQL
    private final String SQL_SELECT_BY_ID = "SELECT * FROM vacancy WHERE id = ?";
    //language=SQL
    private final String SQL_INSERT_VACANCY = "INSERT INTO vacancy (idDeveloper, position, salaryFrom, salaryTo, vacancyState, experienceYearsRequire)" +
            " VALUES (?,?,?,?,?,?)";
    //language=SQL
    private final String SQL_UPDATE_VACANCY_BY_ID = "UPDATE vacancy SET" +
            " idDeveloper = ?, position = ?, salaryFrom = ?, salaryTo = ?, vacancyState = ?, experienceYearsRequire = ?)" +
            " WHERE id = ?";
    //language=SQL
    private final String SQL_DELETE_VACANCY_BY_ID = "DELETE FROM vacancy WHERE id = ?";
    //language=SQL
    private final String SQL_SELECT_BY_PAGE = "SELECT * FROM vacancy LIMIT ?,?";
    //language=SQL
    private final String SQL_SELECT_SORTED_ENTITIES_BY_PAGE = "SELECT * FROM candidate ORDER BY ? LIMIT ?,?";

    private Connection connection;

    public VacancyDAO() {
        try
        {
            this.connection = DBConnector.getConnection();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void save(Vacancy model) {
        queryUpdate(model,this.SQL_INSERT_VACANCY);
    }

    @Override
    public Vacancy getById(long id)
    {
        return query(this.SQL_SELECT_BY_ID).get(0);
    }

    @Override
    public List<Vacancy> getAll() {
        return query(this.SQL_SELECT_ALL);
    }

    @Override
    public int count() {
        return getAll().size();
    }

    @Override
    public void update(Vacancy model) {
        queryUpdate(model,this.SQL_UPDATE_VACANCY_BY_ID);
    }

    @Override
    public void delete(long id) {
        queryDelete(id,this.SQL_DELETE_VACANCY_BY_ID);
    }

    @Override
    public List<Vacancy> getEntitiesByPage(int offset, int total) {
        try{
            PreparedStatement pStatement = this.connection.prepareStatement(this.SQL_SELECT_BY_PAGE);
            pStatement.setInt(1,offset - 1);
            pStatement.setInt(2,total);
            return getQueryResult(pStatement.executeQuery());
        } catch (SQLException e) {
            throw new IllegalStateException();
        }
    }

    @Override
    public List<Vacancy> getSortedEntitiesByPage(String sortBy, int pageid, int total) {
        try{
            PreparedStatement pStatement = this.connection.prepareStatement(this.SQL_SELECT_SORTED_ENTITIES_BY_PAGE);
            pStatement.setString(1,sortBy);
            pStatement.setInt(2,pageid - 1);
            pStatement.setInt(3,total);
            return getQueryResult(pStatement.executeQuery());
        } catch (SQLException e) {
            throw new IllegalStateException();
        }
    }

    private void queryUpdate(Vacancy model, String sqlQuery)
    {
        try
        {
            PreparedStatement pStatement = this.connection.prepareStatement(sqlQuery);
            pStatement.setInt(1,model.getIdDeveloper());
            pStatement.setString(2,model.getPosition());
            pStatement.setDouble(3,model.getSalaryFrom());
            pStatement.setDouble(4,model.getSalaryTo());
            pStatement.setString(5,model.getVacancyState());
            pStatement.setDouble(6,model.getExperienceYearsRequire());
            pStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private void queryDelete(long id, String sqlQuery)
    {
        try
        {
            PreparedStatement pStatement = this.connection.prepareStatement(sqlQuery);
            pStatement.setLong(1,id);
            pStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private List<Vacancy> query(String sqlQuery)
    {
        List<Vacancy> vacancyList = new ArrayList<>();
        try{
            PreparedStatement pStatement = this.connection.prepareStatement(sqlQuery);
            return getQueryResult(pStatement.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Vacancy> getQueryResult(ResultSet resultSet) throws SQLException
    {
        try {
            List<Vacancy> vacancyList = new ArrayList<>();
            if (resultSet.next()) {
                vacancyList.add(new Vacancy(
                        resultSet.getInt("id"),
                        resultSet.getInt("idDeveloper"),
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
