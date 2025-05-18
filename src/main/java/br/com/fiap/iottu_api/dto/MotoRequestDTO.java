package br.com.fiap.iottu_api.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MotoRequestDTO {

    @NotBlank
    private String modelo;

    @NotBlank
    private String placa;

    @NotNull
    private Integer ano;

    @NotBlank
    private String fabricante;

    @NotBlank
    private String status;

    @NotBlank
    private String codigoRfid;

    @NotBlank
    private String ssidWifi;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    private Long patioId;
    
    private String observacao;
}
