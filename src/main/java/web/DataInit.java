package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;


@Component
public class DataInit {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataInit(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;

        //добавляем в БД две роли
        Role roleUser = new Role(1L, "ROLE_USER");
        Role roleAdmin = new Role(2L, "ROLE_ADMIN");

        roleService.addRole(roleUser);
        roleService.addRole(roleAdmin);


        //и двоих пользователей
        User user = new User("User", "123", null);
        userService.add(user);

        User admin = new User("Admin",
                passwordEncoder.encode("123"), null);
        admin.setAdmin("ROLE_ADMIN");
        userService.add(admin);
    }

}
