package team5.dao.CandidateDAO;

import team5.models.Candidate;
import team5.dao.DBConnector;
import team5.dao.DBUtils;
import team5.dao.interfaces.EntityDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CandidateDAO implements EntityDao<Candidate> {

    @Override
    public void save(Candidate candidate) {


        String sql = "INSERT INTO candidate (name, surname, birthday, salary) VALUES ('" +
                candidate.getName() +
                "', '" + candidate.getSurname() +
                "', '" + candidate.getBirthday() +
                "', '" + candidate.getSalary();
        DBUtils.insertByQuery(sql);
    }

    @Override
    public Candidate getById(long id) {
        return null;
    }

    @Override
    public List<Candidate> getAll() {
        String sql="SELECT * FROM candidate";
        return createListEntitiesFromQueryResult(sql);
    }

    @Override
    public int count() {
        return getAll().size();
    }

    @Override
    public void update(Candidate model) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<Candidate> getEntitiesByPage(int pageid, int total) {
        String sql="SELECT * FROM candidate LIMIT "+(pageid-1)+","+total;
        return createListEntitiesFromQueryResult(sql);
    }

    @Override
    public List<Candidate> getSortedEntitiesByPage(String sortBy, int pageid, int total) {
        return null;
    }

    private List<Candidate> createListEntitiesFromQueryResult(String sql){
        ResultSet resultSet;
        List<Candidate> candidates = new ArrayList<>();

        try {
            Connection connection = DBConnector.getConnection();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                candidates.add(new Candidate(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDate(4),
                        resultSet.getDouble(5)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return candidates;
    }
}
