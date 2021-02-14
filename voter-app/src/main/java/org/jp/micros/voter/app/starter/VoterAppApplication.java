package org.jp.micros.voter.app.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication()
@ComponentScan(basePackages  = { "org.jp.micros.voter.app" })
public class VoterAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoterAppApplication.class, args);
	}

}
