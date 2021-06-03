package Giorno4;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;


public class Tombola {

    public static void main(String[] args) {


        int[][] cartella = new int[3][5];

        scrivicartella(cartella);

        int[] estrazione = new int[15];

        stampacartella(cartella);

        estrainumeri(estrazione);

        verificavincita(cartella, estrazione);


        /* int [][] cartella =
        {
        {12,58,90,36,71},
        {1,5,9,7,3},
        {52,88,77,64,22}
        };
        int [] estrazione = {12,58,90,36,71,1,5,9,7,3,52,88,77,64,22};
        stampacartella(cartella);
        Arrays.sort(estrazione);
        System.out.println("I numeri estratti sono: ");
        System.out.println(Arrays.toString(estrazione));
        verificavincita (cartella, estrazione);                 */ //CASO TOMBOLA

        /*int [][] cartella =
                {
                {12,58,90,36,71},
                {1,5,9,7,3},
                {52,88,77,64,22}
                };
        int [] estrazione = {12,59,89,36,71,2,5,9,7,3,53,88,78,65,23};
        stampacartella(cartella);
        Arrays.sort(estrazione);
        System.out.println("I numeri estratti sono: ");
        System.out.println(Arrays.toString(estrazione));
        verificavincita (cartella, estrazione);                 */ //CASO VINCITA
    }


    public static void scrivicartella (int[][] cartella)
    {
        Scanner sc = new Scanner(System.in);
        int j=0;
        int i =0;
        int numero;

        while (i<3)
        {
            while (j < 5)
            {
                System.out.println("Inserisci l'elemento n° "+ (j+1) + "  della " + (i+1) + " riga della cartella:");
                numero = sc.nextInt();
                boolean doppione = false;
                for (int righe = 0; righe < 3; righe++)
                {
                    for (int colonne = 0; colonne < 5; colonne++)
                    {
                        if (cartella[righe][colonne] == numero)
                        {
                            doppione = true;
                            break;
                        }
                    }
                }
                while (numero<1 || numero>90 || doppione)
                {
                    if (numero<1 || numero>90)
                    {
                        System.out.println("Il numero dev'essere compreso tra 1 e 90, reinserisci l'elemento n° "+ (j+1) + "  della " + (i+1) + " riga :");
                    }
                    else System.out.println("Questo numero è gia stato inserito , reinserisci l'elemento n° "+ (j+1) + "  della " + (i+1) + " riga :");
                    numero = sc.nextInt();
                    doppione = false;
                    for (int righe = 0; righe < 3; righe++)
                    {
                        for (int colonne = 0; colonne < 5; colonne++)
                        {
                            if (cartella[righe][colonne] == numero)
                            {
                                doppione = true;
                                break;

                            }

                        }
                    }

                }
                cartella [i][j] = numero;
                j++;
            }
            j=0;
            i++;
        }
    }
    public static void estrainumeri (int [] estrazione)
    {
        Random rnd = new Random();
        int estratti = 0;
        int n;
        boolean nondoppione;
        while (estratti<15)
        {
            do
            {
                nondoppione = true;
                n = rnd.nextInt(89) + 1;
                for (int i = 0; i < 15; i++)
                {
                    if (n==estrazione[i])
                    {
                        nondoppione = false;
                        break;
                    }
                }
            } while(!nondoppione);

            estrazione [estratti] = n;
            estratti++;
        }
        Arrays.sort(estrazione);
        System.out.println("I numeri estratti sono: ");
        System.out.println(Arrays.toString(estrazione));

    }
    public static void verificavincita (int [][] cartella,int [] estrazione)
    {

        int ambo =0;
        int terno = 0;
        int quaterna =0;
        int cinquina=0;
        int tombola=0;

            for (int i =0;i<3;i++)
            {
                int count;
                count =0;
                for (int j = 0; j < 5; j++)
                {
                    for (int k = 0; k < 15; k++)
                    {
                        if (cartella[i][j] == estrazione[k])
                        {
                            count++;
                            tombola++;
                        }
                    }
                }
                switch (count)
                {
                    case 2 -> ambo = (ambo + 1);
                    case 3 -> terno = (terno + 1);
                    case 4 -> quaterna = (quaterna + 1);
                    case 5 -> cinquina = (cinquina + 1);
                }

            }

        if (tombola<15)
        {
            System.out.println("Il giocatore ha totalizzato: " + ambo + " ambi, " + terno + " terni, " + quaterna + " quaterne, " + cinquina + " cinquine");
        }
        else System.out.println("TOMBOLA!!");
    }

    public static void stampacartella (int[][] cartella)
    {
        Arrays.sort(cartella[0]);                                               //l'ordine è solo per comodità visiva
        Arrays.sort(cartella[1]);
        Arrays.sort(cartella[2]);
        System.out.println("La tua cartella è: ");
        for (int i=0; i<3;i++)
        {
            System.out.print("[");
            for (int j=0 ;j<5;j++)
            {
                if (cartella[i][j]<10)
                System.out.print("  "+cartella[i][j]);
                else  System.out.print(" "+cartella[i][j]);
                if (j<4) System.out.print(",");
            }
            System.out.println("]");
        }
    }

}
