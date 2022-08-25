package com.innovation.team7_carrot_clone.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.innovation.team7_carrot_clone.dto.S3RequestDto;
import com.innovation.team7_carrot_clone.model.User;
import com.innovation.team7_carrot_clone.repository.PostRepository;
import com.innovation.team7_carrot_clone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class S3Service {
    private final AmazonS3Client amazonS3Client;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public S3RequestDto S3UserImageUpload(MultipartFile file, Long userId, String username) throws IOException {
        User userFoundById = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저는 존재하지 않습니다."));

        if (userFoundById.getUsername().equals(username)) {
            File uploadFile = convert(file).orElseThrow(() -> new IllegalArgumentException("파일 업로드에 실패하였습니다."));
            String dir = "static/images/".concat(String.valueOf(userId));
            return upload(uploadFile, dir);
        }
        return null;
    }

    private S3RequestDto upload(File uploadFile, String dir) {
        String sourceName = uploadFile.getName();
        String sourceExt = FilenameUtils.getExtension(sourceName).toLowerCase();

        String fileName = dir + "/" + LocalDateTime.now().toString().concat(".").concat(sourceExt);
        String uploadImageUrl = putS3(uploadFile, fileName);
        removeNewFile(uploadFile);

        return S3RequestDto.builder()
                .imageUrl(uploadImageUrl)
                .build();
    }

    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("파일이 삭제되었습니다.");
        } else {
            log.info("파일이 삭제되지 못했습니다.");
        }
    }

    private String putS3(File uploadFile, String fileName) {
        try {
            amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (Exception e) {
            log.error("이미지 s3 업로드 실패");
            log.error(e.getMessage());
            removeNewFile(uploadFile);
            throw new RuntimeException();
        }

        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    public String uploadFile(MultipartFile file) throws IllegalArgumentException {
        String fileName = UUID.randomUUID() + "-" + Objects.requireNonNull(file.getOriginalFilename()).toLowerCase();
        try {
            if (!(fileName.endsWith(".bmp") || fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png"))) {
                throw new IllegalArgumentException("bmp,jpg,jpeg,png 형식의 이미지 파일이 요구됨.");
            }
            amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, file.getInputStream(), null)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            throw new RuntimeException("S3 파일 업로드 실패.");
        }
        return amazonS3Client.getUrl(bucket, fileName).toString();    ///url string 리턴
    }


    private Optional<File> convert(MultipartFile file) throws IOException {
        File convertFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        if (convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }
        return Optional.empty();
    }
}


