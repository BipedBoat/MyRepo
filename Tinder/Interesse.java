package Tinder;

import java.util.Objects;
import java.util.UUID;

public class Interesse
{
    private String codice;
    private String nome;

    public Interesse(String nome)
    {
        setCodice(UUID.randomUUID().toString());
        setNome(nome);
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interesse interesse = (Interesse) o;
        return Objects.equals(codice, interesse.codice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codice);
    }
}
