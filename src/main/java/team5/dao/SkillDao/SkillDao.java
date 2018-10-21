package team5.dao.SkillDao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import team5.dao.EntityDao;
import team5.dao.MySQLConnector;
import team5.models.Skill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SkillDao implements EntityDao<Skill> {

    private JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Skill find(long id) {
        return null;
    }

    @Override
    public void save(Skill model) {

    }

    @Override
    public void update(Skill model) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<Skill> findAll() {
        return null;
    }

    @Override
    public List<Skill> getByPage(int pageid, int total) {
        String sql="select * from skill limit "+(pageid-1)+","+total;
        ResultSet resultSet;
        List<Skill> skills = new ArrayList<>();

        try {
            Connection connection = MySQLConnector.getMySQLConnector().getConnection();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                skills.add(new Skill(resultSet.getLong(1),resultSet.getString(2)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return skills;
    }
}
