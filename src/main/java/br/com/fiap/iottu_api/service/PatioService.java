package br.com.fiap.iottu_api.service;

import br.com.fiap.iottu_api.model.Patio;
import br.com.fiap.iottu_api.repository.PatioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatioService {

    private final PatioRepository patioRepository;

    @CacheEvict(value = "patios", allEntries = true)
    public Patio criarPatio(Patio patio) {
        return patioRepository.save(patio);
    }

    @Cacheable("patios")
    public List<Patio> listarPatios() {
        return patioRepository.findAll();
    }

    public Optional<Patio> buscarPorId(Long id) {
        return patioRepository.findById(id);
    }

    @CacheEvict(value = "patios", allEntries = true)
    public Patio atualizarPatio(Long id, Patio novoPatio) {
        return patioRepository.findById(id)
                .map(patio -> {
                    patio.setNome(novoPatio.getNome());
                    patio.setEndereco(novoPatio.getEndereco());
                    patio.setCidade(novoPatio.getCidade());
                    patio.setEstado(novoPatio.getEstado());
                    patio.setPais(novoPatio.getPais());
                    patio.setCapacidade(novoPatio.getCapacidade());
                    return patioRepository.save(patio);
                })
                .orElseThrow(() -> new EntityNotFoundException("Pátio não encontrado com ID: " + id));
    }

    @CacheEvict(value = "patios", allEntries = true)
    public void deletar(Long id) {
        patioRepository.deleteById(id);
    }
    public Page<Patio> listarPatios(Pageable pageable) {
        return patioRepository.findAll(pageable);
    }
}
