package comp5200m.sc22ao.project.tracingdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class TracingDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(TracingDemoApplication.class, args);
	}

}
