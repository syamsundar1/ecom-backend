package com.ecom.api_gateway.auth;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import io.jsonwebtoken.Jwts;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import java.security.Key;
import java.util.Objects;

@Component
public class AuthenticationFilter  extends AbstractGatewayFilterFactory<AuthenticationFilter>  {


    @Autowired
    private RouteValidator routeValidator;

    private final Key key;

    public AuthenticationFilter(@Value("${SECRET_KEY}") String jwtSecret) {
        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }


    @Override
    public GatewayFilter apply(AuthenticationFilter config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = null;
        if(routeValidator.isSecured.test(exchange.getRequest())){
            if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                throw new RuntimeException("Unauthenticated user");
            }

            String authHeader = Objects.requireNonNull(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION)).get(0);
            if(authHeader!=null && authHeader.startsWith("Bearer ")){
                authHeader = authHeader.substring(7);
            }
            try{

                Claims claims = Jwts.parser()
                        .setSigningKey(key)
                        .build()
                        .parseClaimsJws(authHeader)
                        .getBody();

                String username = claims.getSubject();

                request = exchange.getRequest().mutate().header("userDetails", username).build();

            }catch (Exception e){
                throw new RuntimeException("Unauthraized Expecption");
            }
        }
            return chain.filter(exchange.mutate().request(request).build());
        });
    }


}

