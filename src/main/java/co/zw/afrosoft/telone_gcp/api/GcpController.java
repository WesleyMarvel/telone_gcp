package co.zw.afrosoft.telone_gcp.api;

import co.zw.afrosoft.telone_gcp.domain.Gcp;
import co.zw.afrosoft.telone_gcp.service.GcpService;
import com.google.cloud.ReadChannel;
import com.google.cloud.storage.Storage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.ByteBuffer;

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
    public String getData() throws IOException{
        StringBuilder stringBuffer = new StringBuilder();
        try(ReadChannel readChannel = storage.reader("my_aggr_files", "champions.csv")){
            ByteBuffer byteBuffer = ByteBuffer.allocate(64*1024);
            while (readChannel.read(byteBuffer) > 0){
                byteBuffer.flip();
                String data = new String(byteBuffer.array(), 0, byteBuffer.limit());
                stringBuffer.append(data);
                byteBuffer.clear();
            }
            return "from cloud storage" +stringBuffer.toString();
        }
    }
}
