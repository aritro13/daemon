package com.trading.daemon.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;


@Service
public class AccessTokenService {

    @Value("${spring.security.oauth2.client.provider.upstox.token-uri}")
    private String access_token_uri;
    public String getAccessToken(String code, String state) throws JsonProcessingException {
        ResponseEntity<String> response = null;
        System.out.println("Authorization Code------" + code);

        RestTemplate restTemplate = new RestTemplate();

        // According OAuth documentation we need to send the client id and secret key in the header for authentication
        String credentials = "b1598960-e487-4dda-8798-0861840c9ec8:422o3evmyz";
        String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("Api-Version","2.0");
        headers.add( "Content-type","application/x-www-form-urlencoded");
//        headers.add("Authorization", "Basic " + encodedCredentials);

        HttpEntity<String> request = new HttpEntity<String>(headers);

//        String access_token_url = "https://api-v2.upstox.com/login/authorization/token";
        String access_token_url = access_token_uri;
        access_token_url += "?code=" + code;
        access_token_url += "&client_id=b1598960-e487-4dda-8798-0861840c9ec8";
        access_token_url += "&client_secret=422o3evmyz";
        access_token_url += "&grant_type=authorization_code";
        access_token_url += "&redirect_uri=http://127.0.0.1:8080/authorized";

        response = restTemplate.exchange(access_token_url, HttpMethod.POST, request, String.class);

        System.out.println("Access Token Response ---------" + response.getBody());

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(response.getBody());
        String accessToken = node.path("access_token").asText();

        return accessToken;
    }
}
