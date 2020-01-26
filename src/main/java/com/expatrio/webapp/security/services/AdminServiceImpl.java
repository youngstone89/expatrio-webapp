package com.expatrio.webapp.security.services;

import com.expatrio.webapp.exceptions.EmailExistException;
import com.expatrio.webapp.exceptions.UserNotExistException;
import com.expatrio.webapp.models.ERole;
import com.expatrio.webapp.models.Role;
import com.expatrio.webapp.models.User;
import com.expatrio.webapp.payload.SignupRequest;
import com.expatrio.webapp.payload.UserRequest;
import com.expatrio.webapp.repository.RoleRepository;
import com.expatrio.webapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;


    @Autowired
    PasswordEncoder encoder;

    @Override
    public User getCustomer(String id) {
        Optional<User> userOptional = userRepository.findById(Long.parseLong(id));
        return userOptional.orElseThrow(()-> new UserNotExistException());
    }

    @Override
    public List<User> getCustomers() {
        Set<Role> roleSet = new HashSet<Role>();
        Role role = new Role();
        role.setId(2);
        role.setName(ERole.ROLE_CUSTOMER);
        roleSet.add(role);
        return userRepository.findByRoles(roleSet);
    }

    @Override
    public User createUser(SignupRequest signUpRequest) {

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {

            throw new EmailExistException("Email already exists");
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        setRole(user, strRoles);
        userRepository.save(user);
        User savedUser = userRepository.save(user);
        log.info("createUser>>"+user.toString());

        return savedUser;
    }

    private void setRole(User user, Set<String> strRoles) {
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = (Role)roleRepository.findByName(ERole.ROLE_CUSTOMER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole =(Role) roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "customer":
                        Role modRole = (Role)roleRepository.findByName(ERole.ROLE_CUSTOMER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                }
            });
        }

        user.setRoles(roles);
    }

    @Override
    public User updateUser(UserRequest userRequest) {

        if (!userRepository.existsByEmail(userRequest.getEmail())) {

            throw new UserNotExistException("User not exists");
        }

        User user = new User(userRequest.getUsername(),
                userRequest.getEmail(),
                encoder.encode(userRequest.getPassword()));
        user.setId(userRequest.getId());
        Set<String> strRoles = userRequest.getRole();
        setRole(user, strRoles);
        User savedUser = userRepository.save(user);
        log.info("updateUser>>"+userRequest.toString());
        return savedUser;
    }

    @Override
    public void deleteUser(UserRequest userRequest) {

        if (!userRepository.existsByEmail(userRequest.getEmail())) {

            throw new UserNotExistException("User not exists");
        }

        if (!userRepository.existsById(userRequest.getId())) {

            throw new UserNotExistException("User not exists");
        }


        User user = new User();
        user.setId(userRequest.getId());
        userRepository.delete(user);

    }
}
