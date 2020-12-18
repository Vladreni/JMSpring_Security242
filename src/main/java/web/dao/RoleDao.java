package web.dao;

import web.model.Role;

public interface RoleDao {
    Role getDBRole(String role);
    void addDBRole(Role role);
}
