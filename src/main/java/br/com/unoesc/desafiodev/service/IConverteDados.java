package br.com.unoesc.desafiodev.service;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
