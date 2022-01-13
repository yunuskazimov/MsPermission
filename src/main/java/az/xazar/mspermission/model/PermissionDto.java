package az.xazar.mspermission.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionDto {
    Long id;
    Long userId;
    PermissionEnum permission;
}
