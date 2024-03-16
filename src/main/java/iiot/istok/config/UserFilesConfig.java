package iiot.istok.config;

import iiot.istok.repository.FileInfoRepository;
import iiot.istok.repository.UserRepository;
import iiot.istok.service.FileContentStorage;
import iiot.istok.service.UserFilesService;
import iiot.istok.service.impl.FileContentStorageFileSystemImpl;
import iiot.istok.service.impl.UserFilesServiceJpaImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.io.IOException;
import java.nio.file.Path;

@Configuration
public class UserFilesConfig {

    @Value("${application.userfiles.root-path}")
    String userFilesRoot;

    @Bean
    public FileContentStorage fileContentStorage() throws IOException {
        return new FileContentStorageFileSystemImpl(Path.of(userFilesRoot));
    }

    @Bean
    public UserFilesService userFilesService(
            UserRepository userRepository,
            FileInfoRepository fileInfoRepository
    ) throws IOException {
        return new UserFilesServiceJpaImpl(userRepository, fileInfoRepository, fileContentStorage());
    }

}