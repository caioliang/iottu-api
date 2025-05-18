package br.com.fiap.iottu_api.service;

import br.com.fiap.iottu_api.dto.NovaMotoRequestDTO;
import br.com.fiap.iottu_api.model.Moto;
import br.com.fiap.iottu_api.model.TagMoto;
import br.com.fiap.iottu_api.repository.MotoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MotoService {

    private final MotoRepository motoRepository;

    @Transactional
    public Moto cadastrar(NovaMotoRequestDTO dto) {
        Moto moto = Moto.builder()
                .modelo(dto.getModelo())
                .placa(dto.getPlaca())
                .ano(dto.getAno())
                .cor(dto.getCor())
                .fabricante(dto.getFabricante())
                .build();

        TagMoto tag = TagMoto.builder()
                .codigoRfid(dto.getCodigoRfid())
                .ssidWifi(dto.getSsidWifi())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .observacao(dto.getObservacao())
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
    public Moto atualizar(Long id, NovaMotoRequestDTO dto) {
        Moto motoExistente = buscarPorId(id);

        motoExistente.setModelo(dto.getModelo());
        motoExistente.setPlaca(dto.getPlaca());
        motoExistente.setAno(dto.getAno());
        motoExistente.setCor(dto.getCor());
        motoExistente.setFabricante(dto.getFabricante());

        TagMoto tag = motoExistente.getTag();
        tag.setCodigoRfid(dto.getCodigoRfid());
        tag.setSsidWifi(dto.getSsidWifi());
        tag.setLatitude(dto.getLatitude());
        tag.setLongitude(dto.getLongitude());
        tag.setObservacao(dto.getObservacao());

        return motoRepository.save(motoExistente);
    }

    @Transactional
    public void deletar(Long id) {
        Moto moto = buscarPorId(id);
        motoRepository.delete(moto);
    }

    public Page<Moto> listarPaginado(Pageable pageable) {
        return motoRepository.findAll(pageable);
    }
}
