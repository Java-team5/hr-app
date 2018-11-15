package com.team5.dao.interfaces;

import java.util.List;

public interface EntityDao<T> extends CrudDao<T> {
    List<T> getEntitiesByPage(int offset, int total);
    List<T> getSortedEntitiesByPage(String sortBy, int pageid, int total);
}
