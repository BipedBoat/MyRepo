package SharedMobility2;

public abstract class  VeicoliImmatricolati extends Veicoli
{
    private int energia;
    private Patente patenterichiesta;
    private Tipomotore tipomotore;
    private double consumo;
    private boolean Assicurazione;
    private double capacitaenergiamax;
    private String Targa;

    public VeicoliImmatricolati(String marca, String modello,String targa, Tipomotore tipomotore,double consumo, double prezzoalminuto,double capacitaenergiamax, Coordinate coordinateveicolo)
    {
        super(marca, modello, prezzoalminuto, coordinateveicolo);
        setConsumo(consumo);
        setTipomotore(tipomotore);
        setEnergia(100);
        setAssicurazione(true);
        setCapacitaenergiamax(capacitaenergiamax);  //serbatoio carburante o capacità batteria
        setTarga(targa);

    }

    public double getCapacitaenergiamax() {
        return capacitaenergiamax;
    }

    public void setCapacitaenergiamax(double capacitaenergiamax) {
        this.capacitaenergiamax = capacitaenergiamax;
    }


    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public Patente getPatenterichiesta() {
        return patenterichiesta;
    }

    public void setPatenterichiesta(Patente patenterichiesta) {
        this.patenterichiesta = patenterichiesta;
    }

    public Tipomotore getTipomotore() {
        return tipomotore;
    }

    public void setTipomotore(Tipomotore tipomotore) {
        this.tipomotore = tipomotore;
    }

    public double getConsumo() {
        return consumo;
    }

    public void setConsumo(double consumo) {
        this.consumo = Math.abs(consumo);
    }

    public boolean isAssicurazione() {
        return Assicurazione;
    }

    public void setAssicurazione(boolean assicurazione) {
        Assicurazione = assicurazione;
    }

    public String getTarga() {
        return Targa;
    }

    public void setTarga(String targa) {
        Targa = targa;
    }

    @Override
    public void Aggiungikm(double km)
    {
        setKmpercorsi(getKmpercorsi()+km);
        setEnergia((int) ((100*(getCapacitaenergiamax()-km*getConsumo()))/getCapacitaenergiamax()));
        if (energia<20) setDisponibilita(Stato.DARICARICARE);
    }
}
enum Patente{PATENTEA,PATENTEB,PATENTEB2,PATENTEC}
enum Tipomotore{ELETTRICO,BENZINA,IBRIDO,DIESEL,GPL}

