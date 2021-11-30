package com.example.springproj7;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class FirebaseTest {
    @Test
    public void test() throws IOException, InterruptedException, JSONException {

        String responseBody = authenticate();
        JSONObject resBody = new JSONObject(responseBody);
        String idToken = resBody.getString("idToken");
        String userId = resBody.getString("localId");

        setAvatar(idToken, "", userId);

        String userDetailsResult = getUserDetails(userId, idToken);
        String avatarReference = new JSONObject(userDetailsResult).getJSONObject("fields").getJSONObject("avatar").getString("stringValue");
        if (!avatarReference.isEmpty()) {
            fail();
        }

        idToken = "";

        String userDetailsResultAfterLogout = getUserDetails(userId, idToken);

        assertEquals("Forbidden", userDetailsResultAfterLogout);
    }

    public String getUserDetails(String userId, String idToken) throws IOException {
        String url = String.format("%s/%s", ApiUrlConstants.FIRESTORE_DOCUMENT, userId);
        return HTTPUtil.get(url, idToken);
    }

    public void setAvatar(String idToken, String fileReference, String userId) throws IOException, InterruptedException, JSONException {
        String url = String.format("%s/%s?updateMask.fieldPaths=avatar", ApiUrlConstants.FIRESTORE_DOCUMENT, userId);
        JSONObject data = new JSONObject();
        JSONObject fieldsObject = new JSONObject();
        JSONObject avatarObject = new JSONObject();
        avatarObject.put("stringValue", fileReference);
        fieldsObject.put("avatar", avatarObject);
        data.put("fields", fieldsObject);

        HTTPUtil.patch(url, idToken, data.toString());
    }

    public String authenticate() throws IOException, InterruptedException, JSONException {
        JSONObject data = new JSONObject();
        data.put("email", System.getenv("FIREBASE_EMAIL"));
        data.put("password", System.getenv("FIREBASE_PASSWORD"));
        data.put("returnSecureToken", "true");
        return HTTPUtil.post(ApiUrlConstants.FIREBASE_SIGNIN_EMAIL_AND_PASSWORD.concat(System.getenv("FIREBASE_WEB_API_KEY")), data);
    }
}

