package br.com.eduardo.springsecurityjwt.service.impl;

import br.com.eduardo.springsecurityjwt.model.User;
import br.com.eduardo.springsecurityjwt.repository.UserRepository;
import br.com.eduardo.springsecurityjwt.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author eduardoranzzani
 * @version 1.0
 * @since 19/01/2022
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        if (user == null) {
            log.error("Usuário não encontrado");
            throw new UsernameNotFoundException("Usuário não encontrado");
        } else {
            log.info("Usuário {} encontrado", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }


    @Override
    public User save(User user) {
        log.info("Salvando usuário {} ", user.getUsername());
        user.setPassword(encoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        log.info("Buscando usuário {} ", username);
        return repository.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        log.info("Buscando todos os usuários");
        return repository.findAll();
    }

}
