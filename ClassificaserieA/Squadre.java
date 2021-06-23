package ClassificaserieA;

public class Squadre
{
    private String ID;
    private String nome;
    private Calciatori[] rosa;
    private int punti;
    private int golfatti;
    private int golsubiti;

    public Squadre(String ID, String nome,Calciatori[] rosa)
    {
        setID(ID);
        setNome(nome);
        setRosa(rosa);

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        if (ID!=null) this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
       if(nome!=null) this.nome = nome;
    }

    public Calciatori[] getRosa() {
        return rosa;
    }

    public void setRosa(Calciatori[] rosa) {
         this.rosa = rosa;
    }

    public int getPunti() {
        return punti;
    }

    public void setPunti(int punti) {
        this.punti = punti;
    }

    public int getGolfatti() {
        return golfatti;
    }

    public void setGolfatti(int golfatti) {
        this.golfatti = Math.abs(golfatti);
    }

    public int getGolsubiti() {
        return golsubiti;
    }

    public void setGolsubiti(int golsubiti) {
        this.golsubiti = Math.abs(golsubiti);
    }
}
