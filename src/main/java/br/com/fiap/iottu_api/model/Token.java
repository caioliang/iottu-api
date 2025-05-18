package br.com.fiap.iottu_api.model;

public record Token(
    String token, 
    String type,
    String email
) {}
