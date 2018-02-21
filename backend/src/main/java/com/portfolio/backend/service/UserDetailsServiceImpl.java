//package com.portfolio.backend.service;
//
//import com.portfolio.backend.repository.UserRepository;
//import com.portfolio.backend.user.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    @Transactional(readOnly = true)
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(username);
//
////        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
////        for (Role role : user.getRoles()){
////            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
////        }
//        return null;
//        //        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
//    }
//}