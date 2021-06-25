import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main
{
    public static void main(String[] args)  {
        AirBnB air= new AirBnB();
        Utente Marco = new Utente("Marco","Gambuti", "MarcoG@gmail.com","via col vento");
        air.nuovoutente(Marco);
        air.autorizzaHost(Marco);
        Abitazione casamarco = new Abitazione("Villetta","Via col vento",4,1,3);
        air.aggiungiAbitazione(Marco,casamarco);
        Annuncio annunciomarco = new Annuncio(40,"25/06/2021","25/07/2021");
        air.aggiungiAnnuncio(casamarco,annunciomarco);
        Utente Giuseppe = new Utente("Giuseppe","Lungariello", "Peppinno@gmail.com","via delle battute squallide");
        air.nuovoutente(Giuseppe);
        air.autorizzaHost(Giuseppe);
        Abitazione casagiuseppe = new Abitazione("Appartamento","Via delle battute squallide",2,3,4);
        air.aggiungiAbitazione(Giuseppe,casagiuseppe);
        Annuncio annunciopeppe = new Annuncio(45,"28/05/2021","25/11/2021");
        air.aggiungiAnnuncio(casagiuseppe,annunciopeppe);

        Scanner sc = new Scanner(System.in);
        boolean run = true;
        while (run)
        {
            String input;
            System.out.println("cosa vuoi fare ? "+'\n'+"|Registrati|"+'\n'+"|Login|"+'\n'+"|STAFF|"+'\n'+"|EXIT|");
            input = sc.nextLine();
            input= input.toUpperCase(Locale.ROOT);
            if (input.equals("REGISTRATI"))
            {
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
                email=email.toLowerCase(Locale.ROOT);
                System.out.println("Inserisci password :");
                password = sc.nextLine();
                Utente user = new Utente(nome,cognome,email,indirizzo);
                if (air.getUtenti().containsKey(user.getID())) System.out.println("Sei già registrato su air!");
                else {user.setPassword(password);air.nuovoutente(user);}

            }
            if (input .equals("LOGIN"))
            {
                String email;
                String password;
                System.out.println("Inserisci email:");
                email=sc.nextLine();
                email=email.toLowerCase(Locale.ROOT);
                System.out.println("Inserisci password:");
                password=sc.nextLine();
                Utente user = null;
                for (String ID:air.getUtenti().keySet())
                {
                    if (air.getUtenti().get(ID).getEmail().equals(email)&&air.getUtenti().get(ID).getPassword().equals(password)) user =air.getUtenti().get(ID);
                }

                if (user==null) System.out.println("I dati che hai inserito non sono corretti");
                else
                {
                    boolean run2=true;
                    while (run2)
                    {
                        System.out.println("cosa vuoi fare ? "+'\n'+"|Prenota|"+'\n'+"|Pubblica Annuncio|"+'\n'+"|Richiesta Host|"+'\n'+"|Recensione|"+'\n'+"|Ricarica Credito|"+'\n'+"|BACK|");
                        input = sc.nextLine();
                        input= input.toUpperCase(Locale.ROOT);
                        if (input.equals("RICARICA CREDITO"))
                        {
                            double ricarica=0;
                            System.out.println("Inserisci importo da ricaricare: ");
                            ricarica= Double.parseDouble(sc.nextLine());
                            user.AddCredito(Math.abs(ricarica));
                            System.out.println("Importo caricato correttamente");

                        }
                        if (input.equals("PRENOTA"))
                        {
                            for (Abitazione abitazione:air.getAbitazioneprenotazioni().keySet())
                            {
                                if (abitazione.getAnnuncio()!=null)
                                System.out.println(abitazione.getNome()+" in "+abitazione.getIndirizzo()+'\n'+"n locali : "+abitazione.getNlocali()+'\n'+"n posti letto: "+abitazione.getNpostiletto()+'\n'+abitazione.getPiano()+"° piano"+'\n'+"Media recensioni: "+abitazione.getMediarecensioni()+'\n'+"ID Abitazione: "+abitazione.getID()+'\n'+"______________________________________________");
                            }
                            System.out.println("Inserisci l'ID dell'abitazione che vuoi prenotare: ");
                            input=sc.nextLine();
                            if (air.getAbitazioni().containsKey(input))
                            {
                                Abitazione ab = air.getAbitazioni().get(input);
                                List<LocalDate> date = air.getAbitazioni().get(input).getAnnuncio().getDate();
                                boolean stampa=true;
                                int i=0;
                                while(i<date.size())
                                {
                                    if(i==date.size()-1) System.out.print(" al: "+date.get(i).format( DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                                    else if(stampa) {System.out.print("La struttura è disponibile dal: "+date.get(i).format( DateTimeFormatter.ofPattern("dd/MM/yyyy")));stampa=false;}
                                    else if (Period.between(date.get(i),date.get(i+1)).getDays()!=1)
                                    {
                                        System.out.println(" al: "+date.get(i).format( DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                                        stampa=true;
                                    }
                                    i++;
                                }
                                String inizio;
                                String fine;
                                System.out.println("Inserisci la data di inizio del soggiorno: GG/MM/AAAA");
                                inizio=sc.nextLine();
                                System.out.println("Inserisci la data di fine del soggiorno: GG/MM/AAAA");
                                fine=sc.nextLine();

                                Prenotazione p = new Prenotazione(ab,user,inizio,fine);
                                double costo =p.getAbitazione().getAnnuncio().getPrezzo()*Period.between(p.getInizio(),p.getFine()).getDays();
                                System.out.println("Pagherai "+costo+"€ , vuoi procedere ? (Y/N)");
                                input = sc.nextLine();
                                input=input.toUpperCase(Locale.ROOT);
                                LocalDate start = LocalDate.parse(inizio, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                                LocalDate end = LocalDate.parse(fine, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                                if (input.equals("Y"))
                                {
                                    int test =air.prenota(user,p);
                                    if (test==1) System.out.println("Prenotazione effettuata con successo");
                                    else if (test==-1)System.out.println("Il tuo credito è insufficiente");
                                    else if (test==0||end.isBefore(start)) System.out.println("Le date inserite non sono valide");
                                    else System.out.println("Ops, Qualcosa è andato storto");
                                }
                            }
                            else System.out.println("Hai inserito un ID non valido");
                        }
                        if (input.equals("PUBBLICA ANNUNCIO"))
                        {
                            if (user.isHost())
                            {
                                String nome;
                                String indirizzo;
                                int npostiletto;
                                int piano;
                                int nlocali;
                                double prezzo;
                                String inizio;
                                String fine;
                                System.out.println("Inserisci nome della struttura: ");
                                nome=sc.nextLine();
                                System.out.println("Inserisci indirizzo della struttura: ");
                                indirizzo=sc.nextLine();
                                System.out.println("Inserisci numero locali della struttura: ");
                                nlocali= Integer.parseInt(sc.nextLine());
                                System.out.println("Inserisci a che piano di trova la struttura: ");
                                piano=Integer.parseInt(sc.nextLine());
                                System.out.println("Inserisci numero posti letto della struttura: ");
                                npostiletto=Integer.parseInt(sc.nextLine());
                                System.out.println("Inserisci prezzo per singola notte: ");
                                prezzo= Double.parseDouble(sc.nextLine());
                                System.out.println("Inserisci data inizio del periodo di disponibilita: GG/MM/AAAA ");
                                inizio=sc.nextLine();
                                System.out.println("Inserisci data fine del periodo di disponibilita: GG/MM/AAAA ");
                                fine=sc.nextLine();
                                Annuncio a = new Annuncio(prezzo,inizio,fine);
                                Abitazione ab = new Abitazione(nome,indirizzo,npostiletto,piano,nlocali);
                                air.aggiungiAbitazione(user,ab);
                                ab.setAnnuncio(a);
                                System.out.println("Annuncio inserito con successo");

                            }else System.out.println("Per pubblicare un annuncio devi prima diventare un host, effettua la richiesta dal menu e attendi che lo staff approvi");
                        }
                        if (input.equals("RICHIESTA HOST"))
                        {
                            if (!user.isHost())
                            {
                                user.setRichiestahost(true);
                                System.out.println("Richiesta inoltrata con successo, attendi che lo staff la approvi");
                            }else System.out.println("Sei gia un host!");
                        }
                        if (input.equals("RECENSIONE"))
                        {
                            if (air.getUtenteprenotazioni().get(user)!= null)
                            {
                                System.out.println("Inserisci l'id della struttura che vuoi recensire: ");
                                for (Prenotazione p:air.getUtenteprenotazioni().get(user))
                                {
                                    boolean controllo=false;
                                    for (Feedback f:air.getUtenterecensioni().get(user))
                                    {
                                        if (p.getAbitazione().equals(f.getAbitazione())) controllo=true;
                                    }
                                    if (!controllo) System.out.println(p.getAbitazione().getNome()+" in "+p.getAbitazione().getIndirizzo()+'\n'+"ID: "+p.getAbitazione().getID()+'\n'+"_____________________________________");
                                }
                                input =sc.nextLine();
                                if (air.getAbitazioni().containsKey(input))
                                {
                                    String titolo;
                                    String testo;
                                    int punteggio;
                                    Abitazione ab = air.getAbitazioni().get(input);
                                    System.out.println("Inserisci il Titolo della recensione:");
                                    titolo=sc.nextLine();
                                    System.out.println("Inserisci il testo della recensione:");
                                    testo=sc.nextLine();
                                    System.out.println("Dai un punteggio a questa struttura (da 1 a 5)");
                                    punteggio= Integer.parseInt(sc.nextLine());
                                    air.addFeedback(user,new Feedback(titolo,testo,punteggio));
                                }else System.out.println("Hai inserito un id non valido");
                            }else System.out.println("Non hai effettuato alcun soggiorno");
                        }
                        if (input.equals("BACK")) run2 = false;
                    }
                }


            }
            if (input.equals("EXIT")) run = false;
            if (input .equals("STAFF"))
            {
                boolean run3=true;
                while(run3)
                {
                System.out.println("cosa vuoi fare ? "+'\n'+"|Stampa Abitazioni Host|"+'\n'+"|Ultima Prenotazione Utente|"+'\n'+"|Lista Superhost|"+'\n'+"|Media posti letto|"+'\n'+"|Abitazione Gettonata|    (Abitazione con piu prenotazioni in uno specificato mese)"+'\n'+"|Host preferiti|    (Host con piu prenotazioni in uno specificato mese )"+'\n'+"|Utenti Piu Attivi|    (Con piu prenotazioni negli in uno specificato mese)"+'\n'+"|Richieste Host|    (Utenti in attesa di approvazione Host)"+'\n'+"|BACK|");
                input = sc.nextLine();
                input= input.toUpperCase(Locale.ROOT);
                if (input.equals("ABITAZIONE GETTONATA"))
                {
                    int mese=0;
                    System.out.println("Inserisci il mese (Numero) :");
                    mese = Integer.parseInt(sc.nextLine());

                    if (air.piugettonataquestomese(Month.of(mese))!=null)
                    {
                        Abitazione ab = air.piugettonataquestomese(Month.of(mese));
                    System.out.println(ab.getNome()+" in "+ab.getIndirizzo()+'\n'+"ID: "+ab.getID());
                    }
                }
                if (input.equals("STAMPA ABITAZIONI HOST"))
                {
                    for (Utente u:air.getHostabitazioni().keySet())
                    {
                        System.out.println(u.getNome()+" "+u.getCognome()+" "+u.getEmail()+'\n'+"ID: "+u.getIDhost()+'\n'+"_________________________________");
                    }
                    System.out.println("Inserisci ID dell'host per visualizzare tutte le sue abitazioni: ");
                    input=sc.nextLine();
                    HashSet<Abitazione> insieme = air.ottieniAbHost(input);
                    if (insieme!=null)
                    {
                        for (Abitazione a:insieme)
                        {
                            System.out.println(a.getNome()+" in "+a.getIndirizzo()+'\n'+"ID: "+a.getID()+'\n'+"_______________________________");
                        }

                    }else System.out.println("L'host non ha attualmente abitazioni");

                }
                if (input.equals("ULTIMA PRENOTAZIONE UTENTE"))
                {
                    for (Utente u:air.getUtenteprenotazioni().keySet())
                    {
                        System.out.println(u.getNome()+" "+u.getCognome()+" "+u.getEmail()+'\n'+"ID: "+u.getID()+'\n'+"_________________________________");
                    }
                    System.out.println("Inserisci ID dell'utente per visualizzare la sua ultima prenotazione: ");
                    input=sc.nextLine();
                    Prenotazione p = air.ultimaPrenUsr(input);
                    if(p!= null)
                    {
                        System.out.println(p.getUtente().getNome()+" "+p.getUtente().getCognome()+" ha effettuato la sua ultima prenotazione presso: "+p.getAbitazione().getNome()+" in"+p.getAbitazione().getIndirizzo()+" dal "+p.getInizio().format( DateTimeFormatter.ofPattern("dd/MM/yyyy"))+" al "+ p.getFine().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    }else System.out.println("L'id inserito non è valido");
                }
                if (input.equals("LISTA SUPERHOST"))
                {
                    HashSet<Utente> insieme =air.tuttiIsuperhost();
                    if(insieme!=null)
                    {
                        for (Utente u:insieme)
                        {
                            System.out.println(u.getNome()+" "+u.getCognome()+" "+u.getEmail()+'\n'+"ID: "+u.getIDhost()+'\n'+"_________________________________");
                        }
                    }else System.out.println("Non ci sono SuperHost");
                }
                if (input.equals("HOST PREFERITI"))
                {
                    int mese=0;
                    System.out.println("Inserisci il mese (Numero) :");
                    mese = Integer.parseInt(sc.nextLine());

                    TreeMap<Utente,Integer> mappa = air.hostpreferitiquestomese(Month.of(mese));
                    if(mappa!=null)
                    {
                        for (Utente u:mappa.keySet())
                        {
                            System.out.println("n prenotazioni: "+mappa.get(u)+" "+u.getNome()+" "+u.getCognome()+" "+u.getEmail()+'\n'+"ID: "+u.getIDhost()+'\n'+"_________________________________");
                        }

                    }else System.out.println("Caro facoltoso turista americano hai buttato i soldi, qui non c'è neanche una prenotazione");
                }
                if (input.equals("UTENTI PIU ATTIVI"))
                {
                    int mese=0;
                    System.out.println("Inserisci il mese (Numero) :");
                    mese = Integer.parseInt(sc.nextLine());
                    List<Utente> lista = air.utentipiuattivimese(Month.of(mese));
                    if(lista!=null)
                    {
                        for (Utente u:lista)
                        {
                            System.out.println(u.getNome()+" "+u.getCognome()+" "+u.getEmail()+'\n'+"ID: "+u.getID()+'\n'+"_________________________________");
                        }

                    }else System.out.println("Caro facoltoso turista americano hai buttato i soldi, qui non c'è neanche una prenotazione");
                }
                if (input.equals("RICHIESTE HOST"))
                {
                    if(air.getUtenteprenotazioni()!=null)
                    {
                        for (Utente u:air.getUtenteprenotazioni().keySet())
                        {
                            if (u.isRichiestahost()&&!u.isHost()) System.out.println(u.getNome()+" "+u.getCognome()+" "+u.getEmail()+'\n'+"ID: "+u.getID()+'\n'+"_________________________________");
                        }
                    }else System.out.println("Caro facoltoso turista americano hai buttato i soldi, qui non c'è neanche un utente");
                    System.out.println("Inserisci ID dell'utente che vuoi approvare come host: ");
                    input= sc.nextLine();
                    if (air.getUtenti().containsKey(input))
                    {
                        air.autorizzaHost(air.getUtenti().get(input));
                        System.out.println("Utente Approvato");
                    }

                }
                if (input.equals("MEDIA POSTI LETTO"))
                {
                    System.out.println("La media dei posti letto delle abitazioni oggi presenti è : "+air.mediaPletto());
                }
                if (input.equals("BACK")) run3=false;
                }
            }

        }

    }
}
