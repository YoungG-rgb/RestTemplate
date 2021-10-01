package com.example.demo;

import com.example.demo.models.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Communication {

    private final RestTemplate restTemplate;
    private final String BaseUrl = "http://91.241.64.178:7081/api/users";
    private final HttpHeaders httpHeader = new HttpHeaders();

    public Communication(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void AllUsers(){
        ResponseEntity<String> response = restTemplate.getForEntity(BaseUrl, String.class);
        System.out.println("Response = " + response);
        // get cookie
        String cookie = response.getHeaders().getFirst("Set-Cookie");
        System.out.println("cookie = " + cookie);
        httpHeader.add("cookie", cookie);
    }
    public void saveUser(User user){
        System.out.println("Response2 = " + restTemplate.exchange(BaseUrl,
                HttpMethod.POST, new HttpEntity<>(user, httpHeader), String.class).getBody());
    }

    public void updateUser(User user) {
        System.out.println("Response2 = " + restTemplate.exchange(BaseUrl, HttpMethod.PUT,
                new HttpEntity<>(user, httpHeader), String.class).getBody());
    }

    public void deleteUser(User user) {
        System.out.println("Response2 = " + restTemplate.exchange(BaseUrl + "/" + user.getId(),
                HttpMethod.DELETE, new HttpEntity<>(user, httpHeader), String.class).getBody());
    }
}