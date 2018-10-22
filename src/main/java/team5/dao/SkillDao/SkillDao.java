package team5.dao.SkillDao;

import org.springframework.jdbc.core.JdbcTemplate;
import team5.dao.EntityDao;
import team5.dao.DBConnector;
import team5.models.Skill;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
        String sql="SELECT * FROM skill";
        return createListEntitiesFromQueryResult(sql);
    }

    @Override
    public List<Skill> getEntitiesByPage(int pageid, int total) {
        String sql="SELECT * FROM skill LIMIT "+(pageid-1)+","+total;
        return createListEntitiesFromQueryResult(sql);
    }

    private List<Skill> createListEntitiesFromQueryResult(String sql){
        ResultSet resultSet;
        List<Skill> skills = new ArrayList<>();

        try {
            Connection connection = DBConnector.getConnection();
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

    public long getCount(){
        return findAll().size();
    }
}