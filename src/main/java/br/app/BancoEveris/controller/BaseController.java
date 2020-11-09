package br.app.BancoEveris.controller;

import br.app.BancoEveris.model.BaseResponse;

public class BaseController {
	
	public BaseResponse errorBase = new BaseResponse();
	public BaseController() {
		errorBase.StatusCode = 500;
		errorBase.Message = "ERRO inesperado. Contate o ADM";
	}
}
