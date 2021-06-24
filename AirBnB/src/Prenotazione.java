import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Prenotazione
{
    private Abitazione abitazione;
    private Utente utente;
    private String ID;
    private LocalDate inizio;
    private LocalDate fine ;
    private List<LocalDate> date = new ArrayList<>();


    public Prenotazione(Abitazione abitazione, Utente utente, String inizio, String fine)
    {
        setID(UUID.randomUUID().toString());
        setAbitazione(abitazione);
        setUtente(utente);
        setInizio(inizio);
        setFine(fine);
        listadate();
    }

    public void listadate()
    {
        LocalDate current = inizio;
        while(current.isBefore(fine))
        {
            date.add(current);
            current = current.plusDays(1);
        }


    }

    public Abitazione getAbitazione() {
        return abitazione;
    }

    public void setAbitazione(Abitazione abitazione) {
        this.abitazione = abitazione;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public LocalDate getInizio() {
        return inizio;
    }

    public void setInizio(String inizio) {
        try
        {
            this.inizio = LocalDate.parse(inizio, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }catch (DateTimeParseException e){
            System.out.println("Data non idonea , inserire data come GG/MM/AAAA");
        }
    }

    public LocalDate getFine() {
        return fine;
    }

    public void setFine(String fine) {
        try
        {
            this.fine = LocalDate.parse(fine, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }catch (DateTimeParseException e)
        {
            System.out.println("Data non idonea , inserire data come GG/MM/AAAA");
        }
    }

    public List<LocalDate> getDate() {
        return date;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prenotazione that = (Prenotazione) o;
        return Objects.equals(ID, that.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}
