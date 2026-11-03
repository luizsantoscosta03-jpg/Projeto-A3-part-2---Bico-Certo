public class Freelancer {
    private String nome;
    private String area;
    private String contato;

    public Freelancer(String nome, String area, String contato) {
        this.nome = nome;
        this.area = area;
        this.contato = contato;
    }

    public String getNome() {
        return nome;
    }

    public String getArea() {
        return area;
    }

    public void mostrar() {
        System.out.println("Freelancer: " + nome);
        System.out.println("Área: " + area);
        System.out.println("Contato: " + contato);
    }
}