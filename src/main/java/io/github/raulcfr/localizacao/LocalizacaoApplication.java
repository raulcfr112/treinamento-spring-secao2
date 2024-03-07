package io.github.raulcfr.localizacao;

import io.github.raulcfr.localizacao.domain.entity.Cidade;
import io.github.raulcfr.localizacao.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class LocalizacaoApplication implements CommandLineRunner {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Transactional
    void salvarCidade(){
        var cidade = new Cidade(1, "São Paulo", 12932152L);
        cidadeRepository.save(cidade);
    }

    void listarCidades(){
        cidadeRepository.findAll().forEach(System.out::println);
    }

    @Override
    public void run(String... args) throws Exception {
        listarCidades();
    }

    public static void main(String[] args) {
        SpringApplication.run(LocalizacaoApplication.class, args);
    }
}
