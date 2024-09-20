package com.ktu.haewooso_ver2.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Configuration
@Component
public class FCMConfig {

    // 운영
    @Value("${FCMSDK}")
    private String FCMSDK;

    @Bean
    FirebaseMessaging firebaseMessaging() throws IOException {
        // Local
        //ClassPathResource resource = new ClassPathResource("firebase/haewooso-firebase-adminsdk-r1plg-b5fdea6348.json");
        //InputStream refreshToken = resource.getInputStream();

        // 운영
        InputStream refreshToken = new ByteArrayInputStream(FCMSDK.getBytes());

        FirebaseApp firebaseApp = null;
        List<FirebaseApp> firebaseAppList = FirebaseApp.getApps();

        if(firebaseAppList != null && !firebaseAppList.isEmpty()){
            for(FirebaseApp app : firebaseAppList){
                if(app.getName().equals(FirebaseApp.DEFAULT_APP_NAME)){
                    firebaseApp = app;
                }
            }
        }else{
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(refreshToken))
                    .build();

            firebaseApp = FirebaseApp.initializeApp(options);
        }

        return FirebaseMessaging.getInstance(firebaseApp);
    }
}
