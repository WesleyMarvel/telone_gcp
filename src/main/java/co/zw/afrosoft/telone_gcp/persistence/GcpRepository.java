package co.zw.afrosoft.telone_gcp.persistence;

import co.zw.afrosoft.telone_gcp.domain.Gcp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GcpRepository extends JpaRepository<Gcp, Long> {

}
