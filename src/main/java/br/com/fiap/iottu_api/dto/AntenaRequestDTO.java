package br.com.fiap.iottu_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AntenaRequestDTO {

    @NotBlank(message = "O identificador da antena é obrigatório.")
    private String identificador;

    @NotBlank(message = "A localização da antena é obrigatória.")
    private String localizacao;

    @NotNull(message = "A latitude é obrigatória.")
    private Double latitude;

    @NotNull(message = "A longitude é obrigatória.")
    private Double longitude;

    private String observacao;

    @NotNull(message = "O ID do pátio vinculado é obrigatório.")
    private Long patioId;
}
