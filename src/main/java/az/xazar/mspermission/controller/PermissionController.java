package az.xazar.mspermission.controller;

import az.xazar.mspermission.model.PermissionDto;
import az.xazar.mspermission.model.RoleDeleteDto;
import az.xazar.mspermission.service.PermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/permission")
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
public class PermissionController {
    private final PermissionService permissionService;

    @PostMapping()
    public ResponseEntity<PermissionDto> savePermission(@RequestBody PermissionDto permissionDto) {
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/permission/save").toUriString());
        return ResponseEntity.created(uri).body(permissionService.create(permissionDto));
    }

    @PutMapping()
    public ResponseEntity<PermissionDto> editPermission(@RequestBody PermissionDto permissionDto) {
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/permission/edit").toUriString());
        return ResponseEntity.created(uri).body(permissionService.edit(permissionDto));
    }

    @GetMapping("/uid/{userId}")
    public List<PermissionDto> getByUserId(@PathVariable Long userId) {
        return permissionService.getByUserId(userId);
    }

    @GetMapping()
    public List<PermissionDto> getList() {
        return permissionService.getList();
    }

    @DeleteMapping("/all/uid/{userId}")
    public String deleteAllByUserId(@PathVariable Long userId) {
        return permissionService.deleteAllByUserId(userId);
    }

    @DeleteMapping("/uid/{userId}")
    public String deleteByUserId(@RequestBody RoleDeleteDto roleDeleteDto) {
        return permissionService.deleteByUserId(roleDeleteDto);
    }

}
