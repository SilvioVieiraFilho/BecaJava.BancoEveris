package br.app.BancoEveris.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.app.BancoEveris.response.*;
import br.app.BancoEveris.service.imp.OperacaoServiceImp;
import br.app.BancoEveris.request.OperacaoRequest;
import br.app.BancoEveris.request.TranferenciaRequest;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/operacao")
public class OperacaoController {
	@Autowired
	private OperacaoServiceImp service;

	@PostMapping(path = "/depositos")
	public ResponseEntity depositar(@RequestBody OperacaoRequest operacaoRequest) {
		try {
			
			BaseResponse response = service.depositar(operacaoRequest);
			
			return ResponseEntity.status(response.StatusCode).body(response);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
		}
	}

	@PostMapping(path = "/saques")
	public ResponseEntity sacar(@RequestBody OperacaoRequest operacaoRequest) {
		try {
			BaseResponse response = service.sacar(operacaoRequest);
			return ResponseEntity.status(response.StatusCode).body(response);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
		}
	}

	@PostMapping(path = "/transferencia")
	public ResponseEntity transferir(@RequestBody TranferenciaRequest operacaoSpec) {
		try {
			BaseResponse response = service.transferir(operacaoSpec);
			return ResponseEntity.status(response.StatusCode).body(response);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
		}
	}

}
