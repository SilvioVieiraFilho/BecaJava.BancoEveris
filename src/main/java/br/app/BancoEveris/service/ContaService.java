
package br.app.BancoEveris.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.app.BancoEveris.model.BaseResponse;
import br.app.BancoEveris.model.Conta;
import br.app.BancoEveris.repository.ContaRepository;
import br.app.BancoEveris.repository.OperacaoRepository;
import br.app.BancoEveris.spec.ContaList;
import br.app.BancoEveris.spec.ContaSpec;

@Service
public class ContaService {
	@Autowired
	private ContaRepository repository;

	public BaseResponse inserir(ContaSpec contaSpec) {
		Conta conta = new Conta();
		
		Optional<Conta> conta1 = repository.findByHash(contaSpec.getHash());
			
		
		BaseResponse base = new BaseResponse();
		base.StatusCode = 400;

		if (contaSpec.getNome() == "") {
			base.Message = "O nome do cliente não foi preenchido.";
			return base;
		}

		if (contaSpec.getCpf() == "") {
			base.Message = "O CPF do cliente não foi preenchido.";
			return base;
		}
		
	

		if (contaSpec.getHash() == "") {
			base.Message = "O Hash do cliente não foi preenchido.";
			return base;
		}
		
		conta.setSaldo(0.0);

		conta.setNome(contaSpec.getNome());
		conta.setCpf(contaSpec.getCpf());
		conta.setHash(contaSpec.getHash());

		repository.save(conta);
		base.StatusCode = 201;
		base.Message = "Cliente inserida com sucesso.";
		return base;
	}

	public Conta obter(Long id) {
		
		
		Optional<Conta> cliente = repository.findById(id);

		Conta response = new Conta();

		if (cliente.isEmpty()) {
			response.Message = "Cliente não encontrado";
			response.StatusCode = 404;
			return response;
		}
		response.setHash(cliente.get().getHash());
		response.setSaldo(cliente.get().getSaldo());
		response.Message = "Cliente obtido com sucesso";
		response.StatusCode = 200;
		return response;
	
	
	
	}

	public ContaList listar() {

		List<Conta> lista = repository.findAll(); // Lista Todas as contas

		ContaList response = new ContaList();
		
		
		response.setContas(lista);
		response.StatusCode = 200;
		response.Message = "Clientes obtidos com sucesso.";

		return response;
	}

	public BaseResponse atualizar(Long id, ContaSpec contaSpec) {
		Conta conta = new Conta();
		BaseResponse base = new BaseResponse();
		base.StatusCode = 400;

		if (contaSpec.getNome() == "") {
			base.Message = "O nome do cliente não foi preenchido.";
			return base;
		}

		if (contaSpec.getCpf() == "") {
			base.Message = "O CPF do cliente não foi preenchido.";
			return base;
		}

		if (contaSpec.getHash() == "") {
			base.Message = "O Hash do cliente não foi preenchido.";
			return base;
		}

		conta.setId(id);
		conta.setNome(contaSpec.getNome());
		conta.setCpf(contaSpec.getCpf());
		conta.setHash(contaSpec.getHash());

		repository.save(conta);
		base.StatusCode = 200;
		base.Message = "Cliente atualizado com sucesso.";
		return base;
	}

	public BaseResponse deletar(Long id) {
		BaseResponse response = new BaseResponse();

		if (id == null || id == 0) {
			response.StatusCode = 400;
			return response;
		}

		repository.deleteById(id);
		response.StatusCode = 200;
		return response;
	}

}
