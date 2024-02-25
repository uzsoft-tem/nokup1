package com.alibou.security.auditing;

import com.alibou.security.auth.AuthenticationService;
import com.alibou.security.auth.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import static com.alibou.security.user.Role.ADMIN;
import static com.alibou.security.user.Role.MANAGER;

@Component
public class DataLoader implements ApplicationRunner{

    @Value("${spring.sql.init.mode}")
    private String initMode;

    @Autowired
    AuthenticationService authenticationService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (initMode.equals("always")){
            var admin = RegisterRequest.builder()
                    .firstname("Admin")
                    .lastname("Admin")
                    .number("916262026")
                    .password("1234")
                    .role(ADMIN)
                    .build();
            System.out.println("Admin token: " + authenticationService.register(admin).getAccessToken());

            var manager = RegisterRequest.builder()
                    .firstname("Admin")
                    .lastname("Admin")
                    .number("916261026")
                    .password("1234")
                    .role(MANAGER)
                    .build();
            System.out.println("Manager token: " + authenticationService.register(manager).getAccessToken());
        }
    }
}
