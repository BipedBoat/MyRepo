import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.*;

public class AirBnB
{
    private Map<Utente, HashSet<Abitazione>> hostabitazioni = new HashMap<>();
    private Map<Abitazione,HashSet<Prenotazione>> abitazioneprenotazioni = new HashMap();
    private Map<String,Utente> utenti= new HashMap<>();
    private Map<String,Abitazione> abitazioni= new HashMap<>(); //Main
    private Map<Utente,HashSet<Feedback>> utenterecensioni= new HashMap<>(); //Main
    private Map<Utente,LinkedList<Prenotazione>> utenteprenotazioni = new HashMap<>();

public void nuovoutente(Utente utente)
{
    utenti.put(utente.getID(),utente);
    LinkedList<Prenotazione> vuota = new LinkedList<>();
    utenteprenotazioni.put(utente,vuota);
    HashSet<Feedback> vuoto = new HashSet<>();//main
    utenterecensioni.put(utente,vuoto);//main

}

public void aggiungiAbitazione(Utente utente ,Abitazione abitazione)
{
    if (hostabitazioni.containsKey(utente))
    {
        abitazioni.put(abitazione.getID(),abitazione);
        hostabitazioni.get(utente).add(abitazione);
        HashSet<Prenotazione> vuoto = new HashSet<>();
        abitazioneprenotazioni.put(abitazione,vuoto);
        abitazione.setHost(utente);
    }
}
public void aggiungiAnnuncio(Abitazione abitazione , Annuncio annuncio)
{
    abitazione.setAnnuncio(annuncio);
}

public void addFeedback(Utente utente,Feedback feedback)
{
    if (utenteprenotazioni.containsKey(utente)&& abitazioneprenotazioni.containsKey(feedback.getAbitazione()))
    {
        feedback.getAbitazione().getListarecensioni().add(feedback);
        utenterecensioni.get(utente).add(feedback);//Main
        feedback.getAbitazione().setMediarecensioni();
    }
}

public void autorizzaHost(Utente utente)
{
    if (utenti.containsKey(utente.getID()))
    {
        utente.ApprovaHost();
        utenti.put(utente.getIDhost(),utente);
        HashSet<Abitazione> vuoto = new HashSet<>();
        hostabitazioni.put(utente,vuoto);
    }
}


public int prenota(Utente utente, Prenotazione prenotazione) //non void per main
{
    double costo =prenotazione.getAbitazione().getAnnuncio().getPrezzo()*Period.between(prenotazione.getInizio(),prenotazione.getFine()).getDays();
    if (utenteprenotazioni.containsKey(utente)&& abitazioneprenotazioni.containsKey(prenotazione.getAbitazione())&&utente.getCredito()>=costo)
    {
        if ((prenotazione.getAbitazione().getAnnuncio()!=null)&&prenotazione.getAbitazione().getAnnuncio().getDate().containsAll(prenotazione.getDate()))
        {
            prenotazione.getAbitazione().getAnnuncio().getDate().removeAll(prenotazione.getDate());
            utenteprenotazioni.get(utente).add(prenotazione);
            abitazioneprenotazioni.get(prenotazione.getAbitazione()).add(prenotazione);
            utente.AddCredito(-costo);
            prenotazione.getAbitazione().getHost().AddCredito(costo);
            prenotazione.getAbitazione().getHost().addPrenotazione();
            return 1;
        }
    }
    if (utente.getCredito()<costo) return -1;
    if (!prenotazione.getAbitazione().getAnnuncio().getDate().containsAll(prenotazione.getDate())) return 0;
    return -2;
}



public HashSet<Abitazione> ottieniAbHost(String ID)
{
    if(utenti.containsKey(ID)&&(hostabitazioni.containsKey(utenti.get(ID)))) return hostabitazioni.get(utenti.get(ID));
    return null;
}
public Prenotazione ultimaPrenUsr(String ID)
{
    if ((utenti.containsKey(ID)&& utenteprenotazioni.containsKey(utenti.get(ID)))) return utenteprenotazioni.get(utenti.get(ID)).getLast();
    return null;
}

public Abitazione piugettonata()
{
    LocalDate oggi =LocalDate.now();
    LocalDate unmesefa = oggi.minusDays(30);
    int max=0;
    Abitazione piugettonata=null;
    for (Abitazione abitazione: abitazioneprenotazioni.keySet())
    {
        int i=0;
        for (Prenotazione prenotazione: abitazioneprenotazioni.get(abitazione))
        {

            if (prenotazione.getInizio().isBefore(oggi)&&prenotazione.getInizio().isAfter(unmesefa)) i++;
        }
        if (i>max) { max=i; piugettonata= abitazione;}
    }
    return piugettonata;
}
    public Abitazione piugettonataquestomese(Month mese)
    {

        int max=0;
        Abitazione piugettonata=null;
        for (Abitazione abitazione: abitazioneprenotazioni.keySet())
        {
            int i=0;
            for (Prenotazione prenotazione: abitazioneprenotazioni.get(abitazione))
            {

                if (prenotazione.getInizio().getMonth().equals(mese)) i++;
            }
            if (i>max) { max=i; piugettonata= abitazione;}
        }
        return piugettonata;
    }

public TreeMap<Utente,Integer> hostpreferiti()
{

    LocalDate oggi =LocalDate.now();
    LocalDate unmesefa = oggi.minusDays(30);


    TreeMap<Utente,Integer> mappa = new TreeMap<>();
    UserComparator comparator = new UserComparator(mappa);
    for (Utente utente: hostabitazioni.keySet())
    {   int i=0;
        for (Abitazione abitazione : hostabitazioni.get(utente))
        {
            for (Prenotazione prenotazione: abitazioneprenotazioni.get(abitazione))
            {
                if (prenotazione.getInizio().isBefore(oggi)&&prenotazione.getInizio().isAfter(unmesefa)) i++;
            }
        }
        if (!mappa.containsKey(utente)) { mappa.put(utente,i);}
    }
    TreeMap<Utente, Integer> result = new TreeMap<Utente, Integer>(comparator);
    return result;
}
    public TreeMap<Utente,Integer> hostpreferitiquestomese(Month mese)
    {
        TreeMap<Utente,Integer> mappa = new TreeMap<>();
        UserComparator comparator = new UserComparator(mappa);
        for (Utente utente: hostabitazioni.keySet())
        {   int i=0;
            for (Abitazione abitazione : hostabitazioni.get(utente))
            {
                for (Prenotazione prenotazione: abitazioneprenotazioni.get(abitazione))
                {
                    if (prenotazione.getInizio().getMonth().equals(mese)) i++;
                }
            }
            if (!mappa.containsKey(utente)) { mappa.put(utente,i);}
        }
        TreeMap<Utente, Integer> result = new TreeMap<Utente, Integer>(comparator);
        return result;
    }
public HashSet<Utente> tuttiIsuperhost()
{
    HashSet<Utente> superhost = new HashSet<>();
    for (Utente utente: hostabitazioni.keySet())
    {
        if (utente.isSuperhost()) superhost.add(utente);
    }
    return superhost;
}

public List<Utente> utentipiuattivi()
{
    LocalDate oggi =LocalDate.now();
    LocalDate unmesefa = oggi.minusDays(30);
    List<Utente> lista = new ArrayList<>();
    for (Utente utente: utenteprenotazioni.keySet())
    {
        int i = 0;
        for (Prenotazione prenotazione: utenteprenotazioni.get(utente))
        {
            if (prenotazione.getInizio().isBefore(oggi)&&prenotazione.getInizio().isAfter(unmesefa))
            {
                if(prenotazione.getFine().isBefore(oggi)) i=i+Period.between(prenotazione.getInizio(),prenotazione.getFine()).getDays();
                else i=i+Period.between(prenotazione.getInizio(),oggi).getDays();
            }
        }
        utente.setGiorniquestomese(i);
        lista.add(utente);
    }
    lista.sort(Comparator.comparingInt(Utente::getGiorniquestomese).reversed());
    return lista.subList(0,5);
}
    public List<Utente> utentipiuattivimese(Month mese)
    {

        List<Utente> lista = new ArrayList<>();

        for (Utente utente: utenteprenotazioni.keySet())
        {
            int i = 0;
            for (Prenotazione prenotazione: utenteprenotazioni.get(utente))
            {
                if (prenotazione.getInizio().getMonth().equals(mese))
                {
                    LocalDate data = LocalDate.of(prenotazione.getInizio().getYear(),mese.getValue()+1,0);
                     i=i+Period.between(prenotazione.getInizio(),data).getDays();
                }
            }
            utente.setGiorniquestomese(i);
            lista.add(utente);
        }
        lista.sort(Comparator.comparingInt(Utente::getGiorniquestomese).reversed());
        return lista.subList(0,5);
    }

public double mediaPletto()
{
    double n =0;
    double i=0;
    for (Abitazione abitazione: abitazioneprenotazioni.keySet())
    {
        i=i+abitazione.getNpostiletto();
        n=n+1;
    }
    return (i/n);
}

    public Map<Utente, HashSet<Abitazione>> getHostabitazioni() {
        return hostabitazioni;
    }

    public Map<Abitazione, HashSet<Prenotazione>> getAbitazioneprenotazioni() {
        return abitazioneprenotazioni;
    }

    public Map<Utente, LinkedList<Prenotazione>> getUtenteprenotazioni() {
        return utenteprenotazioni;
    }

    public Map<String, Utente> getUtenti() {
        return utenti;
    }

    public Map<String, Abitazione> getAbitazioni() {
        return abitazioni;
    }

    public Map<Utente, HashSet<Feedback>> getUtenterecensioni() {
        return utenterecensioni;
    }
}

class UserComparator implements Comparator<Utente>
{
    Map<Utente, Integer> map;
    public UserComparator(Map<Utente, Integer> map)
    {
        this.map = map;
    }
    @Override
    public int compare(Utente o1, Utente o2)
    {
        return ((Integer) map.get(o1)).compareTo((Integer) map.get(o2));
    }

}
