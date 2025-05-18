package br.com.fiap.iottu_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ANTENAS")
@EqualsAndHashCode(of = "id")
public class Antena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O identificador da antena é obrigatório.")
    private String identificador;

    @NotBlank(message = "A descrição/localização da antena é obrigatória.")
    private String localizacao;

    private Double latitude;

    private Double longitude;

    private String observacao;
}
