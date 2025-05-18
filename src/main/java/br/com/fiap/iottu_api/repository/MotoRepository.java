package br.com.fiap.iottu_api.repository;

import br.com.fiap.iottu_api.model.Moto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotoRepository extends JpaRepository<Moto, Long> {
}
