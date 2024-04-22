package model;

import java.io.Serializable;

public class PessoaFisica extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	private String cpf;
	private int idade;

	public PessoaFisica(Integer id, String nome, String cpf, int idade) {
		super(id, nome);
		this.cpf = cpf;
		this.idade = idade;
	}

	@Override
	public String toString() {
		return String.format("Id: %d\nNome: %s\nCPF: %s\nIdade: %d ", getId(), getNome(), this.cpf, this.idade);
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}
}
