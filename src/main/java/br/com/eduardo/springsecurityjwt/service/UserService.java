package br.com.eduardo.springsecurityjwt.service;

import br.com.eduardo.springsecurityjwt.model.User;

import java.util.List;

/**
 * @author eduardoranzzani
 * @version 1.0
 * @since 19/01/2022
 */
public interface UserService {

    User save(User user);

    User findByUsername(String username);

    List<User> findAll();


}
