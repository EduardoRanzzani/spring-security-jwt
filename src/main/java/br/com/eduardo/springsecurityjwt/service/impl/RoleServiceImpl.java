package br.com.eduardo.springsecurityjwt.service.impl;

import br.com.eduardo.springsecurityjwt.model.Role;
import br.com.eduardo.springsecurityjwt.model.User;
import br.com.eduardo.springsecurityjwt.repository.RoleRepository;
import br.com.eduardo.springsecurityjwt.service.RoleService;
import br.com.eduardo.springsecurityjwt.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author eduardoranzzani
 * @version 1.0
 * @since 19/01/2022
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final UserService userService;
    private final RoleRepository repository;

    @Override
    public Role save(Role role) {
        log.info("Salvando permissao {}", role.getName());
        return repository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adicionando permissão {} ao usuário {} ", roleName, username);
        User user = userService.findByUsername(username);
        Role role = repository.findByName(roleName);
        user.getRoles().add(role);
    }
}
