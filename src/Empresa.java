public class Empresa {
    private String nome;
    private String documentos;
    private  String contato;

    public Empresa(String nome, String documentos, String contato){
        this.nome = nome;
        this.documentos = documentos.replaceAll("[^0-9]","");
        this.contato = contato;
    }
public String getTipoDocumentos(){
    if (documentos.length()==11) {
        return "CPF";
    }else if (documentos.length()== 14) {
        return "CNPJ";
    }
    return "Invalido";

    }
public boolean documentoValido(){
        return !getTipoDocumentos().equals("Invalido");

}
public String getNome(){
        return nome;

}
public void mostrar(){
    System.out.println("Emprsa:" + nome);
    System.out.println(getTipoDocumentos() + ":" + documentos);
    System.out.println("Contatos: " + contato);
            System.out.println("Opção inválida.");
}
}


