package SharedMobility2;

import java.util.ArrayList;
import java.util.Random;

public class Auto extends VeicoliImmatricolati implements GeneraID
{
    private double capacitaenergiamax;
    private Cambio cambio;
    private boolean ariacondiz;


    public Auto(String marca, String modello, String targa,Tipomotore tipomotore, double consumo, double prezzoalminuto, double capacitaenergiamax, Cambio cambio, boolean ariacondiz, Coordinate coordinateveicolo)
    {
        super(marca, modello,targa,tipomotore, consumo, prezzoalminuto, capacitaenergiamax,coordinateveicolo);
        setAriacondiz(ariacondiz);
        setCapacitaenergiamax(capacitaenergiamax);
        super.setPatenterichiesta(Patente.PATENTEB);
        super.setIdveicolo(GeneraIdentificativo());
        setCambio(cambio);
    }

    public double getCapacitaenergiamax() {
        return capacitaenergiamax;
    }

    public void setCapacitaenergiamax(double capacitaenergiamax) {
        this.capacitaenergiamax = capacitaenergiamax;
    }

    public Cambio getCambio() {
        return cambio;
    }

    public void setCambio(Cambio cambio) {
        this.cambio = cambio;
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

    public static void main(String[] args)
    {
        Auto prova = new Auto("fiat","punto","cd75fr",Tipomotore.DIESEL,10,5,100,Cambio.MANUALE,true,new Coordinate(50,60));
        System.out.println(prova.getIdveicolo());
    }
}
enum Cambio{AUTOMATICO,MANUALE,SEMIAUTOMATICO}
