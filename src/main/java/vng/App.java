package vng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vng.model.DBController;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        System.out.println("Server is starting...");

        DBController.init();

        SpringApplication.run(App.class, args);
    }
}
