package br.com.concessionaria.api.entity;

import jakarta.persistence.*;
        import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import java.util.UUID;

// classe vira tabela
@Entity
@Table(name = "cliente")
public class Cliente {



    @Id //primarykey
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // nada de campos vazios
    @NotBlank(message = "o nome é obrigatório")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "o telefone é obrigatório")
    @Column(nullable = false)
    private String telefone;

    @NotBlank(message = "O CPF é obrigatório")
    @CPF(message = "CPF inválido")
    @Column(nullable = false, unique = true)
    private String cpf;


    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "Formato de e-mail errado")
    @Column(nullable = false)
    private String email;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}