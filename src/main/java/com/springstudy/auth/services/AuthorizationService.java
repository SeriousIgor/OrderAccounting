package com.springstudy.auth.services;

import com.springstudy.auth.enitites.AuthenticationRequest;
import com.springstudy.auth.enitites.AuthenticationResponse;
import com.springstudy.auth.enitites.RegisterRequest;
import com.springstudy.dao.UserDao;
import com.springstudy.exceptions.entities.DatabaseDataUpdateException;
import com.springstudy.models.User;
import com.springstudy.repositories.UserRepository;
import com.springstudy.security.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthorizationService(UserRepository userRepository, JwtService jwtService, PasswordEncoder encoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.encoder = encoder;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(RegisterRequest request) throws DatabaseDataUpdateException {
        var user = new User(
            request.getEmail(),
            request.getUsername(),
            request.getFirstName(),
            request.getLastName(),
            this.encoder.encode(request.getPassword())
        );
        this.userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws UsernameNotFoundException {
        this.authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
            )
        );
        var user = this.userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }
}
