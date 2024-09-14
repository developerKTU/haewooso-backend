package com.ktu.haewooso_ver2.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

import java.util.*;

@Configuration
public class FCMConfig {

//    @Bean
//    FirebaseMessaging firebaseMessaging() throws IOException {
//        // Local
//        //ClassPathResource resource = new ClassPathResource("firebase/haewooso-firebase-adminsdk-r1plg-3e9c10ed08.json");
//
//        // 운영
//        ClassPathResource resource = new ClassPathResource("/app/build/classes/java/main/com/ktu/haewooso_ver2/firebase/haewooso-firebase-adminsdk-r1plg-3e9c10ed08.json");
//
//        InputStream refreshToken = resource.getInputStream();
//
//        FirebaseApp firebaseApp = null;
//        List<FirebaseApp> firebaseAppList = FirebaseApp.getApps();
//
//        if(firebaseAppList != null && !firebaseAppList.isEmpty()){
//            for(FirebaseApp app : firebaseAppList){
//                if(app.getName().equals(FirebaseApp.DEFAULT_APP_NAME)){
//                    firebaseApp = app;
//                }
//            }
//        }else{
//            FirebaseOptions options = FirebaseOptions.builder()
//                    .setCredentials(GoogleCredentials.fromStream(refreshToken))
//                    .build();
//
//            firebaseApp = FirebaseApp.initializeApp(options);
//        }
//
//        return FirebaseMessaging.getInstance(firebaseApp);
//    }
}
