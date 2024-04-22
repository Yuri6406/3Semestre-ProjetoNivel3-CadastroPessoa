package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PessoaJuridicaRepo {

	private PessoaJuridica[] pessoaJuridicaArray;

	public PessoaJuridica[] getPessoaJuridica() {
		return pessoaJuridicaArray;
	}

	public void setPessoaJuridica(PessoaJuridica[] pessoaJuridicaArray) {
		this.pessoaJuridicaArray = pessoaJuridicaArray;
	}

	public Boolean vetorVazio() {
		if (this.pessoaJuridicaArray.length == 0) {
			System.out.println("Array Vazio!!!");
			return true;
		} else {
			return false;
		}
	}

	public void inserirPessoaVazio(Integer n, PessoaJuridica... pessoaJuridica) {
		if (n == 0) {
			System.out.println("Adição invalida");
		} else {
			this.pessoaJuridicaArray = new PessoaJuridica[n];
			int i = 0;
			for (PessoaJuridica p : pessoaJuridica) {
				if (i > (this.pessoaJuridicaArray.length - 1)) {
					throw new ArrayIndexOutOfBoundsException("Número de registro excede o tamanho do Array!!!");
				} else {
					this.pessoaJuridicaArray[i] = new PessoaJuridica(p.getId(), p.getNome(), p.getCnpj());
					i++;
				}
			}
		}
	}

	public void adicionarPessoa(Integer n, PessoaJuridica... pessoaJuridicas) {
		if (this.pessoaJuridicaArray.length == 0) {
			System.out.println("Necessario criar o Array primeiro!!!");
		} else {
			int i = 0;
			while ((i < this.pessoaJuridicaArray.length) && (this.pessoaJuridicaArray[i] != null)) {
				i++;
			}
			if (i > (this.pessoaJuridicaArray.length - 1)) {
				System.out.println("Array cheio!!!");
			} else {
				if ((this.pessoaJuridicaArray.length - i) < n) {
					System.out.println("Numero de registros excede o tamanho do Array!!!");
				} else {
					for (PessoaJuridica p : pessoaJuridicas) {
						this.pessoaJuridicaArray[i] = new PessoaJuridica(p.getId(), p.getNome(), p.getCnpj());
						i++;
					}
				}
			}
		}
	}

	public void alterar(PessoaJuridica pessoaJuridica) {
		int encont = 0;
		if (!vetorVazio()) {
			for (int i = 0; i < this.pessoaJuridicaArray.length; i++) {
				if ((this.pessoaJuridicaArray[i] != null)
						&& (this.pessoaJuridicaArray[i].getId() == (pessoaJuridica.getId()))) {
					this.pessoaJuridicaArray[i] = pessoaJuridica;
					encont++;
				}
			}
		}
		if (encont == 0) {
			System.out.println("Pessoa nao encontrada!!!");
		}
	}

	public void excluir(PessoaJuridica id) {
		int encont = 0;
		if (!vetorVazio()) {
			for (int i = 0; i < this.pessoaJuridicaArray.length; i++) {
				if ((this.pessoaJuridicaArray[i] != null) && (this.pessoaJuridicaArray[i].getId().equals(id.getId()))) {
					encont++;
					for (int j = i; j < this.pessoaJuridicaArray.length; j++) {
						if (this.pessoaJuridicaArray[j] != null)
							pessoaJuridicaArray[j] = pessoaJuridicaArray[j + 1];
					}
				}
			}
		}
		if (encont == 0) {
			System.out.println("Pessoa nao encontrada!!!");
		}
	}

	public void obter(int id) {
		if (!vetorVazio()) {
			for (int i = 0; i < this.pessoaJuridicaArray.length; i++) {
				if (pessoaJuridicaArray[i] != null) {
					System.out.println(pessoaJuridicaArray[id]);
				} else {
				}
			}
		}
	}

	public void obterTodos() {
		if (!vetorVazio()) {
			for (int i = 0; i < this.pessoaJuridicaArray.length; i++) {
				if (pessoaJuridicaArray[i] != null) {
					System.out.println(pessoaJuridicaArray[i]);
				} else {
				}
			}
		}
	}

	public static boolean persistir(String nomeArquivo, Object obj) throws IOException {
		File arquivo = new File(nomeArquivo);

		if (!arquivo.exists()) {
			try {
				arquivo.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}

		try {
			FileOutputStream escreverArq = new FileOutputStream(arquivo);
			ObjectOutputStream inserirObj = new ObjectOutputStream(escreverArq);
			inserirObj.writeObject(obj);

			inserirObj.flush();
			escreverArq.flush();

			inserirObj.close();
			escreverArq.close();
			System.out.println("Dados de Pessoa Juridica Armazenados.");

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public static Object recuperar(String caminho) {
		File arquivo = new File(caminho);

		try {
			FileInputStream recDados = new FileInputStream(arquivo);
			ObjectInputStream objInput = new ObjectInputStream(recDados);

			Object retorno = objInput.readObject();

			objInput.close();
			recDados.close();
			System.out.println("Dados de Pessoa Juridica Recuperados.");
			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
