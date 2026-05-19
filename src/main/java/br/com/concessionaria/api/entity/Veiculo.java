package br.com.concessionaria.api.entity;

import jakarta.persistence.*;
        import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Entity
@Table(name = "veiculo")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    // @ManyToOne muitos pra um
    // @JoinColumn cria a coluna cliente_id que guarda o id do dono do carrinho lindo
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @NotBlank(message = "A marca é obrigatória")
    @Column(nullable = false)
    private String marca;

    @NotBlank(message = "O modelo é obrigatório")
    @Column(nullable = false)
    private String modelo;

    // @Min(1900) regra do swagger
    @NotNull(message = "O ano é obrigatório")
    @Min(value = 1900, message = "O ano deve ser maior ou igual a 1900")
    @Column(nullable = false)
    private Integer ano;

    // @DecimalMin valor minimo 1
    @NotNull(message = "O valor é obrigatório")
    @DecimalMin(value = "1.0", message = "O valor deve ser no mínimo 1")
    @Column(nullable = false)
    private Double valor;


    @NotBlank(message = "A placa é obrigatória")
    @Column(nullable = false, unique = true)
    private String placa;

    @NotNull(message = "O máximo de desconto é obrigatório")
    @DecimalMin(value = "0.0", message = "O desconto não pode ser negativo")
    @Column(name = "maximo_desconto", nullable = false)
    private Double maximoDesconto;


    @NotNull(message = "O status de venda é obrigatório")
    @Column(nullable = false)
    private Boolean vendido;

    @Column(name = "valor_venda", nullable = true)
    private Double valorVenda;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Double getMaximoDesconto() {
        return maximoDesconto;
    }

    public void setMaximoDesconto(Double maximoDesconto) {
        this.maximoDesconto = maximoDesconto;
    }

    public Boolean getVendido() {
        return vendido;
    }

    public void setVendido(Boolean vendido) {
        this.vendido = vendido;
    }

    public Double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(Double valorVenda) {
        this.valorVenda = valorVenda;
    }
}