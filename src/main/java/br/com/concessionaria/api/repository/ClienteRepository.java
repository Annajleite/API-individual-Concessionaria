package br.com.concessionaria.api.repository;

import br.com.concessionaria.api.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {


    // Opcional significa que a busca pode ou não encontrar o cliente
    Optional<Cliente> findByCpf(String cpf);

    // Containig busca um nome que pode estar atrelado a outro, tipo: procurei Almeida, e aparece Anna Almeida :)
    java.util.List<Cliente> findByNomeContainingIgnoreCase(String nome);
}