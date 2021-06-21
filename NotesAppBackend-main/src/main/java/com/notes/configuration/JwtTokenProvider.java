/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notes.configuration;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {
    
    private static String jwtSecret="TestSigmaKeepSecret";
    private static String jwtTokenPrefix="Bearer";
    private static String jwtHeader="Authorization";
    
    private static long jwtExpirationInMs=36000000;

    public JwtTokenProvider() {
        
    }

    public Authentication authenticateToken(HttpServletRequest request)
    {
        String token = this.getToken(request);
        if(token==null)
            return null;
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        String username = claims.getSubject();
       List<GrantedAuthority> authorities = Arrays.stream(claims.get("roles").toString().split(","))
                                            .map(role -> role.startsWith("ROLE_") ? role : "ROLE_" + role)
                                             .map(SimpleGrantedAuthority::new)
                                             .collect(Collectors.toList());
                
        if(username==null)
            return null;
        return new UsernamePasswordAuthenticationToken(username, null,authorities);
    }
    
    public String generateToken(Authentication authentication)
    {

       
        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining());
        System.out.println("----------------------------------------------------------");
         System.out.println(authorities);
         System.out.println("----------------------------------------------------------");
         
        return Jwts.builder().setSubject(authentication.getName())
                .claim("roles",authorities)
                .setExpiration(new Date(System.currentTimeMillis()+jwtExpirationInMs))
                .signWith(SignatureAlgorithm.HS512,jwtSecret).compact();
    }
    public String getToken(HttpServletRequest request)
    {
        String token = request.getHeader(jwtHeader);
        if(token==null)
            return null;
        return token.substring(7);
    }
    
    public boolean validateToken(HttpServletRequest request) 
    {
        String token = getToken(request);
        if (token == null) {
            return false;
        }
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        if (claims.getExpiration().before(new Date())) {
            return false;
        }
        return true;
    }
    


    
    
}
