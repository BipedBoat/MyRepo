import java.util.Objects;
import java.util.UUID;

public class Utente
{
    private String ID;
    private String password;
    private String nome;
    private String cognome;
    private String email;
    private String indirizzo;
    private boolean host;
    private boolean superhost;
    private String IDhost;
    private int prenotazioniricevute;
    private int giorniquestomese;
    private double credito;
    private boolean richiestahost = false; //Main

    public Utente(String nome, String cognome, String email, String indirizzo)
    {
        setID(UUID.randomUUID().toString());
        setNome(nome);
        setCognome(cognome);
        setEmail(email);
        setIndirizzo(indirizzo);
        this.superhost=false;
        this.host=false;
        this.prenotazioniricevute=0;
        this.credito=0;

    }


    public void setPassword(String password) {
        this.password = password;
    }

    public double getCredito() {
        return credito;
    }

    public void AddCredito(double credito)
    {
        if (this.credito+credito>=0)
        this.credito = this.credito+credito;
    }

    public int getGiorniquestomese() {
        return giorniquestomese;
    }

    public void setGiorniquestomese(int giorniquestomese) {
        this.giorniquestomese = giorniquestomese;
    }

    public void addPrenotazione()
    {
        this.prenotazioniricevute=this.prenotazioniricevute+1;
        if (this.prenotazioniricevute==100) this.superhost=true;

    }

    public int getPrenotazioniricevute() {
        return prenotazioniricevute;
    }



    public void ApprovaHost()
    {
        this.host=true;
        setIDhost(UUID.randomUUID().toString());
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public boolean isHost() {
        return host;
    }


    public boolean isSuperhost() {
        return superhost;
    }


    public String getIDhost() {
        return IDhost;
    }

    public void setIDhost(String IDhost) {
        this.IDhost = IDhost;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utente utente = (Utente) o;
        return Objects.equals(ID, utente.ID) || Objects.equals(email, utente.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

    public boolean isRichiestahost() {
        return richiestahost;
    }

    public void setRichiestahost(boolean richiestahost) {
        this.richiestahost = richiestahost;
    }
}
