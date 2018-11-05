package team5.service;

import org.springframework.beans.factory.annotation.Autowired;
import team5.dao.Skill.SkillDao;
import team5.models.Skill;

import java.util.List;

public class SkillService {

    /**
     * Dao for entity 'Skill'.
     */
    @Autowired
    private SkillDao skillDao;


    /**
     * Find record in DB by PK.
     * @param id PK.
     * @return Found record.
     */
    public Skill getById(final String id) {
        return skillDao.getById(id);
    }

    /**
     * Add new record to DB.
     * @param model record.
     */
    public void save(final Skill model) {
        skillDao.save(model);
    }

    /**
     * Update record in DB.
     * @param model record.
     * @param id PK.
     */
    public void update(final Skill model, final String id) {
        skillDao.update(model, id);
    }

    /**
     * Delete record from DB.
     * @param id PK.
     */
    public void delete(final String id) {
        skillDao.delete(id);
    }

    /**
     * Get all records from DB.
     * @return Skill list.
     */
    public List<Skill> getAll() {
        return skillDao.getAll();
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
            final int total
    ) {
        return skillDao.getFilteredEntitiesByPage(field, filter, page ,total);
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
        return skillDao.getFilteredSortedEntitiesByPage(field, filter, sortBy, page ,total);
    }

    /**
     * @param filter value from filtering.
     * @return count filtering record in db.
     */
    public int count(final String filter) {
        return skillDao.count(filter);
    }

}
