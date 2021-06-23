package Negozio;

public class Prodotti
{
    private String nome;
    private double prezzo;
    private int quantita;
    private Tipo tipoprodotto;


    public Prodotti(String nome, double prezzo, int quantita,Tipo tipoprodotto)
    {
        setNome(nome);
        setPrezzo(prezzo);
        setQuantita(quantita);
        setTipoprodotto(tipoprodotto);
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public Tipo getTipoprodotto() {
        return tipoprodotto;
    }

    public void setTipoprodotto(Tipo tipoprodotto) {
        this.tipoprodotto = tipoprodotto;
    }
}
