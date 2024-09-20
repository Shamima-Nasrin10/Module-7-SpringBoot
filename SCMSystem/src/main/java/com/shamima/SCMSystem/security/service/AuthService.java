package com.shamima.SCMSystem.security.service;

import com.shamima.SCMSystem.security.entity.Token;
import com.shamima.SCMSystem.security.entity.User;
import com.shamima.SCMSystem.security.repository.TokenRepository;
import com.shamima.SCMSystem.security.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
@Autowired
    private UserRepository userRepository;
@Autowired
    private PasswordEncoder passwordEncoder;
@Autowired
    private JwtService jwtService;
@Autowired
    private TokenRepository tokenRepository;
@Autowired
    private AuthenticationManager authenticationManager;

private void saveUserToken(String jwt, User user) {
    Token token=new Token();
    token.setToken(jwt);
    token.setLoggedOut(false);
    token.setUser(user);
    tokenRepository.save(token);
}
}
