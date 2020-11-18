package br.app.BancoEveris.service;

import br.app.BancoEveris.request.ContaRequest;
import br.app.BancoEveris.response.BaseResponse;
import br.app.BancoEveris.response.ContaList;
import br.app.BancoEveris.response.ContaResponse;

public interface ContaService {
	
	public BaseResponse inserir(ContaRequest contaRequest);
	
	public ContaResponse obter(Long id);

	public ContaList listar();
	
	public BaseResponse atualizar(Long id, ContaRequest contaRequest);

	public BaseResponse deletar(Long id);
	
	public ContaResponse Saldo(String hash);
}
