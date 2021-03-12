package com.vendas.api.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "venda")
public class Venda implements Serializable{
	
	private static final long srialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "dataVenda", nullable = false)
	private Date dataVenda;
	
	@Column(name = "valor", nullable = false)
	private double valor;
	
	@JsonBackReference
   	@ManyToOne(fetch = FetchType.EAGER)
	private Funcionario funcionario;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	
	@Override
   	public String toString() {
         	return "venda[" + "id=" + id + ","
                       	+ "dataVenda=" + dataVenda + ","
                       	+ "valor=" + valor + ","
                       	+ "funcionario=" + funcionario + "]";
	}

}
