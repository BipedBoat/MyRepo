package Libreria;


import java.util.Objects;
import java.util.UUID;

public class Autore implements Comparable<Autore>
{

    private String nome;
    private String cognome;
    private String ID;


    public Autore(String nome, String cognome)
    {
        setNome(nome);
        setCognome(cognome);
        this.ID = UUID.randomUUID().toString();

    }


    public String getID() {
        return ID;
    }



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


    @Override
    public int compareTo(Autore o) {
       return (this.cognome+this.nome).compareTo(o.getCognome()+o.getNome());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autore autore = (Autore) o;
        return Objects.equals(ID, autore.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}
