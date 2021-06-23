package ProblemSolving;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class subsets
{
    public static void main(String[] args) {
        int[] array = {1,2,2};
        ArrayList<ArrayList<Integer>> lista = new ArrayList();
        ArrayList<Integer> nullo = new ArrayList<>();
        lista.add(nullo);

        for (int num : array)
        {
            ArrayList<ArrayList<Integer>> nuovalista = new ArrayList();

            for (ArrayList<Integer> insieme : lista)
            {
                ArrayList<Integer> sottoinsieme = new ArrayList<>();
                sottoinsieme.addAll(insieme);
                sottoinsieme.add(num);
                sottoinsieme.sort(Integer::compareTo);
                nuovalista.add(sottoinsieme);
            }

            for (ArrayList<Integer> insieme : nuovalista) {
                insieme.sort(Integer::compareTo);
                if (!lista.contains(insieme)) lista.add(insieme);
            }
        }

        for (ArrayList<Integer> insieme:lista)
        {
            System.out.println(insieme);
        }


    }
}
