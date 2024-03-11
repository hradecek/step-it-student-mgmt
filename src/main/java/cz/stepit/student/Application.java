package cz.stepit.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import cz.stepit.student.repository.DBConnectionManager;

import java.sql.Connection;
import java.util.Scanner;

@SpringBootApplication
public class Application {

    private static final String CONNECTION_URL =
            "jdbc:sqlserver://192.168.0.113\\SQLEXPRESS:49875;databaseName=school;username=admin1;password=admin;trustServerCertificate=true;";

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    @Bean
    public Connection connection() {
        return new DBConnectionManager(CONNECTION_URL).getConnection();
    }
}
