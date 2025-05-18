package br.com.fiap.iottu_api.service;

import br.com.fiap.iottu_api.dto.MotoRequestDTO;
import br.com.fiap.iottu_api.dto.TagMotoUpdateDTO;
import br.com.fiap.iottu_api.model.Moto;
import br.com.fiap.iottu_api.model.Patio;
import br.com.fiap.iottu_api.model.TagMoto;
import br.com.fiap.iottu_api.repository.MotoRepository;
import br.com.fiap.iottu_api.repository.PatioRepository;
import br.com.fiap.iottu_api.specification.MotoSpecifications;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MotoService {

    private final MotoRepository motoRepository;
    private final PatioRepository patioRepository;

    @Transactional
    public Moto cadastrar(MotoRequestDTO dto) {
        Moto moto = Moto.builder()
                .modelo(dto.getModelo())
                .placa(dto.getPlaca())
                .ano(dto.getAno())
                .cor(dto.getCor())
                .fabricante(dto.getFabricante())
                .build();

        Patio patio = patioRepository.findById(dto.getPatioId())
                .orElseThrow(() -> new EntityNotFoundException("Pátio não encontrado com ID: " + dto.getPatioId()));

        TagMoto tag = TagMoto.builder()
                .codigoRfid(dto.getCodigoRfid())
                .ssidWifi(dto.getSsidWifi())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .observacao(dto.getObservacao())
                .patio(patio)
                .moto(moto)
                .build();

        moto.setTag(tag);
        return motoRepository.save(moto);
    }

    public List<Moto> listarTodas() {
        return motoRepository.findAll();
    }

    public Moto buscarPorId(Long id) {
        return motoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Moto não encontrada com ID: " + id));
    }

    @Transactional
    public Moto atualizar(Long id, MotoRequestDTO dto) {
        Moto motoExistente = buscarPorId(id);

        motoExistente.setModelo(dto.getModelo());
        motoExistente.setPlaca(dto.getPlaca());
        motoExistente.setAno(dto.getAno());
        motoExistente.setCor(dto.getCor());
        motoExistente.setFabricante(dto.getFabricante());

        TagMoto tag = motoExistente.getTag();
        tag.setSsidWifi(dto.getSsidWifi());
        tag.setLatitude(dto.getLatitude());
        tag.setLongitude(dto.getLongitude());
        tag.setObservacao(dto.getObservacao());

        if (dto.getPatioId() != null) {
            Patio patio = patioRepository.findById(dto.getPatioId())
                    .orElseThrow(() -> new EntityNotFoundException("Pátio não encontrado com ID: " + dto.getPatioId()));
            tag.setPatio(patio);
        }

        return motoRepository.save(motoExistente);
    }

    @Transactional
    public Moto atualizarTagPorCodigoRfid(String codigoRfid, TagMotoUpdateDTO dto) {
        Moto moto = motoRepository.findByTag_CodigoRfid(codigoRfid)
                .orElseThrow(() -> new EntityNotFoundException("Tag com código RFID não encontrada: " + codigoRfid));

        TagMoto tag = moto.getTag();

        tag.setSsidWifi(dto.getSsidWifi());
        tag.setLatitude(dto.getLatitude());
        tag.setLongitude(dto.getLongitude());
        tag.setObservacao(dto.getObservacao());
        tag.setDataAtualizacao(LocalDateTime.now());

        if (dto.getPatioId() != null) {
            Patio patio = patioRepository.findById(dto.getPatioId())
                    .orElseThrow(() -> new EntityNotFoundException("Pátio não encontrado com ID: " + dto.getPatioId()));
            tag.setPatio(patio);
        }

        return motoRepository.save(moto);
    }

    @Transactional
    public void deletar(Long id) {
        Moto moto = buscarPorId(id);
        motoRepository.delete(moto);
    }

    public Page<Moto> listarPaginado(Pageable pageable) {
        return motoRepository.findAll(pageable);
    }

    public Page<Moto> buscarPorParametros(String modelo, String fabricante, String cor, String placa, String patioNome, Pageable pageable) {
        Specification<Moto> spec = Specification.where(MotoSpecifications.modeloContains(modelo))
            .and(MotoSpecifications.fabricanteContains(fabricante))
            .and(MotoSpecifications.corContains(cor))
            .and(MotoSpecifications.placaEquals(placa))
            .and(MotoSpecifications.patioNomeContains(patioNome));

        return motoRepository.findAll(spec, pageable);
    }

}
