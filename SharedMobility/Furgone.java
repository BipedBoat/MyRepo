package SharedMobility2;

import java.util.Random;

public class Furgone extends VeicoliImmatricolati implements GeneraID
{
    private int capienza;
    private boolean ariacondiz;

    public Furgone(String marca, String modello,String targa, Tipomotore tipomotore, double consumo, double prezzoalminuto, double capacitaenergiamax,boolean ariacondiz,int capienza, Coordinate coordinateveicolo)
    {
        super(marca, modello,targa, tipomotore, consumo, prezzoalminuto, capacitaenergiamax, coordinateveicolo);
        setCapienza(capienza);
        setAriacondiz(ariacondiz);
        super.setPatenterichiesta(Patente.PATENTEB2);
        super.setIdveicolo(GeneraIdentificativo());
    }

    public int getCapienza() {
        return capienza;
    }

    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }

    public boolean isAriacondiz() {
        return ariacondiz;
    }

    public void setAriacondiz(boolean ariacondiz) {
        this.ariacondiz = ariacondiz;
    }

    @Override
    public String GeneraIdentificativo() {
        Random rnd = new Random();
        String s = new String();
        s=s.concat(getMarca().substring(0,2));
        s=s.concat(getModello().substring(0,2));
        s=s.concat(getTipomotore().name().substring(0,2));
        s=s.concat(String.valueOf(rnd.nextInt(9)));
        s=s.concat(String.valueOf(rnd.nextInt(9)));
        s=s.concat(String.valueOf(rnd.nextInt(9)));
        s=s.concat(String.valueOf(rnd.nextInt(9)));
        s=s.toUpperCase();
        return s;
    }
}
