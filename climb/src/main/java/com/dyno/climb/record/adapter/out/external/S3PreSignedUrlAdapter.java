package com.dyno.climb.record.adapter.out.external;

import com.dyno.climb.record.application.port.PreSignedUrlPort;
import com.dyno.climb.shared.properties.AwsProperties;
import java.net.URL;
import java.time.Duration;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

@Component
public class S3PreSignedUrlAdapter implements PreSignedUrlPort {
  private final S3Presigner s3Presigner;
  private final S3Client s3Client;
  private final AwsProperties awsProperties;

  public S3PreSignedUrlAdapter(
      S3Presigner s3Presigner, S3Client s3Client, AwsProperties awsProperties) {
    this.s3Presigner = s3Presigner;
    this.s3Client = s3Client;
    this.awsProperties = awsProperties;
  }

  @Override
  public String generateUploadPreSignedUrl(String path) {
    PutObjectRequest putObjectRequest =
        PutObjectRequest.builder().bucket(awsProperties.getS3().getBucket()).key(path).build();

    PutObjectPresignRequest preSignRequest =
        PutObjectPresignRequest.builder()
            .putObjectRequest(putObjectRequest)
            .signatureDuration(Duration.ofMinutes(10)) // URL valid for 10 minutes
            .build();

    URL preSignedUrl = s3Presigner.presignPutObject(preSignRequest).url();

    return preSignedUrl.toString();
  }

  @Override
  public String generateDownloadPreSignedUrl(String path) {
    GetObjectRequest getObjectRequest =
        GetObjectRequest.builder().bucket(awsProperties.getS3().getBucket()).key(path).build();

    GetObjectPresignRequest preSignRequest =
        GetObjectPresignRequest.builder()
            .getObjectRequest(getObjectRequest)
            .signatureDuration(Duration.ofMinutes(10)) // URL valid for 10 minutes
            .build();

    URL preSignedUrl = s3Presigner.presignGetObject(preSignRequest).url();

    return preSignedUrl.toString();
  }

  @Override
  public boolean fileExists(String path) {
    try {
      s3Client.headObject(builder -> builder.bucket(awsProperties.getS3().getBucket()).key(path));
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
