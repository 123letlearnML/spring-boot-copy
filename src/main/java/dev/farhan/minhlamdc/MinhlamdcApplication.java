package dev.farhan.minhlamdc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//import java.sql.*;

//@SpringBootApplication
@SpringBootApplication(scanBasePackages = "dev.farhan")
//@EnableWebMvc
//@RestController
//@Configuration
//@ComponentScan("dev.farhan.service")
public class MinhlamdcApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinhlamdcApplication.class, args);

//		fetchDataFromDatabase();
//	}
//	@GetMapping("/fetchData")
//	public static void fetchDataFromDatabase() {
//		try {
//			Connection connection = DriverManager.getConnection(
//					"jdbc:mysql://127.0.0.1:3306/new_schema",
//					"root",
//					"duongsql"
//			);
//			Statement statement = connection.createStatement();
//			ResultSet resultSet = statement.executeQuery("SELECT * FROM USERS");
//
//			while (resultSet.next()) {
//				System.out.println(resultSet.getString("username"));
//				System.out.println(resultSet.getString("password"));
//			}
//
//			// Close resources
//			resultSet.close();
//			statement.close();
//			connection.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
	}
}
