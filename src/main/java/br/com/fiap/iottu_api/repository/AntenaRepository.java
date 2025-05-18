package br.com.fiap.iottu_api.repository;

import br.com.fiap.iottu_api.model.Antena;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AntenaRepository extends JpaRepository<Antena, Long> {
    Optional<Antena> findByIdentificador(String identificador);
}