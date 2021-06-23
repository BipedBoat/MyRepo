package Negozio;

import java.util.Objects;

public class Clienti
{
    private int anni;
    private int idcliente;
    private int punticarta;

    public Clienti(int anni, int punticarta) {
        this.anni = anni;
        this.idcliente = hashCode();
        this.punticarta = punticarta;
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

    public int getPunticarta() {
        return punticarta;
    }

    public void setPunticarta(int punticarta) {
        this.punticarta = punticarta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clienti clienti = (Clienti) o;
        return Objects.equals(idcliente, clienti.idcliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idcliente);
    }
}
