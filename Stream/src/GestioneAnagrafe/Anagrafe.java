package GestioneAnagrafe;

import java.util.*;
import java.util.stream.Collectors;

public class Anagrafe
{
    private Set<Persona> listapersone;


    public Anagrafe(Set<Persona> listapersone)
    {
        this.listapersone = listapersone;
    }

    public void rimuovidaCF (String CF)
    {
        listapersone.remove(listapersone.stream().filter(x->x.getCodicefiscale().equals(CF)).findFirst().get());
    }
    public Set<Persona> ottienidaNome(String nome)
    {
       return listapersone.stream().filter(x->x.getNome().toUpperCase(Locale.ROOT).startsWith(nome.toUpperCase(Locale.ROOT))).collect(Collectors.toSet());
    }

    public Set<Persona> piuanziani()
    {
      return listapersone.stream().sorted(Comparator.comparingInt(Persona::getEta).reversed()).limit(3).collect(Collectors.toSet());
    }

    public Set<String> trovaindirizzi(String nome)
    {
        return listapersone.stream().filter(x->x.getNome().toUpperCase(Locale.ROOT).equals(nome.toUpperCase(Locale.ROOT))).map(Persona::getIndirizzo).collect(Collectors.toSet());
    }
    public Set<String> getFigli(String nome)
    {
        return listapersone.stream().filter(x->x.getNome().toUpperCase(Locale.ROOT).startsWith(nome.toUpperCase(Locale.ROOT))).map(Persona::getFigli).flatMap(Collection::stream).collect(Collectors.toSet());
    }

    public void Inseriscipersona (Persona p)
    {
        listapersone.add(p);
    }

    public Set<Persona> getListapersone()
    {
        return listapersone;
    }

    public void setListapersone(Set<Persona> listapersone)
    {
        this.listapersone = listapersone;
    }

    public static void main(String[] args) {
        Persona Marco = new Persona("Marco","Gambuti",30,"GGGPFLE","Via a caso");
        Persona Giuseppe = new Persona("Giuseppe","Deg",27,"DGVGPP","Via Arno");
        Persona Vizzarro = new Persona("Marco","Vizzarro",25,"MRCVZZ","Via Coviddi");
        Set<Persona> lista = new HashSet<>();
        lista.add(Marco);
        lista.add(Giuseppe);
        lista.add(Vizzarro);
        Anagrafe a = new Anagrafe(lista);
        Persona Giovanni = new Persona("Giovanni","Bricco",15,"GVNNBRR","Via Girasoli");
        Persona Giacomo = new Persona("Giacomo","Bricco",58,"GCMNBRR","Via Girasoli");
        Giacomo.getFigli().add(Giovanni.getNome());
        Persona Maria = new Persona("Maria","Bricco",49,"MRNNBRR","Via Girasoli");
        Maria.getFigli().add(Giacomo.getNome());
        Persona Anna = new Persona("Anna","Caputo",45,"ANNCPT","Via Roma");
        Persona Giacomo2 = new Persona("Giacomo","Verde",60,"GVNVRD","Via Lussenburgo");
        Persona Giorgia = new Persona("Giorgia","Verde",15,"GRGVRD","Via Lussenburgo");
        Giacomo2.getFigli().add(Giorgia.getNome());
        a.Inseriscipersona(Giovanni);
        a.Inseriscipersona(Giacomo);
        a.Inseriscipersona(Maria);
        a.Inseriscipersona(Anna);
        a.Inseriscipersona(Giacomo2);
        a.Inseriscipersona(Giorgia);
        a.rimuovidaCF("GRGVRD");
        for (Persona p:a.getListapersone())
        {
            System.out.println(p.getNome()+" "+p.getCognome());
        }

    }
}
