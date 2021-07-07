package ArcadeRank;

import java.util.HashMap;
import java.util.UUID;

public class Videogioco
{
    private String ID;
    private String nome;
    private int difficolta;
    private HashMap<Utente,Integer> punteggi= new HashMap<>();

    public Videogioco(String nome, int difficolta)
    {
        setID(UUID.randomUUID().toString());
        setNome(nome);
        setdifficolta(difficolta);

    }

    public HashMap<Utente, Integer> getPunteggi()
    {
        return punteggi;
    }

    public void setPunteggi(HashMap<Utente, Integer> punteggi)
    {
        this.punteggi = punteggi;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public int getdifficolta()
    {
        return difficolta;
    }

    public void setdifficolta(int difficolta)
    {
        if (difficolta<=5&&difficolta>=0) this.difficolta = difficolta;
        else this.difficolta = 3;
    }

    @Override
    public String toString() {
        return "Videogioco{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
