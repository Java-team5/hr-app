package com.team5.dao;


import com.team5.models.Skill;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository("skillDao")
@Transactional
public class SkillDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Get all filtered records.
     * @param field filtered field.
     * @param filter value from filtering.
     * @param page page number.
     * @param total count record on page.
     */
    public List<Skill> getFilteredEntitiesByPage(
            final String field,
            final String filter,
            final int page,
            final int total
    ) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("SELECT * FROM skill WHERE :field LIKE :filter");
        query.setParameter("field", field);
        query.setParameter("filter", "%" + filter + "%");
        query.setFirstResult(page - 1);
        query.setMaxResults(total);
        return query.list();

    }

    /**
     * Get all filtered and sorting records.
     * @param field filtered field.
     * @param filter value from filtering.
     * @param sortBy value from sorting.
     * @param page page number.
     * @param total count record on page.
     */
    public List<Skill> getFilteredSortedEntitiesByPage(
            final String field,
            final String filter,
            final String sortBy,
            final int page,
            final int total
    ) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("SELECT * FROM skill WHERE :field LIKE :filter ORDER BY :sortBy");
        query.setParameter("field", field);
        query.setParameter("filter", "%" + filter + "%");
        query.setParameter("sortBy", sortBy);
        query.setFirstResult(page - 1);
        query.setMaxResults(total);
        return query.list();
    }

    /**
     * Add new record to DB.
     * @param model record.
     */
    public void save(final Skill model) {
        hibernateTemplate.save(model);
    }

    /**
     * Find record in DB by PK.
     * @param id PK.
     * @return Found record.
     */
    public Skill getById(final String id) {
        return hibernateTemplate.get(Skill.class, id);
    }

    /**
     * Get all records from DB.
     * @return Skill list.
     */
    public List<Skill> getAll() {
        return (List<Skill>) hibernateTemplate.find("FROM Skill");
    }

    /**
     * @param filter value from filtering.
     * @return count filtering record in db.
     */
    public int count(final String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("SELECT * FROM skill WHERE skill LIKE :filter");
        query.setParameter("filter", "%" + filter + "%");
        return query.list().size();
    }

    /**
     * Update record in DB.
     * @param model record.
     * @param id PK.
     */
    public void update(final Skill model, final String id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("UPDATE skill SET skill=:newSkill WHERE skill=:id");
        query.setParameter("newSkill", model.getSkill());
        query.setParameter("id", id);
        query.executeUpdate();
    }

    /**
     * Delete record from DB.
     * @param id PK.
     */
    public void delete(final String id) {
        hibernateTemplate.delete(getById(id));
    }

}
