import java.io.File;
import java.util.*;

public class Libro
{
    private File file;
    private Map<File,Double> mappadistanze = new HashMap<>();
    private long[] vett;

    public Libro(File file, Map<File,Double> mappadistanze)
    {
        setFile(file);
        this.mappadistanze.putAll(mappadistanze);
        this.mappadistanze.remove(file);
    }

    public long[] getVett() {
        return vett;
    }

    public void setVett(long[] vett) {
        this.vett = vett;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Map<File, Double> getMappadistanze() {
        return mappadistanze;
    }

    public void setMappadistanze(Map<File, Double> mappadistanze)
    {
        this.mappadistanze=mappadistanze;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return Objects.equals(file, libro.file);
    }

    @Override
    public int hashCode() {
        return Objects.hash(file);
    }

}
