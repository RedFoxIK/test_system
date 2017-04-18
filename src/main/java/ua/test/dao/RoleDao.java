package ua.test.dao;

import ua.test.entity.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDao {
    private final String SELECT_ALL = "SELECT id_role, role FROM roles";
    private final String FIND_BY_ID = "SELECT id_role, role FROM roles WHERE id_role = ?";

    Connection conn;

    public RoleDao(Connection con) {
        this.conn = conn;
    }

    public Role findById(int id) {
        Role role = null;

        try ( PreparedStatement statement = conn.prepareStatement(FIND_BY_ID) ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if ( rs.next() ) {
                role.setId(id);
                role.setRole(rs.getString("role"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    public List<Role> selectALL() {
        List<Role> roles = new ArrayList<>();

        try (Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(SELECT_ALL) ) {

            while ( rs.next() ) {
                Role role = new Role();
                role.setId(rs.getInt("id_role"));
                role.setRole(rs.getString("role"));
                roles.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }
}
