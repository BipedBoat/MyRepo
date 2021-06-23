package Libreria;

import java.util.*;


public class Libreria
{
   private final TreeMap<Autore, List<Libri>> libreria = new TreeMap<>();


    public void InserimentoLibro(Libri libro)
    {
        List<Libri> listalibri = new ArrayList<>();
        listalibri.add(libro);
        if (!libreria.containsKey(libro.getAutore())) libreria.put(libro.getAutore(),listalibri);
        else
        {
            if (!libreria.get(libro.getAutore()).contains(libro)) libreria.get(libro.getAutore()).add(libro);
        }
    }
    public void CancellaLibro(Libri libro)
    {
        if (libreria.containsKey(libro.getAutore()))
               if(libreria.get(libro.getAutore()).contains(libro))
                        libreria.get(libro.getAutore()).remove(libro);
    }


    public void stampa (Autore autore)
    {
        libreria.get(autore).sort(Libri::compareTo);
        System.out.println(autore.getNome()+" "+autore.getCognome()+": ");
        for (Libri libro:libreria.get(autore))
        {

            System.out.println(libro.getTitolo()+": "+libro.getSinossi());
        }


    }

    public void cercalibro(String ricerca)
    {
        int i=1;
        for (Autore autore: libreria.keySet())
        {
            for (Libri libro: libreria.get(autore))
            {
                if ((libro.getTitolo()+libro.getAutore().getNome()+libro.getAutore().getCognome()+libro.getID()).toUpperCase(Locale.ROOT).contains(ricerca.toUpperCase(Locale.ROOT)))
                {
                    System.out.println(i+"- "+libro.getID()+" "+autore.getNome()+" "+autore.getCognome()+": "+libro.getTitolo()+": "+libro.getSinossi());
                    i++;
                }
            }
        }

    }
   /* public void CancellaLibro2(String ricerca)
    {
        int i=1;
        for (Autore autore: libreria.navigableKeySet())
        {
            for (Libri libro: libreria.get(autore))
            {
                if ((libro.getTitolo()+libro.getAutore()+libro.getID()).contains(ricerca))
                {
                    System.out.println(i+"- "+autore.getNome()+" "+autore.getCognome()+": "+libro.getTitolo()+": "+libro.getSinossi()+" "+libro.getID());
                    i++;
                }
            }
        }
        if (i>1){
        System.out.println("Inserisci l'indice del libro che vuoi rimuovere: ");
        int x=1;
        i=1;
        for (Autore autore: libreria.navigableKeySet())
        {
            for (Libri libro: libreria.get(autore))
            {
                if ((libro.getTitolo()+libro.getAutore()+libro.getID()).contains(ricerca))
                {
                    System.out.println(i+"- "+autore.getNome()+" "+autore.getCognome()+": "+libro.getTitolo()+": "+libro.getSinossi()+" "+libro.getID());
                    if (i==x)
                    {
                        CancellaLibro(libro);
                        break;
                    }
                    i++;

                }
            }
        }}
        else System.out.println("Nessun libro corrisponde alla ricerca");
    } */

    public TreeMap<Autore, List<Libri>> getLibreria() {
        return libreria;
    }

    public static void main(String[] args)
    {
       /* Libreria library = new Libreria();
        Autore a = new Autore("John Ronald Reuel", "Tolkien");
        Libri b = new Libri("Il signore degli anelli", "Il Signore degli Anelli (titolo originale in inglese: " +
                        "The Lord of the Rings) è un romanzo high fantasy epico scritto da J. R. R. Tolkien" +
                        " e ambientato alla fine della Terza Era dell'immaginaria Terra di Mezzo",a);

        library.InserimentoLibro(b);
        Libri b2 = new Libri("Lo Hobbit", "Lo Hobbit o la riconquista del tesoro (titolo originale: The Hobbit, sottotitolato" +
                        " There and Back Again, ossia \"Andata e ritorno\")," +
                        " noto anche semplicemente come Lo Hobbit, è un romanzo fantasy scritto da J. R. R. Tolkien.",a);
        library.InserimentoLibro(b2);
        List<Libri> books = null;
        try {
            books = library.getLibreria().get(a);
        } catch (Exception e) {
            System.out.println("My bad");
        }
        System.out.println(books != null);
        System.out.println(books.stream().count() == 2);
        Autore a2 = new Autore("George", "Orwell");
        Libri b3 = new Libri("1984",
                "1984 (Nineteen Eighty-Four) è uno dei più celebri romanzi di George Orwell, " +
                        "pubblicato nel 1949 ma iniziato a scrivere nel" +
                        " 1948 (anno da cui deriva il titolo, ottenuto appunto dall'inversione delle ultime due cifre).",a2);

        library.CancellaLibro(b);
        try {
            books = library.getLibreria().get(a);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(books.stream().count() == 1);
        library.InserimentoLibro(b3);
        try {
            books = library.getLibreria().get(a2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(books.stream().count() == 1);
        Libri b4 = new Libri(
                "La fattoria degli animali",
                "La fattoria degli animali è una novella allegorica di George Orwell pubblicata " +
                        "per la prima volta il 17 agosto 1945. Secondo Orwell, il libro riflette sugli " +
                        "eventi che portarono alla Rivoluzione russa e successivamente all'era staliniana " +
                        "dell'Unione sovietica",a2);
        library.InserimentoLibro(b4);
        library.stampa(a);
        library.stampa(a2);*/

       /* Libreria libreria = new Libreria();
        Scanner sc= new Scanner(System.in);
        boolean ciclo = true;
        while (ciclo){
            System.out.println("Cosa vuoi fare? "+"\n"+"1- Aggiungi Libro"+"\n"+"2- Rimuovi Libro"+"\n"+"3- Cerca Bibliografia di un Autore"+"\n"+"4- Cerca un Libro"+"\n"+"5- Exit");
            int scelta = sc.nextInt();
        switch (scelta)
        {
            case 1:
            {
                String cognomeautore = sc.nextLine();
                System.out.println("Inserisci cognome autore libro: ");
                cognomeautore = sc.nextLine();
                System.out.println("Inserisci nome autore libro: ");
                String nomeautore = sc.nextLine();
                System.out.println("Inserisci titolo libro: ");
                String titolo = sc.nextLine();
                System.out.println("Inserisci sinossi libro: ");
                String sinossi = sc.nextLine();
                libreria.InserimentoLibro(new Libri(titolo,sinossi,new Autore(nomeautore,cognomeautore)));
                break;
            }
            case 2:
            {
                String titolo = sc.nextLine();
                System.out.println("Inserisci titolo libro: ");
                titolo = sc.nextLine();
                libreria.CancellaLibro2(titolo);
                break;
            }
            case 3:
            {
                String cognomeautore = sc.nextLine();
                System.out.println("Inserisci cognome autore libro: ");
                cognomeautore = sc.nextLine();
                System.out.println("Inserisci nome autore libro: ");
                String nomeautore = sc.nextLine();
                libreria.stampa(new Autore(nomeautore,cognomeautore));
                break;
            }
            case 4:
            {
                String ricerca = sc.nextLine();
                System.out.println("Inserisci autore, libro o ID : ");
                ricerca=sc.nextLine();
                libreria.cercalibro(ricerca);
                break;
            }
            case 5:
            {
                ciclo=false;
                break;
            }
            default:break;

        }

        }*/

        Libreria libreria = new Libreria();
        Autore io = new Autore("Giuseppe", "Di Giovannan");
        Libri prova = new Libri("prova", "yoyoy",io);
        libreria.InserimentoLibro(prova);

        libreria.cercalibro("prova");

    }
}
