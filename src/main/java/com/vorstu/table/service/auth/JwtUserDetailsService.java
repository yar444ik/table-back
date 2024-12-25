package com.vorstu.table.service.auth;

import com.vorstu.table.dto.AuthUser;
import com.vorstu.table.dto.Role;
import com.vorstu.table.dto.auth.SignInRequest;
import com.vorstu.table.entity.PasswordEntity;
import com.vorstu.table.entity.UserEntity;
import com.vorstu.table.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User %s not found", username))
        );

        return AuthUser.builder()
                .username(user.getUsername())
                .password(user.getPasswordEntity().getPassword())
                .authorities(List.of(new SimpleGrantedAuthority(user.getRole().name())))
                .enabled(user.isEnabled())
                .build();
    }

    public void createUser(SignInRequest request) {
        if (!userRepository.findByUsername(request.getUsername()).isPresent()) {
            UserEntity entity = UserEntity.builder()
                    .username(request.getUsername())
                    .passwordEntity(new PasswordEntity(request.getPwd()))
                    .enabled(true)
                    .role(Role.STUDENT)
                    .build();

            userRepository.save(entity);
        } else {
            throw new UsernameNotFoundException(String.format("User %s not found", request.getUsername()));
        }
    }
}
