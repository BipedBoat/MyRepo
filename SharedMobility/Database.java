package SharedMobility2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Database
{
    private ArrayList<Utente> listautenti;
    private ArrayList<Veicoli> listaveicoli;
    private ArrayList<Auto> listaAutomobili= new ArrayList<>();
    private ArrayList<Moto> listaMoto= new ArrayList<>();
    private ArrayList<Furgone> listaFurgoni= new ArrayList<>();
    private ArrayList<MonopattinoElett> listaMonopattini= new ArrayList<>();
    private ArrayList<ScooterElettrico> listaScooter= new ArrayList<>();
    private ArrayList<Bicicletta> listaBicicletta= new ArrayList<>();

    public ArrayList<Utente> getListautenti() {
        return listautenti;
    }

    public void setListautenti(ArrayList<Utente> listautenti) {
        this.listautenti = listautenti;
    }

    public ArrayList<Veicoli> getListaveicoli() {
        return listaveicoli;
    }

    public void setListaveicoli(ArrayList<Veicoli> listaveicoli) {
        this.listaveicoli = listaveicoli;
    }

    public Database(ArrayList<Utente> listautenti, ArrayList<Veicoli> listaveicoli) {
        setListautenti(listautenti);
        setListaveicoli(listaveicoli);
        aggiornaliste(listaveicoli);
    }

    public void aggiornaliste(ArrayList<Veicoli> listaveicoli)
    {
        for (Veicoli veicolo:listaveicoli)
        {
            if (veicolo instanceof Auto) listaAutomobili.add((Auto) veicolo);
            if (veicolo instanceof Moto) listaMoto.add((Moto) veicolo);
            if (veicolo instanceof Furgone) listaFurgoni.add((Furgone) veicolo);
            if (veicolo instanceof MonopattinoElett) listaMonopattini.add((MonopattinoElett) veicolo);
            if (veicolo instanceof ScooterElettrico) listaScooter.add((ScooterElettrico) veicolo);
            if (veicolo instanceof Bicicletta) listaBicicletta.add((Bicicletta) veicolo);
        }
    }

    public void svuotaListe()
    {
            listaAutomobili.clear();
            listaMoto.clear();
            listaFurgoni.clear();
            listaMonopattini.clear();
            listaScooter.clear();
            listaBicicletta.clear();

    }


    public boolean checkpatente(Veicoli veicolo,Utente utente)
    {
        List<Patente> patentiutente = utente.getPatente();
        if (veicolo instanceof VeicoliImmatricolati&& patentiutente!=null)
        {
            Patente patenterichiesta = ((VeicoliImmatricolati) veicolo).getPatenterichiesta();

            for (Patente patente:patentiutente)
            {
                if (patenterichiesta.equals(patente)) return true;
            }
            System.out.println("Non disponi della patente necessaria: "+ ((VeicoliImmatricolati) veicolo).getPatenterichiesta());
            return false;
        }
        else if (veicolo instanceof VeicoliNonImmatricolatiConMotore || veicolo instanceof Bicicletta) return true;

        System.out.println("Non disponi della patente necessaria: "+ ((VeicoliImmatricolati) veicolo).getPatenterichiesta());
        return false;
    }


    private double distance(Coordinate posizione1,Coordinate posizione2)
    {
        double lon1=posizione1.getLongitudine();
        double lat1=posizione1.getLatitudine();
        double lon2=posizione2.getLongitudine();
        double lat2=posizione2.getLatitudine();
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515*1.609344;
        return (dist);
    }

    private void ordinaperdistanza(Utente utente)
    {
        int dimensione = listaveicoli.size();
        for (int i=1; i <dimensione; i++)
        {
            for (int j=i; j>0 && (distance(utente.getCoordinate(),listaveicoli.get(j).getCoordinateveicolo())<(distance(utente.getCoordinate(),listaveicoli.get(j-1).getCoordinateveicolo()))); j--)
                if(distance(utente.getCoordinate(),listaveicoli.get(j).getCoordinateveicolo())<(distance(utente.getCoordinate(),listaveicoli.get(j-1).getCoordinateveicolo())))
                {
                    Object temp = listaveicoli.get(j);
                    listaveicoli.set(j,listaveicoli.get(j-1));
                    listaveicoli.set(j-1, (Veicoli) temp);

                }
        }
        svuotaListe();
        aggiornaliste(listaveicoli);
    }

    /* The function to convert decimal into radians */
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
    /* The function to convert radians into decimal */
    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
    public void Stampaeprenota(Utente utente)
    {
        ordinaperdistanza(utente);
        Scanner sc = new Scanner(System.in);
        System.out.println("Cosa vuoi nolleggiare ?"+"\n"+"1- Auto"+"\n"+"2- Moto"+"\n"+"3- Furgone"+"\n"+"4- Scooter"+"\n"+"5- Monopattino"+"\n"+"6- Bici");
        int r = sc.nextInt();
        switch (r)
        {
            case 1:
                for (Auto auto:listaAutomobili)
                {
                    if (auto.getDisponibilita().equals(Stato.DISPONIBILE))
                        System.out.println(auto.getMarca()+" "+auto.getModello()+" "+auto.getPrezzoalminuto()+" € al minuto"+"\nTarga: "+auto.getTarga()+"\nCambio: "+auto.getCambio()+"\nMotore: "+auto.getTipomotore()+"\nAria Condizionata: "+auto.isAriacondiz()+"\nID prenotazione: "+auto.getIdveicolo()+"\n_____________________________");
                }
                break;
            case 2:
                for (Moto moto:listaMoto)
                {
                    if (moto.getDisponibilita().equals(Stato.DISPONIBILE))
                        System.out.println(moto.getMarca()+" "+moto.getModello()+" "+moto.getPrezzoalminuto()+" € al minuto"+"\nTarga: "+moto.getTarga()+"\nMotore: "+moto.getTipomotore()+"\nCasco incluso: "+moto.isCascoincluso()+"\nID prenotazione: "+moto.getIdveicolo()+"\n_____________________________");
                }
                break;
            case 3:
                for (Furgone furgone:listaFurgoni)
                {
                    if (furgone.getDisponibilita().equals(Stato.DISPONIBILE))
                        System.out.println(furgone.getMarca()+" "+furgone.getModello()+" "+furgone.getPrezzoalminuto()+" € al minuto"+"\nTarga: "+furgone.getTarga()+"\nCapienza: "+furgone.getCapienza()+" litri"+"\nMotore: "+furgone.getTipomotore()+"\nAria Condizionata: "+furgone.isAriacondiz()+"\nID prenotazione: "+furgone.getIdveicolo()+"\n_____________________________");
                }
                break;
            case 4:
                for (ScooterElettrico scooter:listaScooter)
                {
                    if (scooter.getDisponibilita().equals(Stato.DISPONIBILE))
                        System.out.println(scooter.getMarca()+" "+scooter.getModello()+" "+scooter.getPrezzoalminuto()+" € al minuto"+"\nMotore: "+scooter.getTipomotore()+"\nCasco incluso: "+scooter.isCascoleggero()+"\nID prenotazione: "+scooter.getIdveicolo()+"\n_____________________________");
                }
                break;
            case 5:
                for (MonopattinoElett monop:listaMonopattini)
                {
                    if (monop.getDisponibilita().equals(Stato.DISPONIBILE))
                        System.out.println(monop.getMarca()+" "+monop.getModello()+" "+monop.getPrezzoalminuto()+" € al minuto"+"\nMotore: "+monop.getTipomotore()+"\nCasco incluso: "+monop.isCascoleggero()+"\nID prenotazione: "+monop.getIdveicolo()+"\n_____________________________");
                }
                break;
            case 6:
                for (Bicicletta bici:listaBicicletta)
                {
                    if (bici.getDisponibilita().equals(Stato.DISPONIBILE))
                        System.out.println(bici.getMarca()+" "+bici.getModello()+" "+bici.getPrezzoalminuto()+" € al minuto"+"\nID prenotazione: "+bici.getIdveicolo()+"\n_____________________________");
                }
                break;
            default:break;
        }
        String id =sc.nextLine();
        System.out.println("Inserisci Id prenotazione del veicolo che desideri nolleggiare :");
        id =sc.nextLine();
        System.out.println(id);
        System.out.println("Inserisci tempo di noleggio in minuti:");
        int minuti =sc.nextInt();
        String risp = sc.nextLine();
        if (cercaperID(id)!=null)
        {
            System.out.println("Pagherai "+cercaperID(id).getPrezzoalminuto()*minuti+" € ,vuoi procedere all'acquisto ? (Y/N)");
            risp = sc.nextLine();
            if (risp.equals("Y")) Prenota(utente,cercaperID(id),minuti);
        }

    }
    public void Prenota(Utente utente,Veicoli veicolo,int minuti)
    {
        if (!utente.isMaggiorenne()) { System.out.println("Devi avere almeno 18 anni");return;}
        if (!utente.getIDnollegiata().equals("NOAUTO")){ System.out.println("L'utente ha gia un noleggio in corso");return;}
        if (!listautenti.contains(utente)){ System.out.println("Utente non valido"); return;}
        if (!listaveicoli.contains(veicolo)) {System.out.println("Veicolo non valido"); return;}
        if(!checkpatente(veicolo,utente)) return;
        if (!veicolo.getDisponibilita().equals(Stato.DISPONIBILE)){System.out.println("Veicolo non disponibile"); return;}
        if (utente.getCredito()<minuti* veicolo.getPrezzoalminuto()){System.out.println("Credito non sufficiente"); return;}
        utente.setCredito(utente.getCredito()-minuti* veicolo.getPrezzoalminuto());
        veicolo.setDisponibilita(Stato.OCCUPATO);
        utente.setIDnollegiata(veicolo.getIdveicolo());
        System.out.println("Veicolo noleggiato con successo");
    }
    public void TerminaPrenotazione(Utente utente)
    {

        if (cercaperID(utente.getIDnollegiata())!=null&&utente.getIDnollegiata().equals(cercaperID(utente.getIDnollegiata()).getIdveicolo()))
        {
            cercaperID(utente.getIDnollegiata()).setDisponibilita(Stato.DISPONIBILE);
            AggiornaposizioneUtente(utente);
            cercaperID(utente.getIDnollegiata()).Aggiungikm(distance(utente.getCoordinate(), cercaperID(utente.getIDnollegiata()).getCoordinateveicolo()));
            cercaperID(utente.getIDnollegiata()).setCoordinateveicolo(utente.getCoordinate());
            VerificaGuasti(cercaperID(utente.getIDnollegiata()));
            utente.setIDAUTOno();
            System.out.println("Noleggio terminato con successo");
        }

    }
    public void VerificaGuasti(Veicoli veicolo)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hai qualche guasto da segnalare ? (Y/N)");
        String risposta = sc.nextLine();
        if (risposta.equals("Y"))
        {
            System.out.println("Che tipo di guasto vuoi segnalare ?"+"\n"+"1- Motore"+"\n"+"2- Batteria"+"\n"+"3- Pneumatici"+"\n"+"4- Carrozzeria"+"\n"+"5- Impianto elettrico");
            int r = sc.nextInt();
            switch (r)
            {
                case 1: veicolo.setTipoguasto(TIPOGUASTOIMM.MOTORE);
                    veicolo.setDisponibilita(Stato.GUASTO);
                    break;
                case 2: veicolo.setTipoguasto(TIPOGUASTOIMM.BATTERIA);
                    veicolo.setDisponibilita(Stato.GUASTO);
                    break;
                case 3: veicolo.setTipoguasto(TIPOGUASTOIMM.PNEUMATICI);
                    veicolo.setDisponibilita(Stato.GUASTO);
                    break;
                case 4: veicolo.setTipoguasto(TIPOGUASTOIMM.CARROZZERIA);
                    veicolo.setDisponibilita(Stato.GUASTO);
                    break;
                case 5: veicolo.setTipoguasto(TIPOGUASTOIMM.IMPIANTOELETTRICO);
                    veicolo.setDisponibilita(Stato.GUASTO);
                    break;
                default:break;
            }
        }
        if (!risposta.equals("Y")&&!risposta.equals("N")) VerificaGuasti(veicolo);
    }
    public Veicoli cercaperID(String ID)
    {
        for (Veicoli veicolo:listaveicoli)
        {
            if (veicolo.getIdveicolo().equals(ID)) { return veicolo;}
        }
        System.out.println("l'ID inserito non corrisponde ad alcun veicolo");
        return null;
    }
    public void AggiornaposizioneUtente(Utente utente)
    {
        utente.setCoordinate(new Coordinate(48.88,100)); //metodo magico che aggiorna la posizione dell'utente;
    }


    public static void main(String[] args)
    {
        Auto auto = new Auto("Fiat","Punto","BCD45RT",Tipomotore.DIESEL,4.50,5,100,Cambio.MANUALE,true,new Coordinate(45.88,96.36));
        Moto Honda = new Moto("Honda","DDR","DFE56TR",Tipomotore.DIESEL,3.50,5.60,100,new Coordinate(48.88,100));
        Auto virgola = new Auto("Fiat","Virgola","BCD45RT",Tipomotore.DIESEL,4.50,5,100,Cambio.MANUALE,true,new Coordinate(47.66,53.01));
        Utente io=new Utente("Giuseppe","Di Giovannantonio","DGVGPP93P02g30J","02/09/1993",new Coordinate(47.66,52.41));
        ArrayList<Veicoli> listaveicoli= new ArrayList<>();
        ArrayList<Utente> listautenti= new ArrayList<>();
        listaveicoli.add(auto);
        listaveicoli.add(Honda);
        listaveicoli.add(virgola);
        listautenti.add(io);
        Database database=new Database(listautenti,listaveicoli);
        List<Patente> patenti = new ArrayList<>();
        patenti.add(Patente.PATENTEA);
        patenti.add(Patente.PATENTEB);
        patenti.add(Patente.PATENTEB2);
        io.addCredito(500);
        io.setPatente(patenti);
        database.Stampaeprenota(io);
        database.TerminaPrenotazione(io);



    }



}

