package group5.webapp.FinalProjectOOP.controllers;

import group5.webapp.FinalProjectOOP.services.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/uploadfile")
public class UploadFileAPI {

    @Autowired
    UploadFileService uploadFileService;

    @GetMapping(value = "/upload/{file}")
    public ResponseEntity<byte[]> readDetailFile(@PathVariable String file){
        try {
            byte[] bytes = uploadFileService.readFileContent(file);
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(bytes);
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }
}
