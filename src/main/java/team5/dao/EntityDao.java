package team5.dao;

import java.util.List;

public interface EntityDao<T> extends CrudDao<T> {
    List<T> getEntitiesByPage(int pageid, int total);
}
