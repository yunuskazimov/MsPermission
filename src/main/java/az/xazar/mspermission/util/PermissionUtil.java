package az.xazar.mspermission.util;

import az.xazar.mspermission.entity.PermissionEntity;
import az.xazar.mspermission.error.ErrorCodes;
import az.xazar.mspermission.exception.PermissionNotFoundException;
import az.xazar.mspermission.repo.PermissionRepo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PermissionUtil {
    private final PermissionRepo repo;

    public PermissionUtil(PermissionRepo repo) {
        this.repo = repo;
    }

    public PermissionEntity findById(Long id){
        return repo.findById(id)
                .orElseThrow(()->
                        new PermissionNotFoundException(ErrorCodes.NOT_FOUND));
    }

    public List<PermissionEntity> findByUserId(Long userId) {
        return repo.findAllByUserId(userId)
                .orElseThrow(() ->
                        new PermissionNotFoundException(ErrorCodes.NOT_FOUND));
    }
}
