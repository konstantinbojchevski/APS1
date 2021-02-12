import java.io.*;

public class Naloga1 {
	
	static int najCena = Integer.MAX_VALUE;
	static int najPumpi = Integer.MAX_VALUE;
	static int[] rez;
	
	public static void rekurzija(int[] dalecina, int[] ceni, int pat, int gorivo, int pari, int momentalno_gorivo, 
			int pumpi, int index, int cel, int[] momentalna, int posledno) {
		
		
		if(pat + posledno >= cel) {
			pat += posledno;
			momentalno_gorivo -= posledno;
		}
		if(momentalno_gorivo < 0) return;
		
		if(pat >= cel) {
			if(pari <= najCena && pumpi <= najPumpi) {
				najCena = pari;
				najPumpi = pumpi;
				System.arraycopy(momentalna, 0, rez, 0, dalecina.length-1);
			}
			return;
		}
		if(pari < najCena || (pumpi < najPumpi && pari == najCena)) {
			 if(momentalno_gorivo - dalecina[index] >=0) {
				 momentalna[index] = index+1;
			rekurzija(dalecina, ceni, pat + dalecina[index], gorivo, 
					pari + ceni[index] * (dalecina[index]) + ceni[index]*(gorivo-momentalno_gorivo), gorivo,
							pumpi + 1, index+1, cel, momentalna, posledno);
			 }
			 else return;
			momentalna[index] = 0;
			rekurzija(dalecina, ceni, pat + dalecina[index], gorivo, pari, momentalno_gorivo - dalecina[index],
							pumpi, index+1, cel, momentalna, posledno);
				
			}
		else return;
	}
	
	public static void main (String[] args) throws IOException {
		
		FileInputStream inputStream = new FileInputStream(args[0]);
	    InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
	    BufferedReader bufferedReader = new BufferedReader(reader);
		
		PrintWriter pisi = new PrintWriter(new File(args[1]), "UTF-8");
		
		String vrstica = bufferedReader.readLine();
		String[] podeli = new String[3];
		podeli = vrstica.split(",");
		int tank = Integer.parseInt(podeli[1]);
		int cel = Integer.parseInt(podeli[0]);
		int n = Integer.parseInt(podeli[2]);
		int[] dalecina = new int[n+1];
		int[] cena = new int[n+1];
		int k = 0;
		for(int i=0; i<n; i++) {
			vrstica = bufferedReader.readLine();
			podeli = vrstica.split(":|,");
			dalecina[i] = Integer.parseInt(podeli[1]);
			cena[i] = Integer.parseInt(podeli[2]);
			k += Integer.parseInt(podeli[1]);
		}
		bufferedReader.close();
		
		dalecina[n] = cel - k;
		int[] momentalna = new int[dalecina.length];
		rez = new int[dalecina.length-1];
		
		rekurzija(dalecina, cena, 0, tank, 0, tank, 0, 0, cel, momentalna, 1);
		
		int st = 0;
		for(int i=0; i<rez.length; i++) {
			if(rez[i] != 0) st++;
		}
		
		int j = 0;
		for(int i = 0; i<rez.length; i++) {
			if(rez[i] != 0) {
				j++;
				if(st == j) pisi.print((rez[i]) + "\n");
				else pisi.print((rez[i]) + ",");
			}
		}
		pisi.close();
	}
}