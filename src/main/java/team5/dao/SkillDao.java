package team5.dao;

import team5.dao.interfaces.EntityDao;
import team5.dao.utils.DBConnector;
import team5.models.Skill;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class SkillDao implements EntityDao<Skill> {

    //private JdbcTemplate template;

    //public void setTemplate(JdbcTemplate template) {
      //  this.template = template;
    //}

    @Override
    public Skill getById(long id) {
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
    public List<Skill> getAll() {
        String sql="SELECT * FROM skill";
        return createListEntitiesFromQueryResult(sql);
    }

    @Override
    public List<Skill> getEntitiesByPage(int offset, int total) {
        String sql="SELECT * FROM skill LIMIT "+(offset -1)+","+total;
        return createListEntitiesFromQueryResult(sql);
    }

    @Override
    public List<Skill> getSortedEntitiesByPage(String sortBy, int pageid, int total) {
        return null;
    }

    @Override
    public int count(){
        return getAll().size();
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
}
