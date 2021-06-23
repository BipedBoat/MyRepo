package Tinder;

import java.util.Objects;
import java.util.UUID;

public class Utente
{
    private String nickname;
    private String ID;

    public Utente(String nickname)
    {
        setNickname(nickname);
        setID(UUID.randomUUID().toString());
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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
}
