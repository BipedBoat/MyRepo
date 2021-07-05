
import java.util.*;
import java.util.stream.Collectors;

public class LettersCount
{
    public static void main(String[] args)
    {
        List<String> listanomi = new ArrayList<>();
        listanomi.add("Giuseppe");
        listanomi.add("Francesco");
        listanomi.add("Marco");
        listanomi.add("Federica");
        listanomi.add("Fulvio");
        listanomi.add("Giovanni");

        long cont =0;
        /*for (String nome:listanomi)
        {
            if (nome.toUpperCase(Locale.ROOT).charAt(0)=='F') cont = cont+nome.length();
        }
        System.out.println(cont);*/

        cont = listanomi.parallelStream().filter(s -> s.toUpperCase(Locale.ROOT).startsWith("F")).mapToInt(String::length).sum();
        System.out.println(cont);
    }

}
