package de.shokhor.costs.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by user on 08.07.2017.
 */
public enum Role implements GrantedAuthority {
    ROLE_USER,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
