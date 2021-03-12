package com.vendas.api.dto;

public class FuncionarioDto {
	
	private String id;
	
	private String nome;
	
	private String totalVendas;
	
	private String mediaVendas;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTotalVendas() {
		return totalVendas;
	}

	public void setTotalVendas(String totalVendas) {
		this.totalVendas = totalVendas;
	}

	public String getMediaVendas() {
		return mediaVendas;
	}

	public void setMediaVendas(String mediaVendas) {
		this.mediaVendas = mediaVendas;
	}
	
	

}
