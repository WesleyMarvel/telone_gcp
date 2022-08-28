package co.zw.afrosoft.telone_gcp.service;

import co.zw.afrosoft.telone_gcp.domain.Gcp;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface GcpService {
    List<Gcp> findAllFiles();

    Gcp findById(Long fileId);

    Gcp uploadFile(MultipartFile file) throws IOException;

    String downloadFile (String fileName) throws IOException;

    GcpDto updateFile(Long fileId, GcpUpdateRequest gcpUpdateRequest);

    void delete(Long fileId);
}
