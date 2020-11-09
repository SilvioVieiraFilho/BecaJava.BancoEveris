package br.app.BancoEveris.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.app.BancoEveris.model.Operacao;

@Repository
public interface OperacaoRepository extends JpaRepository<Operacao, Long> {

}
