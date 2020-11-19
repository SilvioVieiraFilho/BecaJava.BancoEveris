package br.app.BancoEveris.request;

public class TranferenciaRequest {
	private String hashOrigem;
	private String hashDestino;
	private Double valor;

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getHashOrigem() {
		return hashOrigem;
	}

	public void setHashOrigem(String hashOrigem) {
		this.hashOrigem = hashOrigem;
	}

	public String getHashDestino() {
		return hashDestino;
	}

	public void setHashDestino(String hashDestino) {
		this.hashDestino = hashDestino;
	}

}
