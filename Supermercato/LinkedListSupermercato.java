package Supermercato;


import java.util.LinkedList;


public class LinkedListSupermercato
{
    public LinkedList<Clientisupermercato> getCoda() {
        return coda;
    }

    public void setCoda(LinkedList<Clientisupermercato> coda) {
        this.coda = coda;
    }

    private LinkedList<Clientisupermercato> coda = new LinkedList<>();

    public LinkedListSupermercato(){};

    public void aggiungiincoda(Clientisupermercato cliente)
    {
         coda.addLast(cliente);
    }
    public void rimuoviincoda()
    {
        coda.removeLast();
    }
    public Clientisupermercato prossimodaservire()
    {
        Clientisupermercato primofila = coda.getFirst();
        coda.removeFirst();
        return primofila;
    }

    public int npersoneincoda ()
    {
      return coda.lastIndexOf(coda.getLast())+1;
    }
    public void cambiaposto(Clientisupermercato cliente, int posti)
    {
        coda.remove(cliente);
        coda.addFirst(cliente);
    }

    public static void main(String[] args) {
        Clientisupermercato cliente1 = new Clientisupermercato(55,"Peppe");
        Clientisupermercato cliente2 = new Clientisupermercato(66,"Marco");
        Clientisupermercato cliente3 = new Clientisupermercato(32,"Giovanni");
        Clientisupermercato cliente4 = new Clientisupermercato(40,"Lucia");
        Clientisupermercato cliente5 = new Clientisupermercato(10,"Angela");
        Clientisupermercato cliente6 = new Clientisupermercato(18,"Anna");
        LinkedListSupermercato supermercato = new LinkedListSupermercato();
        supermercato.aggiungiincoda(cliente1);
        supermercato.aggiungiincoda(cliente2);
        supermercato.aggiungiincoda(cliente3);
        supermercato.aggiungiincoda(cliente4);
        supermercato.aggiungiincoda(cliente5);
        supermercato.aggiungiincoda(cliente6);
        supermercato.cambiaposto(cliente3,4);
        System.out.println(supermercato.prossimodaservire().getNome());
        System.out.println(supermercato.prossimodaservire().getNome());
        System.out.println(supermercato.prossimodaservire().getNome());
        System.out.println(supermercato.prossimodaservire().getNome());
        System.out.println(supermercato.prossimodaservire().getNome());




        System.out.println(supermercato.npersoneincoda());


    }
}
