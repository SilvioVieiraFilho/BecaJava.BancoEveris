package br.app.BancoEveris.request;

public class OperacaoRequest {
	private double valor;
	private String hash;

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

}
