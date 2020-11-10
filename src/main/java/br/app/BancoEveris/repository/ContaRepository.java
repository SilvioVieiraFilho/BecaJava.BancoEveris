package br.app.BancoEveris.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.app.BancoEveris.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
	
	//public Optional<Conta> findByHash(String hash);
	
	 Conta  findByHash(String hash);
	
	
	//public Optional<Conta> findBysaldo(double saldo , String hash);
   
}
