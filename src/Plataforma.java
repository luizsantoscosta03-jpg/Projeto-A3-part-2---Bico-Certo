import java.util.ArrayList;

public class Plataforma {
    private ArrayList<Servico> servicos = new ArrayList<>();

    public void cadastrar(Servico servico) {
        servicos.add(servico);
    }

    public ArrayList<Servico> getServicos() {
        return servicos;
    }

    public void relatorio() {
        if (servicos.isEmpty()) {
            System.out.println("Nenhum serviço cadastrado.");
            return;
        }

        double totalServicos = 0;
        double totalTransporte = 0;
        double totalAdicional = 0;
        double totalGeral = 0;

        System.out.println("\n===== RELATÓRIO GERAL =====");

        for (Servico s : servicos) {
            s.mostrar();

            totalServicos += s.calcularServico();
            totalTransporte += s.getTransporte();
            totalAdicional += s.calcularAdicional();
            totalGeral += s.calcularTotal();
        }

        System.out.println("\n===== RESUMO =====");
        System.out.println("Quantidade de serviços: " + servicos.size());
        System.out.printf("Total dos serviços: R$ %.2f%n", totalServicos);
        System.out.printf("Total de transporte: R$ %.2f%n", totalTransporte);
        System.out.printf("Total de adicional: R$ %.2f%n", totalAdicional);
        System.out.printf("Total geral: R$ %.2f%n", totalGeral);
    }
}