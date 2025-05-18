package br.com.fiap.iottu_api.controller;

import br.com.fiap.iottu_api.dto.AntenaRequestDTO;
import br.com.fiap.iottu_api.model.Antena;
import br.com.fiap.iottu_api.service.AntenaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/antenas")
@RequiredArgsConstructor
public class AntenaController {

    private final AntenaService antenaService;

    @PostMapping
    public ResponseEntity<Antena> criar(@RequestBody @Valid AntenaRequestDTO dto) {
        Antena novaAntena = antenaService.criarAntena(dto);
        return ResponseEntity.ok(novaAntena);
    }

    @GetMapping
    public ResponseEntity<Page<Antena>> listar(Pageable pageable) {
        Page<Antena> antenas = antenaService.listarAntenas(pageable);
        return ResponseEntity.ok(antenas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Antena> buscar(@PathVariable Long id) {
        return antenaService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        antenaService.deletarAntena(id);
        return ResponseEntity.noContent().build();
    }
}
