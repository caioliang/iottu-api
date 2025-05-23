package br.com.fiap.iottu_api.model;

import br.com.fiap.iottu_api.model.StatusMoto;
import br.com.fiap.iottu_api.validation.PlacaValida;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MOTOS")
@EqualsAndHashCode(of = "id")
public class Moto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O modelo da moto é obrigatório.")
    private String modelo;

    @Column(unique = true)
    @PlacaValida
    private String placa;

    private Integer ano;

    private String fabricante;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O status da moto é obrigatório.")
    private StatusMoto status;

    @OneToOne(mappedBy = "moto", cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
    @NotNull(message = "A tag da moto é obrigatória.")
    @Valid
    private TagMoto tag;
}
