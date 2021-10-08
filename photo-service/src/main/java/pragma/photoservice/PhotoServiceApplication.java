package pragma.photoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableEurekaClient
//@EnableMongoRepositories
@SpringBootApplication
//@EnableMongoAuditing
public class PhotoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoServiceApplication.class, args);
	}

}
