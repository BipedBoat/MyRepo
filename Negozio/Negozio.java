package Negozio;


public class Negozio {
    private Prodotti[] listaprodotti;
    private Clienti[] listaclienti;

    public Prodotti[] getListaprodotti() {
        return listaprodotti;
    }

    public void setListaprodotti(Prodotti[] listaprodotti) {
        this.listaprodotti = listaprodotti;
    }

    public Clienti[] getListaclienti() {
        return listaclienti;
    }

    public void setListaclienti(Clienti[] listaclienti) {
        this.listaclienti = listaclienti;
    }

    public Negozio(Prodotti[] listaprodotti, Clienti[] listaclienti) {
        setListaclienti(listaclienti);
        setListaprodotti(listaprodotti);
    }

    public void aggiungiprodotto(Prodotti nuovoprodotto) {
        boolean esistegia = false;
        Prodotti[] nuovalista = new Prodotti[listaprodotti.length + 1];
        for (int i = 0; i < listaprodotti.length; i++) {
            if (listaprodotti[i].getNome().equals(nuovoprodotto.getNome())) {
                listaprodotti[i].setQuantita(listaprodotti[i].getQuantita() + nuovoprodotto.getQuantita());
                esistegia = true;
                break;
            }
        }
        if (!esistegia) {
            for (int i = 0; i < listaprodotti.length; i++) {
                nuovalista[i] = listaprodotti[i];
            }
            nuovalista[nuovalista.length - 1] = new Prodotti(nuovoprodotto.getNome(), nuovoprodotto.getPrezzo(), nuovoprodotto.getQuantita(), nuovoprodotto.getTipoprodotto());
            listaprodotti = nuovalista;
        }
    }

    public int ottieniQuantita(Prodotti nuovoprodotto) {
        for (int i = 0; i < listaprodotti.length; i++) {
            if (listaprodotti[i].getNome().equals(nuovoprodotto.getNome())) {
                return listaprodotti[i].getQuantita();

            }

        }
        return 0;
    }

        public void rimuoviprodotto (Prodotti prodotto)
        {
            Prodotti[] nuovalista = new Prodotti[listaprodotti.length - 1];
            int j = 0;
            int i = 0;
            boolean Esistegia = false;
            while (i + j < listaprodotti.length) {
                if (listaprodotti[i].getNome().equals(prodotto.getNome())) {
                    j++;
                    Esistegia = true;
                }
                nuovalista[i] = listaprodotti[i + j];
                i++;
            }
            if (Esistegia) listaprodotti = nuovalista;
        }

        public static void main (String[]args){
            Prodotti[] listaprodotti = {};
            Clienti Marco = new Clienti(62, 0);
            Clienti[] listaclienti = {Marco};
            Negozio Coop = new Negozio(listaprodotti, listaclienti);
            Prodotti prodotto1 = new Prodotti("mela", 3.50, 5, Tipo.Alimentare);
            Coop.aggiungiprodotto(prodotto1);
            Coop.aggiungiprodotto(prodotto1);
            Coop.aggiungiprodotto(prodotto1);
            Coop.aggiungiprodotto(prodotto1);

            listaprodotti = Coop.getListaprodotti();
            System.out.println(Coop.ottieniQuantita(prodotto1));
        }

    }
