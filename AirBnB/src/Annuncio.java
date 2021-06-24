import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;


public class Annuncio
{
    private double prezzo;
    private LocalDate inizio;
    private LocalDate fine;
    private List<LocalDate> date = new ArrayList<>();

    public Annuncio(double prezzo, String inizio, String fine)
    {
        setPrezzo(prezzo);
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


    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
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

    public void setDate(List<LocalDate> date) {
        this.date = date;
    }

}
