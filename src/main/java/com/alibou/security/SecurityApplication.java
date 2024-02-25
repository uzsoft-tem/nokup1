package com.alibou.security;

import com.alibou.security.auth.AuthenticationService;
import com.alibou.security.auth.RegisterRequest;
import com.alibou.security.user.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import static com.alibou.security.user.Role.ADMIN;
import static com.alibou.security.user.Role.MANAGER;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class SecurityApplication {
	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

//	@Value("${spring.sql.init.mode}")
//	private String initMode;
//	@Bean
//	public CommandLineRunner commandLineRunner(AuthenticationService service) {
//		return args -> {
//			if (initMode.equals("always")){
//				var admin = RegisterRequest.builder()
//						.firstname("Admin")
//						.lastname("Admin")
//						.number("916262026")
//						.password("1234")
//						.role(ADMIN)
//						.build();
//				System.out.println("Admin token: " + service.register(admin).getAccessToken());
//
//				var manager = RegisterRequest.builder()
//						.firstname("Admin")
//						.lastname("Admin")
//						.number("916261026")
//						.password("1234")
//						.role(MANAGER)
//						.build();
//				System.out.println("Manager token: " + service.register(manager).getAccessToken());
//			}
//		};
//	}
}
