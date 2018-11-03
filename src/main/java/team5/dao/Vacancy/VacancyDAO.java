package team5.dao.Vacancy;

import team5.dao.interfaces.SortFilterCrudDao;
import team5.dao.utils.DBConnector;
import team5.models.Vacancy;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VacancyDAO implements SortFilterCrudDao<Vacancy> {

    //language=SQL
    private final String SQL_SELECT_ALL = "SELECT * FROM vacancy";
    //language=SQL
    private final String SQL_SELECT_BY_ID = "SELECT * FROM vacancy WHERE id = ?";
    //language=SQL
    private final String SQL_INSERT_VACANCY = "INSERT INTO vacancy (idDeveloper, position, salaryFrom, salaryTo, vacancyState, experienceYearsRequire)" +
            " VALUES (?,?,?,?,?,?)";
    //language=SQL
    private final String SQL_UPDATE_VACANCY_BY_ID = "UPDATE vacancy SET" +
            " idDeveloper = ?, position = ?, salaryFrom = ?, salaryTo = ?, vacancyState = ?, experienceYearsRequire = ?" +
            " WHERE id = ?";
    //language=SQL
    private final String SQL_DELETE_VACANCY_BY_ID = "DELETE FROM vacancy WHERE id = ?";
    //language=SQL
    private final String SQL_SELECT_FILTERED_ENTITIES_BY_PAGE = "SELECT * FROM vacancy LIKE '%'?'%' LIMIT ?,?";
    //language=SQL
    private final String SQL_SELECT_SORTED_FILTERED_ENTITIES_BY_PAGE = "SELECT * FROM vacancy LIKE '%'?'%' ORDER BY ? LIMIT ?,?";

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
        try{
            queryUpdate(model,this.SQL_INSERT_VACANCY);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Vacancy getById(long id)
    {
        try{
            return query(this.SQL_SELECT_BY_ID).get(0);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Vacancy> getAll() {
        try{
            return query(this.SQL_SELECT_ALL);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int count() {
        return getAll().size();
    }

    @Override
    public void update(Vacancy model) {
        try{
            queryUpdate(model,this.SQL_UPDATE_VACANCY_BY_ID);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        try{
            queryDelete(id,this.SQL_DELETE_VACANCY_BY_ID);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Vacancy> getEntitiesByPage(String filter,int offset, int total) {
        try{
            PreparedStatement pStatement = this.connection.prepareStatement(this.SQL_SELECT_FILTERED_ENTITIES_BY_PAGE);
            pStatement.setInt(1,offset - 1);
            pStatement.setInt(2,total);
            return getListOfQueryResult(pStatement.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Vacancy> getSortedEntitiesByPage(String filter, String sortBy, int pageid, int total) {
        try{
            PreparedStatement pStatement = this.connection.prepareStatement(this.SQL_SELECT_SORTED_FILTERED_ENTITIES_BY_PAGE);
            pStatement.setString(1,filter);
            pStatement.setString(2,sortBy);
            pStatement.setInt(3,pageid - 1);
            pStatement.setInt(4,total);
            return getListOfQueryResult(pStatement.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void queryUpdate(Vacancy model, String sqlQuery)throws SQLException
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
            throw e;
        }
    }

    private void queryDelete(long id, String sqlQuery) throws SQLException
    {
        try
        {
            PreparedStatement pStatement = this.connection.prepareStatement(sqlQuery);
            pStatement.setLong(1,id);
            pStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            throw e;
        }
    }

    private List<Vacancy> query(String sqlQuery) throws SQLException
    {
        List<Vacancy> vacancyList = new ArrayList<>();
        try{
            PreparedStatement pStatement = this.connection.prepareStatement(sqlQuery);
            return getListOfQueryResult(pStatement.executeQuery());
        } catch (SQLException e) {
            throw e;
        }
    }

    private List<Vacancy> getListOfQueryResult(ResultSet resultSet) throws SQLException
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
