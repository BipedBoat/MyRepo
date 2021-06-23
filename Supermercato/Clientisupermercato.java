package Supermercato;



import java.util.Objects;

public class Clientisupermercato {

    private int anni;
    private String nome;
    private int idcliente;


    public Clientisupermercato(int anni,String nome) {
        setAnni(anni);
        setNome(nome);
        this.idcliente = hashCode();

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAnni() {
        return anni;
    }

    public void setAnni(int anni) {
        this.anni = anni;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clientisupermercato clienti = (Clientisupermercato) o;
        return Objects.equals(idcliente, clienti.idcliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idcliente);
    }
}


