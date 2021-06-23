package ClassificaserieA;

public class Calciatori
{
    private String nome;
    private String cognome;
    private String ID;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID)
    {
        if (ID != null) this.ID = ID;
    }

    public Calciatori(String nome, String cognome, String ID)
    {
        setNome(nome);
        setCognome(cognome);
        setID(ID);
    }
}
