package br.com.fiap.iottu_api.controller;

import br.com.fiap.iottu_api.dto.NovaMotoRequestDTO;
import br.com.fiap.iottu_api.model.Moto;
import br.com.fiap.iottu_api.service.MotoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/motos")
@RequiredArgsConstructor
public class MotoController {

    private final MotoService motoService;

    @PostMapping
    public ResponseEntity<Moto> cadastrar(@RequestBody @Valid NovaMotoRequestDTO dto) {
        Moto motoCriada = motoService.cadastrar(dto);
        return ResponseEntity.ok(motoCriada);
    }

    @GetMapping
    public ResponseEntity<Page<Moto>> listarPaginado(
        @PageableDefault(size = 10, sort = "id", direction = Direction.ASC) Pageable pageable
    ) {
        return ResponseEntity.ok(motoService.listarPaginado(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Moto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(motoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Moto> atualizar(@PathVariable Long id, @RequestBody @Valid NovaMotoRequestDTO dto) {
        return ResponseEntity.ok(motoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        motoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
