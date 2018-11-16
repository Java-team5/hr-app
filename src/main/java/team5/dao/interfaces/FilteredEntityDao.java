package team5.dao.interfaces;

import java.util.List;

public interface FilteredEntityDao<T> extends CrudDao<T> {
    List<T> getFilteredEntitiesByPage(String field, String filter, int offset, int total);
    List<T> getFilteredSortedEntitiesByPage(String field, String filter, String sortBy, int pageId, int total);
}
