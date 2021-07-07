package ArcadeRank;

import java.util.Objects;
import java.util.UUID;

public class Partita
{
    private String idutente;
    private String idvideogioco;
    private int punti;
    private String ID;

    public Partita(String idutente, String idvideogioco, int punti)
    {
        this.idutente = idutente;
        this.idvideogioco = idvideogioco;
        this.punti = punti;
        setID(UUID.randomUUID().toString());
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getIdutente() {
        return idutente;
    }

    public void setIdutente(String idutente) {
        this.idutente = idutente;
    }

    public String getIdvideogioco() {
        return idvideogioco;
    }

    public void setIdvideogioco(String idvideogioco) {
        this.idvideogioco = idvideogioco;
    }

    public int getPunti() {
        return punti;
    }

    public void setPunti(int punti) {
        this.punti = punti;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Partita partita = (Partita) o;
        return Objects.equals(ID, partita.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

    @Override
    public String toString() {
        return "Partita{" +
                "idutente='" + idutente + '\'' +
                ", idvideogioco='" + idvideogioco + '\'' +
                ", punti=" + punti +
                ", ID='" + ID + '\'' +
                '}';
    }
}
