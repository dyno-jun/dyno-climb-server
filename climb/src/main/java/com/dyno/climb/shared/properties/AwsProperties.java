package com.dyno.climb.shared.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "aws")
@NoArgsConstructor
@Getter
@Setter
@Component
public class AwsProperties {

  private S3 s3;
  private Credentials credentials;

  // Getters and Setters

  @NoArgsConstructor
  @Getter
  @Setter
  public static class S3 {
    private String bucket;
    private String region;

    // Getters and Setters
  }

  @NoArgsConstructor
  @Getter
  @Setter
  public static class Credentials {
    private String accessKey;
    private String secretKey;

    // Getters and Setters

  }
}
