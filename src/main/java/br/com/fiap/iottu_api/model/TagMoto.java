package br.com.fiap.iottu_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TAG_MOTO")
@EqualsAndHashCode(of = "id")
public class TagMoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank(message = "O código RFID é obrigatório.")
    private String codigoRfid;

    @NotBlank(message = "O nome da rede Wi-Fi é obrigatório.")
    private String ssidWifi;

    @NotNull(message = "A latitude é obrigatória.")
    private Double latitude;

    @NotNull(message = "A longitude é obrigatória.")
    private Double longitude;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao; 
    
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "patio_id")
    private Patio patio;

    @OneToOne
    @JoinColumn(name = "moto_id", nullable = false)
    @JsonIgnore
    @NotNull(message = "A moto associada é obrigatória.")
    private Moto moto;
}
