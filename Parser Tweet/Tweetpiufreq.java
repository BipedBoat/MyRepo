package Mappe;

import com.sun.source.tree.Tree;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.*;

public class Tweetpiufreq {

    public static void main(String[] args) {
        HashMap<String, Integer> mappa = new HashMap<>();
        HashSet<String> stopwords = stopWords();
        try (BufferedReader br = new BufferedReader(new FileReader("realdonaldtrump.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] parts = line.split(",");
                String[] parts2 = parts[2].split(" ");

                for (String str : parts2)
                {
                    str=str.replaceAll("[^a-zA-Z0-9]", "").toUpperCase(Locale.ROOT);
                    if (stopwords.contains(str)) str="";
                    if (str != null && str.length() > 2)
                    {
                        System.out.println(str);
                        if (mappa.containsKey(str)) mappa.put(str,mappa.get(str)+1);
                        else mappa.put(str, 1);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        trovaRicorrenti(mappa,10);







    }


    public static void trovaRicorrenti(Map<String, Integer> mappa, int numeroricorrenti)
    {
        for (int j = 0; j < numeroricorrenti; j++) {
            int i = 0;
            String ricorrente = null;
            for (String prl : mappa.keySet()) {
                if (mappa.get(prl) > i) {
                    i = mappa.get(prl);
                    ricorrente = prl;
                }
            }
            System.out.println("la parola " + ricorrente + " è stata tweettata :" + i + " volte");
            mappa.remove(ricorrente);
        }
    }

    public static String rimuovi(String s) {
        try (BufferedReader br2 = new BufferedReader(new FileReader("english_stopwords.txt"))) {
            String rem;
            while ((rem = br2.readLine()) != null) {
                rem = rem.toUpperCase();
                if (rem.equals(s))

                    s = s.replaceAll(rem.toUpperCase(Locale.ROOT), "");


            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static HashSet<String> stopWords() {
        HashSet<String> stopwords = new HashSet<>();
        try (BufferedReader br2 = new BufferedReader(new FileReader("english_stopwords.txt")))
        {
            String rem;
            while ((rem = br2.readLine()) != null)
            {
                rem = rem.toUpperCase();
                stopwords.add(rem);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stopwords;
    }
}



