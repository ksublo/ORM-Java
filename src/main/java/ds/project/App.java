package ds.project;

import ds.project.CREATING.Create;
import ds.project.UTIL.DatabaseUtil;
import ds.project.DAO.HostDAO;
import ds.project.DAO.PetDAO;
import ds.project.DTO.HostDTO;
import ds.project.DTO.PetDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		try {
			Create.dropTables();
			Create.createTables();
			performDatabaseOperations();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void performDatabaseOperations() {
		Scanner scanner = new Scanner(System.in);
		Connection conn = null;

		try {
			conn = DatabaseUtil.getConnection();
			conn.setAutoCommit(false);

			System.out.println("Enter Host Information:");
			System.out.print("First Name: ");
			String firstName = scanner.nextLine();
			System.out.print("Last Name: ");
			String lastName = scanner.nextLine();
			System.out.print("Address: ");
			String address = scanner.nextLine();
			System.out.print("Phone: ");
			String phone = scanner.nextLine();
			System.out.print("Email: ");
			String email = scanner.nextLine();

			HostDAO hostDAO = new HostDAO();
			PetDAO petDAO = new PetDAO();

			int hostId = hostDAO.insertHost(new HostDTO(0, firstName, lastName, address, phone, email), conn);
			if (hostId == -1) {
				conn.rollback();
				throw new RuntimeException("Failed to insert host");
			}

			System.out.println("Enter Pet Information:");
			System.out.print("Name: ");
			String petName = scanner.nextLine();
			System.out.print("Species: ");
			String species = scanner.nextLine();
			System.out.print("Breed: ");
			String breed = scanner.nextLine();
			System.out.print("Age: ");
			int age = Integer.parseInt(scanner.nextLine());
			System.out.print("Sex (M/F): ");
			char sex = scanner.nextLine().charAt(0);
			System.out.print("Weight: ");
			float weight = Float.parseFloat(scanner.nextLine());

			int petId = petDAO.insertPet(new PetDTO(0, petName, species, breed, age, sex, weight, hostId), conn);
			if (petId == -1) {
				conn.rollback();
				throw new RuntimeException("Failed to insert pet");
			}

			conn.commit();
			System.out.println("Both host and pet were inserted successfully");

		} catch (SQLException e) {
			System.err.println("Database operation failed: " + e.getMessage());
			try {
				if (conn != null) conn.rollback();
			} catch (SQLException ex) {
				System.err.println("Failed to rollback transaction: " + ex.getMessage());
			}
		} catch (RuntimeException e) {
			System.err.println(e.getMessage());
			try {
				if (conn != null) conn.rollback();
			} catch (SQLException ex) {
				System.err.println("Failed to rollback transaction: " + ex.getMessage());
			}
		} finally {
			if (scanner != null) {
				scanner.close();
			}
			if (conn != null) {
				try {
					conn.setAutoCommit(true);
					conn.close();
				} catch (SQLException e) {
					System.err.println("Failed to close the database connection: " + e.getMessage());
				}
			}
		}
	}

}
