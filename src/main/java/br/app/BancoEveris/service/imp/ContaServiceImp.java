
package br.app.BancoEveris.service.imp;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.app.BancoEveris.model.Conta;
import br.app.BancoEveris.repository.ContaRepository;
import br.app.BancoEveris.repository.OperacaoRepository;
import br.app.BancoEveris.request.ContaRequest;
import br.app.BancoEveris.response.BaseResponse;
import br.app.BancoEveris.response.ContaList;
import br.app.BancoEveris.response.ContaResponse;
import br.app.BancoEveris.service.ContaService;

@Service
public class ContaServiceImp implements ContaService {
	@Autowired
	private ContaRepository repository;

	@Autowired
	private OperacaoServiceImp _operacaoService;

	public BaseResponse inserir(ContaRequest contaRequest) {
		Conta conta = new Conta();
		ContaResponse base = new ContaResponse();

		base.StatusCode = 400;

		if (contaRequest.getNome() == "") {
			base.Message = "O nome do cliente não foi preenchido.";
			return base;
		}

		if (contaRequest.getCpf() == "") {
			base.Message = "O CPF do cliente não foi preenchido.";
			return base;
		}

		if (contaRequest.getNome().isEmpty()) {
			base.Message = "O Nome do cliente não foi preenchido.";
			return base;
		}

		conta.setSaldo(0.0);
		conta.setNome(contaRequest.getNome());
		conta.setCpf(contaRequest.getCpf());

		UUID uuid = UUID.randomUUID();
		conta.setHash(uuid.toString());

		repository.save(conta);

		base.Message = "Hash Gerado Automaticamente";
		base.setHash(conta.getHash());
		base.setNome(conta.getNome());
		base.setId(conta.getId());

		base.StatusCode = 201;
		base.Message = "Cliente inserida com sucesso.";
		return base;

	}

	public ContaResponse obter(Long id) {
		Optional<Conta> cliente = repository.findById(id);
		ContaResponse response = new ContaResponse();

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
		List<Conta> lista = repository.findAll();
		ContaList response = new ContaList();

		response.setContas(lista);
		response.StatusCode = 200;
		response.Message = "Clientes obtidos com sucesso.";

		return response;
	}

	public BaseResponse atualizar(Long id, ContaRequest contaRequest) {
		Conta conta = new Conta();
		BaseResponse base = new BaseResponse();
		base.StatusCode = 400;

		if (contaRequest.getNome() == "" || contaRequest.getNome() == null) {
			base.Message = "O nome do cliente não foi preenchido.";
			return base;
		}

		if (contaRequest.getCpf() == "" || contaRequest.getCpf() == null) {
			base.Message = "O CPF do cliente não foi preenchido.";
			return base;
		}

		if (contaRequest.getHash() == "" || contaRequest.getHash() == null) {
			base.Message = "O Hash do cliente não foi preenchido.";
			return base;
		}

		conta.setId(id);
		conta.setNome(contaRequest.getNome());
		conta.setCpf(contaRequest.getCpf());
		conta.setHash(contaRequest.getHash());

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

	public ContaResponse Saldo(String hash) {
		ContaResponse response = new ContaResponse();
		response.StatusCode = 400;

		Conta conta = repository.findByHash(hash);

		if (conta == null) {
			response.Message = "Conta não encontrada!!";
			return response;
		}

		double saldo = _operacaoService.Saldo(conta.getId());

		response.setSaldo(saldo);
		response.setNome(conta.getNome());
		response.setHash(conta.getHash());
		response.StatusCode = 200;
		return response;
	}
}
