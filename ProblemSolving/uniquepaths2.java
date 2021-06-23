package ProblemSolving;

import java.util.Arrays;

public class uniquepaths2
{

        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            int n= obstacleGrid.length;
            int m=obstacleGrid[0].length;
            int[][] verifica = new int[n][m];
            boolean ostacolo =false;
            if (m==1||n==1)
            {
                for (int j=0;j<m;j++)
                {
                    if (obstacleGrid[0][j] == 1) return 0;
                }
                for (int i=0;i<n;i++)
                {
                    if (obstacleGrid[i][0] == 1) return 0;
                }
                return 1;
            }

            for (int i=n-1;i>=0;i--)
            {
                if (obstacleGrid[i][m-1]!=1 && !ostacolo) verifica[i][m-1]=1;
                else {verifica[i][m-1] =0; ostacolo=true;}
            }

            ostacolo =false;
            for (int j=m-1;j>=0;j--)
            {
                if (obstacleGrid[n-1][j]!=1 && !ostacolo) verifica[n-1][j]=1;
                else {verifica[n-1][j] =0; ostacolo=true;}
            }

            for (int i=n-2;i>=0;i--)
            {
                for (int j=m-2;j>=0;j--)
                {
                    if (obstacleGrid[i][j]==1) verifica[i][j]=0;
                    else verifica[i][j]= verifica[i][j+1]+verifica[i+1][j];
                }
            }
            return verifica[0][0];

        }
    }

