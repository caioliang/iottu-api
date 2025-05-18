package br.com.fiap.iottu_api.dto;

import lombok.Data;

@Data
public class TagMotoUpdateDTO {

    private String ssidWifi;
    private Double latitude;
    private Double longitude;
    private String observacao;
    private Long patioId; 

}
