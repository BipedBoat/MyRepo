package ArcadeRank;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class Utente
{
    private String ID;
    private String username;


    public Utente(String username)
    {
        setUsername(username);
        setID(UUID.randomUUID().toString());
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utente utente = (Utente) o;
        return Objects.equals(ID, utente.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

    @Override
    public String toString() {
        return "Utente{" +
                "username='" + username + '\'' +
                '}';
    }
}
