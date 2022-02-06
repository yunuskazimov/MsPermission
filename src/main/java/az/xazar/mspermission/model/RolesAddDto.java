package az.xazar.mspermission.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolesAddDto {
    private Long userId;
    private Set<Role> role;
}
