import java.io.PipedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Abitazione

{
    private String nome;
    private String indirizzo;
    private int npostiletto;
    private int piano;
    private int nlocali;
    private String ID;
    private Annuncio annuncio;
    private String IDhost;
    private List<Feedback> listarecensioni = new ArrayList<>();
    private double mediarecensioni;

    public Abitazione(String nome, String indirizzo, int npostiletto, int piano, int nlocali)
    {
       setID(UUID.randomUUID().toString());
       setIndirizzo(indirizzo);
       setNome(nome);
       setNlocali(nlocali);
       setNpostiletto(npostiletto);
       setPiano(piano);
    }

    public String getIDhost() {
        return IDhost;
    }

    public void setIDhost(String host) {
        this.IDhost = host;
    }

    public Annuncio getAnnuncio() {
        return annuncio;
    }

    public void setAnnuncio(Annuncio annuncio) {
        this.annuncio = annuncio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public int getNpostiletto() {
        return npostiletto;
    }

    public void setNpostiletto(int npostiletto) {
        this.npostiletto = npostiletto;
    }

    public int getPiano() {
        return piano;
    }

    public void setPiano(int piano) {
        this.piano = piano;
    }

    public int getNlocali() {
        return nlocali;
    }

    public void setNlocali(int nlocali) {
        this.nlocali = nlocali;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public List<Feedback> getListarecensioni() {
        return listarecensioni;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Abitazione that = (Abitazione) o;
        return Objects.equals(ID, that.ID);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(ID);
    }

    public double getMediarecensioni() {
        return mediarecensioni;
    }

    public void setMediarecensioni() {
        if (listarecensioni!=null)
        { double i=0;
            for (Feedback f:listarecensioni)
            {
                i=i+f.getPunteggio();
            }
           this.mediarecensioni=(i/listarecensioni.size());
        }
    }

    public void setListarecensioni(List<Feedback> listarecensioni) {
        this.listarecensioni = listarecensioni;
    }
}
