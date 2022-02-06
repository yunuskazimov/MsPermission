package az.xazar.mspermission.client;

import az.xazar.mspermission.model.client.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class UserClientRest {
    private final RestTemplate restTemplate;
    private final String apiUrl;

    public UserClientRest(RestTemplate restTemplate
            , @Value("${client.users.int.url}") String apiUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = apiUrl;
    }

    public UserDto getById(Long id) {
        log.info("UserClientRest -> getById started with user Id : ,{}", id);
        String url = String.format("%s/%d", apiUrl, id);
        return restTemplate.getForObject(url, UserDto.class);
    }
}
