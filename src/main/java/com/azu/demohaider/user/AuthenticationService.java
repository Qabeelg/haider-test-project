package com.azu.demohaider.user;


import com.azu.demohaider.user.securirty.config.AuthenticationRequest;
import com.azu.demohaider.user.securirty.config.AuthenticationResponse;
import com.azu.demohaider.user.securirty.config.JwtService;
import com.azu.demohaider.user.securirty.token.Token;
import com.azu.demohaider.user.securirty.token.TokenRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class AuthenticationService {

    private final UserRepository baseUserDao;
    private final TokenRepository tokenDao;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @Autowired
    public AuthenticationService(
            UserRepository baseUserDao,
             TokenRepository tokenDao,
            @Qualifier("jwt") JwtService jwtService,
            @Qualifier("AuthManger") AuthenticationManager authenticationManager) {
        this.baseUserDao = baseUserDao;
        this.tokenDao = tokenDao;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Transactional
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        User authenticatedUser = baseUserDao.findByEmail(request.email())
                .orElseThrow(
                        () -> new UsernameNotFoundException
                                (
                                        "User Not Found with email: " + request.email()
                                )
                );


        var jwtToken = jwtService.generateToken(authenticatedUser, authenticatedUser.getId());
        saveUserToken(authenticatedUser, jwtToken);

        return new AuthenticationResponse(
                authenticatedUser.getUsername(),
                authenticatedUser.getEmail(),
                jwtToken,
                new HashSet<>(authenticatedUser.getRoles())
        );


    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .users(user)
                .token(jwtToken)
                .isExpire(false)
                .isRevoke(false)
                .build();
        tokenDao.save(token);
    }






}
