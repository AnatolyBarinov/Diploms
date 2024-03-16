package iiot.istok.repository;

import iiot.istok.entity.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {

    List<FileInfo> findAllByOwnerUsernameAndFilenameAndDeletedAtIsNull(String username, String filename);

    Optional<FileInfo> findFirstByOwnerUsernameAndFilenameAndDeletedAtIsNull(String username, String filename);

    List<FileInfo> findAllByOwnerUsername(String username);

    List<FileInfo> findAllByOwnerUsernameAndDeletedAtIsNull(String username);

}