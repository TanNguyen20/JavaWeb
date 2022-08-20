package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.Log;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Transactional
@Service
@Builder
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByEmail(username);
        if(user == null) {
            Log.error("User not found in database...");
            throw new UsernameNotFoundException("User not found in database");
        }
        else{
            Log.info("User found in database");
        }
        Collection <SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

    public User saveUser(User user) {
        Log.info("Save role to database with name: " + user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public List<User> getAllUsers() {
        Log.info("Get all users from database");
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getUserByEmailLike(String email){
        return userRepository.findByEmailLike(email);
    }

    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "user removed !! " + id;
    }

    public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        assert existingUser != null;
        existingUser.setName(user.getName());

        return userRepository.save(existingUser);
    }

    public void addRoleToUser(String email, String name) {
        User user = userRepository.findByEmail(email);
        Role role = roleRepository.findByName(name);
        if (user != null && role != null) {
            user.getRoles().add(role);
            userRepository.save(user);
        }
    }
}
