
public class VsotaElementov {

	public static int vsota(int[] array, int index) {
		
		//ali smo pregledali celoten seznam
		if (index >= array.length)
			return 0;
		else
			return array[index] + vsota(array, index + 1);
	}
		
	public static void main(String[] args) {
		int[] data = {11, 7, 10, 10, 11, 6, 3, 4, 12, 3, 7, 8, 4};

		System.out.print("Vsota stevil ");
		for (int i = 0;  i< data.length; i++)
			System.out.print(data[i] + " ");
		
		System.out.println("je " + vsota(data, 0));
	}

}
