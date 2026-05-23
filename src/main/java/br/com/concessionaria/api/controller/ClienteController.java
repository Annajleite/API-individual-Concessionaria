package br.com.concessionaria.api.controller;

import br.com.concessionaria.api.entity.Cliente;
import br.com.concessionaria.api.model.ClienteInput;
import br.com.concessionaria.api.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;
import java.util.UUID;

@RestController

@RequestMapping("/api/v1/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;



    @PostMapping
    public ResponseEntity<Cliente> cadastrar(@Valid @RequestBody ClienteInput input) {
        Cliente clienteSalvo = clienteService.cadastrar(input);

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
    }


    @GetMapping
    public ResponseEntity<?> buscarClientes(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String cpf) {

        if (cpf != null) {
            return ResponseEntity.ok(clienteService.buscarPorCpf(cpf));
        } else if (nome != null) {
            return ResponseEntity.ok(clienteService.buscarPorNome(nome));
        } else {

            return ResponseEntity.ok(clienteService.listarTodos());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable UUID id) {
        clienteService.remover(id);

        return ResponseEntity.noContent().build();
    }
}