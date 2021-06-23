package Tinder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Tinder
{
    private HashMap<Utente, HashSet<Interesse>> tinder = new HashMap<>();
    private List<Utente> listautenti= new ArrayList<>();

    public void Aggiungiutente(Utente utente, HashSet<Interesse> interessi)
    {
        if (!tinder.containsKey(utente))
        {
            tinder.put(utente,interessi);
        }
            else
            {
                HashSet<Interesse> nuoviinteressi = new HashSet<>();
                nuoviinteressi.addAll(interessi);
                tinder.get(utente).addAll(nuoviinteressi);
            }
        }

    public void trovapiusimili ()
    {
        int max=0;
        Utente umax = null;
        Utente umax2= null;
        for (Utente utente: tinder.keySet())
        {
            for (Utente utente2: tinder.keySet())
            {
                int match=0;
                for (Interesse interesse: tinder.get(utente))
                {
                    if (tinder.get(utente2).contains(interesse)) match++;
                }
                if (match > max&&!utente2.equals(utente)) { max=match ; umax = utente;umax2 = utente2;}
            }

            }

        System.out.println(umax.getNickname()+" "+umax2.getNickname());
    }
    public void trovamatch (Utente u)
    {
        int max=0;
        Utente umax = null;

            for (Utente utente: tinder.keySet())
            {
                int match=0;
                for (Interesse interesse: tinder.get(utente))
                {
                    if (tinder.get(u).contains(interesse)) match++;
                }
                if (match > max&&!utente.equals(u)) { max=match ; umax = utente;}
            }


        System.out.println(umax.getNickname());
    }



    public static void main(String[] args)
    {
        Tinder tinder = new Tinder();
        Interesse Musica = new Interesse("Musica");
        Interesse Sport = new Interesse("Sport");
        Interesse Sballarsi = new Interesse("Sballarsi");
        Interesse Dormire = new Interesse("Dormire");
        Interesse Cucina = new Interesse("Cucina");
        HashSet<Interesse> iutente1 = new HashSet<>();
        iutente1.add(Musica);
        iutente1.add(Sballarsi);
        iutente1.add(Dormire);
        Utente utente1 = new Utente("Bebe78");
        HashSet<Interesse> iutente2 = new HashSet<>();
        iutente2.add(Cucina);
        iutente2.add(Sport);
        iutente2.add(Musica);
        Utente utente2 = new Utente("Yereke");
        tinder.Aggiungiutente(utente1,iutente1);
        tinder.Aggiungiutente(utente2,iutente2);
        HashSet<Interesse> iutente3 = new HashSet<>();
        iutente3.add(Sballarsi);
        iutente3.add(Sport);
        iutente3.add(Dormire);
        Utente utente3 = new Utente("Baby2003");
        tinder.Aggiungiutente(utente3,iutente3);
        tinder.trovamatch(utente3);
        tinder.trovapiusimili();



    }


}
