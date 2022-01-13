package az.xazar.mspermission.repo;

import az.xazar.mspermission.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PermissionRepo extends JpaRepository<PermissionEntity,Long> {
    Optional<List<PermissionEntity>> findAllByUserId(Long userId);

}
