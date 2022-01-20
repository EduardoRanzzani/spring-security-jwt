package br.com.eduardo.springsecurityjwt.service;

import br.com.eduardo.springsecurityjwt.model.Role;

/**
 * @author eduardoranzzani
 * @version 1.0
 * @since 19/01/2022
 */
public interface RoleService {
    Role save(Role role);

    void addRoleToUser(String username, String roleName);
}
