package br.com.unoesc.desafiodev.service;

import br.com.unoesc.desafiodev.model.Pessoa;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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