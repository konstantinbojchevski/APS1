import java.util.*;
import java.io.*;
public class Naloga6 {

    public static class Node implements Comparable<Node>{
        int id;
        int cena;
        int grupa;
        Node prethodnik;

        public Node(int id, int cena, int grupa, Node prethodnik){
            this.id = id;
            this.cena = cena;
            this.grupa = grupa;
            this.prethodnik = prethodnik;
        }

        @Override
        public int compareTo(Node vtoro){

            return this.cena - vtoro.cena;
        }
    }

    public static class Edge{
        int do_tocka;
        int cena;

        public Edge(int do_tocka, int cena){
            this.do_tocka = do_tocka;
            this.cena = cena;
        }
    }
    public static  void main(String[] args) throws IOException {

        FileInputStream inputStream = new FileInputStream(args[0]);
        InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(reader);

        PrintWriter pisi = new PrintWriter(new File(args[1]), "UTF-8");

        String[] parametri = new String[3];
        String[] prvo_posledno = new String[2];

        //citam parametri
        String vrstica = bufferedReader.readLine();
        parametri = vrstica.split(" ");
        int tocki = Integer.parseInt(parametri[0]);
        int slicni = Integer.parseInt(parametri[2]);
        int redici = Integer.parseInt(parametri[1]);
        vrstica = bufferedReader.readLine();
        prvo_posledno = vrstica.split(" ");

        ArrayList<Edge>[] graf = (ArrayList<Edge>[]) new ArrayList[tocki];
        for(int i=0; i<tocki; i++){
            graf[i] = new ArrayList<Edge>();
        }

        for(int i=0; i<redici; i++) {
            vrstica = bufferedReader.readLine();
            parametri = vrstica.split(" ");
            graf[Integer.parseInt(parametri[0])].add(new Edge(Integer.parseInt(parametri[1]), Integer.parseInt(parametri[2])));
            graf[Integer.parseInt(parametri[1])].add(new Edge(Integer.parseInt(parametri[0]), Integer.parseInt(parametri[2])));

        }

        int pocetok = Integer.parseInt(prvo_posledno[0]);
        int end = Integer.parseInt(prvo_posledno[1]);

       int[] grupi = new int[tocki];

       for(int i=0; i<slicni; i++) {
           vrstica = bufferedReader.readLine();
           String[] tocki_slicni = vrstica.split(" ");
           for (int j = 1; j <= Integer.parseInt(tocki_slicni[0]); j++) {
               grupi[Integer.parseInt(tocki_slicni[j])] = i+1;
           }
       }
       PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>();
       int grupa;
       int skupina = -1;
       if(grupi[pocetok] != 0) skupina = grupi[pocetok];
       priorityQueue.add(new Node(pocetok, 0, skupina, null));

       while(priorityQueue.peek().id != end){
           Node naslednje = priorityQueue.poll();
           ArrayList<Edge> neighbours = graf[naslednje.id];

           for(Edge e: neighbours){
               if(grupi[e.do_tocka] != 0) grupa = grupi[e.do_tocka];
               else grupa= -1;

               if(naslednje.prethodnik == null || grupa != naslednje.prethodnik.grupa || naslednje.prethodnik.grupa == -1 || grupa == -1){
                   priorityQueue.add(new Node(e.do_tocka, naslednje.cena + e.cena, grupa, naslednje));
               }
           }
       }

       pisi.print(priorityQueue.poll().cena + "\n");
       pisi.close();
    }
}

















