package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PessoaFisicaRepo {

	private PessoaFisica[] pessoaFisicaArray;

	public PessoaFisica[] getPessoaFisicacaArray() {
		return pessoaFisicaArray;
	}

	public void setPessoaFisicacaArray(PessoaFisica[] pessoaFisicacaArray) {
		this.pessoaFisicaArray = pessoaFisicacaArray;
	}

	public Boolean vetorVazio() {
		if (this.pessoaFisicaArray.length == 0) {
			System.out.println("Array Vazio!!!");
			return true;
		} else {
			return false;
		}
	}

	public void inserirPessoaVazio(Integer n, PessoaFisica... pessoaFisica) {
		if (n == 0) {
			System.out.println("Adição invalida");
		} else {
			this.pessoaFisicaArray = new PessoaFisica[n];
			int i = 0;
			for (PessoaFisica p : pessoaFisica) {
				if (i > (this.pessoaFisicaArray.length)) {
					throw new ArrayIndexOutOfBoundsException("Número de registro excede o tamanho do Array!!!");
				} else {
					this.pessoaFisicaArray[i] = new PessoaFisica(p.getId(), p.getNome(), p.getCpf(), p.getIdade());
					i++;
				}
			}
		}
	}

	public void adicionarPessoa(Integer n, PessoaFisica... pessoaFisica) {
		if (this.pessoaFisicaArray.length == 0) {
			System.out.println("Necessario criar o Array primeiro!!!");
		} else {
			int i = 0;
			while ((i < this.pessoaFisicaArray.length) && (this.pessoaFisicaArray[i] != null)) {
				i++;
			}
			if (i > (this.pessoaFisicaArray.length - 1)) {
				System.out.println("Array cheio!!!");
			} else {
				if ((this.pessoaFisicaArray.length - i) < n) {
					System.out.println("Numero de registros excede o tamanho do Array!!!");
				} else {
					for (PessoaFisica p : pessoaFisica) {
						this.pessoaFisicaArray[i] = new PessoaFisica(p.getId(), p.getNome(), p.getCpf(), p.getIdade());
						i++;
					}
				}
			}
		}
	}

	public void alterar(PessoaFisica pessoaFisica) {
		int encont = 0;
		if (!vetorVazio()) {
			for (int i = 0; i < this.pessoaFisicaArray.length; i++) {
				if ((this.pessoaFisicaArray[i] != null)
						&& (this.pessoaFisicaArray[i].getId() == (pessoaFisica.getId()))) {
					this.pessoaFisicaArray[i] = pessoaFisica;
					encont++;
				}
			}
		}
		if (encont == 0) {
			System.out.println("Pessoa nao encontrada!!!");
		}
	}

	public void excluir(PessoaFisica id) {
		int encont = 0;
		if (!vetorVazio()) {
			for (int i = 1; i < this.pessoaFisicaArray.length; i++) {
				if ((this.pessoaFisicaArray[i] != null) && (this.pessoaFisicaArray[i].getId().equals(id.getId()))) {
					encont++;
					for (int j = i; j < this.pessoaFisicaArray.length; j++) {
						if (this.pessoaFisicaArray[j] != null)
							pessoaFisicaArray[j] = pessoaFisicaArray[j];
					}
				}
			}

			if (encont == 0) {
				System.out.println("Pessoa nao encontrada!!!");
			}
		}
	}

	public void obter(int id) {
		if (!vetorVazio()) {
			for (int i = 0; i < this.pessoaFisicaArray.length; i++) {
				if (pessoaFisicaArray[i] != null) {
					System.out.println(pessoaFisicaArray[id + 1]);
				} else {
				}
			}
		}
	}

	public void obterTodos() {
		if (!vetorVazio()) {
			for (int i = 0; i < this.pessoaFisicaArray.length; i++) {
				if (pessoaFisicaArray[i] != null) {
					System.out.println(pessoaFisicaArray[i]);
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
			System.out.println("Dados de Pessoa Fisica Armazenados.");

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
			System.out.println("Dados de Pessoa Fisica Recuperados.");
			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
