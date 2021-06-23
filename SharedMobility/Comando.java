package SharedMobility2;
import java.util.Locale;

public enum Comando {
    START("/start"), REGISTRATI("Registrati"), AGGIUNGI("Aggiungi"),RIMUOVI("Rimuovi"), TASTIERA("tastiera"), ERRORE("");

    private String text;

    Comando(String text) {
        text=text.toLowerCase(Locale.ROOT);
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static Comando fromString(String testo) {
        testo=testo.toLowerCase(Locale.ROOT);

        for (Comando c : Comando.values()) {
            if (testo.equals(c.getText())) {
                return c;
            }
        }
        return Comando.ERRORE;
    }
}
