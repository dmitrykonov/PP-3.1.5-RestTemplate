package ru.dmitrykonov.resttemplate;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.dmitrykonov.resttemplate.entity.User;

public class Communication {
    private final String URL = "http://94.198.50.185:7081/api/users";
    private final RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders httpHeaders = new HttpHeaders();
    private final String cookie = getAllUsers();

    public String getAllUsers() {
        HttpEntity<String> httpEntity = restTemplate.exchange(URL, HttpMethod.GET, null, String.class);
        HttpHeaders headers = httpEntity.getHeaders();
        String cookie = headers.getFirst(headers.SET_COOKIE);
        return cookie;
    }
    public String saveUser() {
        User user = new User(3L,"James","Brown", (byte) 23);
        httpHeaders.add("Cookie", cookie);
        HttpEntity httpEntity = new HttpEntity(user, httpHeaders);
        ResponseEntity responseEntity = restTemplate.exchange(URL, HttpMethod.POST, httpEntity, String.class);
        String saveBody = (String) responseEntity.getBody();
        return saveBody;
    }
    public String updateUser() {
        String saveBody = saveUser();
        User user = new User(3L,"Thomas","Shelby", (byte) 23);
        httpHeaders.add("Cookie", cookie);
        HttpEntity httpEntity = new HttpEntity(user, httpHeaders);
        ResponseEntity responseEntity = restTemplate.exchange(URL, HttpMethod.PUT, httpEntity, String.class);
        String updateBody = (String) responseEntity.getBody();
        return saveBody + updateBody;
    }
    public String deleteUser() {
        String saveBody = saveUser();
        String updateBody = updateUser();
        httpHeaders.add("Cookie", cookie);
        HttpEntity httpEntity = new HttpEntity(null, httpHeaders);
        ResponseEntity responseEntity = restTemplate.exchange(URL+"/3", HttpMethod.DELETE, httpEntity, String.class);
        String deleteBody = (String) responseEntity.getBody();
        return saveBody + updateBody + deleteBody;
    }
}
