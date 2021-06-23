package Libreria;


import java.util.Objects;
import java.util.UUID;

public class Libri implements Comparable<Libri>
{

    private String titolo;
    private String sinossi;
    private Autore autore;
    private String ID;

    public Libri(String titolo, String sinossi, Autore autore)
    {

        setTitolo(titolo);
        setSinossi(sinossi);
        setAutore(autore);
        this.ID = UUID.randomUUID().toString();
    }


    public String getID() {
        return ID;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getSinossi() {
        return sinossi;
    }

    public void setSinossi(String sinossi) {
        this.sinossi = sinossi;
    }

    public Autore getAutore() {
        return autore;
    }

    public void setAutore(Autore autore) {
        this.autore = autore;
    }


    @Override
    public int compareTo(Libri o) {
       return this.titolo.compareTo(o.getTitolo());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Libri libri = (Libri) o;
        return Objects.equals(ID, libri.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}
