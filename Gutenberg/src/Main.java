import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();


        File folder = new File("/Users/Giuse/IdeaProjects/Gutenberg/Libri");
        File[] listOfFiles = folder.listFiles();
        assert listOfFiles != null;


        Map<File, Double> mappadistanze = new HashMap<>();
        for (File f : listOfFiles) {
            mappadistanze.put(f, (double) 0);
        }

        List<Libro> listalibri = new ArrayList<>();

        for (File f : listOfFiles)
        {
            Libro libro = new Libro(f, mappadistanze);
            listalibri.add(libro);
        }


        ExecutorService pool = Executors.newFixedThreadPool(8);

        for (Libro l : listalibri) {
            try {
                l.setVett(pool.submit(new Contalettere(l.getFile())).get());
            } catch (InterruptedException | ExecutionException e)
            {
                e.printStackTrace();
            }
        }

        pool.shutdown();
        try {
            pool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<Thread> listath = new ArrayList<>();
        for (Libro l : listalibri) {
            Distanza nuova = new Distanza(listalibri, l);
            nuova.start();
            listath.add(nuova);
        }
        for (Thread t : listath) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (Libro l : listalibri) {
            System.out.println("Il testo : " + l.getFile().getName().substring(0, l.getFile().getName().length() - 4) + " è piu vicino a :");
            int i = 0;
            for (File f : l.getMappadistanze().keySet()) {
                System.out.println("    " + (i + 1) + "- " + f.getName().substring(0, f.getName().length() - 4) + ": " + l.getMappadistanze().get(f));
                i++;
                if (i == 5) break;
            }

        }
        System.out.println(System.currentTimeMillis() - startTime);
    }
}

