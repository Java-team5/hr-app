package team5.dao;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import team5.dao.utils.DBConnector;
import team5.models.Skill;

import javax.persistence.TypedQuery;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


@Repository
@Transactional
public class SkillDao {

    private Statement statement;

    /**
     * Get connection and create statement.
     */
    public SkillDao() {
        try {
            Connection connection = DBConnector.getConnection();
            statement = connection.createStatement();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Find record in DB by PK.
     * @param id PK.
     * @return Found record.
     */
    public Skill getById(final String id) {
        String sql = String.format("SELECT * FROM skill WHERE skill='%s'", id);
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            return new Skill(resultSet.getString(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Add new record to DB.
     * @param model record.
     */
    public void save(final Skill model) {
        String sql = "INSERT INTO skill(skill) VALUES ('"
                + model.getSkill() + "')";
        executDatabaseQuery(sql);
    }

    /**
     * Update record in DB.
     * @param model record.
     * @param id PK.
     */
    public void update(final Skill model, final String id) {
        String sql = "UPDATE skill SET skill='" + model.getSkill()
                + "' WHERE skill='" + id + "'";
        executDatabaseQuery(sql);
    }

    /**
     * Delete record from DB.
     * @param id PK.
     */
    public void delete(final String id) {
        String sql = "DELETE FROM skill WHERE skill='" + id + "'";
        executDatabaseQuery(sql);
    }

    /**
     * Get all records from DB.
     * @return Skill list.
     */
    public List<Skill> getAll() {
        String sql = "SELECT * FROM skill";
        return createListEntitiesFromQueryResult(sql);
    }

    /**
     * Get all filtered records.
     * @param field filtered field.
     * @param filter value from filtering.
     * @param page page number.
     * @param total count record on page.
     * @return sql query.
     */
    public List<Skill> getFilteredEntitiesByPage(
            final String field,
            final String filter,
            final int page,
            final int total) {
        String sql = "SELECT * FROM skill WHERE "
                + field + " LIKE '%" + filter
                + "%' LIMIT " + (page - 1) + "," + total;
        return createListEntitiesFromQueryResult(sql);
    }

    /**
     * Get all filtered and sorting records.
     * @param field filtered field.
     * @param filter value from filtering.
     * @param sortBy value from sorting.
     * @param page page number.
     * @param total count record on page.
     * @return sql query.
     */
    public List<Skill> getFilteredSortedEntitiesByPage(
            final String field,
            final String filter,
            final String sortBy,
            final int page,
            final int total
    ) {
        String sql = "SELECT * FROM skill WHERE "
                + field + " LIKE '%" + filter + "%' ORDER BY "
                + sortBy + " LIMIT " + (page - 1) + "," + total;
        return createListEntitiesFromQueryResult(sql);
    }

    /**
     * Performs sql query.
     * @param sql query.
     * @return Skill list.
     */
    private List<Skill> createListEntitiesFromQueryResult(final String sql) {
        List<Skill> skills = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                skills.add(new Skill(resultSet.getString(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return skills;
    }

    /**
     * @param filter value from filtering.
     * @return count filtering record in db.
     */
    public int count(final String filter) {
        String sql = "SELECT * FROM skill  WHERE skill LIKE '%" + filter + "%'";
        return createListEntitiesFromQueryResult(sql).size();
    }

    /**
     * Performs sql query.
     * @param sql query.
     */
    private void executDatabaseQuery(final String sql) {
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
