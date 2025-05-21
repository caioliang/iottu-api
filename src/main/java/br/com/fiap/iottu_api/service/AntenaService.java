package br.com.fiap.iottu_api.service;

import br.com.fiap.iottu_api.dto.AntenaRequestDTO;
import br.com.fiap.iottu_api.model.Antena;
import br.com.fiap.iottu_api.model.Patio;
import br.com.fiap.iottu_api.repository.AntenaRepository;
import br.com.fiap.iottu_api.repository.PatioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AntenaService {

    private final AntenaRepository antenaRepository;
    private final PatioRepository patioRepository;

    public Antena criarAntena(AntenaRequestDTO dto) {
        if (antenaRepository.findByIdentificador(dto.getIdentificador()).isPresent()) {
            throw new IllegalArgumentException("Já existe uma antena com o identificador: " + dto.getIdentificador());
        }

        Patio patio = patioRepository.findById(dto.getPatioId())
            .orElseThrow(() -> new EntityNotFoundException("Pátio não encontrado com ID: " + dto.getPatioId()));

        Antena antena = Antena.builder()
                .identificador(dto.getIdentificador())
                .localizacao(dto.getLocalizacao())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .patio(patio)
                .build();

        return antenaRepository.save(antena);
    }
    
    public List<Antena> listarAntenas() {
        return antenaRepository.findAll();
    }

    public Optional<Antena> buscarPorId(Long id) {
        return antenaRepository.findById(id);
    }

    public void deletarAntena(Long id) {
        antenaRepository.deleteById(id);
    }
    
    public Page<Antena> listarAntenas(Pageable pageable) {
        return antenaRepository.findAll(pageable);
    }
}
