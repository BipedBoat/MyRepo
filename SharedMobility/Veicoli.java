package SharedMobility2;

public abstract class Veicoli
{
    private String idveicolo;
    private Stato disponibilita;
    private Coordinate coordinateveicolo;
    private double prezzoalminuto;
    private TIPOGUASTOIMM tipoguasto;
    private String marca;
    private String modello;
    private double kmpercorsi;

    public Veicoli (String marca,String modello,double prezzoalminuto,Coordinate coordinateveicolo)
    {
        setMarca(marca);
        setModello(modello);
        setPrezzoalminuto(prezzoalminuto);
        setCoordinateveicolo(coordinateveicolo);
        setDisponibilita(Stato.DISPONIBILE);
        setKmpercorsi(0);
        setTipoguasto(TIPOGUASTOIMM.NESSUNGUASTO);

    }

    public Coordinate getCoordinateveicolo() { return coordinateveicolo; }

    public void setCoordinateveicolo(Coordinate coordinateveicolo) { this.coordinateveicolo = coordinateveicolo; }

    public String getIdveicolo() { return idveicolo; }

    public void setIdveicolo(String idveicolo) { this.idveicolo = idveicolo; }

    public Stato getDisponibilita() { return disponibilita; }

    public void setDisponibilita(Stato disponibilita) { this.disponibilita = disponibilita; }

    public double getPrezzoalminuto() { return prezzoalminuto; }

    public void setPrezzoalminuto(double prezzoalminuto) { this.prezzoalminuto = prezzoalminuto; }

    public TIPOGUASTOIMM getTipoguasto() { return tipoguasto; }

    public void setTipoguasto(TIPOGUASTOIMM tipoguasto) { this.tipoguasto = tipoguasto; }

    public String getMarca() { return marca; }

    public void setMarca(String marca) { this.marca = marca; }

    public String getModello() { return modello; }

    public void setModello(String modello) { this.modello = modello; }

    public double getKmpercorsi() { return kmpercorsi; }

    public void setKmpercorsi(double kmpercorsi) { this.kmpercorsi = kmpercorsi; }

    public void Aggiungikm(double km){}

}

enum Stato {DISPONIBILE,OCCUPATO,GUASTO,DARICARICARE}
enum TIPOGUASTOIMM {NESSUNGUASTO,MOTORE,PNEUMATICI,BATTERIA,CARROZZERIA,IMPIANTOELETTRICO}

