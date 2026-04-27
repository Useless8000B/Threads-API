package com.useless.threads_api.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Configuration
public class FirebaseConfig {

	@Bean
	public Firestore firestore() throws IOException {
		if (FirebaseApp.getApps().isEmpty()) {
			String env = System.getenv("FIREBASE_CONFIG");
			InputStream serviceAccount;

			if (env != null && !env.isEmpty()) {
				serviceAccount = new ByteArrayInputStream(env.getBytes(StandardCharsets.UTF_8));
			} else {
				serviceAccount = new ClassPathResource("serviceAccountKey").getInputStream();
			}

			FirebaseOptions options = FirebaseOptions.builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.build();

			FirebaseApp.initializeApp(options);
		}

		return FirestoreClient.getFirestore();
	}

	@Bean
	public FirebaseAuth firebaseAuth() throws IOException {
		return FirebaseAuth.getInstance();
	}
}
