package com.expatrio.webapp.controllers;

import com.expatrio.webapp.models.User;
import com.expatrio.webapp.payload.LoginRequest;
import com.expatrio.webapp.payload.MessageResponse;
import com.expatrio.webapp.payload.SignupRequest;
import com.expatrio.webapp.payload.UserRequest;
import com.expatrio.webapp.security.services.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminServiceImpl adminServiceImpl;

    @GetMapping("/customers")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getCustomers() {

        return ResponseEntity.ok(adminServiceImpl.loadCustomerUsers());
    }

    @PostMapping("/customer")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createCustomer(@Valid @RequestBody SignupRequest signupRequest) {

        return ResponseEntity.ok(adminServiceImpl.createUser(signupRequest));

    }



    @PutMapping("/customer")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateCustomer(@Valid @RequestBody UserRequest userRequest) {

        return ResponseEntity.ok(adminServiceImpl.updateUser(userRequest));

    }

    @DeleteMapping("/customer")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteCustomer(@Valid @RequestBody UserRequest userRequest) {

        try {
            adminServiceImpl.deleteUser(userRequest);
        } catch (Exception e) {
            return ResponseEntity.ok(new MessageResponse(e.getMessage()));
        }
        return ResponseEntity.ok(new MessageResponse("Deleted"));

    }
}
