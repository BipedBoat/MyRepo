
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        AirBnB air = new AirBnB();
        Utente Marco = new Utente("Marco", "Gambuti", "MarcoG@gmail.com", "via col vento");
        air.nuovoutente(Marco);
        air.autorizzaHost(Marco);
        Abitazione casamarco = new Abitazione("Villetta", "Via col vento", 4, 1, 3);
        air.aggiungiAbitazione(Marco, casamarco);
        Annuncio annunciomarco = new Annuncio(40, "25/05/2021", "25/07/2021");
        air.aggiungiAnnuncio(casamarco, annunciomarco);
        Utente Giuseppe = new Utente("Giuseppe", "Lungariello", "Peppino@gmail.com".toLowerCase(Locale.ROOT), "via delle battute squallide");
        Giuseppe.setPassword("peppino");
        air.nuovoutente(Giuseppe);
        air.autorizzaHost(Giuseppe);
        Giuseppe.setSuperhost(true);
        Abitazione casagiuseppe = new Abitazione("Appartamento", "Via delle battute squallide", 2, 3, 4);
        air.aggiungiAbitazione(Giuseppe, casagiuseppe);
        Annuncio annunciopeppe = new Annuncio(45, "28/05/2021", "25/11/2021");
        air.aggiungiAnnuncio(casagiuseppe, annunciopeppe);
        Utente Giovanni = new Utente("Giovanni", "Verde", "GiovanniG@gmail.com", "via Roma");
        Giovanni.AddCredito(5000);
        air.nuovoutente(Giovanni);
        Prenotazione prenotazionegiovanni = new Prenotazione(casamarco, Giovanni, "25/05/2021", "24/06/2021");
        air.prenota(Giovanni, prenotazionegiovanni);
        Utente Mara = new Utente("Mara", "Di Martino", "Maradm@gmail.com", "via Roma");
        Mara.AddCredito(5000);
        air.nuovoutente(Mara);
        Prenotazione prenotazionemara = new Prenotazione(casagiuseppe, Mara, "28/05/2021", "24/06/2021");
        air.prenota(Mara, prenotazionemara);


        Scanner sc = new Scanner(System.in);
        boolean run = true;

        while (run) {
            try {
                String input;
                System.out.println("""
                        cosa vuoi fare ?
                        |Registrati|
                        |Login|
                        |STAFF|
                        |EXIT|""");
                input = sc.nextLine();
                input = input.toUpperCase(Locale.ROOT);
                if (input.equals("REGISTRATI")) {
                    try {
                        String nome;
                        String cognome;
                        String email;
                        String indirizzo;
                        String password;
                        System.out.println("Inserisci nome :");
                        nome = sc.nextLine();
                        System.out.println("Inserisci cognome :");
                        cognome = sc.nextLine();
                        System.out.println("Inserisci indirizzo di residenza :");
                        indirizzo = sc.nextLine();
                        System.out.println("Inserisci email :");
                        email = sc.nextLine();
                        email = email.toLowerCase(Locale.ROOT);
                        System.out.println("Inserisci password :");
                        password = sc.nextLine();
                        Utente user = new Utente(nome, cognome, email, indirizzo);
                        if (air.getUtenti().containsKey(user.getID())) System.out.println("Sei già registrato su air!");
                        else {
                            user.setPassword(password);
                            air.nuovoutente(user);
                        }

                    } catch (NumberFormatException a) {
                        System.out.println("ERRORE 01: Hai inserito un valore non valido");
                    }
                }
                if (input.equals("LOGIN")) {
                    try {
                        String email;
                        String password;
                        System.out.println("Inserisci email:");
                        email = sc.nextLine();
                        email = email.toLowerCase(Locale.ROOT);
                        System.out.println("Inserisci password:");
                        password = sc.nextLine();
                        Utente user = null;
                        for (String ID : air.getUtenti().keySet()) {
                            if (air.getUtenti().get(ID).getEmail().equals(email) && air.getUtenti().get(ID).getPassword().equals(password))
                                user = air.getUtenti().get(ID);
                        }

                        if (user == null) System.out.println("I dati che hai inserito non sono corretti");
                        else {
                            boolean run2 = true;
                            while (run2) {
                                if (user.isSuperhost()) {
                                    System.out.println("Ciao ✦" + user.getNome() + "          Credito: " + user.getCredito() + '\n' + "Cosa vuoi fare ? " + '\n' + "|Prenota|" + '\n' + "|Pubblica Annuncio|" + '\n' + "|Richiesta Host|" + '\n' + "|Recensione|" + '\n' + "|Ricarica Credito|" + '\n' + "|BACK|");
                                } else {
                                    System.out.println("Ciao " + user.getNome() + "          Credito: " + user.getCredito() + '\n' + "Cosa vuoi fare ? " + '\n' + "|Prenota|" + '\n' + "|Pubblica Annuncio|" + '\n' + "|Richiesta Host|" + '\n' + "|Recensione|" + '\n' + "|Ricarica Credito|" + '\n' + "|BACK|");
                                }


                                input = sc.nextLine();
                                input = input.toUpperCase(Locale.ROOT);
                                if (input.equals("RICARICA CREDITO")) {
                                    double ricarica;
                                    System.out.println("Inserisci importo da ricaricare: ");
                                    ricarica = Double.parseDouble(sc.nextLine());
                                    user.AddCredito(Math.abs(ricarica));
                                    System.out.println("Importo caricato correttamente");

                                }
                                if (input.equals("PRENOTA")) try{
                                    for (Abitazione abitazione : air.getAbitazioneprenotazioni().keySet()) {
                                        if (abitazione.getAnnuncio() != null && !abitazione.getIDhost().equals(user.getIDhost()))
                                            System.out.println(abitazione.getNome() + " in " + abitazione.getIndirizzo() + '\n' + "n locali : " + abitazione.getNlocali() + '\n' + "n posti letto: " + abitazione.getNpostiletto() + '\n' + abitazione.getPiano() + "° piano" + '\n' + "Prezzo: " + abitazione.getAnnuncio().getPrezzo() + "€ a notte" + '\n' + "Media recensioni: " + abitazione.getMediarecensioni() + '\n' + "ID Abitazione: " + abitazione.getID() + '\n' + "______________________________________________");
                                    }
                                    System.out.println("Inserisci l'ID dell'abitazione che vuoi prenotare: ");
                                    input = sc.nextLine();
                                    if (air.getAbitazioni().containsKey(input)) {
                                        Abitazione ab = air.getAbitazioni().get(input);
                                        List<LocalDate> date = air.getAbitazioni().get(input).getAnnuncio().getDate();
                                        boolean stampa = true;
                                        int i = 0;
                                        while (i < date.size()) {
                                            if (i == date.size() - 1)
                                                System.out.println(" al: " + date.get(i).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                                            else if (stampa) {
                                                System.out.print("La struttura è disponibile dal: " + date.get(i).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                                                stampa = false;
                                            } else if (Period.between(date.get(i), date.get(i + 1)).getDays() != 1) {
                                                System.out.println(" al: " + date.get(i).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                                                stampa = true;
                                            }
                                            i++;
                                        }
                                        String inizio;
                                        String fine;
                                        System.out.println("Inserisci la data di inizio del soggiorno: GG/MM/AAAA");
                                        inizio = sc.nextLine();
                                        System.out.println("Inserisci la data di fine del soggiorno: GG/MM/AAAA");
                                        fine = sc.nextLine();

                                        Prenotazione p = new Prenotazione(ab, user, inizio, fine);
                                        double costo = p.getAbitazione().getAnnuncio().getPrezzo() * Period.between(p.getInizio(), p.getFine()).getDays();
                                        System.out.println("Pagherai " + costo + "€ , vuoi procedere ? (Y/N)");
                                        input = sc.nextLine();
                                        input = input.toUpperCase(Locale.ROOT);
                                        LocalDate start = LocalDate.parse(inizio, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                                        LocalDate end = LocalDate.parse(fine, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                                        if (input.equals("Y")) {
                                            int test = air.prenota(user, p);
                                            if (test == 1) System.out.println("Prenotazione effettuata con successo");
                                            else if (test == -1) System.out.println("Il tuo credito è insufficiente");
                                            else if (test == 0 || end.isBefore(start))
                                                System.out.println("Le date inserite non sono valide");
                                            else System.out.println("Ops, Qualcosa è andato storto");
                                        }
                                    } else System.out.println("Hai inserito un ID non valido");
                                }catch(NullPointerException a){
                                    System.out.println("ERRORE 02 : Hai inserito date non valide, inserisci data nel formato GG/MM/AAAA");}
                                if (input.equals("PUBBLICA ANNUNCIO")) {
                                    if (user.isHost()) {
                                        String nome;
                                        String indirizzo;
                                        int npostiletto;
                                        int piano;
                                        int nlocali;
                                        double prezzo;
                                        String inizio;
                                        String fine;
                                        System.out.println("Inserisci nome della struttura: ");
                                        nome = sc.nextLine();
                                        System.out.println("Inserisci indirizzo della struttura: ");
                                        indirizzo = sc.nextLine();
                                        System.out.println("Inserisci numero locali della struttura: ");
                                        nlocali = Integer.parseInt(sc.nextLine());
                                        System.out.println("Inserisci a che piano di trova la struttura: ");
                                        piano = Integer.parseInt(sc.nextLine());
                                        System.out.println("Inserisci numero posti letto della struttura: ");
                                        npostiletto = Integer.parseInt(sc.nextLine());
                                        System.out.println("Inserisci prezzo per singola notte: ");
                                        prezzo = Double.parseDouble(sc.nextLine());
                                        System.out.println("Inserisci data inizio del periodo di disponibilita: GG/MM/AAAA ");
                                        inizio = sc.nextLine();
                                        System.out.println("Inserisci data fine del periodo di disponibilita: GG/MM/AAAA ");
                                        fine = sc.nextLine();
                                        Annuncio a = new Annuncio(prezzo, inizio, fine);
                                        Abitazione ab = new Abitazione(nome, indirizzo, npostiletto, piano, nlocali);
                                        air.aggiungiAbitazione(user, ab);
                                        ab.setAnnuncio(a);
                                        System.out.println("Annuncio inserito con successo");

                                    } else
                                        System.out.println("Per pubblicare un annuncio devi prima diventare un host, effettua la richiesta dal menu e attendi che lo staff approvi");
                                }
                                if (input.equals("RICHIESTA HOST")) {
                                    if (!user.isHost()) {
                                        user.setRichiestahost(true);
                                        System.out.println("Richiesta inoltrata con successo, attendi che lo staff la approvi");
                                    } else System.out.println("Sei gia un host!");
                                }
                                if (input.equals("RECENSIONE")) {
                                    if (air.getUtenteprenotazioni().get(user) != null)
                                    {
                                        int i =0;
                                        for (Prenotazione p : air.getUtenteprenotazioni().get(user))
                                        {
                                            boolean controllo = false;
                                            for (Feedback f : air.getUtenterecensioni().get(user))
                                            {
                                                if (p.getAbitazione().equals(f.getAbitazione()))
                                                {
                                                    controllo = true;
                                                    break;
                                                }
                                            }
                                            if (!controllo)
                                            {
                                                System.out.println(p.getAbitazione().getNome() + " in " + p.getAbitazione().getIndirizzo() + '\n' + "ID: " + p.getAbitazione().getID() + '\n' + "_____________________________________");
                                                i++;
                                            }
                                        }
                                        if (i>0){System.out.println("Inserisci l'id della struttura che vuoi recensire: ");
                                        input = sc.nextLine();
                                        if (air.getAbitazioni().containsKey(input))
                                        {
                                            String titolo;
                                            String testo;
                                            int punteggio;
                                            Abitazione ab = air.getAbitazioni().get(input);
                                            System.out.println("Inserisci il Titolo della recensione:");
                                            titolo = sc.nextLine();
                                            System.out.println("Inserisci il testo della recensione:");
                                            testo = sc.nextLine();
                                            System.out.println("Dai un punteggio a questa struttura (da 1 a 5)");
                                            punteggio = Integer.parseInt(sc.nextLine());
                                            Feedback f = new Feedback(titolo, testo, punteggio);
                                            f.setAbitazione(ab);
                                            air.addFeedback(user, f);
                                        } else System.out.println("Hai inserito un id non valido");
                                    }else System.out.println("Non hai effettuato alcun soggiorno");
                                    } else System.out.println("Non hai effettuato alcun soggiorno");
                                }
                                if (input.equals("BACK")) run2 = false;
                            }
                        }


                    } catch (NumberFormatException b) {
                        System.out.println("ERRORE 01: Hai inserito un valore non valido");
                    }
                }
                if (input.equals("EXIT")) run = false;
                if (input.equals("STAFF")) {
                    boolean run3 = true;
                    while (run3) {
                        System.out.println("""
                                cosa vuoi fare ?
                                |Stampa Abitazioni Host|
                                |Ultima Prenotazione Utente|
                                |Lista Superhost|
                                |Media posti letto|
                                |Abitazione Gettonata|    (Abitazione con piu prenotazioni in uno specificato mese)
                                |Host preferiti|          (Host con piu prenotazioni in uno specificato mese )
                                |Utenti Piu Attivi|       (Con piu prenotazioni negli in uno specificato mese)
                                |Richieste Host|          (Utenti in attesa di approvazione Host)
                                |BACK|""");
                        input = sc.nextLine();
                        input = input.toUpperCase(Locale.ROOT);
                        if (input.equals("ABITAZIONE GETTONATA")) try {
                            int mese;
                            System.out.println("Inserisci il mese (Numero) :");
                            mese = Integer.parseInt(sc.nextLine());

                            if (air.piugettonataquestomese(Month.of(mese)) != null) {
                                Abitazione ab = air.piugettonataquestomese(Month.of(mese));
                                System.out.println(ab.getNome() + " in " + ab.getIndirizzo() + '\n' + "ID: " + ab.getID());
                            }
                        } catch (DateTimeException a) {
                            System.out.println("ERRORE 02: hai inserito una data non valida");
                        }
                        if (input.equals("STAMPA ABITAZIONI HOST")) {
                            for (Utente u : air.getHostabitazioni().keySet()) {
                                System.out.println(u.getNome() + " " + u.getCognome() + " " + u.getEmail() + '\n' + "ID: " + u.getIDhost() + '\n' + "_________________________________");
                            }
                            System.out.println("Inserisci ID dell'host per visualizzare tutte le sue abitazioni: ");
                            input = sc.nextLine();
                            HashSet<Abitazione> insieme = air.ottieniAbHost(input);
                            if (insieme != null) {
                                for (Abitazione a : insieme) {
                                    System.out.println(a.getNome() + " in " + a.getIndirizzo() + '\n' + "ID: " + a.getID() + '\n' + "_______________________________");
                                }

                            } else System.out.println("L'host non ha attualmente abitazioni");

                        }
                        if (input.equals("ULTIMA PRENOTAZIONE UTENTE")) {
                            for (Utente u : air.getUtenteprenotazioni().keySet()) {
                                System.out.println(u.getNome() + " " + u.getCognome() + " " + u.getEmail() + '\n' + "ID: " + u.getID() + '\n' + "_________________________________");
                            }
                            System.out.println("Inserisci ID dell'utente per visualizzare la sua ultima prenotazione: ");
                            input = sc.nextLine();
                            Prenotazione p = air.ultimaPrenUsr(input);
                            if (p != null) {
                                System.out.println(p.getUtente().getNome() + " " + p.getUtente().getCognome() + " ha effettuato la sua ultima prenotazione presso: " + p.getAbitazione().getNome() + " in " + p.getAbitazione().getIndirizzo() + " dal " + p.getInizio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " al " + p.getFine().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                            } else System.out.println("L'id inserito non è valido");
                        }
                        if (input.equals("LISTA SUPERHOST")) {
                            HashSet<Utente> insieme = air.tuttiIsuperhost();
                            if (insieme != null) {
                                for (Utente u : insieme) {
                                    System.out.println(u.getNome() + " " + u.getCognome() + " " + u.getEmail() + '\n' + "ID: " + u.getIDhost() + '\n' + "_________________________________");
                                }
                            } else System.out.println("Non ci sono SuperHost");
                        }
                        if (input.equals("HOST PREFERITI")) try {
                            int mese;
                            System.out.println("Inserisci il mese (Numero) :");
                            mese = Integer.parseInt(sc.nextLine());

                            List<Utente> lista = air.hostpreferitiquestomese(Month.of(mese));
                            if (lista != null) {
                                for (Utente u : lista) {
                                    System.out.println("n prenotazioni: " + u.getGiorniquestomese() + " " + u.getNome() + " " + u.getCognome() + " " + u.getEmail() + '\n' + "ID: " + u.getIDhost() + '\n' + "_________________________________");
                                }

                            } else
                                System.out.println("Caro facoltoso turista americano hai buttato i soldi, qui non c'è neanche una prenotazione");
                        } catch (DateTimeException a) {
                            System.out.println("ERRORE 02: hai inserito una data non valida");
                        }
                        if (input.equals("UTENTI PIU ATTIVI")) try
                        {
                            int mese;
                            System.out.println("Inserisci il mese (Numero) :");
                            mese = Integer.parseInt(sc.nextLine());
                            List<Utente> lista = air.utentipiuattivimese(Month.of(mese));
                            if (lista != null)
                            {
                                for (Utente u : lista)
                                {
                                    System.out.println(u.getNome() + " " + u.getCognome() + " " + u.getEmail() + '\n' + "n giorni a " + Month.of(mese).toString() + ": " + u.getGiorniquestomese() + '\n' + "ID: " + u.getID() + '\n' + "_________________________________");
                                }

                            } else
                                System.out.println("Caro facoltoso turista americano hai buttato i soldi, qui non c'è neanche una prenotazione");
                        } catch (DateTimeException a) { System.out.println("ERRORE 01: Hai inserito un valore non valido"); }
                        if (input.equals("RICHIESTE HOST"))
                        {
                            int i = 0;
                            for (Utente u : air.getUtenteprenotazioni().keySet())
                            {
                                if (u.isRichiestahost() && !u.isHost())
                                {
                                    System.out.println(u.getNome() + " " + u.getCognome() + " " + u.getEmail() + '\n' + "ID: " + u.getID() + '\n' + "_________________________________");
                                    i++;
                                }
                            }
                            if (i > 0)
                            {
                                System.out.println("Inserisci ID dell'utente che vuoi approvare come host: ");
                                input = sc.nextLine();
                                if (air.getUtenti().containsKey(input))
                                {
                                    air.autorizzaHost(air.getUtenti().get(input));
                                    System.out.println("Utente Approvato");
                                }
                            } else System.out.println("Caro facoltoso turista americano hai buttato i soldi, qui non c'è neanche un utente");
                        }
                        if (input.equals("MEDIA POSTI LETTO"))
                        {
                            System.out.println("La media dei posti letto delle abitazioni oggi presenti è : " + air.mediaPletto());
                        }
                        if (input.equals("BACK")) run3 = false;
                    }
                }
            } catch (NumberFormatException a) { System.out.println("ERRORE 01: Hai inserito un valore non valido"); }
        }
    }
}