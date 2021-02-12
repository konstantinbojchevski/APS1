
public class Postfix 
{

	public static void main(String[] args) 
	{
		
		Stack stack = new Stack();
		
		String[] izraz = {"2","3","2","*","1","+","+","4","-"};
		
		try
		{
			for (int i = 0; i < izraz.length; i++)
			{
				String token = izraz[i];
				Double arg1;
				Double arg2;
				
				if (token.compareTo("+") == 0)
				{
					//trenutni znak je operator '+'
					
					//s sklada vzemi nazadnje dodana argumenta 
					arg2 = (Double)stack.top(); stack.pop();
					arg1 = (Double)stack.top(); stack.pop();
					
					//na sklad dodaj rezultat operacije
					stack.push(arg1 + arg2);
				}
				else
				if (token.compareTo("-") == 0)	
				{
					//trenutni znak je operator '-'
					
					//s sklada vzemi nazadnje dodana argumenta 
					arg2 = (Double)stack.top(); stack.pop();
					arg1 = (Double)stack.top(); stack.pop();
					
					//na sklad dodaj rezultat operacije
					stack.push(arg1 - arg2);
				}
				else
				if (token.compareTo("*") == 0)
				{
					//trenutni znak je operator '*'
					
					//s sklada vzemi nazadnje dodana argumenta 
					arg2 = (Double)stack.top(); stack.pop();
					arg1 = (Double)stack.top(); stack.pop();
					
					//na sklad dodaj rezultat operacije
					stack.push(arg1 * arg2);
				}
				else
				if (token.compareTo("/") == 0)
				{
					//trenutni znak je operator '/'
					
					//s sklada vzemi nazadnje dodana argumenta 
					arg2 = (Double)stack.top(); stack.pop();
					arg1 = (Double)stack.top(); stack.pop();
					
					//na sklad dodaj rezultat operacije
					stack.push(arg1 / arg2);
				}
				else
				{
					// imamo argument
					
					// dodaj ga na sklad
					stack.push(Double.parseDouble(token));	
				}
			}
			
			System.out.print("Vrednost postfix izraza ");
			for (int i = 0; i < izraz.length; i++)
				System.out.print(izraz[i]+" ");
			System.out.println(" je " + stack.top());
		}
		catch (Exception ex)
		{
			System.out.println("Izraz je v nepravilni obliki");
		}
	}
}
