package iiot.istok.service;

import iiot.istok.dto.FileContentDto;
import iiot.istok.dto.FileInfoDto;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public interface UserFilesService {
    List<FileInfoDto> findFilesList(String userName, int limit);

    Optional<FileInfoDto> findFileInfo(String userName, String fileName);

    void deleteFile(String userName, String fileName) throws IOException;

    void renameFile(String userName, String oldFileName, String newFileName) throws IOException;

    FileContentDto openFile(String userName, String fileName) throws IOException;

    void saveFile(String userName, String fileName, String hash, InputStream inputStream) throws IOException;
}