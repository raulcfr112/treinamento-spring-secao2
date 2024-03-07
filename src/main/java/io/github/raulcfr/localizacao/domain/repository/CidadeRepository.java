package io.github.raulcfr.localizacao.domain.repository;

import io.github.raulcfr.localizacao.domain.entity.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
