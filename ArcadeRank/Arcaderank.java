package ArcadeRank;

import java.util.*;
import java.util.stream.Collectors;

public class Arcaderank
{
    private Map<String,Utente> usersmap = new HashMap<>();
    private Map<String,Videogioco> gamesmap= new HashMap<>();
    private List<Partita> listapartite = new ArrayList<>();

    public void InserisciUtente (Utente utente)
    {
        usersmap.put(utente.getID(), utente);
    }

    public void InserisciVideogioco(Videogioco gioco)
    {
        gamesmap.put(gioco.getID(),gioco);
    }

    public void InserisciPartita(String IDUtente, String IDGioco, int punteggio)
    {
        Partita nuova = new Partita(IDUtente,IDGioco,punteggio);
        listapartite.add(nuova);
        Utente user = usersmap.get(IDUtente);
        Videogioco gioco = gamesmap.get(IDGioco);
        if (gioco.getPunteggi().get(user)!= null && gioco.getPunteggi().get(user)>punteggio) return;
        gioco.getPunteggi().put(user,punteggio);
    }
    public Map<Utente,Integer> Top3Locale (String IDGioco)
    {
        return gamesmap.get(IDGioco).getPunteggi().entrySet().stream().sorted((a,b)-> -a.getValue().compareTo(b.getValue())).
                limit(3).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(a,b)->a,LinkedHashMap::new));
    }
    public List<Partita> ultimeNpartite (int n)
    {
        if (listapartite.size()>n)  return listapartite.subList(listapartite.size()-n,listapartite.size());
        return listapartite;
    }

    public Map<Utente, Integer> ClassificaGlobale()
    {
        Map<Utente,Integer> nuova = new HashMap<>();
        gamesmap.values().stream().forEach(x->x.getPunteggi().entrySet().stream().forEach(y->nuova.put(y.getKey(),Optional.ofNullable(nuova.get(y.getKey())).orElse(0)+y.getValue()*x.getdifficolta())));
        return nuova.entrySet().stream().sorted((a,b)->-a.getValue().compareTo(b.getValue())).limit(3).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(a,b)->a,LinkedHashMap::new));
    }
    private Integer nullornot(Integer x)
    {
        if (x==null) return 0;
        else return x;
    }

    public static void main(String[] args) {
        Utente Marco = new Utente("MarcoV");
        Utente Peppe = new Utente("Peppino");
        Utente Gamba = new Utente("Gamba7");
        Utente Melvin = new Utente("Melvin");
        Utente io = new Utente("BipedBoat");
        Videogioco Destiny = new Videogioco("Destiny",3);
        Videogioco MarioBros = new Videogioco("SuperMario",2);
        Videogioco SpaceInveders = new Videogioco("SpaceInvaders",2);
        Videogioco Warzone = new Videogioco("Warzone",5);
        Videogioco Fifa = new Videogioco("Fifa",1);
        Arcaderank rank = new Arcaderank();
        rank.InserisciUtente(Marco);
        rank.InserisciUtente(Peppe);
        rank.InserisciUtente(Gamba);
        rank.InserisciUtente(Melvin);
        rank.InserisciUtente(io);
        rank.InserisciVideogioco(Destiny);
        rank.InserisciVideogioco(MarioBros);
        rank.InserisciVideogioco(SpaceInveders);
        rank.InserisciVideogioco(Warzone);
        rank.InserisciVideogioco(Fifa);
        rank.InserisciPartita(Gamba.getID(),Destiny.getID(),600);
        rank.InserisciPartita(Gamba.getID(),MarioBros.getID(),50);
        rank.InserisciPartita(Gamba.getID(),SpaceInveders.getID(),1200);
        rank.InserisciPartita(Gamba.getID(),Warzone.getID(),2);
        rank.InserisciPartita(Gamba.getID(),Fifa.getID(),150);
        rank.InserisciPartita(Marco.getID(),Destiny.getID(),300);
        rank.InserisciPartita(Marco.getID(),MarioBros.getID(),25);
        rank.InserisciPartita(Marco.getID(),SpaceInveders.getID(),600);
        rank.InserisciPartita(Marco.getID(),Warzone.getID(),1);
        rank.InserisciPartita(Marco.getID(),Fifa.getID(),75);
        rank.InserisciPartita(Peppe.getID(),Destiny.getID(),1800);
        rank.InserisciPartita(Peppe.getID(),MarioBros.getID(),100);
        rank.InserisciPartita(Peppe.getID(),SpaceInveders.getID(),850);
        rank.InserisciPartita(Peppe.getID(),Warzone.getID(),66);
        rank.InserisciPartita(Peppe.getID(),Fifa.getID(),20);
        rank.InserisciPartita(Melvin.getID(),Destiny.getID(),10);
        rank.InserisciPartita(Melvin.getID(),MarioBros.getID(),50);
        rank.InserisciPartita(Melvin.getID(),SpaceInveders.getID(),2000);
        rank.InserisciPartita(Melvin.getID(),Warzone.getID(),50);
        rank.InserisciPartita(Melvin.getID(),Fifa.getID(),200);
        rank.InserisciPartita(io.getID(),Destiny.getID(),100);
        rank.InserisciPartita(io.getID(),MarioBros.getID(),500);
        rank.InserisciPartita(io.getID(),SpaceInveders.getID(),1000);
        rank.InserisciPartita(io.getID(),Warzone.getID(),1000);
        rank.InserisciPartita(io.getID(),Fifa.getID(),2500);


        System.out.println(rank.ClassificaGlobale());




    }




}
