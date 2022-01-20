package br.com.eduardo.springsecurityjwt.repository;

import br.com.eduardo.springsecurityjwt.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author eduardoranzzani
 * @version 1.0
 * @since 19/01/2022
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String nome);
}
