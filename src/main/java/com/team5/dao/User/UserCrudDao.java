package com.team5.dao.User;

import com.team5.dao.interfaces.CrudDao;
import com.team5.models.User;
import com.team5.models.UserFilter;

import java.util.List;

public interface UserCrudDao extends CrudDao<User> {

    List<User> getEntitiesByPage(UserFilter filter, int offset, int total);
    List<User> getSortedEntitiesByPage(UserFilter filter, String sortBy, int pageId, int total);

}