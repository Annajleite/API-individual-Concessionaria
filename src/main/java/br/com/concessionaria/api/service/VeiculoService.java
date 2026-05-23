package br.com.concessionaria.api.service;

import br.com.concessionaria.api.entity.Cliente;
import br.com.concessionaria.api.entity.Veiculo;
import br.com.concessionaria.api.exception.PlacaJaCadastradaException;
import br.com.concessionaria.api.model.VeiculoInput;
import br.com.concessionaria.api.model.VeiculoUpdateInput;
import br.com.concessionaria.api.repository.ClienteRepository;
import br.com.concessionaria.api.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private ClienteRepository clienteRepository;


    public Veiculo cadastrar(VeiculoInput input) {

        Cliente cliente = clienteRepository.findById(input.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));


        if (veiculoRepository.findByPlaca(input.getPlaca()).isPresent()) {
            throw new PlacaJaCadastradaException("A placa informada já está cadastrada no sistema");
        }


        if (Boolean.TRUE.equals(input.getVendido()) && input.getValorVenda() == null) {
            throw new IllegalArgumentException("O valor de venda é obrigatório para veículos vendidos");
        }

        Veiculo veiculo = new Veiculo();
        veiculo.setCliente(cliente);
        veiculo.setMarca(input.getMarca());
        veiculo.setModelo(input.getModelo());
        veiculo.setAno(input.getAno());
        veiculo.setValor(input.getValor());
        veiculo.setPlaca(input.getPlaca());
        veiculo.setMaximoDesconto(input.getMaximoDesconto());
        veiculo.setVendido(input.getVendido());
        veiculo.setValorVenda(input.getValorVenda());

        return veiculoRepository.save(veiculo);
    }

    public Veiculo atualizar(UUID id, VeiculoUpdateInput input) {

        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));


        if (Boolean.TRUE.equals(input.getVendido()) && input.getValorVenda() == null) {
            throw new IllegalArgumentException("O valor de venda é obrigatório para veículos vendidos");
        }

        veiculo.setMarca(input.getMarca());
        veiculo.setModelo(input.getModelo());
        veiculo.setAno(input.getAno());
        veiculo.setValor(input.getValor());
        veiculo.setMaximoDesconto(input.getMaximoDesconto());
        veiculo.setVendido(input.getVendido());
        veiculo.setValorVenda(input.getValorVenda());

        return veiculoRepository.save(veiculo);
    }

    public List<Veiculo> listarTodos() {
        return veiculoRepository.findAll();
    }


    public List<Veiculo> buscarPorMarca(String marca) {
        return veiculoRepository.findByMarcaContainingIgnoreCase(marca);
    }


    public List<Veiculo> buscarPorModelo(String modelo) {
        return veiculoRepository.findByModeloContainingIgnoreCase(modelo);
    }


    public Veiculo buscarPorPlaca(String placa) {
        return veiculoRepository.findByPlaca(placa)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));
    }

    public void remover(UUID id) {
        if (!veiculoRepository.existsById(id)) {
            throw new RuntimeException("Veículo não encontrado");
        }
        veiculoRepository.deleteById(id);
    }
}