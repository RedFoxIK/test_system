package ua.test.dao;

import ua.test.entity.Role;

import java.sql.*;
import java.util.List;

public class RoleDao {
    private final String SELECT_ALL = "SELECT id_role, role FROM roles";
    private final String FIND_BY_ID = "SELECT id_role, role FROM roles WHERE id_role = ?";

    Connection conn;

    public RoleDao(Connection con) {
        this.conn = conn;
    }

    public Role findById(int id) {
        return null;
    }

    public List<Role> selectALL() {
        return null;
    }
}
