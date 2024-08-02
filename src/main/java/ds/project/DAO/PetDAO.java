package ds.project.DAO;

import ds.project.DTO.PetDTO;
import ds.project.UTIL.DatabaseUtil;

import java.sql.*;

public class PetDAO {

    public int insertPet(PetDTO pet, Connection conn) {
        String sql = "INSERT INTO Pet (name, species, breed, age, sex, weight, hostID) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, new String[]{"petID"})) {

            pstmt.setString(1, pet.getName());
            pstmt.setString(2, pet.getSpecies());
            pstmt.setString(3, pet.getBreed());
            pstmt.setInt(4, pet.getAge());
            pstmt.setString(5, String.valueOf(pet.getSex()));
            pstmt.setFloat(6, pet.getWeight());
            pstmt.setInt(7, pet.getHostID());

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
            System.err.println("Failed to insert pet: " + e.getMessage());
        }
        return -1;
    }

}
