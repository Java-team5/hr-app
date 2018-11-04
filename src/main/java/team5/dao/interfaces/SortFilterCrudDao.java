package team5.dao.interfaces;


import team5.dao.interfaces.CrudDao;
import team5.models.Vacancy;

import java.util.List;

public interface SortFilterCrudDao<T,F> extends CrudDao<T> {
    List<T> getEntitiesByPage(F filter, int offset, int total);
    List<T> getSortedEntitiesByPage(F filter, String sortBy, int pageId, int total);
}
