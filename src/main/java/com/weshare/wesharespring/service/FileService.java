package com.weshare.wesharespring.service;

import com.weshare.wesharespring.common.client.AwsS3Client;
import com.weshare.wesharespring.common.constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileService {

    private static final String SPLITTER = "/";

    private AwsS3Client awsS3Client;

    @Autowired
    public FileService(final AwsS3Client awsS3Client) {
        this.awsS3Client = awsS3Client;
    }

    public void uploadProfileImage(final Long userId, final MultipartFile multipartFile)
        throws IOException {

        final String filePath = getProfileImagePath(userId);
        awsS3Client.uploadFile(Constant.S3_BUCKET_NAME, filePath, multipartFile);
    }

    public String getProfileImageUrl(final Long userId) {
        final String profileImagePath = getProfileImagePath(userId);
        return getS3FileUrl(Constant.S3_BUCKET_NAME, profileImagePath);
    }

    public String getS3FileUrl(final String bucketName, final String filePath) {
        return awsS3Client.getFileUrl(bucketName, filePath).toString();
    }

    public String getProfileImagePath(final Long userId) {
        return Constant.S3_USERS_FOLDER_NAME + SPLITTER + userId.toString() + SPLITTER +
            Constant.S3_USER_PROFILE_IMAGE_NAME;
    }

    public File multipartToFile(final MultipartFile multipartFile)
        throws IOException {

        final File file = new File(multipartFile.getOriginalFilename());
        multipartFile.transferTo(file);
        return file;
    }



}
