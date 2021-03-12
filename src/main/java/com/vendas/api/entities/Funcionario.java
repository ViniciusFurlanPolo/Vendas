package com.vendas.api.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "funcionario")
public class Funcionario implements Serializable {
	
	private static final long srialVersionUID = 1l;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "nome", nullable = false, length = 100)
	private String nome;
	
	@Column(name = "totalVendas", nullable = false)
	private int totalVendas;
	
	@Column(name = "mediaVendas", nullable = false)
	private int mediaVendas;

	public int getMediaVendas() {
		return mediaVendas;
	}

	public void setMediaVendas(int mediaVendas) {
		this.mediaVendas = mediaVendas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getTotalVendas() {
		return totalVendas;
	}

	public void setTotalVendas(int totalVendas) {
		this.totalVendas = totalVendas;
	}
	
	@Override
	public String toString() {
		return "funcionario[" + "id=" + id + ","
				+ "nome=" + nome + ","
				+ "totalVendas=" + totalVendas + "]";
	}

}
