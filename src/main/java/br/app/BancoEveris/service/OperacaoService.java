package br.app.BancoEveris.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.app.BancoEveris.response.BaseResponse;
import br.app.BancoEveris.response.ContaResponse;
import br.app.BancoEveris.model.Conta;
import br.app.BancoEveris.model.Operacao;
import br.app.BancoEveris.repository.ContaRepository;
import br.app.BancoEveris.repository.OperacaoRepository;
import br.app.BancoEveris.request.ContaRequest;
import br.app.BancoEveris.request.OperacaoRequest;
import br.app.BancoEveris.request.TranferenciaRequest;

@Service
public class OperacaoService {
	@Autowired
	private OperacaoRepository repository;

	@Autowired
	private ContaRepository repositoryConta;

	public BaseResponse depositar(OperacaoRequest operacaoRequest) {

		Conta conta = repositoryConta.findByHash(operacaoRequest.getHash());

		Operacao op = new Operacao();
		BaseResponse base = new BaseResponse();
		base.StatusCode = 400;

		if (operacaoRequest.getTipo() == "") {
			base.Message = "Insira o tipo D (deposito)";
			return base;
		}

		if (operacaoRequest.getValor() <= 0) {

			base.Message = "O Valor do cliente não foi preenchido.";

			return base;

		}

		op.setTipo(operacaoRequest.getTipo());
		op.setValor(operacaoRequest.getValor());
		op.setContaOrigem(conta);

		conta.setSaldo(conta.getSaldo() + operacaoRequest.getValor());

		repositoryConta.save(conta);

		repository.save(op);

		base.StatusCode = 200;
		base.Message = "Deposito realizado com sucesso.";
		return base;
	}

	public BaseResponse sacar(OperacaoRequest operacaoSpecSacar) {

		Conta conta = repositoryConta.findByHash(operacaoSpecSacar.getHash());

		Operacao op = new Operacao();

		BaseResponse base = new BaseResponse();

		base.StatusCode = 400;

		if (operacaoSpecSacar.getValor() > conta.getSaldo()) {

			base.Message = "Saque nao pode ser efetuado  valor do saldo menor ";
			return base;

		}

		op.setValor(operacaoSpecSacar.getValor());
		op.setTipo(operacaoSpecSacar.getTipo());
		op.setContaOrigem(conta);

		conta.setSaldo(conta.getSaldo() - operacaoSpecSacar.getValor());

		repositoryConta.save(conta);
		repository.save(op);

		base.StatusCode = 200;
		base.Message = "Saque realizado com sucesso.";

		return base;
	}

	public BaseResponse transferir(TranferenciaRequest operacaoSpec) {

		Conta conta1 = repositoryConta.findByHash(operacaoSpec.getHashOrigem());
		Conta conta2 = repositoryConta.findByHash(operacaoSpec.getHashDestino());

		ContaResponse contaa = new ContaResponse();

		BaseResponse base = new BaseResponse();

		Operacao operacao = new Operacao();

		
		if (conta1 == null) {
			base.StatusCode = 404;
			base.Message = "Conta origem não foi encontrada tente novamente";
			return base;
		}
		if (conta2 == null) {
			base.StatusCode = 404;
			base.Message = "A conta  destino não foi  encontrada  tente novamente";
			return base;
		}
		
		base.StatusCode = 400;
		
		if (operacaoSpec.getValor() == 0) {

			base.Message = "O valor Esta abaixo do limite Tente novamente ";

			return base;

		}

		if (operacaoSpec.getValor() > conta1.getSaldo()) {

			base.Message = "O valor Inserido esta Abaixo do seu Saldo Tente Novamente";

			return base;
		}

		conta1.setSaldo(conta1.getSaldo() - operacaoSpec.getValor());
		conta2.setSaldo(conta2.getSaldo() + operacaoSpec.getValor());

		operacao.setContaOrigem(conta1);
		operacao.setContaDestino(conta2);

		operacao.setValor(operacaoSpec.getValor());
		operacao.setTipo(operacaoSpec.getTipo());

		repositoryConta.save(conta1);
		repositoryConta.save(conta2);

		repository.save(operacao);

		base.StatusCode = 200;
		base.Message = "Transferencia realizada com sucesso.";
		return base;
	}

	public double Saldo(Long contaId) {

		double saldo = 0;

		Conta contaOrigem = new Conta();
		contaOrigem.setId(contaId);

		Conta contaDestino = new Conta();
		contaDestino.setId(contaId);

		
		
		List<Operacao> lista = repository.findOperacoesPorConta(contaId);

		for (Operacao o : lista) {
			switch (o.getTipo()) {
			case "D":
				saldo += o.getValor();
				break;
			case "S":
				saldo -= o.getValor();
				break;
			case "T":
				
				if(o.getContaDestino().getId() == contaId ) {
					
				saldo -= o.getValor();
				}
				if(o.getContaOrigem().getId() == contaId) {
					saldo += o.getValor();
				}
					break;
			default:
				break;
			}
		}

		
		
		return saldo;
	}

}
