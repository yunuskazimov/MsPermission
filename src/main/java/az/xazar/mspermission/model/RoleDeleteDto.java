package az.xazar.mspermission.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDeleteDto {
    private Long userId;
    private Role role;
}
