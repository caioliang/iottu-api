package br.com.fiap.iottu_api.specification;
import org.springframework.data.jpa.domain.Specification;
import br.com.fiap.iottu_api.model.Moto;

public class MotoSpecifications {

    public static Specification<Moto> modeloContains(String modelo) {
        return (root, query, builder) -> 
            modelo == null ? null : builder.like(builder.lower(root.get("modelo")), "%" + modelo.toLowerCase() + "%");
    }

    public static Specification<Moto> fabricanteContains(String fabricante) {
        return (root, query, builder) -> 
            fabricante == null ? null : builder.like(builder.lower(root.get("fabricante")), "%" + fabricante.toLowerCase() + "%");
    }

    public static Specification<Moto> placaEquals(String placa) {
        return (root, query, builder) -> 
            placa == null ? null : builder.equal(root.get("placa"), placa);
    }

    public static Specification<Moto> patioNomeContains(String patioNome) {
        return (root, query, builder) -> {
            if (patioNome == null) return null;
            return builder.like(builder.lower(root.join("tag").get("patio").get("nome")), "%" + patioNome.toLowerCase() + "%");
        };
    }

}
