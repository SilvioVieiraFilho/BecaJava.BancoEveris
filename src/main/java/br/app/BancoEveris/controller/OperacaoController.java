package br.app.BancoEveris.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.app.BancoEveris.model.BaseResponse;
import br.app.BancoEveris.service.OperacaoService;
import br.app.BancoEveris.spec.OperacaoSpec;
import br.app.BancoEveris.spec.TranferenciaSpec;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/operacao")
public class OperacaoController {
	@Autowired
	private OperacaoService service;

	@PostMapping(path = "/depositos")
	public ResponseEntity depositar(@RequestBody OperacaoSpec operacaoSpec) {
		try {
			BaseResponse response = service.depositar(operacaoSpec);
			return ResponseEntity.status(response.StatusCode).body(response);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
		}
	}

	@PostMapping(path = "/saques")
	public ResponseEntity sacar(@RequestBody OperacaoSpec operacaoSpec) {
		try {
			BaseResponse response = service.sacar(operacaoSpec);
			return ResponseEntity.status(response.StatusCode).body(response);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
		}
	}

	@PostMapping(path = "/transferencia")
	public ResponseEntity transferir(@RequestBody TranferenciaSpec operacaoSpec) {
		try {
			BaseResponse response = service.transferir(operacaoSpec);
			return ResponseEntity.status(response.StatusCode).body(response);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
		}
	}

}
