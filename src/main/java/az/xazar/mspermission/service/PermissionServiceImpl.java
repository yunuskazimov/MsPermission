package az.xazar.mspermission.service;

import az.xazar.mspermission.client.UserClientRest;
import az.xazar.mspermission.entity.PermissionEntity;
import az.xazar.mspermission.exception.PermissionNotFoundException;
import az.xazar.mspermission.mapper.PermissionMapper;
import az.xazar.mspermission.model.PermissionDto;
import az.xazar.mspermission.model.RoleDeleteDto;
import az.xazar.mspermission.model.RolesAddDto;
import az.xazar.mspermission.repo.PermissionRepo;
import az.xazar.mspermission.util.PermissionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepo permissionRepo;
    private final PermissionUtil permissionUtil;
    private final PermissionMapper permissionMapper;
    private final UserClientRest userClient;

    @Override
    public PermissionDto create(PermissionDto permissionDto) {
        PermissionEntity entity = permissionRepo
                .save(permissionMapper.toPermissionEntity(permissionDto));
        permissionDto.setId(entity.getId());
        return permissionDto;
    }

    @Override
    public PermissionDto edit(PermissionDto permissionDto) {
        if (permissionUtil.findAllByUserId(permissionDto.getUserId()).isEmpty()) {
            throw new PermissionNotFoundException("Permission Not Found By this User Id");
        }
        permissionRepo.save(permissionMapper.toPermissionEntity(permissionDto));
        return permissionDto;
    }

    @Override
    public String deleteAllByUserId(Long userId) {
        log.info("deleteAllByUserId start with userId : {}", userId);

        List<PermissionEntity> entityList = permissionUtil.findAllByUserId(userId);
        if (entityList.isEmpty()) {
            throw new PermissionNotFoundException("Permission Not Found By this User Id");
        } else {
            entityList.forEach(permissionEntity ->
                    permissionRepo.deleteById(permissionEntity.getId()));
            log.info("deleteAllByUserId completed with userId : {}", userId);
            return "Role is Deleted.";
        }
    }

    @Override
    public String deleteByUserId(RoleDeleteDto roleDeleteDto) {
        log.info("deleteByUserId start with role : {}", roleDeleteDto);

        List<PermissionEntity> entityList =
                permissionUtil.findAllByUserId(roleDeleteDto.getUserId());
        if (entityList.isEmpty()) {
            throw new PermissionNotFoundException("Permission Not Found By this User Id");
        } else {
            log.info("deleteByUserId continues with role list : {}", entityList);
            entityList
                    .stream()
                    .filter(permissionEntity ->
                            permissionEntity
                                    .getRole()
                                    .name()
                                    .equals(roleDeleteDto.getRole().name()))
                    .forEach(e -> {
                        log.info("test");
                        permissionRepo.deleteById(e.getId());
                        log.info("deleteAllByUserId completed with userId : {}",
                                roleDeleteDto.getUserId());
                    });
            return "Role not Found By this user Id. " + roleDeleteDto.getUserId();
        }
    }


    @Override
    public List<PermissionDto> getList() {
        return permissionMapper.toPermissionDtoList(permissionRepo.findAll());
    }

    @Override
    public List<PermissionDto> getByUserId(Long userId) {
        List<PermissionEntity> entityList = permissionUtil.findAllByUserId(userId);
        if (entityList.isEmpty()) {
            throw new PermissionNotFoundException("Permission Not Found By this User Id");
        } else {
            return permissionMapper.toPermissionDtoList(entityList);
        }
    }

    @Override
    public boolean checkRole(Long userId, String userRole) {
        //TODO alqoritmi deyismek. db ile islemek. accessleri sonradan elave etmek olsun

        userClient.getById(userId);
        List<PermissionEntity> entityList = permissionUtil.findAllByUserId(userId);
        if (entityList.isEmpty()) {
            return false;
        } else {
            for (PermissionEntity permission : entityList) {
                String role = permission.getRole().name();
                if ((userRole.equals("USER")) && ((role.equals("ADMIN"))
                        || (role.equals("MANAGER")) || (role.equals("REPORTER")))) {
                    return true;
                }

                //Bu menitiqi is yerine gore deyismek lazimdir.
                if (role.equals("SUPER")) {
                    return true;
                }

                if (role.equals(userRole)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String addRoles(RolesAddDto rolesDto) {
        rolesDto.getRole().forEach(role -> {
            log.info("role name is{}", role.name());
            if (!checkRole(rolesDto.getUserId(), role.name())) {
                PermissionDto permissionDto = new PermissionDto();
                permissionDto.setUserId(rolesDto.getUserId());
                permissionDto.setRole(role);
                log.info("permissionDto is{}", create(permissionDto));
            }
        });
        return "Role is added";
    }


}
