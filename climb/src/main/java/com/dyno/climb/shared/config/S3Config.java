package com.dyno.climb.shared.config;

import com.dyno.climb.shared.properties.AwsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

@Configuration
public class S3Config {

  @Autowired private AwsProperties awsProperties;

  @Bean
  public S3Presigner s3Presigner() {
    return S3Presigner.builder()
        .region(Region.of(awsProperties.getS3().getRegion()))
        .credentialsProvider(
            StaticCredentialsProvider.create(
                AwsBasicCredentials.create(
                    awsProperties.getCredentials().getAccessKey(),
                    awsProperties.getCredentials().getSecretKey())))
        .build();
  }

  @Bean
  public S3Client s3Client() {
    return S3Client.builder()
        .region(Region.of(awsProperties.getS3().getRegion()))
        .credentialsProvider(
            StaticCredentialsProvider.create(
                AwsBasicCredentials.create(
                    awsProperties.getCredentials().getAccessKey(),
                    awsProperties.getCredentials().getSecretKey())))
        .build();
  }
}
