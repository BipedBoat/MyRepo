import java.util.*;

public class ReverseLinkedList
{
    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
    public static ListNode reverseBetween1part1(ListNode head, int left, int right)
    {
        ListNode inizio=head;


        if ((left<right)&& head.next!=null)
        {
            if (left != 1) reverseBetween1part2(head.next,left,right,null,inizio,2);
            else {int memory = head.val;reverseBetween1part2(head.next,left,right,memory,inizio,2);}
        }
        return head;


    }
    public static void reverseBetween1part2(ListNode head, int left, int right,Integer memory,ListNode inizio,int counter)
    {

        if ((counter==left)&&memory==null&&head.next!=null) {memory = head.val; reverseBetween1part2(head.next,left,right,memory,inizio,counter+1);}
       else if (counter==right) {int temp = head.val; head.val=memory; memory=temp; reverseBetween1part2(inizio,left,right,memory,inizio,1);}
        else if ((counter==left)){head.val=memory;if ((left-1)<(right-1)&&(left-1>0)&&(right-1>0)) reverseBetween1part2(inizio,left-1,right-1,null,inizio,1);}
        else if (head.next!=null) reverseBetween1part2(head.next,left,right,memory,inizio,counter+1);


    }
    public static ListNode reverseBetween2(ListNode head, int left, int right)
    {
        ArrayList<ListNode> lista = new ArrayList<>();
        boolean controllo = true;
        while (controllo)
        {
            lista.add(head);
            if (head.next!= null) head=head.next;
            else controllo = false;
        }
        while (left<right)
        {
          int temp = lista.get(right-1).val;
          lista.get(right-1).val = lista.get(left-1).val;
          lista.get(left-1).val=temp;
          left=left+1;
          right=right-1;
        }
        return lista.get(0);
    }
    public static ListNode reverseBetween3(ListNode head, int left, int right)
    {ArrayList<Integer> lista = new ArrayList<>();
        ListNode inizio = head;
        int i = 0;
        while (i<right)
        {
            if (i>=left-1)
            {
                lista.add(head.val);
            }
            if (head.next!= null) head=head.next;
            i=i+1;
        }

        i = 0;
        int j =lista.size()-1;
        head=inizio;
        while (i<right)
        {
            if (i>=left-1)
            {
                head.val=lista.get(j);
                j=j-1;
            }
            head=head.next;
            i=i+1;

        }
        return inizio;
    }



    public static void main(String[] args) {
        ListNode prova1 = new ListNode(5);
        ListNode prova2 = new ListNode(3,prova1);


        ListNode n = reverseBetween3(prova2,1,2);

        System.out.println(n.val+" "+n.next.val);

    }
}
