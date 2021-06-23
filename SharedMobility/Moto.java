package SharedMobility2;

import java.util.Random;

public class Moto extends VeicoliImmatricolati implements GeneraID
{

    private boolean cascoincluso;

    public Moto(String marca, String modello,String targa ,Tipomotore tipomotore, double consumo, double prezzoalminuto, double capacitaenergiamax, Coordinate coordinateveicolo)
    {
        super(marca, modello, targa, tipomotore, consumo, prezzoalminuto, capacitaenergiamax, coordinateveicolo);
        super.setPatenterichiesta(Patente.PATENTEA);
        setCascoincluso(true);
        super.setIdveicolo(GeneraIdentificativo());
    }
    public boolean isCascoincluso() {
        return cascoincluso;
    }
    public void Cascosmarrito() {
        setCascoincluso(false);
    }

    public void setCascoincluso(boolean cascoincluso) {
        this.cascoincluso = cascoincluso;
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
