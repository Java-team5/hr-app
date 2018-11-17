package com.team5.dao.User;

import com.team5.dao.utils.DBConnector;
import com.team5.dao.utils.DBUtils;
import com.team5.models.User;
import com.team5.models.UserFilter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements UserCrudDao {
    private Connection connection;
    private Statement statement;
    public UserDao() {
        try {
            connection = DBConnector.getConnection();
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getById(long id) {
        String sql = "SELECT * FROM users WHERE id=" + id + "";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            return new User(resultSet.getLong(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(User model) {
        String sql = "INSERT INTO users (email, password, name, surname, userState, isAdmin) VALUES ('" +
                model.getEmail() +
                "', '" + model.getPassword() +
                "', '" + model.getName() +
                "', '" + model.getSurname() +
                "', 'Active', 'User')";
        DBUtils.updateByQuery(sql);
    }

    @Override
    public void update(User model) {
        String sql = "UPDATE users SET email='" + model.getEmail() + "', password='"+model.getPassword()
                +"', name='"+model.getName()+"', surname='"+model.getSurname()+"' WHERE id=" + model.getId();
        DBUtils.updateByQuery(sql);
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<User> getAll() {
        String sql="SELECT * FROM users";
        return createListEntitiesFromQueryResult(sql);
    }

    @Override
    public List<User> getEntitiesByPage(UserFilter filter, int offset, int total) {
        String sql="SELECT * FROM users WHERE (email LIKE '%" + filter.getEmail() +
                "%' AND name LIKE '%"+filter.getName() +
                "%' AND surname LIKE '%"+filter.getSurname() +
                "%') LIMIT " + (offset -1) + "," + total;
        return createListEntitiesFromQueryResult(sql);
    }

    @Override
    public List<User> getSortedEntitiesByPage(UserFilter filter, String sortBy, int pageid, int total){
        String sql = "SELECT * FROM users WHERE (email LIKE '%" + filter.getEmail() +
                "%' AND name LIKE '%"+filter.getName() +
                "%' AND surname LIKE '%"+filter.getSurname() +
                "%') ORDER BY " + sortBy + " LIMIT " + (pageid - 1) + "," +total;
        return createListEntitiesFromQueryResult(sql);
    }

    private List<User> createListEntitiesFromQueryResult(String sql){
        ResultSet resultSet;
        List<User> users = new ArrayList<>();

        try {
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                users.add(new User(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
    @Override
    public int count(){
        return getAll().size();
    }
    public int count(UserFilter filter) {
        String sql = "SELECT * FROM users WHERE (email LIKE '%" + filter.getEmail() +
                "%' AND name LIKE '%"+filter.getName() +
                "%' AND surname LIKE '%"+filter.getSurname()+"%')";
        return createListEntitiesFromQueryResult(sql).size();
    }

}
