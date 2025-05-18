package br.com.fiap.iottu_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PatioRequestDTO {

    @NotBlank(message = "O nome do pátio é obrigatório.")
    private String nome;

    @NotBlank(message = "O endereço do pátio é obrigatório.")
    private String endereco;

    @NotBlank(message = "A cidade é obrigatória.")
    private String cidade;

    @NotBlank(message = "O estado é obrigatório.")
    private String estado;

    @NotBlank(message = "O país é obrigatório.")
    private String pais;

    @NotNull(message = "A capacidade do pátio é obrigatória.")
    private Integer capacidade;
}
