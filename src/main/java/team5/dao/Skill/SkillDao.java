package team5.dao.Skill;

import team5.dao.utils.DBConnector;
import team5.models.Skill;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class SkillDao implements SkillCrudDao {

    private Connection connection;
    private Statement statement;

    public SkillDao() {
        try {
            connection = DBConnector.getConnection();
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Skill getById(long id) {
        String sql = "SELECT * FROM skill WHERE id="+id+"";
        try {
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
    public List<Skill> getAll() {
        String sql = "SELECT * FROM skill";
        return createListEntitiesFromQueryResult(sql);
    }

    @Override
    public List<Skill> getEntitiesByPage(String filter, int page, int total) {
        String sql = "SELECT * FROM skill WHERE skill LIKE '%" + filter + "%' LIMIT " + (page - 1) + "," + total;
        return createListEntitiesFromQueryResult(sql);
    }

    @Override
    public List<Skill> getSortedEntitiesByPage(String filter, String sortBy, int pageid, int total) {
        String sql = "SELECT * FROM skill WHERE skill LIKE '%" + filter + "%' ORDER BY " + sortBy + " LIMIT " + (pageid - 1) + "," + total;
        return createListEntitiesFromQueryResult(sql);
    }

    private List<Skill> createListEntitiesFromQueryResult(String sql) {
        List<Skill> skills = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                skills.add(new Skill(resultSet.getLong(1), resultSet.getString(2)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return skills;
    }

    @Override
    public int count() {
        return getAll().size();
    }

    public int count(String filter) {
        String sql = "SELECT * FROM skill  WHERE skill LIKE '%" + filter + "%'";
        return createListEntitiesFromQueryResult(sql).size();
    }

    private void executуDatabaseQuery(String sql) {
        try {
            statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
