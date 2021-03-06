package az.xazar.mspermission.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionDto {
    private Long id;
    private Long userId;
    private Role role;
}
