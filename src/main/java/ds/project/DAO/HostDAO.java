package ds.project.DAO;

import ds.project.DTO.HostDTO;

import java.sql.*;

public class HostDAO {

    public int insertHost(HostDTO host, Connection conn) {
        String sql = "INSERT INTO Host (firstName, lastName, address, phone, email) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, new String[]{"hostID"})) {

            pstmt.setString(1, host.getFirstName());
            pstmt.setString(2, host.getLastName());
            pstmt.setString(3, host.getAddress());
            pstmt.setString(4, host.getPhone());
            pstmt.setString(5, host.getEmail());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }


}
