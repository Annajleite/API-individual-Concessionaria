package br.com.concessionaria.api.repository;

import br.com.concessionaria.api.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, UUID> {

    // procura pela placa
    Optional<Veiculo> findByPlaca(String placa);

    List<Veiculo> findByMarcaContainingIgnoreCase(String marca);

    // procura por modelo
    List<Veiculo> findByModeloContainingIgnoreCase(String modelo);
}