
import java.io.File;
import java.util.*;

public class Distanza extends Thread {
    List<Libro> lista;
    Libro libro;
    public Distanza(List<Libro> lista,Libro libro)
    {
        this.lista=lista;
        this.libro=libro;
    }

    @Override
    public void run() {
        double dist;
        for (Libro l:lista)
        {   dist=0;
            if (!l.equals(libro))
            {
                long[] vettl = l.getVett();
                long[] vettlibro = libro.getVett();
                int p = vettl.length;

                for (int i=0;i<p;i++)
                        {
                            dist = dist + (Math.pow(Math.abs(vettlibro[i]-vettl[i]),p));
                        }

                dist = Math.pow(dist,1.0/p);

                libro.getMappadistanze().put(l.getFile(),dist);


            }
        }
        BookComparator comparator = new BookComparator(libro.getMappadistanze());
        Map<File, Double> nuova = new TreeMap<>(comparator);
        nuova.putAll(libro.getMappadistanze());
        libro.setMappadistanze(nuova);


    }
}
class BookComparator implements Comparator<File>
{
    Map<File, Double> map;
    public BookComparator(Map<File, Double> map)
    {
        this.map = map;
    }
    @Override
    public int compare(File o1, File o2)
    {
        return ((Double) map.get(o1)).compareTo((Double) map.get(o2));
    }

}
