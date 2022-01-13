package az.xazar.mspermission.service;

import az.xazar.mspermission.model.PermissionDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PermissionService {

    PermissionDto create(PermissionDto permissionDto);

    PermissionDto edit(Long id, PermissionDto permissionDto);

    String delete(Long id);

    PermissionDto getById(Long id);

    String getFileUrlById(Long id);

    List<PermissionDto> getList();

    List<PermissionDto> getByUserId(Long userid);
}
