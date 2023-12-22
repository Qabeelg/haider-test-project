package com.azu.demohaider.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserServices userServices;

    @Autowired
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping
    public ResponseEntity<Void> createNewUser(@RequestBody UserRegistrationsRequest request){
        userServices.createUser(request);
        return ResponseEntity.ok().build();
    }


    @GetMapping ("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id){
        return ResponseEntity.ok(userServices.getUserById(id));
    }

    @GetMapping ("/all")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(userServices.getUsers());
    }

    @PutMapping("/id")
    public ResponseEntity<Void> updateNewUser(@RequestParam Long id, @RequestBody UserRegistrationsRequest request){
        userServices.updateUser(id,request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deleteNewUser(@PathVariable("id") Long id){
        userServices.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

}
