package ClassificaserieA;

public class Classifica {
    private Squadre[] listasquadre;

    public Squadre[] getListasquadre() {
        return listasquadre;
    }

    public void setListasquadre(Squadre[] listasquadre) {
        this.listasquadre = listasquadre;
    }

    public Classifica(Squadre[] listasquadre) {
        setListasquadre(listasquadre);
    }

    public void esitoPartita(Squadre casa, int golcasa, Squadre ospite, int golospite)
    {
        casa.setGolfatti(casa.getGolfatti() + golcasa);
        ospite.setGolfatti(ospite.getGolfatti() + golospite);
        casa.setGolsubiti(casa.getGolsubiti() + golospite);
        ospite.setGolsubiti(ospite.getGolsubiti() + golcasa);

        if (golcasa > golospite) casa.setPunti(casa.getPunti() + 3);
        else if (golcasa < golospite) ospite.setPunti(ospite.getPunti() + 3);
        else {
            casa.setPunti(casa.getPunti() + 1);
            ospite.setPunti(ospite.getPunti() + 1);
        }
    }

    public Squadre[] getClassifica()
    {
        Squadre[] classifica = new Squadre[getListasquadre().length];
        Squadre[] squadre = getListasquadre();
        for (int j = 0; j < squadre.length; j++) {
            int posizione = 0;
            int max=0;

            for (int i = 0; i < squadre.length; i++)
            {
                if (squadre[i] != null && squadre[i].getPunti() >= max)
                {
                    max = max + squadre[i].getPunti();
                    posizione = i;
                }
            }
            classifica[j] = squadre[posizione];
            squadre[posizione] = null;
        }
         setListasquadre(classifica);

        return classifica;

    }
    public void stampaclassifica(Squadre[] listasquadre)
    {
        for (int i=0;i< listasquadre.length;i++)
        {
            System.out.println((i+1)+". "+ listasquadre[i].getNome()+" "+listasquadre[i].getPunti());
        }
    }
    public Squadre getMigliorAttacco()
    {
        Squadre[] squadre = getListasquadre();
        int posizione = 0;
        int max=0;

        for (int i = 0; i < squadre.length; i++)
        {
            if (squadre[i] != null && squadre[i].getGolfatti() >= max)
            {
                max = squadre[i].getGolfatti();
                posizione = i;
            }
        }
        return squadre[posizione];
    }
    public Squadre getPeggiorDifesa()
    {
        Squadre[] squadre = getListasquadre();
        int posizione = 0;
        int max=0;

        for (int i = 0; i < squadre.length; i++)
        {
            if (squadre[i] != null && squadre[i].getGolsubiti() >= max)
            {
                max = squadre[i].getGolsubiti();
                posizione = i;
            }
        }
        return squadre[posizione];
    }

    public static void main(String[] args) {
        Calciatori Ronaldo= new Calciatori("Cristiano", "Penaldo", "1");
        Calciatori Mertens =new Calciatori("Ciro", "Mertens", "2");
        Calciatori Lukaku= new Calciatori("Romelu", "Lukaku", "3");
        Calciatori Zlatan =new Calciatori("Zlatan", "Ibrahimovic", "4");
        Calciatori[] rosanapoli = {Mertens};
        Calciatori[] rosajuventus = {Ronaldo};
        Calciatori[] rosainter = {Lukaku};
        Calciatori[] rosamilan = {Zlatan};
        Squadre Napoli = new Squadre("01","SSC Napoli",rosanapoli);
        Squadre Rubentus = new Squadre("02","Rubentus",rosajuventus);
        Squadre Inter = new Squadre("03","Inter",rosainter);
        Squadre Milan = new Squadre("04","Milan",rosamilan);
        Squadre[] listasquadre = {Napoli,Rubentus,Inter,Milan};
        Classifica classifica =new Classifica(listasquadre);
        classifica.esitoPartita(Milan,4,Inter,4);
        classifica.esitoPartita(Napoli,3,Rubentus,1);
        classifica.stampaclassifica(classifica.getClassifica());
        System.out.println("il miglior attacco è : " + classifica.getMigliorAttacco().getNome());
        System.out.println("la peggior difesa è : "+classifica.getPeggiorDifesa().getNome());


    }
}
