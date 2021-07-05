import java.io.*;
import java.util.*;
import java.util.concurrent.Callable;

public class Contalettere implements Callable<long[]> {
    File nomefile ;
    char[] alfabeto = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    public Contalettere(File nomefile)
    {
        this.nomefile=nomefile;
    }



    @Override
    public long[] call() throws Exception {
        int j;
        long[] vett = new long[26];
        try (BufferedReader br = new BufferedReader(new FileReader(nomefile))) {
            String line;
            while ((line = br.readLine()) != null)
            {
                line = line.toUpperCase(Locale.ROOT);
                for (int i=0;i<line.length();i++)
                {
                    j=0;
                    for (char a:alfabeto)
                    {
                        if(line.charAt(i)==a) {vett[j]=vett[j]+1;break;}
                        else j++;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vett;
    }
}

