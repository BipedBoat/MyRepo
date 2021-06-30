package Thread.Covid;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

public class Caso1 extends Thread
{
    private Set<String> possibilicasi;
    private String nomefile;

    public Caso1(Set<String> possibilicasi, String nomefile)
    {
        this.possibilicasi=possibilicasi;
        this.nomefile=nomefile;
    }

    @Override
    public void run() {
        Double temp;
        int eta ;
        boolean gusto;
        boolean tosse;
        boolean debolezza;
        String sitclinica;
        String ID;
        boolean sintomi;

        try (BufferedReader br = new BufferedReader(new FileReader(nomefile))) {
            String line;
            while ((line = br.readLine()) != null)
            {
                String[] parts = line.split(";");
                ID = parts[0];
                temp = Double.valueOf(parts[1]);
                eta = Integer.valueOf(parts[2]);
                gusto = Boolean.parseBoolean(parts[3]);
                tosse = Boolean.parseBoolean(parts[4]);
                debolezza = Boolean.parseBoolean(parts[5]);
                sitclinica = parts[6];
                int sumSymptoms = (gusto ? 1 : 0) + (tosse ? 1 : 0) + (debolezza ? 1 : 0);

                if (!possibilicasi.contains(ID)&&
                        (
                                (temp>=40)||
                                (temp>=38&&sumSymptoms==3)||
                                (sitclinica.equals("CRITICA")&&(temp>=38.5||sumSymptoms>=1))||
                                (eta>=50&&temp>=37)||
                                (eta>=60&&((gusto&&sitclinica.equals("CAUTELA"))||(tosse&&sitclinica.equals("CRITICA"))))
                        )
                    )
                {
                    possibilicasi.add(ID);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
