import java.util.*;

public class Potenca {

	public static boolean jePotenca(int a, int b)
	{
		if (a == 0 || b == 0)
			return false;
		else if (a == 1)
			return true;
		else if (b == 1)
			return false;
		else if (a % b != 0)
			return false;
		else
			return jePotenca(a/b, b);
	}
	
	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in);
		in.useLocale(Locale.US);
		 
        int a, b;
        System.out.print("Vnesi stevilo a: ");
        a = in.nextInt();
        System.out.print("Vnesi stevilo b: ");
        b = in.nextInt();
        
        in.close();
            
        if (jePotenca(a,b))
            System.out.println("Stevilo " + a + " je potenca stevila " + b);
        else
            System.out.println("Stevilo " + a + " ni potenca stevila " + b); 

	}
}
