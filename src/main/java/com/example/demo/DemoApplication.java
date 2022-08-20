
package com.example.demo;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import com.example.demo.utils.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;

import java.io.Console;
import java.util.ArrayList;

@SpringBootApplication
@RestController
public class DemoApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		Log.info("App running on port 8080");
	}

	@Bean
	public OpenAPI openApiConfig() {
		return new OpenAPI().info(apiInfo());
	}

	public Info apiInfo() {
		Info info = new Info();
		info.title("API For Product").description("System Swagger Open API").version("v1.0.0");
		return info;
	}

//	@Bean
//	CommandLineRunner run(UserService userService, RoleService roleService) {
//		return args -> {
//			roleService.saveRole(new Role(null,"ADMIN"));
//			roleService.saveRole(new Role(null,"USER"));
//			roleService.saveRole(new Role(null,"GUEST"));
//			userService.saveUser(new User(null,"Nguyen Nhat Tan","tan@gmail.com","123456", new ArrayList<>()));
//			userService.saveUser(new User(null,"Tran Van Chien","chien@gmail.com","123456", new ArrayList<>()));
//			userService.saveUser(new User(null,"Vo Nhat Linh","linh@gmail.com","123456", new ArrayList<>()));
//			userService.addRoleToUser("tan@gmail.com","ADMIN");
//			userService.addRoleToUser("tan@gmail.com","USER");
//			userService.addRoleToUser("chien@gmail.com","USER");
//			userService.addRoleToUser("chien@gmail.com","GUEST");
//			userService.addRoleToUser("linh@gmail.com","GUEST");
//			userService.addRoleToUser("linh@gmail.com","ADMIN");
//
//
//		};
//	}
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
            