package br.com.unoesc.desafiodev.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import br.com.unoesc.desafiodev.model.Pessoa;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultadoAPI {
    private List<Pessoa> results;

    public List<Pessoa> getResults() {
        return results;
    }

    public void setResults(List<Pessoa> results) {
        this.results = results;
    }
}