package spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "spring" })
public class Test {

	public static void main (String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(Test.class, args);
		
//		applicationContext.close();
	}
}
