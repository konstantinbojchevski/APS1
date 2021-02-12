import java.io.*;
import java.util.*;

public class Naloga7 {

        public static HashSet<String> bridgesResult = new HashSet<>();
        public static HashSet<String> shortestPath = new HashSet<>();
        public static ArrayList<Integer> adj[];
        public static int pati = 0;
        static int pocetok;

        public static class Graph {

            public int V;

            Graph(int v) {
                V = v;
                adj = new ArrayList[v];
                for (int i = 0; i < v; ++i) adj[i] = new ArrayList<>();
            }

            void addEdge(int v, int w) {
                adj[v].add(w);
                adj[w].add(v);
            }
        }

        //MOSTOVI----------------------------------------------------

        public static void bridge(int u, boolean visited[], int d[], int low[], int parent[]) {
            visited[u] = true;
            d[u] = low[u] = ++pati;
            Iterator<Integer> i = adj[u].iterator();
            while (i.hasNext()) {
                int v = i.next();
                if (!visited[v]) {
                    parent[v] = u;
                    bridge(v, visited, d, low, parent);
                    low[u] = Math.min(low[u], low[v]);
                    if (low[v] > d[u]) {
                        if (u < v) bridgesResult.add(u + " " + v);
                        else bridgesResult.add(v + " " + u);
                    }
                }
                else if (v != parent[u])
                    low[u] = Math.min(low[u], d[v]);
            }
        }

        public static void bridges(int V) {
            boolean visited[] = new boolean[V];
            int d[] = new int[V];
            int low[] = new int[V];
            int parent[] = new int[V];

            for (int i = 0; i < V; i++) {
                parent[i] = -1;
                visited[i] = false;
            }

            int u = pocetok;
            if (visited[u] == false) {
                bridge(u, visited, d, low, parent);
            }
        }

        //BFS-------------------------------------------------------------

        public static void najkratokPat(ArrayList<Integer> adj[], int s, int dest, int v) {

            int pred[] = new int[v];
            int dist[] = new int[v];

            //ne se povrzani
            if (BFS(adj, s, dest, v, pred, dist) == false) return;
            LinkedList<Integer> path = new LinkedList<Integer>();
            int momentalno = dest;
            path.add(momentalno);
            while (pred[momentalno] != -1) {
                if(momentalno < pred[momentalno]) shortestPath.add(momentalno + " " + pred[momentalno]);
                else shortestPath.add(pred[momentalno] + " " + momentalno);
                momentalno = pred[momentalno];
            }
        }

        private static boolean BFS(ArrayList<Integer> adj[], int src, int finish, int v, int pred[], int dist[]) {
            LinkedList<Integer> queue = new LinkedList<Integer>();
            boolean visited[] = new boolean[v];
           for (int i = 0; i < v; i++) {
                 visited[i] = false;
                 dist[i] = Integer.MAX_VALUE;
                 pred[i] = -1;
            }
            visited[src] = true;
            dist[src] = 0;
            queue.add(src);
            while (!queue.isEmpty()) {
                int u = queue.remove();
                for (int i = 0; i < adj[u].size(); i++) {
                    if (visited[adj[u].get(i)] == false) {
                        visited[adj[u].get(i)] = true;
                        dist[adj[u].get(i)] = dist[u] + 1;
                        pred[adj[u].get(i)] = u;
                        queue.add(adj[u].get(i));
                        if (adj[u].get(i) == finish)
                            return true;
                    }
                }
            }
            return false;
        }

        //--------------------------------------------------------------------------------------------------------

        public static TreeSet<String> intersection(HashSet<String> list1, HashSet<String> list2) {

              TreeSet<String> rez = new TreeSet<>((str1, str2) -> {
              String[] temp1 = str1.split(" ");
              String[] temp2 = str2.split(" ");
              int prvi1 = Integer.parseInt(temp1[0]); int prvi2 = Integer.parseInt(temp1[1]);
              int vtor1 = Integer.parseInt(temp2[0]); int vtor2 = Integer.parseInt(temp2[1]);
              if(prvi1 == vtor1) return prvi2 - vtor2;
              else return prvi1 - vtor1;
              });
              for (String t : list1) {
                  if (list2.contains(t)) rez.add(t);
              }
              return rez;
        }

            public static void main(String args[]) throws IOException {

                FileInputStream inputStream = new FileInputStream(args[0]);
                InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(reader);
                PrintWriter pisi = new PrintWriter(new File(args[1]), "UTF-8");

                String[] vrednosti = new String[2];
                String vrstica = bufferedReader.readLine();
                vrednosti = vrstica.split(" ");

                int tocki = Integer.parseInt(vrednosti[0]);
                int mostovi = Integer.parseInt(vrednosti[1]);
                Graph g = new Graph(tocki);
                vrstica = bufferedReader.readLine();
                vrednosti = vrstica.split(" ");
                pocetok = Integer.parseInt(vrednosti[0]);
                int d = Integer.parseInt(vrednosti[1]);

                for (int i = 0; i < mostovi; i++) {
                    vrstica = bufferedReader.readLine();
                    vrednosti = vrstica.split(" ");
                    g.addEdge(Integer.parseInt(vrednosti[0]), Integer.parseInt(vrednosti[1]));
                }
                bridges(tocki);
                najkratokPat(adj, pocetok, d, tocki);
                reader.close();

                TreeSet<String> result =  intersection(bridgesResult, shortestPath);
                for(String s: result){
                    pisi.print(s + "\n");
                }
                pisi.close();
            }
        }