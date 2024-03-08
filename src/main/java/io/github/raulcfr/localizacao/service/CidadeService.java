package io.github.raulcfr.localizacao.service;

import io.github.raulcfr.localizacao.domain.entity.Cidade;
import io.github.raulcfr.localizacao.domain.repository.CidadeRepository;

import static io.github.raulcfr.localizacao.domain.repository.specs.CidadeSpecs.*;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CidadeService {
    private CidadeRepository repository;

    public CidadeService(CidadeRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void salvarCidade() {
        var cidade = new Cidade(1L, "São Paulo", 12932152L);
        repository.save(cidade);
    }

    public void listarCidades() {
        repository.findAll().forEach(System.out::println);
    }

    public void listarCidadePorQtdMenorDeHabitantes() {
        repository.findByHabitantesLessThan(100000L).forEach(System.out::println);
    }

    public void listarCidadePorQtdMaiorDeHabitantes() {
        repository.findByHabitantesGreaterThanEqual(100000L).forEach(System.out::println);
    }

    public void listarCidadePorQtdMenorENomeIgual() {
        repository.findByHabitantesLessThanAndNomeLike(100000L, "Palmas").forEach(System.out::println);
    }

    public void listarCidadesPorHabitantes() {
        repository.findByHabitantes(36953L).forEach(System.out::println);
    }

    public void listarCidadesPorNome() {
        Pageable pageable = PageRequest.of(1, 2);
        repository.findByNomeLike("%%%%", pageable).forEach(System.out::println);
    }

    public List<Cidade> filtroDinamico(Cidade cidade) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.STARTING);
        Example<Cidade> example = Example.of(cidade, matcher);
        return repository.findAll(example);
    }

    public void listarCidadeByNomeSpecs() {
        repository
                .findAll(nomeEqual("São Paulo").and(idEqual(1L)))
                .forEach(System.out::println);
    }

    public void listarCidadesSpecsFiltroDinamico(Cidade filtro) {
        Specification<Cidade> specs = Specification.where((root, query, cb) -> cb.conjunction());

        if (filtro.getId() != null) {
            specs = specs.and(idEqual(filtro.getId()));
        }

        if (StringUtils.hasText(filtro.getNome())) {
            specs = specs.and(nomeLike(filtro.getNome()));
        }

        if (filtro.getHabitantes() != null) {
            specs = specs.and(habitantesGreaterThan(filtro.getHabitantes()));
        }
        repository.findAll(specs).forEach(System.out::println);
    }
}
