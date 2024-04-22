package model;

import java.io.Serializable;

public class PessoaJuridica extends Pessoa implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String cnpj;
	

	public PessoaJuridica(Integer id, String nome, String cnpj) {
		super(id, nome);
		this.cnpj = cnpj;
	}
	
	@Override
	public String toString() {
		return String.format("Id: %d\nNome: %s\nCNPJ: %s", getId(), getNome(), this.cnpj);
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	

}
