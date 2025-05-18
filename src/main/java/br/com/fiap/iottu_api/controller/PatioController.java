package br.com.fiap.iottu_api.controller;

import br.com.fiap.iottu_api.model.Patio;
import br.com.fiap.iottu_api.service.PatioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patios")
@RequiredArgsConstructor
public class PatioController {

    private final PatioService patioService;

    @PostMapping
    public ResponseEntity<Patio> criar(@RequestBody @Valid Patio patio) {
        Patio novo = patioService.criarPatio(patio);
        return ResponseEntity.ok(novo);
    }

    @GetMapping
    public ResponseEntity<Page<Patio>> listar(Pageable pageable) {
        Page<Patio> patios = patioService.listarPatios(pageable);
        return ResponseEntity.ok(patios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patio> buscar(@PathVariable Long id) {
        return patioService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        patioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
