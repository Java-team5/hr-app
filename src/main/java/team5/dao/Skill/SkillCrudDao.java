package team5.dao.Skill;

import team5.dao.interfaces.CrudDao;
import team5.models.Skill;

import java.util.List;

public interface SkillCrudDao extends CrudDao<Skill> {

    List<Skill> getEntitiesByPage(String filter, int offset, int total);
    List<Skill> getSortedEntitiesByPage(String filter, String sortBy, int pageId, int total);

}
