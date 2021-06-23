package ProblemSolving;

import java.util.*;

public class validparenthesis
{
    public static void main(String[] args) {
        System.out.println(isValid("]"));
    }
    public static boolean isValid(String s)
    {
        Map<Character, Character> map = new HashMap<>();
        map.put('(',')');
        map.put('[',']');
        map.put('{','}');
        List<Character> lista = new ArrayList<>();
        for (int i=0; i<s.length(); i++)
        {
            if(map.containsKey(s.charAt(i)))
            {
                lista.add(s.charAt(i));
            }

            else if (map.containsValue(s.charAt(i)))
            {
                if (lista.size()==0) return false;
                else if(map.get('(') == s.charAt(i) && lista.get(lista.size()-1)=='(') lista.remove(lista.size() - 1);
                else if(map.get('[') == s.charAt(i) && lista.get(lista.size()-1)=='[') lista.remove(lista.size() - 1);
                else if(map.get('{') == s.charAt(i) && lista.get(lista.size()-1)=='{') lista.remove(lista.size() - 1);
                else return false;
            }
        }

        return lista.size()==0;
    }
}
