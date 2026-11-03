import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner leia = new Scanner(System.in);
        Plataforma plataforma = new Plataforma();
        ArrayList<Empresa> empresas = new ArrayList<>();
        ArrayList<Freelancer> freelancers = new ArrayList<>();

        int opcao;

        do {
            System.out.println("\n===== BICO CERTO =====");
            System.out.println("1 - Cadastrar empresa");
            System.out.println("2 - Cadastrar freelancer");
            System.out.println("3 - Publicar serviço (empresa)");
            System.out.println("4 - Ver serviços disponíveis");
            System.out.println("5 - Aceitar serviço (freelancer)");
            System.out.println("6 - Relatório");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = leia.nextInt();

            switch (opcao) {
                case 1: {
                    leia.nextLine();

                    System.out.print("Nome da empresa: ");
                    String nome = leia.nextLine();

                    System.out.print("CPF ou CNPJ: ");
                    String documento = leia.nextLine();

                    System.out.print("Contato: ");
                    String contato = leia.nextLine();

                    Empresa empresa = new Empresa(nome, documento, contato);

                    if (empresa.documentoValido()) {
                        empresas.add(empresa);
                        System.out.println("Empresa cadastrada!");
                    } else {
                        System.out.println("Documento inválido!");
                    }
                    break;
                }

                case 2: {
                    leia.nextLine();

                    System.out.print("Nome do freelancer: ");
                    String nome = leia.nextLine();

                    System.out.print("Área de atuação: ");
                    String area = leia.nextLine();

                    System.out.print("Contato: ");
                    String contato = leia.nextLine();

                    freelancers.add(new Freelancer(nome, area, contato));
                    System.out.println("Freelancer cadastrado!");
                    break;
                }

                case 3: {
                    if (empresas.isEmpty()) {
                        System.out.println("Cadastre uma empresa primeiro (opção 1).");
                        break;
                    }

                    System.out.println("\n===== EMPRESAS =====");
                    for (int i = 0; i < empresas.size(); i++) {
                        System.out.println((i + 1) + " - " + empresas.get(i).getNome());
                    }
                    System.out.print("Qual empresa está publicando o serviço? ");
                    int numEmpresa = leia.nextInt();

                    if (numEmpresa < 1 || numEmpresa > empresas.size()) {
                        System.out.println("Empresa inválida!");
                        break;
                    }
                    Empresa empresaEscolhida = empresas.get(numEmpresa - 1);

                    leia.nextLine();
                    System.out.print("Nome do serviço: ");
                    String nomeServico = leia.nextLine();

                    System.out.print("Valor por hora (R$): ");
                    double valorHora = leia.nextDouble();

                    System.out.print("Horas de trabalho: ");
                    double horas = leia.nextDouble();

                    System.out.print("Valor do transporte (R$): ");
                    double transporte = leia.nextDouble();

                    leia.nextLine();
                    System.out.print("Horário de início (HH:mm): ");
                    String texto = leia.nextLine();

                    LocalTime horario;
                    try {
                        horario = LocalTime.parse(texto);
                    } catch (DateTimeParseException e) {
                        System.out.println("Horário inválido! Use o formato HH:mm, ex: 22:30");
                        break;
                    }

                    Servico servico;
                    if (horario.getHour() >= 22 || horario.getHour() < 6) {
                        servico = new ServicoNoturno(empresaEscolhida, nomeServico, horas,
                                valorHora, transporte, horario);
                    } else {
                        servico = new Servico(empresaEscolhida, nomeServico, horas,
                                valorHora, transporte, horario);
                    }

                    plataforma.cadastrar(servico);

                    System.out.println("\nServiço publicado! Status: ABERTO");
                    System.out.printf("Total previsto: R$ %.2f%n", servico.calcularTotal());
                    break;
                }

                case 4: {
                    boolean achou = false;
                    System.out.println("\n===== SERVIÇOS DISPONÍVEIS =====");
                    for (Servico s : plataforma.getServicos()) {
                        if (s.estaDisponivel()) {
                            s.mostrar();
                            achou = true;
                        }
                    }
                    if (!achou) {
                        System.out.println("Nenhum serviço disponível no momento.");
                    }
                    break;
                }

                case 5: {
                    if (freelancers.isEmpty()) {
                        System.out.println("Cadastre um freelancer primeiro (opção 2).");
                        break;
                    }

                    ArrayList<Servico> disponiveis = new ArrayList<>();
                    for (Servico s : plataforma.getServicos()) {
                        if (s.estaDisponivel()) {
                            disponiveis.add(s);
                        }
                    }
                    if (disponiveis.isEmpty()) {
                        System.out.println("Nenhum serviço disponível no momento.");
                        break;
                    }

                    System.out.println("\n===== QUEM É VOCÊ? =====");
                    for (int i = 0; i < freelancers.size(); i++) {
                        System.out.println((i + 1) + " - " + freelancers.get(i).getNome());
                    }
                    System.out.print("Escolha seu nome: ");
                    int numFreelancer = leia.nextInt();

                    if (numFreelancer < 1 || numFreelancer > freelancers.size()) {
                        System.out.println("Freelancer inválido!");
                        break;
                    }
                    Freelancer escolhido = freelancers.get(numFreelancer - 1);

                    System.out.println("\n===== SERVIÇOS DISPONÍVEIS =====");
                    for (int i = 0; i < disponiveis.size(); i++) {
                        Servico d = disponiveis.get(i);
                        System.out.printf("%d - %s (%s) - R$ %.2f%n", i + 1,
                                d.getNomeServico(), d.getEmpresa().getNome(), d.calcularTotal());
                    }
                    System.out.print("Qual serviço você quer? ");
                    int numServico = leia.nextInt();

                    if (numServico < 1 || numServico > disponiveis.size()) {
                        System.out.println("Serviço inválido!");
                        break;
                    }

                    disponiveis.get(numServico - 1).aceitar(escolhido);
                    System.out.println("Serviço aceito! Boa sorte, " + escolhido.getNome() + "!");
                    break;
                }

                case 6:
                    plataforma.relatorio();
                    break;

                case 0:
                    System.out.println("Programa encerrado.");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        leia.close();
    }
}