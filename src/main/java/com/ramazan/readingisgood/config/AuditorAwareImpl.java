package com.ramazan.readingisgood.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Objects;
import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user=null;

        if (Objects.nonNull(authentication)){
            user= (User) authentication.getPrincipal();
        }

        return (user!=null&&user.getUsername()!=null)?Optional.of(user.getUsername()):Optional.of("SYSTEM");
    }
}
