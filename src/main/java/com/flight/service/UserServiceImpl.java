package com.flight.service;

import com.flight.entity.Role;
import com.flight.entity.User;
import com.flight.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<User> getUsers(){
        return userRepository.findAll();
    }
    @Override
    public User findUserById(long id){
        return userRepository.findByUserId(id);
    }
    @Override
    public User saveUser(User user){
        Role role=new Role("ROLE_ADMIN");
        User user1 = new User(user.getName(),
                user.getEmailId(), user.getPhoneNo(),
                passwordEncoder.encode(user.getPassword()),role);
        return userRepository.save(user1);
    }
    @Override
    public void deleteUserById(long userId){
        userRepository.deleteById(userId);
    }
    @Override
    public User updateUser(long userId, User user) {
        User user1 = userRepository.findByUserId(userId);
        if (Objects.nonNull(user.getName()) && !"".equals(user.getName())) {
            user1.setName(user.getName());
        }
        if (Objects.nonNull(user.getEmailId()) && !"".equals(user.getEmailId())) {
            user1.setEmailId(user.getEmailId());
        }
        if (Objects.nonNull(user.getPassword()) && !"".equals(user.getPassword())) {
            user1.setPassword(user.getPassword());
        }
        if (Objects.nonNull(user.getPhoneNo()) && !"".equals(user.getPhoneNo())) {
            user1.setPhoneNo(user.getPhoneNo());
        }
        userRepository.save(user1);
        return user1;
    }

    @Override
    public User findByEmailId(String emailId) {
        return userRepository.findByEmailId(emailId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmailId(username);//username is email id here
        if(user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmailId(), user.getPassword(), mapRolesToAuthorities(user.getRole()));
    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Role role){
        return Collections.singleton(new SimpleGrantedAuthority(role.getRoleName()));
    }
}
