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
    private String cookie = "";
    private HttpHeaders httpHeader = new HttpHeaders();

    public Communication(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void AllUsers(){
        ResponseEntity<String> response = restTemplate.getForEntity(BaseUrl, String.class);
        System.out.println("Response = " + response);
        // get cookie
        cookie = response.getHeaders().getFirst("Set-Cookie");
        System.out.println("cookie = " + cookie);
        httpHeader.add("cookie", cookie);
    }
    public void saveUser(User user){
        HttpEntity<User> entity = new HttpEntity<>(user, httpHeader);
        ResponseEntity<String> response = restTemplate.postForEntity(BaseUrl, entity, String.class);
        System.out.println("Response = " + response);
        System.out.println("Response2 = " + restTemplate.exchange(BaseUrl, HttpMethod.POST, entity, String.class).getBody());
    }

    public void updateUser(User user) {
        HttpEntity<User> entity = new HttpEntity<>(user, httpHeader);
        ResponseEntity<String> response = restTemplate.getForEntity(BaseUrl, String.class);
        System.out.println("Response = " + response);
        System.out.println("Response2 = " + restTemplate.exchange(BaseUrl, HttpMethod.PUT, entity, String.class).getBody());
    }

    public void deleteUser(User user) {
        HttpEntity<User> entity = new HttpEntity<>(httpHeader);
        ResponseEntity<String> response = restTemplate.getForEntity(BaseUrl, String.class);
        System.out.println("Response = " + response);
        System.out.println("Response2 = " + restTemplate.exchange(BaseUrl + "/" + user.getId(), HttpMethod.DELETE, entity, String.class).getBody());
    }
}