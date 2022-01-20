package br.com.eduardo.springsecurityjwt;

import br.com.eduardo.springsecurityjwt.model.Role;
import br.com.eduardo.springsecurityjwt.model.User;
import br.com.eduardo.springsecurityjwt.service.RoleService;
import br.com.eduardo.springsecurityjwt.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;

@SpringBootApplication
public class SpringSecurityJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJwtApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner runner(UserService userService, RoleService roleService) {
        return args -> {
            roleService.save(new Role(null, "ROLE_USER"));
            roleService.save(new Role(null, "ROLE_MANAGER"));
            roleService.save(new Role(null, "ROLE_ADMIN"));
            roleService.save(new Role(null, "ROLE_SUPER_ADMIN"));

            userService.save(new User(null, "John Travolta", "john", "1234", LocalDateTime.now(), new ArrayList<>()));
            userService.save(new User(null, "Will Smith", "will", "1234", LocalDateTime.now(), new ArrayList<>()));
            userService.save(new User(null, "Jim Carrey", "jim", "1234", LocalDateTime.now(), new ArrayList<>()));
            userService.save(new User(null, "Arnold Schwarzenegger", "arnold", "1234", LocalDateTime.now(), new ArrayList<>()));

            roleService.addRoleToUser("john", "ROLE_USER");
            roleService.addRoleToUser("will", "ROLE_MANAGER");
            roleService.addRoleToUser("jim", "ROLE_ADMIN");
            roleService.addRoleToUser("arnold", "ROLE_SUPER_ADMIN");
            roleService.addRoleToUser("arnold", "ROLE_ADMIN");
            roleService.addRoleToUser("arnold", "ROLE_USER");
        };
    }

}
