package co.zw.afrosoft.telone_gcp.service;

import co.zw.afrosoft.telone_gcp.persistence.GcpRepository;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import org.springframework.stereotype.Service;
import co.zw.afrosoft.telone_gcp.domain.Gcp;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class GcpServiceImpl implements GcpService {
    private final GcpRepository gcpRepository;
    private final Storage storage;

    public GcpServiceImpl(GcpRepository gcpRepository, Storage storage) {
        this.gcpRepository = gcpRepository;
        this.storage = storage;
    }

    @Override
    public List<Gcp> findAllFiles(){
        return gcpRepository.findAll();
    }

    @Override
    public Gcp findById(Long fileId){
        return gcpRepository.findById(fileId)
                .orElseThrow(() -> new NullPointerException("File not found"));
    }

    @Override
    public Gcp uploadFile(MultipartFile file) throws IOException {
        File fileName = new File(Objects.requireNonNull(file.getOriginalFilename()));
        File fileType = new File(Objects.requireNonNull(file.getContentType()));
        BlobId id = BlobId.of("my_aggr_files", String.valueOf(fileName));
        BlobInfo info = BlobInfo.newBuilder(id).build();
        storage.create(info, file.getBytes());
        Gcp gcp = new Gcp();
        gcp.setName(String.valueOf(fileName));
        gcp.setUrl(info.getMediaLink());
        gcp.setFileType(String.valueOf(fileType));
        return gcpRepository.save(gcp);
    }

    @Override
    public GcpDto updateFile(Long fileId, GcpUpdateRequest gcpUpdateRequest){
        return null;
    }

    @Override
    public void delete(Long fileId){
        gcpRepository.deleteById(fileId);
    }
}
