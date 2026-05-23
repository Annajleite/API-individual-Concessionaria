package br.com.concessionaria.api.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public class VeiculoInput {

    //o Swagger chama o id do cliente UUID para saber de quem é o carritcho
    @NotNull(message = "O ID do cliente é obrigatório")
    private UUID clienteId;

    @NotBlank(message = "A marca é obrigatória")
    private String marca;

    @NotBlank(message = "O modelo é obrigatório")
    private String modelo;

    // minimo 1900
    @NotNull(message = "O ano é obrigatório")
    @Min(value = 1900, message = "O ano deve ser no mínimo 1900")
    private Integer ano;

    // minimo 1
    @NotNull(message = "O valor é obrigatório")
    @DecimalMin(value = "1.0", message = "O valor deve ser no mínimo 1")
    private Double valor;

    @NotBlank(message = "A placa é obrigatória")
    private String placa;


    @NotNull(message = "O máximo de desconto é obrigatório")
    @DecimalMin(value = "0.0", message = "O desconto não pode ser negativo")
    private Double maximoDesconto;

    @NotNull(message = "O status de vendido (true/false) é obrigatório")
    private Boolean vendido;


    private Double valorVenda;

    public UUID getClienteId() {
        return clienteId;
    }

    public void setClienteId(UUID clienteId) {
        this.clienteId = clienteId;
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