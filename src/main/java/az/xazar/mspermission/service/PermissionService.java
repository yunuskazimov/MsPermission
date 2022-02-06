package az.xazar.mspermission.service;

import az.xazar.mspermission.model.PermissionDto;
import az.xazar.mspermission.model.Role;
import az.xazar.mspermission.model.RoleDeleteDto;
import az.xazar.mspermission.model.RolesAddDto;

import java.util.List;

public interface PermissionService {

    PermissionDto create(PermissionDto permissionDto);

    PermissionDto edit(PermissionDto permissionDto);

    String deleteAllByUserId(Long userId);

    String deleteByUserId(RoleDeleteDto roleDeleteDto);

    List<PermissionDto> getList();

    List<PermissionDto> getByUserId(Long userid);

    boolean checkRole(Long userId, String role);

    String addRoles(RolesAddDto rolesDto);
}
