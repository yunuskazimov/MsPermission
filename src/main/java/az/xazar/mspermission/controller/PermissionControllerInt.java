package az.xazar.mspermission.controller;

import az.xazar.mspermission.model.PermissionDto;
import az.xazar.mspermission.model.RolesAddDto;
import az.xazar.mspermission.service.PermissionService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/int/api/permission")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
public class PermissionControllerInt {
    private final PermissionService permissionService;

    @PostMapping("/add")
    public String addRole(@RequestBody RolesAddDto rolesDto) {
        return permissionService.addRoles(rolesDto);
    }

    @PostMapping()
    public boolean checkRoleByUserId(@RequestBody UserRoleForm form) {
        log.info("getByUserId internal start with id:{}, role:{}", form.getUserId(), form.getUserRole());
        return permissionService.checkRole(form.getUserId(), form.getUserRole());
    }

    @GetMapping("/uid/{userId}")
    public List<PermissionDto> getRoleListByUserId(@PathVariable Long userId) {
        log.info("getRoleListByUserId internal start with id:{}", userId);
        List<PermissionDto> list = permissionService.getByUserId(userId);
        return list;
    }

    @GetMapping()
    public List<PermissionDto> getList() {
        log.info("getList internal start");
        return permissionService.getList();
    }

    @GetMapping("/all/uid/{userId}")
    public String deleteAllByUserId(@PathVariable Long userId) {
        log.info("deleteAllByUserId start with user Id: {}", userId );
        return permissionService.deleteAllByUserId(userId);
    }


}

@Data
class UserRoleForm {
    private Long userId;
    private String userRole;
}