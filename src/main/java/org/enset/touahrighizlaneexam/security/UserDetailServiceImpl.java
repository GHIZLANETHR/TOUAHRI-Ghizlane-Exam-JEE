package org.enset.touahrighizlaneexam.security;

import org.enset.touahrighizlaneexam.entities.AppUser;
import org.enset.touahrighizlaneexam.repositories.AppUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final AppUserRepository repo;
    public UserDetailServiceImpl(AppUserRepository repo) { this.repo = repo; }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = repo.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException("User not found");
        String[] roles = user.getRoles().stream()
                .map(r -> r.getRoleName())
                .toArray(String[]::new);
        return User.withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(roles)
                .build();
    }
}
