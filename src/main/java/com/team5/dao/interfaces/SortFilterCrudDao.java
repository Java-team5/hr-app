package com.team5.dao.interfaces;


import java.util.List;

public interface SortFilterCrudDao<T, F> extends CrudDao<T> {
    List<T> getFilteredSortedEntitiesByPage(
            F filter, int pageId, int total);
}
