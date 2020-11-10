package br.app.BancoEveris.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.app.BancoEveris.model.Conta;
import br.app.BancoEveris.model.Operacao;

@Repository
public interface OperacaoRepository extends JpaRepository<Operacao, Long> {


	List<Operacao> findByContaDestino(Conta contaDestino);

	List<Operacao> findByContaOrigem(Conta contaOrigem);
	
	
	
	@Query(value = "EXEC SP_obterOperacoesPorConta :idConta", nativeQuery = true)

    List<Operacao> findOperacoesPorConta(@Param("idConta") Long idConta);
}
