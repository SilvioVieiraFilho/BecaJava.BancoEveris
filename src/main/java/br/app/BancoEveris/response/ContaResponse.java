package br.app.BancoEveris.response;

public class ContaResponse extends BaseResponse{

private long id ;
private String hash;
private  String nome;	
private String cpf;
private Double saldo;


public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getHash() {
	return hash;
}
public void setHash(String hash) {
	this.hash = hash;
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public String getCpf() {
	return cpf;
}
public void setCpf(String cpf) {
	this.cpf = cpf;
}
public Double getSaldo() {
	return saldo;
}
public void setSaldo(Double saldo) {
	this.saldo = saldo;
}



	
	
	
	



}
