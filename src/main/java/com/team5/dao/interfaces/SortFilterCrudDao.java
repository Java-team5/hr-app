package com.team5.dao.interfaces;


import java.util.List;

public interface SortFilterCrudDao<T, F> extends CrudDao<T> {
    List<T> getFilteredEntitiesByPage(F filter, int offset, int total);
    List<T> getFilteredSortedEntitiesByPage(
            F filter, String sortBy, int pageId, int total);
}
