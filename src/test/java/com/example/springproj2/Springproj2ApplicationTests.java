package com.example.springproj2;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Springproj2ApplicationTests {
    public String url = "https://60a21d3f745cd70017576092.mockapi.io/api/v1/repos";

    @Test
    @DisplayName("Integration test to check the SSL certificate of the URL is valid with the hosted certificate")
    public void firstTest() throws IOException{
        try {
            URL https_url = new URL(url);
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) https_url.openConnection();
            httpsURLConnection.connect();
            Certificate[] certificates = httpsURLConnection.getServerCertificates();
            Certificate certs = certificates[0];

            CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
            FileInputStream inputStream  =  new FileInputStream ("certificate.cer");
            X509Certificate lCertificate = (X509Certificate) certFactory.generateCertificate(inputStream);
            assertEquals(lCertificate, certs);
        } catch (IOException | CertificateException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName(" integration test to check the response JSON of the URL (4.a) is a list, and item parameters (id, name) is unique")

    public void secondTest() throws IOException, JSONException {
        Set<String> idSet = new HashSet<>();
        Set<String> nameSet = new HashSet<>();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        JSONArray repositories = new JSONArray(response.getBody());

        for (int i=0; i < repositories.length(); i++) {
            JSONObject a = (JSONObject) repositories.get(i);
            idSet.add(a.getString("id"));
            nameSet.add(a.getString("name"));
        }
        boolean unique = idSet.size() == repositories.length() && nameSet.size() == repositories.length();
        assertTrue(unique);
    }
}
