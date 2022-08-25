package com.lnduy8120.securitysample;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.lnduy8120.securitysample.entity.RoleEntity;
import com.lnduy8120.securitysample.entity.UserEntity;
import com.lnduy8120.securitysample.repository.RoleRepo;
import com.lnduy8120.securitysample.repository.UserRepo;

@SpringBootApplication
public class SecuritySampleApplication {

        @Autowired
        private UserRepo userRepo;
        @Autowired
        private RoleRepo roleRepo;
        @Autowired
        private BCryptPasswordEncoder passwordEncoder;
    
	public static void main(String[] args) {
		SpringApplication.run(SecuritySampleApplication.class, args);
	}

	@PostConstruct
	public void initData() {
		
	    List<RoleEntity> roles = roleRepo.findAll();
	    if (roles.isEmpty()) {
	        
                RoleEntity roleAdmin = new RoleEntity();
                roleAdmin.setName("ROLE_ADMIN");
                
                RoleEntity roleUser = new RoleEntity();
                roleUser.setName("ROLE_USER");
                
                RoleEntity roleCommon = new RoleEntity();
                roleCommon.setName("ROLE_COMMON");
                
                roleRepo.save(roleAdmin);
                roleRepo.save(roleUser);
                roleRepo.save(roleCommon);
            }
	    
	    
	    List<UserEntity> users = userRepo.findAll();
	    if (users.isEmpty()) {
	        UserEntity admin = new UserEntity();
	        admin.setUsername("admin");
	        admin.setPassword(passwordEncoder.encode("admin"));
	        List<RoleEntity> rolesAdmin = new ArrayList<RoleEntity>();
	        rolesAdmin.add(roleRepo.findByName("ROLE_ADMIN"));
	        rolesAdmin.add(roleRepo.findByName("ROLE_COMMON"));
	        admin.setRoles(rolesAdmin);
	        
	        UserEntity user = new UserEntity();
	        user.setUsername("user");
	        user.setPassword(passwordEncoder.encode("8120"));
	        List<RoleEntity> rolesUser = new ArrayList<RoleEntity>();
	        rolesUser.add(roleRepo.findByName("ROLE_USER"));
	        rolesUser.add(roleRepo.findByName("ROLE_COMMON"));
	        user.setRoles(rolesUser);
	        
	        userRepo.save(admin);
	        userRepo.save(user);
                
            }
	}
}