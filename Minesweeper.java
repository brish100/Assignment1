import java.util.Scanner;

public class Minesweeper {

	private static MineField field;
	private static Ranking rank;	
	public static void main(String[] args) {
		rank=new Ranking();
		mainMessage()
		;
		while(gameCountinue());
		System.out.println("\nThank you for playing :) Have a nice day!");
	}

	private static boolean gameCountinue() { //Is a main method that is true as long as the game need to be continued, and false when it need to be ended
		field = new MineField();
		int result = 0;
		while (true) {

			field.show();
			System.out.print("\nPlease enter your move(row col): ");
			Scanner in = new Scanner(System.in);
			String input = in.nextLine();

			
			if(inputcheck1(input)) continue;


			if(inputcheck2(input, result)) return true;

			if(inputcheck3(input, result)) return false;
			
			if (field.legalMoveString(input)) {
				result++;
				if (result == 35) {
					System.out.println("Congratulations you WON the game!");
					{
						rank.recordName(result);
						{
							return true;
						}
					}
				}
				continue;
			}
			
			//	if(input.equals("exit")){
			//	rank.recordName(result);
			//	return false;
			//	}
			//	if(field.legalMoveString(input)){
			//	result++;
			//	if(result==35){
			//		System.out.println("Congratulations you WON the game!");
			//		rank.recordName(result);
			//		return true;
			//	}
			//	continue;
			//}
			
			if(inputcheck4(result)) return true;

		}
	}

	private static boolean inputcheck1(String input){ //If the user wants to get the ranking table printed out to she/he
		if (input.equals("top")) {
			rank.show();
			return true;
		}
		return false;
	}

	private static boolean inputcheck2(String input, int result){ //If the user wants to end her/his turn they can type this and write their score and the game will restart
		if (input.equals("restart")) {
			rank.recordName(result);
			return true;
		}
		return false;
	}

	private static boolean inputcheck3(String input, int result){ //A command that exites the program, firstly by recording score of the player.
		if (input.equals("exit")) {
			rank.recordName(result);
			return true;
		}
		return false;
	}

	private static boolean inputcheck4(int result){ //Here the player have chosen a spot with a bomb, which ends the game accordingly
		if (field.getBoom()) {
			System.out.println("\nBooooooooooooooooooooooooooooom!You stepped on a mine!You survived " + result + " turns");
			rank.recordName(result);
			return true;
		}
		return false;
	}

	private static void mainMessage(){ //Prints a quick introduction of the game
		System.out.println("Welcome to Minesweeper!");
		System.out.println("To play just input some coordinates and try not to step ont mine :)");
		System.out.println("Usefull commands:");
		System.out.println("restart- Starts a new game.");
		System.out.println("exit- Quits the game.");
		System.out.println("top- Reveals the top scoreboard.");
		System.out.println("Have Fun !");
	}
}