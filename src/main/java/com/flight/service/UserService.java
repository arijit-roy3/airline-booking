package com.flight.service;

import com.flight.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {
    public List<User> getUsers();
    public User findUserById(long id);

    public User saveUser(User user);

    public void deleteUserById(long userId);

    public User updateUser(long userId, User user);

    public User findByEmailId(String emailId);

}
