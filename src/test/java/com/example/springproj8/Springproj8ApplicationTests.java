package com.example.springproj8;

import com.nimbusds.srp6.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Springproj8ApplicationTests {

    static String userID = "manisa";
    static String password = "password12345";
    static BigInteger salt = new BigInteger("6327562746215837");

    @LocalServerPort
    private int port;
    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    public void verifierGeneratorUtilityFunc() {
        SRP6CryptoParams config = SRP6CryptoParams.getInstance();
        SRP6VerifierGenerator gen = new SRP6VerifierGenerator(config);
        BigInteger verifier = gen.generateVerifier(salt, userID, password);
        System.out.println(verifier);
    }

    @Test
    public void SRPAuthenticationTest() throws SRP6Exception {
        SRP6ClientSession Client = new SRP6ClientSession();
        Client.step1(userID, password);
        HttpEntity<String> newSesEntity = new HttpEntity<>(userID, headers);
        String newSesResponse = restTemplate.exchange("http://localhost:" + port + "/new_session",
                HttpMethod.POST, newSesEntity, String.class).getBody();
        BigInteger serverPublicB = new BigInteger(newSesResponse);
        SRP6CryptoParams defaultCryptoParams = SRP6CryptoParams.getInstance();
        SRP6ClientCredentials clientPublicAEvidenceM1 = Client.step2(defaultCryptoParams, salt, serverPublicB);
        CompValReqBody compValBody = new CompValReqBody(clientPublicAEvidenceM1.A, clientPublicAEvidenceM1.M1);
        HttpEntity<CompValReqBody> compValEntity = new HttpEntity<>(compValBody, headers);
        String serverEvidenceM2 = restTemplate.postForEntity("http://localhost:" + port + "/compute_values",
                compValEntity, String.class).getBody();
        assertNotEquals("", serverEvidenceM2);
    }

}
