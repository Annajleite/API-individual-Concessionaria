package br.com.concessionaria.api.service;

import br.com.concessionaria.api.entity.Cliente;
import br.com.concessionaria.api.exception.CpfJaCadastradoException;
import br.com.concessionaria.api.model.ClienteInput;
import br.com.concessionaria.api.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;


@Service
public class ClienteService {

    //  repositório pra poder usar os métodos de banco de dados
    @Autowired
    private ClienteRepository clienteRepository;


    public Cliente cadastrar(ClienteInput input) {

        // Verifica se o CPF já existe no banco, se não avisa
        if (clienteRepository.findByCpf(input.getCpf()).isPresent()) {
            throw new CpfJaCadastradoException("CPF já cadastrado no sistema");
        }

        // Verificado certinho, passa de clientinput e vai pra cliente
        Cliente cliente = new Cliente();
        cliente.setNome(input.getNome());
        cliente.setTelefone(input.getTelefone());
        cliente.setCpf(input.getCpf());
        cliente.setEmail(input.getEmail());

        // Salva no banco e retorna o cliente salvo , e gera id
        return clienteRepository.save(cliente);
    }


    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    // caçando o cliente pelo nomezin
    public List<Cliente> buscarPorNome(String nome) {
        return clienteRepository.findByNomeContainingIgnoreCase(nome);
    }

    //por cpf
    public Cliente buscarPorCpf(String cpf) {

        return clienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    // pra remover cliente
    public void remover(UUID id) {
        // verifica se o id existe antes de excluir
        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("Cliente não encontrado");
        }
        clienteRepository.deleteById(id);
    }
}