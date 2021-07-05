package GestioneAnagrafe;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Persona
{
   private String nome;
   private String cognome;
   private int eta;
   private String codicefiscale;
   private String indirizzo;
   private List<String> figli = new ArrayList<>();

    public Persona(String nome, String cognome, int eta, String codicefiscale, String indirizzo)
    {
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta;
        this.codicefiscale = codicefiscale;
        this.indirizzo = indirizzo;
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

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public String getCodicefiscale() {
        return codicefiscale;
    }

    public void setCodicefiscale(String codicefiscale) {
        this.codicefiscale = codicefiscale;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public List<String> getFigli() {
        return figli;
    }

    public void setFigli(List<String> figli) {
        this.figli = figli;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(codicefiscale, persona.codicefiscale);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codicefiscale);
    }
}
