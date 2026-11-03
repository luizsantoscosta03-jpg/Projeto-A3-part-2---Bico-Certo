import java.time.LocalTime;

public class Servico {
    private Empresa empresa;
    private String nomeServico;
    private double horas;
    private double valorHora;
    private double transporte;
    private LocalTime horario;
    private StatusServico status = StatusServico.ABERTO;
    private Freelancer freelancer;

    public Servico(Empresa empresa, String nomeServico, double horas,
                   double valorHora, double transporte, LocalTime horario) {
        this.empresa = empresa;
        this.nomeServico = nomeServico;
        this.horas = horas;
        this.valorHora = valorHora;
        this.transporte = transporte;
        this.horario = horario;
    }

    public Empresa getEmpresa() { return empresa; }
    public String getNomeServico() { return nomeServico; }
    public double getHoras() { return horas; }
    public double getValorHora() { return valorHora; }
    public double getTransporte() { return transporte; }
    public LocalTime getHorario() { return horario; }
    public StatusServico getStatus() { return status; }
    public Freelancer getFreelancer() { return freelancer; }

    public boolean estaDisponivel() {
        return status == StatusServico.ABERTO;
    }

    public void aceitar(Freelancer freelancer) {
        this.freelancer = freelancer;
        this.status = StatusServico.EM_ANDAMENTO;
    }

    public double calcularServico() {
        return horas * valorHora;
    }

    public double calcularAdicional() {
        return 0;
    }

    public double calcularTotal() {
        return calcularServico() + calcularAdicional() + transporte;
    }

    public void mostrar() {
        System.out.println("\nEmpresa: " + empresa.getNome());
        System.out.println("Serviço: " + nomeServico);
        System.out.println("Status: " + status);
        if (freelancer != null) {
            System.out.println("Freelancer: " + freelancer.getNome());
        }
        System.out.println("Horas trabalhadas: " + horas);
        System.out.println("Horário: " + horario);

        System.out.printf("Valor do serviço: R$ %.2f%n", calcularServico());
        System.out.printf("Transporte: R$ %.2f%n", transporte);
        System.out.printf("Adicional noturno: R$ %.2f%n", calcularAdicional());
        System.out.printf("Total: R$ %.2f%n", calcularTotal());
    }
}