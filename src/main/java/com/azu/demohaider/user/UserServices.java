package com.azu.demohaider.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServices(UserRepository repository, @Qualifier("PassEncoder") PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(UserRegistrationsRequest request){
        User newUser = new User(
                request.getUsername(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getAge()
        );
        repository.save(newUser);
    }


    public Optional<User> getUserById(Long userId){
        return Optional.ofNullable(repository.findById(userId)
                .orElseThrow(() -> new RuntimeException(
                        "Could not find user"
                )));
    }

    public List<User> getUsers(){
        return repository.findAll();
    }

    public void updateUser(Long userId,UserRegistrationsRequest request){
        Optional<User> user = getUserById(userId);
        User newUser = user.get();
        boolean changes = false;
        if (request.getUsername() != null) {
            newUser.setUsername(request.getUsername());
            changes = true;
        }if (request.getEmail() != null){
        newUser.setEmail(request.getEmail());
        changes = true;
    }if (request.getPassword() != null){
        newUser.setPassword(request.getPassword());
        changes = true;
    }if (request.getAge() != null){
        newUser.setAge(request.getAge());
        changes = true;
    }if (!changes){
        throw  new RuntimeException("there is no changes ");
    }
        repository.save(newUser);
    }


    public void deleteUserById(Long userId){
        Optional<User> user = getUserById(userId);
        User newUser = user.get();
        repository.deleteById(newUser.getId());
    }
}
