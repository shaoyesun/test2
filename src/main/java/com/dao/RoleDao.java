package com.dao;

import com.entity.Role;


import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import javax.inject.Named;

/**
 * Created by root on 17-2-17.
 */
@Named
public class RoleDao extends BaseDao<Role, Long> {

    public Role findByName(String name) {
        Criteria c = getSession().createCriteria(Role.class).add(Restrictions.eq("name", name));
        return (Role) c.uniqueResult();
    }

}
