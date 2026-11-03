import java.time.LocalTime;

public class ServicoNoturno extends Servico {

    public ServicoNoturno(Empresa empresa, String nomeServico, double horas,
                          double valorHora, double transporte, LocalTime horario) {
        super(empresa, nomeServico, horas, valorHora, transporte, horario);
    }

    @Override
    public double calcularAdicional() {
        return calcularServico() * 0.05;
    }
}
