package br.app.BancoEveris.service;

import br.app.BancoEveris.request.OperacaoRequest;
import br.app.BancoEveris.request.TranferenciaRequest;
import br.app.BancoEveris.response.BaseResponse;

public interface OperacaoService {

	public BaseResponse depositar(OperacaoRequest operacaoRequest);

	public BaseResponse sacar(OperacaoRequest operacaoSpecSacar);

	public BaseResponse transferir(TranferenciaRequest operacaoSpec);

	public double Saldo(Long contaId);

	
	
	
}
