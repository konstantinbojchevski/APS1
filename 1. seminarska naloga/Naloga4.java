import java.util.Scanner;
import java.io.*;

public class Naloga4 {

	public static void main(String[] args) throws IOException {
		
		FileInputStream inputStream = new FileInputStream(args[0]);
		InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
		BufferedReader bufferedReader = new BufferedReader(reader);
		
		PrintWriter printWriter = new PrintWriter(new File(args[1]), "UTF-8");
		
		String vrstica = bufferedReader.readLine();
		int n = Integer.parseInt(vrstica);
		String[] podeli = new String[3];
		int prvo_prazno = 0;
		
		vrstica = bufferedReader.readLine();
		podeli = vrstica.split(",");
		int[] struktura = new int[Integer.parseInt(podeli[1])];
		
		for(int i=0; i<n-1; i++) {
			vrstica = bufferedReader.readLine();
			podeli = vrstica.split(",");
			if(podeli[0].equals("a")) {
				
				if(prvo_prazno != -1) {
					
					int momentalno = prvo_prazno;
					int j = momentalno;
					boolean namesti = false;
					boolean bese_vo_else = false;
					
					//kolku ima mesto
					while((struktura[j] == 0) || (j == Integer.parseInt(podeli[1]))) {
						j++;
						if(j == struktura.length) break; 
					}
					
					while(!namesti) {
						
					//ako ima mesto
					if((j - (momentalno)) >= Integer.parseInt(podeli[1])) {
						namesti = true;
						j = momentalno;
						while(j < (momentalno + Integer.parseInt(podeli[1]))) {
							struktura[j] = Integer.parseInt(podeli[2]);
							j++;
						}
						
						//update - prvo_prazno
						if(!bese_vo_else) {
							if(j == struktura.length) {
								j = -1;
								break;
							}
							if(struktura[j] == 0)
								prvo_prazno = j;
							else {
								while(struktura[j] != 0) {
									j++;
									if(j == struktura.length) {
										prvo_prazno = -1;
										break;
									}
									
								}
								prvo_prazno = j;
							}
						}
					}
					else {

						bese_vo_else = true;
						while(struktura[j] != 0) {
							j++;
							if(j == struktura.length) break; 
						}
						momentalno = j;
						
						
						while(struktura[j] == 0 || j == Integer.parseInt(podeli[1])) {
							j++;
							if(j == struktura.length) break; 
						}
					}
					}
				}
			}
			
			if(podeli[0].equals("f")) {
				int element = Integer.parseInt(podeli[1]);
				int j = 0;
				while(struktura[j] != element) {
					j++;
					if(j == struktura.length) break;
				}
				if(prvo_prazno > j) prvo_prazno = j;
				while(struktura[j] == element) { 
					struktura[j] = 0;
					j++;
					if(j == struktura.length) break;
				}				
			}
			
			if(podeli[0].equals("d")) {
				for(int s=0; s<Integer.parseInt(podeli[1]); s++) {
					int j = prvo_prazno;
					int mesto = 0;
					while(struktura[j] == 0) {
						mesto++;
						j++;
						if(j == struktura.length) break; 
					}
					int golemina = 0;
					int element = struktura[j];
					while(struktura[j] == element) {
						golemina++;
						j++;
						if(j == struktura.length) break; 
					}
					j = prvo_prazno;
					
					if(mesto > golemina) {
						for(j = prvo_prazno; j<(golemina + prvo_prazno) ; j++) {
							struktura[j] = element;
						}
						int nov = j;
						int od = (prvo_prazno + mesto);
						int doo = (prvo_prazno + mesto + golemina);
						for(j = od; j<doo; j++) struktura[j] = 0;
						prvo_prazno = nov;
					}
					else {
						for(j = prvo_prazno; j<(prvo_prazno + mesto); j++) {
							struktura[j] = element;
						}
						int od = prvo_prazno + golemina;
						int doo = prvo_prazno + mesto + golemina;
						for(j = od; j<doo; j++) {
							struktura[j] = 0;
						}
						prvo_prazno = (prvo_prazno + golemina);
					}
				}				
			}
		}
		bufferedReader.close();
		
		int i=0;
		while(i < struktura.length) {
			if(prvo_prazno != -1)
			while(struktura[i] == 0) {
				i++;
				if(i == struktura.length) break;
			}
			if(i == struktura.length) break;
			int pocetok = i;
			int element = struktura[i];
			while(struktura[i] == element) {
				i++;
				if(i == struktura.length) break;
			}
			int kraj = i - 1;
			printWriter.print(element + "," + pocetok + "," + kraj + "\n");
		}
		printWriter.close();
	}
}
