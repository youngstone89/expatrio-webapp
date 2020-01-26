package com.expatrio.webapp.security.services;


import com.expatrio.webapp.models.User;
import com.expatrio.webapp.payload.SignupRequest;
import com.expatrio.webapp.payload.UserRequest;

import java.util.List;

public interface AdminService {

    List<User> getCustomers();
    User createUser(SignupRequest signupRequest);
    User updateUser(UserRequest userRequest);
    void deleteUser(UserRequest userRequest);
}
