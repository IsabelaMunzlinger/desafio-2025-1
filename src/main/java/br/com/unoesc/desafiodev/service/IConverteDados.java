package br.com.unoesc.desafiodev.service;


//Definição genérica para usar poder implementar em qualquer classe
public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
