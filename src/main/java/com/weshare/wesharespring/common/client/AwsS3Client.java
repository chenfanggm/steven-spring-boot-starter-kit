package com.weshare.wesharespring.common.client;


import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class AwsS3Client {

    private final static Logger logger = LoggerFactory.getLogger(AwsS3Client.class);

    private static final String SUFFIX = "/";

    private final AmazonS3Client s3Client;

    public AwsS3Client() {
        logger.info("Connecting to AWS S3...");
        final BasicAWSCredentials awsCredentials = new BasicAWSCredentials("", "");
        final ClientConfiguration clientConfig = new ClientConfiguration();
        clientConfig.setProtocol(Protocol.HTTP);
        this.s3Client = new AmazonS3Client(awsCredentials, clientConfig);
        logger.info("Successfully connected to AWS S3...");
    }

    public List<Bucket> getBucketList() {
        return this.s3Client.listBuckets();
    }

    public ObjectListing getBucketObjectListing(final String bucketName) {
        return this.s3Client.listObjects(bucketName);
    }

    public S3Object getObject(final String bucketName, final String filePath) {
        final GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, filePath);
        return this.s3Client.getObject(getObjectRequest);
    }

    public URL getFileUrl(final String bucketName, final String filePath) {
        logger.info("<In> getFileUrl(): for bucket: {} and path: {}", bucketName, filePath);
        return this.s3Client.getUrl(bucketName, filePath);
    }

    public void createFolder(final String bucketName, final String folderName) {
        final ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(0);

        final InputStream emptyContent = new ByteArrayInputStream(new byte[0]);

        final PutObjectRequest putObjectRequest = new PutObjectRequest(
            bucketName, folderName + SUFFIX, emptyContent, metadata);

        this.s3Client.putObject(putObjectRequest);
    }

    public void uploadFile(final String bucketName, final String filePath, final File file) {

        logger.info("<In> uploadFile(): for bucket: {} and path: {}", bucketName, filePath);
        final PutObjectRequest putObjectRequest = new PutObjectRequest(
            bucketName, filePath, file)
            .withCannedAcl(CannedAccessControlList.PublicRead);

        this.s3Client.putObject(putObjectRequest);
    }

    public void uploadFile(final String bucketName, final String filePath, final MultipartFile multipartFile)
        throws IOException {

        logger.info("<In> uploadFile(): for bucket: {} and path: {}", bucketName, filePath);
        final ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getSize());
        objectMetadata.setContentType(multipartFile.getContentType());

        final PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, filePath, multipartFile.getInputStream(), objectMetadata)
            .withCannedAcl(CannedAccessControlList.PublicRead);

        this.s3Client.putObject(putObjectRequest);
    }

    public void deleteFile(final String bucketName, final String filePath) {
        this.s3Client.deleteObject(bucketName, filePath);
    }

    public void updateFileACL(final String bucketName, final String filePath, final CannedAccessControlList aclStatus) {
        this.s3Client.setObjectAcl(bucketName, filePath, aclStatus);
    }

}
