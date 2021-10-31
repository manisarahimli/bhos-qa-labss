package com.example.springproj4;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;


class Springproj4ApplicationTests {

    String url = "https://api.nytimes.com/svc/books/v3/lists.json?list=Combined%20Print%20and%20E-Book%20Nonfiction&api-key=oihqSeLTV9uNQ8TabGZsjecWLPAr3ZsO";

    TestRestTemplate testRestTemplate = new TestRestTemplate();

    HttpHeaders httpHeaders = new HttpHeaders();

    @Test
    public void IntegrationTest() throws JSONException {

        HttpEntity<String> bnch;

        bnch = new HttpEntity<>(null, httpHeaders);

        ResponseEntity<String> res;

        res = testRestTemplate.exchange(url, HttpMethod.GET, bnch, String.class);

        JSONObject jsonObject = new JSONObject(res.getBody());

        JSONArray bookApiArray;

        bookApiArray = (JSONArray) jsonObject.get("results");

        boolean result;

        int a=1;

        result= true;

        for (int i=0; i < bookApiArray.length(); i++) {

            JSONObject book = (JSONObject) bookApiArray.get(i);

            Integer b = (Integer) book.get("da");

            JSONArray bookInfo = (JSONArray) book.get("info");

            JSONObject finalProd = (JSONObject) bookInfo.get(0);

            String desc = finalProd.getString("desc");

            if (desc.isEmpty() || b != a + 1) {

                result = false;

                break;

            }
            a = b;

        }
        assertTrue(result);
    }

}
