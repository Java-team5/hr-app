package com.team5.dao.Candidate;

import com.team5.dao.utils.DBConnector;
import com.team5.dao.utils.DBUtils;
import com.team5.models.Candidate;
import com.team5.models.CandidateFilter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CandidateDAO implements CandidateCrudDAO {

    private Connection connection;
    private Statement statement;
    public CandidateDAO() {
        try {
            connection = DBConnector.getConnection();
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Candidate candidate) {
        String sql = "INSERT INTO candidate (name, surname, birthday, salary) VALUES ('" +
                candidate.getName() +
                "', '" + candidate.getSurname() +
                "', '" + candidate.getBirthday() +
                "', '" + candidate.getSalary();
        DBUtils.updateByQuery(sql);
    }

    @Override
    public void update(Candidate model) {
        String sql = "UPDATE candidate SET name='" + model.getName() + "', surname='"+model.getSurname()
                +"', birthday='"+model.getBirthday()+"', salary='"+model.getSalary()+"' WHERE id=" + model.getId();
        System.out.println("sql = " + sql);
        DBUtils.updateByQuery(sql);
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Candidate getById(long id) {
        String sql = "SELECT * FROM candidate WHERE id="+id+"";
        try {
            Connection connection = DBConnector.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            return new Candidate(resultSet.getLong(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDate(4),
                    resultSet.getDouble(5));
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public int count(CandidateFilter filter) {
        String sql = "SELECT * FROM candidate WHERE (name LIKE '%" + filter.getName() +
                "%' AND surname LIKE '%" + filter.getSurname()+"%')";
        return createListEntitiesFromQueryResult(sql).size();
    }

    @Override
    public List<Candidate> getEntitiesByPage(CandidateFilter filter,int offset, int total) {
        String sql="SELECT * FROM candidate WHERE (name LIKE '%" + filter.getName() +
                "%' AND surname LIKE '%"+filter.getSurname() +
                "%') LIMIT " + (offset -1) + "," + total;
        return createListEntitiesFromQueryResult(sql);
    }

    @Override
    public List<Candidate> getSortedEntitiesByPage(CandidateFilter filter, String sortBy, int pageid, int total) {
        String sql = "SELECT * FROM users WHERE (name LIKE '%" + filter.getName() +
                "%' AND surname LIKE '%" + filter.getSurname() +
                "%') ORDER BY " + sortBy + " LIMIT " + (pageid - 1) + "," + total;
        return createListEntitiesFromQueryResult(sql);
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
                        resultSet.getLong(1),
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
