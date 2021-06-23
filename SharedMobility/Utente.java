package SharedMobility2;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class Utente implements GeneraID
{
    private String nome;
    private String cognome;
    private String codicefiscale;
    private String ID;
    private String datadinascita;
    private boolean maggiorenne;
    private Coordinate coordinate;
    private String IDnollegiata;
    private double credito;
    private boolean fiducia ;
    private List<Patente> patente;

    public Utente(String nome, String cognome, String codicefiscale, String datadinascita, Coordinate coordinate)
    {
        setNome(nome);
        setCognome(cognome);
        setCodicefiscale(codicefiscale);
        setID(GeneraIdentificativo());
        setDatadinascita(datadinascita);
        setCoordinate(coordinate);
        setIDAUTOno();
        setCredito(0);
        setFiducia(true);
    }

    public void Aggiungipatente(Patente nuovapatente)
    {
        patente.add(nuovapatente);
    }

    public List<Patente> getPatente() {
        return patente;
    }

    public void setPatente(List<Patente> patente) {
        this.patente = patente;
    }

    public double getCredito() {
        return credito;
    }

    public void setCredito(double credito) {
        this.credito = Math.abs(credito);
    }
    public void addCredito(double credito)
    {
        setCredito(getCredito()+credito);
    }

    public boolean isFiducia() {
        return fiducia;
    }

    public void setFiducia(boolean fiducia) {
        this.fiducia = fiducia;
    }

    public String getIDnollegiata() {
        return IDnollegiata;
    }
    public void setIDAUTOno() {
        this.IDnollegiata = "NOAUTO";
    }

    public void setIDnollegiata(String IDnollegiata) {
        this.IDnollegiata = IDnollegiata;
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

    public String getCodicefiscale() {
        return codicefiscale;
    }

    public void setCodicefiscale(String codicefiscale) {
        this.codicefiscale = codicefiscale;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDatadinascita() {
        return datadinascita;
    }

    public void setDatadinascita(String datadinascita)
    {
        this.datadinascita = datadinascita;
        Date d = null;
        try
        {
            DateFormat formatoData = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
            formatoData.setLenient(false);
            d = formatoData.parse(datadinascita);
        } catch (ParseException e)
        {
            System.out.println("Formato data non valido.");
        }
        Date c = new Date();
        int x = c.getYear();
        int y = d.getYear();
        if ((x-y)>=18) setMaggiorenne(true);
        else setMaggiorenne(false);
    }


    public boolean isMaggiorenne() {
        return maggiorenne;
    }

    public void setMaggiorenne(boolean maggiorenne) {
        this.maggiorenne = maggiorenne;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public String GeneraIdentificativo()
    {
        Random rnd = new Random();
        String s = new String();
        s=s.concat(getNome().substring(0,2));
        s=s.concat(getCognome().substring(0,2));
        s=s.concat(getCodicefiscale().substring(5,7));
        s=s.concat(String.valueOf(rnd.nextInt(9)));
        s=s.concat(String.valueOf(rnd.nextInt(9)));
        s=s.concat(String.valueOf(rnd.nextInt(9)));
        s=s.concat(String.valueOf(rnd.nextInt(9)));
        s=s.toUpperCase();
        return s;
    }
}
