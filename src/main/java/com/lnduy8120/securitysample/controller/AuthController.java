package com.lnduy8120.securitysample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lnduy8120.securitysample.entity.UserEntity;
import com.lnduy8120.securitysample.payload.AuthRequest;
import com.lnduy8120.securitysample.payload.BaseResponse;
import com.lnduy8120.securitysample.payload.JwtResponse;
import com.lnduy8120.securitysample.repository.UserRepo;
import com.lnduy8120.securitysample.security.CustomUserDetails;
import com.lnduy8120.securitysample.security.JwtService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class AuthController {

	@Autowired
	private UserRepo UserRepo;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthRequest auth) {
		if (auth.getUsername() != null && auth.getPassword() != null) {
			UserEntity user = UserRepo.findByUsername(auth.getUsername());
			if (user != null) {
				UserDetails userDetails = new CustomUserDetails(user);
				Authentication authentication = authenticationManager
						.authenticate(new UsernamePasswordAuthenticationToken(auth.getUsername(), auth.getPassword()));
				SecurityContextHolder.getContext().setAuthentication(authentication);

				String token = jwtService.generateTokenFromUsername(userDetails.getUsername());

				return ResponseEntity.ok(new JwtResponse(200, "success", token));
			} else {
				return ResponseEntity.ok(new BaseResponse(400, "username invalid"));
			}
		}
		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/other")
	public ResponseEntity<?> getApi() {
		return ResponseEntity.ok(new BaseResponse(200, "ok"));
	}
}
