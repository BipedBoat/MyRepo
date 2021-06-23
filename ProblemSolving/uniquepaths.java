package ProblemSolving;

import java.util.Arrays;

public class uniquepaths
{
    public static void unique (int m, int n)
    {
        int [][] matrice = new int [n][m];

            for (int j=m-1;j>=0;j--)
            {
                matrice[n-1][j] =1;

            }

            for (int i=n-1;i>=0;i--)
            {
                matrice[i][m-1] =1;
            }

        for (int i=n-2;i>=0;i--)
        {
            for (int j=m-2;j>=0;j--)
            {
                matrice[i][j]= matrice[i][j+1]+matrice[i+1][j];
            }
        }
        for (int i=0;i<n;i++)
        {
            System.out.println(Arrays.toString(matrice[i]));
        }
    }

    public static void main(String[] args) {
        unique(3,7);
    }
}
