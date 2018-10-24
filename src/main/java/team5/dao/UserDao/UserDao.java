package team5.dao.UserDao;

import org.springframework.jdbc.core.JdbcTemplate;
import team5.dao.EntityDao;
import team5.dao.DBConnector;
import team5.models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements EntityDao<User>{
    private JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public User find(long id) {
        return null;
    }

    @Override
    public void save(User model) {
        String sql = "INSERT INTO users (email, password, name, surname, userState, isAdmin) VALUES ('"+model.getEmail()+"', '"+model.getPassword()+"', '"+model.getName()+"', '"+
                model.getSurname()+"', 'Active', 'User')";
        insertUserByQuery(sql);
    }

    @Override
    public void update(User model) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<User> findAll() {
        String sql="SELECT * FROM users";
        return createListEntitiesFromQueryResult(sql);
    }

    @Override
    public List<User> getEntitiesByPage(int pageid, int total) {
        String sql="SELECT * FROM users LIMIT "+(pageid-1)+","+total;
        return createListEntitiesFromQueryResult(sql);
    }

    @Override
    public List<User> getSortedEntitiesByPage(String sortBy, int pageid, int total){
        String sql = "SELECT * FROM users ORDER BY "+sortBy+" LIMIT "+(pageid-1)+","+total;
        return createListEntitiesFromQueryResult(sql);
    }

    private void insertUserByQuery(String sql){
        try {
            Connection connection = DBConnector.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
    private List<User> createListEntitiesFromQueryResult(String sql){
        ResultSet resultSet;
        List<User> users = new ArrayList<>();

        try {
            Connection connection = DBConnector.getConnection();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                users.add(new User(resultSet.getLong(1),resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getString(6), resultSet.getString(7)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public long getCount(){
        return findAll().size();
    }

}
