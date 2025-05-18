package br.com.fiap.iottu_api.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NovaMotoRequestDTO {

    @NotBlank
    private String modelo;

    @NotBlank
    private String placa;

    @NotNull
    private Integer ano;

    @NotBlank
    private String cor;

    @NotBlank
    private String fabricante;

    // Dados da TagMoto
    @NotBlank
    private String codigoRfid;

    @NotBlank
    private String ssidWifi;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    private String observacao;
}
