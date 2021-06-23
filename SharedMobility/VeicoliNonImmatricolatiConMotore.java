package SharedMobility2;

public abstract class VeicoliNonImmatricolatiConMotore extends Veicoli
{
    private int percBatteria;
    private double consumo;
    private int capacitabatteria;
    final private Tipomotore tipomotore = Tipomotore.ELETTRICO;
    private boolean Cascoleggero;


    public VeicoliNonImmatricolatiConMotore(String marca, String modello, double prezzoalminuto,double consumo,int capacitabatteria, Coordinate coordinateveicolo)
    {
        super(marca, modello, prezzoalminuto, coordinateveicolo);
        setPercBatteria(100);
        setConsumo(consumo);
        setCapacitabatteria(capacitabatteria);
        setCascoleggero(true);

    }

    public int getPercBatteria() {
        return percBatteria;
    }

    public void setPercBatteria(int percBatteria) {
        this.percBatteria = percBatteria;
    }

    public double getConsumo() {
        return consumo;
    }

    public void setConsumo(double consumo) {
        this.consumo = Math.abs(consumo);
    }

    public int getCapacitabatteria() {
        return capacitabatteria;
    }

    public void setCapacitabatteria(int capacitabatteria) {
        this.capacitabatteria = capacitabatteria;
    }

    public Tipomotore getTipomotore() {
        return tipomotore;
    }

    public boolean isCascoleggero() {
        return Cascoleggero;
    }

    public void setCascoleggero(boolean cascoleggero) {
        Cascoleggero = cascoleggero;
    }
    public void CascoleggeroSmarrito()
    {
        setCascoleggero(false);
    }
    @Override
    public void Aggiungikm(double km)
    {
        setKmpercorsi(getKmpercorsi()+km);
        setPercBatteria((int) ((100*(getCapacitabatteria()-km*getConsumo()))/getCapacitabatteria()));
        if (getPercBatteria()<20) setDisponibilita(Stato.DARICARICARE);
    }
}
