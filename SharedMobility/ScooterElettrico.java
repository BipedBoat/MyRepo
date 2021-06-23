package SharedMobility2;

import java.util.Random;

public class ScooterElettrico extends VeicoliNonImmatricolatiConMotore implements GeneraID
{

    public ScooterElettrico(String marca, String modello, double prezzoalminuto, double consumo, int capacitabatteria, Coordinate coordinateveicolo) {
        super(marca, modello, prezzoalminuto, consumo, capacitabatteria, coordinateveicolo);
        super.setIdveicolo(GeneraIdentificativo());
    }

    @Override
    public String GeneraIdentificativo()
    {
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
