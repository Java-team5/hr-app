package team5.dao;

import java.util.List;

public interface EntityDao<T> extends CrudDao<T> {
    List<T> getByPage(int pageid, int total);
}
