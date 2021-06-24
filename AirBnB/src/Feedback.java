import java.util.Objects;
import java.util.UUID;

public class Feedback
{
    private Abitazione abitazione;
    private String ID;
    private String titolo;
    private String testo;
    private int punteggio;
    Utente recensore;

    public Feedback(String titolo, String testo, int punteggio)
    {
        setID(UUID.randomUUID().toString());
        setTitolo(titolo);
        setTesto(testo);
        setPunteggio(punteggio);
    }

    public Abitazione getAbitazione() {
        return abitazione;
    }

    public void setAbitazione(Abitazione abitazione) {
        this.abitazione = abitazione;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public int getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(int punteggio) {
        if (punteggio<6&&punteggio>0) this.punteggio = punteggio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return Objects.equals(ID, feedback.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "Titolo='" + titolo + '\n' + ", Recensione='" + testo + '\n' + ", Punteggio=" + punteggio +'}';
    }

    public Utente getRecensore() {
        return recensore;
    }

    public void setRecensore(Utente recensore) {
        this.recensore = recensore;
    }
}
