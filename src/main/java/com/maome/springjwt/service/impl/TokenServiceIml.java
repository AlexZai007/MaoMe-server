package com.maome.springjwt.service.impl;

import com.maome.springjwt.security.services.UserDetailsImpl;
import com.maome.springjwt.service.TokenService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;



@Service
public class TokenServiceIml implements TokenService {


    //Метод для
    public Long tokenToID(String token){
        String jwt = token.substring(7); // Убираем "Bearer " из токена
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            return userDetails.getId();
        }
        return null;
    }



}
