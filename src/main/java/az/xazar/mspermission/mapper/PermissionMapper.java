package az.xazar.mspermission.mapper;

import az.xazar.mspermission.entity.PermissionEntity;
import az.xazar.mspermission.model.PermissionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PermissionMapper {

    PermissionMapper INSTANCE = Mappers.getMapper(PermissionMapper.class);

    PermissionDto toPermissionDto(PermissionEntity entity);

    List<PermissionDto> toPermissionDtoList(List<PermissionEntity> entities);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    PermissionEntity toPermissionEntity(PermissionDto dto);
}
