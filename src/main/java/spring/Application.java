package spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "spring" })
public class Application {

	public static void main (String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);
		
//		applicationContext.close();
	}
}
