package loaders;

import models.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import repositories.RoleRepository;

import java.util.Arrays;

@Component
public class RoleDataLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public RoleDataLoader(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.findAll().isEmpty()) {
            loadInitialColorPersonalities();
        }
    }

    private void loadInitialColorPersonalities() {

        Role user = createRole("USER");
        Role admin = createRole("ADMIN");

        Role[] roles = {user, admin};
        roleRepository.saveAll(Arrays.asList(roles));
    }

    private Role createRole (String name) {
        Role role = new Role();
        role.setName(name);
        return role;
    }
}