package br.com.fiap.iottu_api.config;

import java.util.Random;
import br.com.fiap.iottu_api.model.*;
import br.com.fiap.iottu_api.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


import java.util.List;

@Configuration
public class DatabaseSeeder {

    @Autowired private MotoRepository motoRepository;
    @Autowired private PatioRepository patioRepository;
    @Autowired private AntenaRepository antenaRepository;

    private List<Patio> patios;

    @PostConstruct
    public void init() {
        seedPatios();
        seedMotos();
        seedAntenas(); 
    }

    private void seedPatios() {
        patios = List.of(
                criarPatio("Patio Central", "Rua A, 123", "São Paulo", "SP", "Brasil", 100),
                criarPatio("Patio Norte", "Rua B, 456", "Campinas", "SP", "Brasil", 150),
                criarPatio("Patio Sul", "Rua C, 789", "Santos", "SP", "Brasil", 120)
        );
        patioRepository.saveAll(patios);
    }

    private Patio criarPatio(String nome, String endereco, String cidade, String estado, String pais, Integer capacidade) {
        return Patio.builder()
                .nome(nome)
                .endereco(endereco)
                .cidade(cidade)
                .estado(estado)
                .pais(pais)
                .capacidade(capacidade)
                .build();
    }

private void seedMotos() {
    if (patios == null || patios.isEmpty()) patios = patioRepository.findAll();

    List<Moto> motos = List.of(
            criarMoto("CG 160", "AAA1A01", 2023, "Honda", "RFID1001", "Rede-1", -23.56, -46.64, patios.get(0)),
            criarMoto("Factor 150", "BBB2B02", 2022, "Yamaha", "RFID1002", "Rede-2", -23.57, -46.65, patios.get(1)),
            criarMoto("Biz 125", "CCC3C03", 2021, "Honda", "RFID1003", "Rede-3", -23.58, -46.66, patios.get(2)),
            criarMoto("XRE 300", "DDD4D04", 2020, "Honda", "RFID1004", "Rede-4", -23.59, -46.67, patios.get(0)),
            criarMoto("Fazer 250", "EEE5E05", 2019, "Yamaha", "RFID1005", "Rede-5", -23.60, -46.68, patios.get(1)),
            criarMoto("Pop 110i", "FFF6F06", 2021, "Honda", "RFID1006", "Rede-6", -23.61, -46.69, patios.get(2)),
            criarMoto("NMax 160", "GGG7G07", 2022, "Yamaha", "RFID1007", "Rede-7", -23.62, -46.70, patios.get(0)),
            criarMoto("PCX 150", "HHH8H08", 2023, "Honda", "RFID1008", "Rede-8", -23.63, -46.71, patios.get(1)),
            criarMoto("Titan 160", "III9I09", 2020, "Honda", "RFID1009", "Rede-9", -23.64, -46.72, patios.get(2)),
            criarMoto("Bros 160", "JJJ0J10", 2021, "Honda", "RFID1010", "Rede-10", -23.65, -46.73, patios.get(0))
    );

    motoRepository.saveAll(motos);
}
    private final Random random = new Random();

    private Moto criarMoto(String modelo, String placa, int ano, String fabricante,
                        String rfid, String ssid, double lat, double lon, Patio patio) {

        StatusMoto status = random.nextBoolean() ? StatusMoto.ATIVA : StatusMoto.DESATIVADA;

        TagMoto tag = TagMoto.builder()
                .codigoRfid(rfid)
                .ssidWifi(ssid)
                .latitude(lat)
                .longitude(lon)
                .patio(patio)
                .build();

        Moto moto = Moto.builder()
                .modelo(modelo)
                .placa(placa)
                .ano(ano)
                .fabricante(fabricante)
                .status(status)
                .tag(tag)
                .build();

        tag.setMoto(moto);
        return moto;
    }
    private void seedAntenas() {
        if (patios == null || patios.isEmpty()) patios = patioRepository.findAll();

        List<Antena> antenas = List.of(
                criarAntena("ANT-001", "Entrada principal", -23.56, -46.64, patios.get(0)),
                criarAntena("ANT-002", "Saída leste", -23.57, -46.65, patios.get(1)),
                criarAntena("ANT-003", "Área interna", -23.58, -46.66, patios.get(2))
        );

        antenaRepository.saveAll(antenas);
    }

    private Antena criarAntena(String identificador, String localizacao, double lat, double lon, Patio patio) {
        return Antena.builder()
                .identificador(identificador)
                .localizacao(localizacao)
                .latitude(lat)
                .longitude(lon)
                .patio(patio)
                .build();
    }
}
