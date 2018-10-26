package team5.dao;

import team5.dao.utils.DBConnector;
import team5.dao.interfaces.EntityDao;
import team5.dao.utils.DBUtils;
import team5.models.Vacancy;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VacancyDAO implements EntityDao<Vacancy> {

    @Override
    public void save(Vacancy vacancy) {
        String sql = "INSERT INTO vacancy (idDeveloper, position, salaryFrom, salaryTo, vacancyState) VALUES ('" +
                vacancy.getIdDeveloper() +
                "', '" + vacancy.getPosition() +
                "', '" + vacancy.getSalaryFrom() +
                "', '" + vacancy.getSalaryTo() +
                "', '" + vacancy.getVacancyState()+
                "')";
        DBUtils.updateByQuery(sql);
    }

    @Override
    public Vacancy getById(long id) {
        return null;
    }

    @Override
    public List<Vacancy> getAll() {
        String sql="SELECT * FROM vacancy";
        return createListEntitiesFromQueryResult(sql);

    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public void update(Vacancy model) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<Vacancy> getEntitiesByPage(int offset, int total) {
        String sql="SELECT * FROM vacancy LIMIT " + (offset -1) + "," + total;
        return createListEntitiesFromQueryResult(sql);
    }

    @Override
    public List<Vacancy> getSortedEntitiesByPage(String sortBy, int pageid, int total) {
        return null;
    }

    private List<Vacancy> createListEntitiesFromQueryResult(String sql){
        ResultSet resultSet;
        List<Vacancy> vacancies = new ArrayList<>();

        try {
            Connection connection = DBConnector.getConnection();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                vacancies.add(new Vacancy(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4),
                        resultSet.getDouble(5),
                        resultSet.getString(6),
                        resultSet.getDouble(7)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return vacancies;
    }

}
