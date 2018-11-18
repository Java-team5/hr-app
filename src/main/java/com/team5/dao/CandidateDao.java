package com.team5.dao;

import com.team5.dao.interfaces.FilteredEntityDao;
import com.team5.models.Candidate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.List;

@Repository
@Transactional
public class CandidateDao implements FilteredEntityDao<Candidate> {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Autowired
    private SessionFactory sessionFactory;

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Get all filtered records.
     * @param field filtered field.
     * @param filter value from filtering.
     * @param page page number.
     * @param total count record on page.
     */
    @Override
    public List<Candidate> getFilteredEntitiesByPage(String field, String filter, int offset, int total) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("SELECT * FROM candidate WHERE :field LIKE :filter");
        query.setParameter("field", field);
        query.setParameter("filter", "%" + filter + "%");
        query.setFirstResult(offset - 1);
        query.setMaxResults (total);
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
    @Override
    public List<Candidate> getFilteredSortedEntitiesByPage(String field, String filter, String sortBy, int pageId, int total) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("SELECT * FROM candidate WHERE :field LIKE :filter ORDER BY " + sortBy);
        query.setParameter("field", field);
        query.setParameter("filter", "%" + filter + "%");
        query.setFirstResult(pageId - 1);
        query.setMaxResults (total);
        return query.list();
    }

    /**
     * Add new record to DB.
     * @param model record.
     */
    @Override
    public void save(Candidate model) {
        hibernateTemplate.save(model);
    }

    /**
     * Find record in DB by PK.
     * @param id PK.
     * @return Found record.
     */
    @Override
    public Candidate getById(long id) {
        return hibernateTemplate.get(Candidate.class, id);
    }

    /**
     * Get all records from DB.
     * @return Skill list.
     */
    @Override
    public List<Candidate> getAll() {
        return (List<Candidate>) hibernateTemplate.find("FROM Candidate");
    }

    /**
     * @return count filtering record in db.
     */
    @Override
    public int count() {
        return getAll().size();
    }

    /**
     * Update record in DB.
     * @param model record.
     */
    @Override
    public void update(Candidate model) {
        hibernateTemplate.update(model);
    }

    /**
     * Delete record from DB.
     * @param id PK.
     */
    @Override
    public void delete(long id) {
        hibernateTemplate.delete(getById(id));
    }
}
