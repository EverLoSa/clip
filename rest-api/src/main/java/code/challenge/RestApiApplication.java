package code.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class RestApiApplication {

    public static void main(String[] args) {
        logger.info("Starting Clip REST Application");
        SpringApplication.run(RestApiApplication.class, args);
    }

}
