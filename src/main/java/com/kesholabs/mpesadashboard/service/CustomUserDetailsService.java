package com.kesholabs.mpesadashboard.service;

import com.kesholabs.mpesadashboard.entity.Kesho.Dashboard_RolesEntity;
import com.kesholabs.mpesadashboard.entity.Kesho.Dashboard_UsersEntity;
import com.kesholabs.mpesadashboard.repo.Kesho.Dashboard_RolesRepo;
import com.kesholabs.mpesadashboard.repo.Kesho.Dashboard_UsersRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Dashboard_RolesRepo dashboard_rolesRepo;
    @Autowired
    private Dashboard_UsersRepo dashboard_usersRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    Date todaysDate = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    String formattedDate = formatter.format(todaysDate);

    public Dashboard_UsersEntity findUserByEmail(String email) {
        try {
            return dashboard_usersRepo.findByEmail(email);
        }catch (Exception ex){
            logger.error(ex.getMessage(), ex);
        }
        return null;
    }

    public void saveUser(Dashboard_UsersEntity user) {
        logger.info("Creating user \n"+user.toString());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Dashboard_RolesEntity userRole = dashboard_rolesRepo.findByRole(user.getDepartment());
        user.setRoles(new HashSet<Dashboard_RolesEntity>(Arrays.asList(userRole)));
        user.setDate(formattedDate);
        dashboard_usersRepo.save(user);
        logger.info("\n====== DONE CREATING USER ======\n"+user.toString());
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Dashboard_UsersEntity user = dashboard_usersRepo.findByEmail(email);
        if(user != null) {
            List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
            return buildUserForAuthentication(user, authorities);
        } else {
            throw new UsernameNotFoundException("username not found");
        }
    }

    private List<GrantedAuthority> getUserAuthority(Set<Dashboard_RolesEntity> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        userRoles.forEach((role) -> {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        });

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }

    private UserDetails buildUserForAuthentication(Dashboard_UsersEntity user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}
