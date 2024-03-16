package iiot.istok.controllers;

import iiot.istok.config.converter.FileContentDtoResponseEntityConverter;
import iiot.istok.dto.FileInfoDto;
import iiot.istok.request.RenameRequest;
import iiot.istok.validation.ValidFileName;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.List;
import iiot.istok.service.UserFilesService;
import iiot.istok.dto.FileContentDto;
@Validated
@RestController
@RequiredArgsConstructor
public class UserFilesController {
    private static final String DEFAULT_FILES_LIST_LIMIT = "100";

    private final UserFilesService userFilesService;
    private final FileContentDtoResponseEntityConverter fileContentDtoResponseEntityConverter;

    @GetMapping("/list")
    public List<FileInfoDto> getFilesList(
            Principal principal,
            @Positive @RequestParam(name = "limit", defaultValue = DEFAULT_FILES_LIST_LIMIT) int limit
    ) throws IOException {
        return userFilesService.findFilesList(principal.getName(), limit);
    }

    @DeleteMapping("/fil" +
            "e")
    public void deleteFile(
            Principal principal,
            @ValidFileName @RequestParam String filename
    ) throws IOException {
        userFilesService.deleteFile(principal.getName(), filename);
    }

    @PutMapping("/file")
    public void renameFile(
            Principal principal,
            @ValidFileName @RequestParam String filename,
            @Valid @RequestBody RenameRequest renameRequest
    ) throws IOException {
        userFilesService.renameFile(principal.getName(), filename, renameRequest.getFilename());
    }

    @GetMapping(value = "/file")
    public ResponseEntity<StreamingResponseBody> downloadFile(
            Principal principal,
            @ValidFileName @RequestParam String filename
    ) throws IOException {
        FileContentDto fileContentDto = userFilesService.openFile(principal.getName(), filename);
        return fileContentDtoResponseEntityConverter.from(fileContentDto);
    }

    @PostMapping(value = "/file")
    public void uploadFile(
            Principal principal,
            @ValidFileName @RequestParam String filename,
            @RequestPart MultipartFile file,
            @RequestParam(defaultValue = "") String hash
    ) throws IOException {
        try (InputStream inputStream = file.getInputStream()) {
            userFilesService.saveFile(principal.getName(), filename, hash, inputStream);
        }
    }

}