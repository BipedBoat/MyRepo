package ProblemSolving;

public class uniquepath3
{
    int percorsi=0;
    int empty=0;

    public void ciclamatrice(int[][] grid, int row, int col, int cont)
    {
        if (row<0 || col<0 || row>= grid.length|| col>=grid[0].length) return;
        if ((grid[row][col]==2)&&cont==empty) {percorsi++; return;}
        if (grid[row][col] == -1) return;
        if (grid[row][col] == 0)
        {
            cont++;
            grid[row][col] = -1;
            ciclamatrice(grid, row + 1, col, cont);
            ciclamatrice(grid, row - 1, col, cont);
            ciclamatrice(grid, row, col + 1, cont);
            ciclamatrice(grid, row, col - 1, cont);
            grid[row][col] = 0;
        }

    }

    public  int[] trovainizio(int[][] grid)
    {
        int rowstart =0;
        int colstart=0;
        for (int i=0;i<grid.length;i++)
        {
            for (int j=0;j<grid[0].length;j++)
            {
                if (grid[i][j]==1) {rowstart=i;colstart=j;}
                if (grid[i][j]==0) {empty++;}
            }
        }
        int[] start = {rowstart,colstart};
        return start;
    }



    public static void main(String[] args)
    {
        uniquepath3 soluzione = new uniquepath3();
        int [][] grid ={{1,0,0,0},{0,0,0,0},{0,0,2,-1}};
        int [] start = soluzione.trovainizio(grid);
        int row=start[0];
        int col = start[1];
        System.out.println(row+" "+col);

        soluzione.ciclamatrice(grid, row + 1, col, 0);
        soluzione.ciclamatrice(grid, row - 1, col, 0);
        soluzione.ciclamatrice(grid, row, col + 1, 0);
        soluzione.ciclamatrice(grid, row, col - 1, 0);
        System.out.println(soluzione.percorsi);

    }
}
