package Mappe;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;

public class Dictionary {
    TreeMap<Character, TreeMap<String, List<String>>> mappadizionario = new TreeMap<>();
    public Dictionary(){};

    public void insert(String parola, String significato)
    {
        parola = parola.toUpperCase(Locale.ROOT);
        significato=significato.toUpperCase(Locale.ROOT);
        List<String> listasignificati = new ArrayList<>();
        TreeMap<String, List<String>> mappaparola = new TreeMap<>();
        listasignificati.add(significato);
        char lettera = parola.charAt(0);
        if (!mappadizionario.containsKey(lettera))
        {
            mappaparola.put(parola, listasignificati);
            mappadizionario.put(lettera, mappaparola);
        }
        else if (mappadizionario.containsKey(lettera))
        {
            if (!mappadizionario.get(lettera).containsKey(parola))
                mappadizionario.get(lettera).put(parola, listasignificati);
            else if (mappadizionario.get(lettera).containsKey(parola))
            {
                if (!mappadizionario.get(lettera).get(parola).contains(significato))
                    mappadizionario.get(lettera).get(parola).add(significato);
            }
        }


    }

    public static void main(String[] args) {
        Dictionary dizionario= new Dictionary();
        dizionario.insert("calcio","elemento chimico");
        dizionario.insert("calcio","sport");
        dizionario.insert("calcio","sonotroppoavasnti");
        dizionario.insert("cadmio","elemento chimico");
        dizionario.insert("football","sport");
        System.out.println(dizionario.mappadizionario.get('C'));
        System.out.println(dizionario.mappadizionario.get('F'));
    }
}










