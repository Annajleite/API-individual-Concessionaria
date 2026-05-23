package br.com.concessionaria.api.controller;

import br.com.concessionaria.api.entity.Veiculo;
import br.com.concessionaria.api.model.VeiculoInput;
import br.com.concessionaria.api.model.VeiculoUpdateInput;
import br.com.concessionaria.api.service.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/veiculo")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @PostMapping
    public ResponseEntity<Veiculo> cadastrar(@Valid @RequestBody VeiculoInput input) {
        Veiculo veiculoSalvo = veiculoService.cadastrar(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculoSalvo);
    }

    //get
    @GetMapping
    public ResponseEntity<?> buscarVeiculos(
            @RequestParam(required = false) String placa,
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) String modelo) {

        if (placa != null) {
            return ResponseEntity.ok(veiculoService.buscarPorPlaca(placa));
        } else if (marca != null) {
            return ResponseEntity.ok(veiculoService.buscarPorMarca(marca));
        } else if (modelo != null) {
            return ResponseEntity.ok(veiculoService.buscarPorModelo(modelo));
        } else {
            return ResponseEntity.ok(veiculoService.listarTodos());
        }
    }
//put
    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> atualizar(@PathVariable UUID id, @Valid @RequestBody VeiculoUpdateInput input) {
        Veiculo veiculoAtualizado = veiculoService.atualizar(id, input);
        return ResponseEntity.ok(veiculoAtualizado);
    }

  //delet
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable UUID id) {
        veiculoService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
