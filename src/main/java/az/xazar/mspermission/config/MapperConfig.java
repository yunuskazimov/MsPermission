package az.xazar.mspermission.config;

import az.xazar.mspermission.mapper.PermissionMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public PermissionMapper permissionMapper() {
        return PermissionMapper.INSTANCE;
    }

}

