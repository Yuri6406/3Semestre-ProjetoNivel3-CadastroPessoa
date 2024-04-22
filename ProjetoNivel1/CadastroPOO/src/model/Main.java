package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	@SuppressWarnings("unchecked")

	public static void main(String[] args) throws IOException {

		PessoaJuridicaRepo repoJ = new PessoaJuridicaRepo();
		PessoaFisicaRepo repoF = new PessoaFisicaRepo();

		String caminhoF = "arquivoPesF.txt";
		List<PessoaFisica> listaF = new ArrayList<PessoaFisica>();

		String caminhoJ = "arquivoJuridico.txt";
		List<PessoaJuridica> lista = new ArrayList<PessoaJuridica>();

		Scanner leia = new Scanner(System.in);
		while (true) {

			System.out.println("Cadastro De Pessoas");
			System.out.println();
			System.out.println("[1] Incluir\n" + "[2] Alterar\n" + "[3] Excluir\n" + "[4] Exibir através do Id\n"
					+ "[5] Exibir Todos Cadastrados\n" + "[6] Salvar dados de Pessoa\n[7]"
					+ " Recuperar dados de Pessoa\n" + "[0] Finalizar a execução ");
			System.out.println();
			System.out.println("Escolha uma das opções á cima: ");
			int n = 100;

			Integer opc = leia.nextInt();

			leia.nextLine();
			if (opc >= 1 && opc <= 7) {

				System.out.println("Digite apenas a letra F ou J :\n[F]Física\n[J]Jurídica? ");
				char cadast = leia.nextLine().toUpperCase().charAt(0);

				switch (opc) {
				case 1:
					if (cadast == 'F') {
						System.out.println("Id:");
						int id = leia.nextInt();
						leia.nextLine();
						System.out.println("Nome:");
						String nome = leia.nextLine();
						System.out.println("CPF:");
						String cpf = leia.nextLine();
						System.out.println("Idade:");
						int idade = leia.nextInt();
						leia.nextLine();

						PessoaFisica p1 = new PessoaFisica(id, nome, cpf, idade);

						int i = 0;
						if (i == 0) {
							repoF.inserirPessoaVazio(n, p1);
						} else {
							PessoaFisica p2 = new PessoaFisica(id, nome, cpf, idade);
							repoF.adicionarPessoa(i, p2);
						}
						i++;

						System.out.println("Cadastrando Pessoa Física");

					} else if (cadast == 'J') {

						System.out.println("Id:");
						int id = leia.nextInt();
						leia.nextLine();
						System.out.println("Nome:");
						String nome = leia.nextLine();
						System.out.println("CNPJ:");
						String cnpj = leia.nextLine();
						PessoaJuridica p1 = new PessoaJuridica(id, nome, cnpj);
						
						int i = 0;
						if (i == 0) {
							repoJ.inserirPessoaVazio(n, p1);
						} else {
							PessoaJuridica p2 = new PessoaJuridica(id, nome, cnpj);
							repoJ.adicionarPessoa(i, p2);
						}
						i++;
						
						System.out.println("Cadastrando Pessoa Jurídica");

					}
					break;
				case 2:
					if (cadast == 'F') {
						repoF.obterTodos();
						System.out.println("Informe o Id da pessoa física que deseja alterar");
						System.out.println("Id:");
						int id = leia.nextInt();
						leia.nextLine();
						System.out.println("Novo pessoa física\nNome:");
						String nome = leia.nextLine();
						System.out.println("CPF:");
						String cpf = leia.nextLine();
						System.out.println("idade");
						int idade = leia.nextInt();
						leia.nextLine();

						repoF.alterar(new PessoaFisica(id, nome, cpf, idade));

						System.out.println("Alterando Pessoa Física");

					} else if (cadast == 'J') {
						repoJ.obterTodos();
						System.out.println("Informe o Id da pessoa juridaca que deseja alterar");
						System.out.println("Id:");
						int id = leia.nextInt();
						leia.nextLine();
						System.out.println("Novo pessoa juridica\nNome:");
						String nome = leia.nextLine();
						System.out.println("CNPJ:");
						String cnpj = leia.nextLine();

						repoJ.alterar(new PessoaJuridica(id, nome, cnpj));

						System.out.println("Alterando Pessoa Jurídica");
					}
					break;
				case 3:

					if (cadast == 'F') {
						repoF.obterTodos();
						System.out.println("Informe o Id da pessoa que deseja excluir");

						Integer id = leia.nextInt();
						leia.nextLine();
						repoF.excluir(new PessoaFisica(id, null, null, cadast));

						System.out.println("Excluindo Pessoa Física");
					} else if (cadast == 'J') {

						Integer id = leia.nextInt();
						leia.nextLine();
						repoJ.excluir(new PessoaJuridica(id, null, null));

						System.out.println("Excluindo Pessoa Jurídica");
					}
					break;
				case 4:

					if (cadast == 'F') {
						System.out.println("Digite o Id de pessoa física que deseja obter os dados:");
						int id = leia.nextInt();
						repoF.obter(id);

						System.out.println("Exibindo Pessoa Física através do Id ");
					} else if (cadast == 'J') {

						System.out.println("Digite o Id de pessoa jurídica que deseja obter os dados:");
						int id = leia.nextInt();
						repoJ.obter(id);

						System.out.println("Exibindo Pessoa Jurídica através do Id ");
					}
					break;
				case 5:

					if (cadast == 'F') {

						repoF.obterTodos();
						System.out.println("Exibindo todas Pessoas Física");
					} else if (cadast == 'J') {

						repoJ.obterTodos();
						System.out.println("Exibindo todas Pessoas Jurídica");
					}
					break;

				case 6:

					if (cadast == 'F') {

						listaF.add(new PessoaFisica(4, "Yuri", "41992258899", 11));
						listaF.add(new PessoaFisica(5, "Ariane", "433445258899", 29));
						listaF.add(new PessoaFisica(6, "Isabelly", "9927258899", 28));

						boolean gravouf = PessoaFisicaRepo.persistir(caminhoF, listaF);

						System.out.println("Salvando dados de Pessoa Física");
					} else if (cadast == 'J') {

						lista.add(new PessoaJuridica(1, "Yuri", "41992258899"));
						lista.add(new PessoaJuridica(2, "Ariane", "433445258899"));
						lista.add(new PessoaJuridica(3, "Isabelly", "9927258899"));
						lista.add(new PessoaJuridica(4, "belly", "9999999999"));

						boolean gravou = PessoaJuridicaRepo.persistir(caminhoJ, lista);

						System.out.println("Salvano dados de Pessoa Jurídica");
					}
					break;

				case 7:

					if (cadast == 'F') {
						List<PessoaFisica> objF = (List<PessoaFisica>) PessoaFisicaRepo.recuperar(caminhoF);
						for (PessoaFisica pessoaFisica : objF) {
							System.out.println(pessoaFisica);
						}

						System.out.println("Recuperando dados de Pessoa Física");

					} else if (cadast == 'J') {
						List<PessoaJuridica> obj = (List<PessoaJuridica>) PessoaJuridicaRepo.recuperar(caminhoJ);
						for (PessoaJuridica pessoaJuridica : obj) {
							System.out.println(pessoaJuridica);
						}

						System.out.println("Recuperando dados de Pessoa Jurídica");
					}
					break;
				}
			} else if (opc == 0) {
				System.out.println("Execução finalizada");
				return;

			} else {
				System.out.println("Opção Invalida");
			}

			while (true) {
				System.out.println("Deseja realizar novas operações ? [S/N]");
				char resp = leia.nextLine().toUpperCase().charAt(0);

				if (resp == 'N') {
					System.out.println("Programa encerrado");
					return;
				} else if (resp != 'S') {
					System.out.println("Opção invalida. Digite S para continuar ou N para não continuar.");
				} else {
					break;
				}
			}
		}

	}

}
