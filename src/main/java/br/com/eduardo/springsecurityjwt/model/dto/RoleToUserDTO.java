package br.com.eduardo.springsecurityjwt.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author eduardoranzzani
 * @version 1.0
 * @since 19/01/2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleToUserDTO {

    private String username;
    private String rolename;

}
