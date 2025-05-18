package br.com.fiap.iottu_api.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import br.com.fiap.iottu_api.model.Moto;

public interface MotoRepository extends JpaRepository<Moto, Long>, JpaSpecificationExecutor<Moto> {

    Optional<Moto> findByTag_CodigoRfid(String codigoRfid);
    
}