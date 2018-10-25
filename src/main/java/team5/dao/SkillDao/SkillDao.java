package team5.dao.SkillDao;

import org.springframework.jdbc.core.JdbcTemplate;
import team5.dao.EntityDao;
import team5.dao.DBConnector;
import team5.models.Skill;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class SkillDao implements EntityDao<Skill> {

    private JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Skill findByID(long id) {
        String sql = "SELECT * FROM skill WHERE id="+id+"";
        try {
            Connection connection = DBConnector.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            return new Skill(id, resultSet.getString(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Skill model) {
        String sql = "INSERT INTO skill(skill) VALUES ('" + model.getSkill() + "')";
        executуDatabaseQuery(sql);

    }

    @Override
    public void update(Skill model) {
        String sql = "UPDATE skill SET skill='" + model.getSkill() + "' WHERE id=" + model.getId();
        executуDatabaseQuery(sql);
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM skill WHERE id=" + id + "";
        executуDatabaseQuery(sql);
    }

    @Override
    public List<Skill> findAll() {
        String sql = "SELECT * FROM skill";
        return createListEntitiesFromQueryResult(sql);
    }

    @Override
    public List<Skill> getEntitiesByPage(int page, int total, String sortField) {
        String sql;
        if (sortField != null)
            sql = "SELECT * FROM skill ORDER BY " + sortField + " LIMIT " + (page - 1) + "," + total;
        else
            sql = "SELECT * FROM skill LIMIT " + (page - 1) + "," + total;

        return createListEntitiesFromQueryResult(sql);
    }

    private List<Skill> createListEntitiesFromQueryResult(String sql) {
        ResultSet resultSet;
        List<Skill> skills = new ArrayList<>();

        try {
            Connection connection = DBConnector.getConnection();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                skills.add(new Skill(resultSet.getLong(1), resultSet.getString(2)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return skills;
    }

    public long getCount() {
        return findAll().size();
    }

    private void executуDatabaseQuery(String sql) {
        Connection connection = null;
        try {
            connection = DBConnector.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
