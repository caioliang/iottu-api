package br.com.fiap.iottu_api.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.fiap.iottu_api.model.*;
import br.com.fiap.iottu_api.repository.*;
import jakarta.annotation.PostConstruct;

@Configuration
public class DatabaseSeeder {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MotoRepository motoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        seedUsers();
        seedMotos();
    }

    private void seedUsers() {
        var joao = User.builder()
                .email("joao@fiap.com.br")
                .password(passwordEncoder.encode("12345"))
                .role(UserRole.ADMIN)
                .build();

        var maria = User.builder()
                .email("maria@fiap.com.br")
                .password(passwordEncoder.encode("12345"))
                .role(UserRole.USER)
                .build();

        userRepository.saveAll(List.of(joao, maria));
    }
        private void seedMotos() {
        List<Moto> motos = List.of(
                criarMoto("CG 160", "AAA1A01", 2023, "Vermelha", "Honda", "RFID1001", "Rede-1", -23.56, -46.64, "Tag 1"),
                criarMoto("Factor 150", "BBB2B02", 2022, "Azul", "Yamaha", "RFID1002", "Rede-2", -23.57, -46.65, "Tag 2"),
                criarMoto("Biz 125", "CCC3C03", 2021, "Preta", "Honda", "RFID1003", "Rede-3", -23.58, -46.66, "Tag 3"),
                criarMoto("XRE 300", "DDD4D04", 2020, "Branca", "Honda", "RFID1004", "Rede-4", -23.59, -46.67, "Tag 4"),
                criarMoto("Fazer 250", "EEE5E05", 2019, "Cinza", "Yamaha", "RFID1005", "Rede-5", -23.60, -46.68, "Tag 5"),
                criarMoto("Pop 110i", "FFF6F06", 2021, "Amarela", "Honda", "RFID1006", "Rede-6", -23.61, -46.69, "Tag 6"),
                criarMoto("NMax 160", "GGG7G07", 2022, "Prata", "Yamaha", "RFID1007", "Rede-7", -23.62, -46.70, "Tag 7"),
                criarMoto("PCX 150", "HHH8H08", 2023, "Vermelha", "Honda", "RFID1008", "Rede-8", -23.63, -46.71, "Tag 8"),
                criarMoto("Titan 160", "III9I09", 2020, "Verde", "Honda", "RFID1009", "Rede-9", -23.64, -46.72, "Tag 9"),
                criarMoto("Bros 160", "JJJ0J10", 2021, "Laranja", "Honda", "RFID1010", "Rede-10", -23.65, -46.73, "Tag 10")
        );

        motoRepository.saveAll(motos);
    }

    private Moto criarMoto(String modelo, String placa, int ano, String cor, String fabricante,
                           String rfid, String ssid, double lat, double lon, String obs) {

        TagMoto tag = TagMoto.builder()
                .codigoRfid(rfid)
                .ssidWifi(ssid)
                .latitude(lat)
                .longitude(lon)
                .observacao(obs)
                .build();

        Moto moto = Moto.builder()
                .modelo(modelo)
                .placa(placa)
                .ano(ano)
                .cor(cor)
                .fabricante(fabricante)
                .tag(tag)
                .build();

        tag.setMoto(moto);

        return moto;
    }
}
