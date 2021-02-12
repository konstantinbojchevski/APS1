import java.io.*;
import java.util.*;

public class Naloga10 {

    public static class Node{
        int id;
        int cena;

        public Node(int id, int cena){
            this.id = id;
            this.cena = cena;
        }
    }

    public static void main(String[] args) throws IOException {

        FileInputStream inputStream = new FileInputStream(args[0]);
        InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(reader);

        PrintWriter pisi = new PrintWriter(new File(args[1]), "UTF-8");

        String[] vrednosti = new String[3];

        String vrstica = bufferedReader.readLine();
        int edges = Integer.parseInt(vrstica);

        ArrayList<ArrayList<Node>> graf = new ArrayList<>();
        graf.add(0, new ArrayList<>());
        boolean ima = false;

        for (int i = 0; i < edges; i++) {
            vrstica = bufferedReader.readLine();
            vrednosti = vrstica.split(",");
            if (Integer.parseInt(vrednosti[0]) + 1 > graf.size()) {
                graf.add(Integer.parseInt(vrednosti[0]), new ArrayList<Node>());
            }
            graf.get(Integer.parseInt(vrednosti[0])).add(new Node(Integer.parseInt(vrednosti[1]), Integer.parseInt(vrednosti[2])));
        }

        String[] posledni = new String[2];
        vrstica = bufferedReader.readLine();
        posledni = vrstica.split(",");
        int source = Integer.parseInt(posledni[0]);
        int destination = Integer.parseInt(posledni[1]);

        ArrayList<Integer> prejsna = new ArrayList<>();
        ArrayList<Integer> trenutna = new ArrayList<>();
        int i = source;
        boolean se_povtoruva= true;
        int[] h = new int[graf.size() + 1];
        int sledno;
        int minCena;
        int cena_Za_i;


        while (se_povtoruva) {

            se_povtoruva = false;
            i = source;
            prejsna = (ArrayList<Integer>) trenutna.clone();
            trenutna.clear();
            trenutna.add(i);
            boolean[] visited = new boolean[graf.size() + 1];
            visited[i] = true;

            while (i != destination) {
                sledno = -1;
                minCena = Integer.MAX_VALUE;

                for (int j = 0; j < graf.get(i).size(); j++) {
                    if (!visited[graf.get(i).get(j).id]) {
                        cena_Za_i = graf.get(i).get(j).cena + h[graf.get(i).get(j).id];
                        if (cena_Za_i < minCena || (cena_Za_i <= minCena && graf.get(i).get(j).id < sledno)) {
                            minCena = cena_Za_i;
                            sledno = graf.get(i).get(j).id;
                        }
                    }
                }
                if (minCena == Integer.MAX_VALUE) break;

                trenutna.add(sledno);

                if (h[i] < minCena) {
                    se_povtoruva = true;
                    h[i] = minCena;
                }

                i = sledno;
                visited[i] = true;
            }

            for (int j = 0; j < trenutna.size(); j++) {
                if (j == trenutna.size() - 1)
                    pisi.print(trenutna.get(j) + "\n");
                else
                    pisi.print(trenutna.get(j) + ",");
            }
        }

        if(!prejsna.equals(trenutna)) {
            for (int m = 0; m < trenutna.size(); m++) {
                if (m == trenutna.size() - 1)
                    pisi.print(trenutna.get(m) + "\n");
                else
                    pisi.print(trenutna.get(m) + ",");
            }
        }
        pisi.close();
        //System.out.println("Execution time in ms: " + (System.nanoTime() - start) / 1000000);
    }
}