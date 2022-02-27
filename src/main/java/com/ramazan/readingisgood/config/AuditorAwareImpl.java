package com.ramazan.readingisgood.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;
import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return (Objects.nonNull(SecurityContextHolder.getContext().getAuthentication()))?Optional.of(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()):Optional.of("SYSTEM");
    }
}
