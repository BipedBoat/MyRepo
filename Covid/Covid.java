package Thread.Covid;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Covid
{
    Set<String > synset = Collections.synchronizedSet(new HashSet<>());

    public static void main(String[] args) {
        Covid v = new Covid();
        Caso1 t1 = new Caso1(v.synset, "nord.txt");
        Caso1 t2 = new Caso1(v.synset, "sud.txt");
        Caso1 t3 =new Caso1(v.synset,"centro.txt");

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();

        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }

        System.out.println(v.synset.size());
        try {
            BufferedWriter scrivi = new BufferedWriter(new FileWriter("positivi.txt"));
            for (String a: v.synset)
            {
                scrivi.write(a+'\n');
            }
            scrivi.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }



    }

}
