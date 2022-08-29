package co.zw.afrosoft.telone_gcp.api;

import co.zw.afrosoft.telone_gcp.domain.Gcp;
import co.zw.afrosoft.telone_gcp.service.GcpService;
import com.google.cloud.storage.Storage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


@RestController
@RequestMapping("/gcp")
public class GcpController {
    private final Storage storage;
    private final GcpService gcpService;


    public GcpController(Storage storage, GcpService gcpService) {
        this.storage = storage;
        this.gcpService = gcpService;
    }

    @GetMapping("/post-data")
    public Gcp uploadFile(@RequestParam("file")MultipartFile file) throws IOException {
        return gcpService.uploadFile(file);
    }

    @GetMapping("/get-data")
    public String downloadFile(String fileName) throws IOException{
        return gcpService.downloadFile(fileName);
        }

}
