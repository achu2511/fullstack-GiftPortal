package com.example.demo.Config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import com.example.demo.utils.JwtUtil;
import org.springframework.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
@Component
@RequiredArgsConstructor
public class JwtAuthcconfig extends OncePerRequestFilter{
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
        @NonNull  HttpServletRequest request,
        @NonNull  HttpServletResponse response, 
        @NonNull  FilterChain filterChain)
        throws ServletException, IOException {
            
      final String authHeader=request.getHeader(AUTHORIZATION);      
      final String token;
      final String username;
      if (authHeader == null || !authHeader.startsWith("Bearer ")){
        filterChain.doFilter(request, response);
        return;
      }
      token=authHeader.substring(7);
      username= jwtUtil.extractUsername(token);

      if(username!=null &&SecurityContextHolder.getContext().getAuthentication()==null){
        UserDetails userDetails=this.userDetailsService.loadUserByUsername(username);
        if (jwtUtil.isTokenValid(token,userDetails)) {
          UsernamePasswordAuthenticationToken autoToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
          autoToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(autoToken);
        }
        filterChain.doFilter(request, response);
      }
            
    }

}
