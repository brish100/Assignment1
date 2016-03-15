import java.util.Random;

class MineField{

	private boolean[][] mines,visible;
	private boolean boom;
	private final int rowMax = 5;
	private final int colMax = 10;
	
	MineField(){
		mines=new boolean[rowMax][colMax]; //Makes the minefield
		visible=new boolean[rowMax][colMax]; //Makes field out of found spots
		boom=false; //If the player have choosen a bomb

		initmap(); //This will initiate the the map
		
		int counter2=15;
		int randomRow = 0;
		int randomCol = 0;
		Random RGenerator=new Random(); //uses Random to set the bombs
		
		while(counter2>0){
			
			randomRow=Math.abs(RGenerator.nextInt()%rowMax);
			randomCol=Math.abs(RGenerator.nextInt()%colMax);
			
			if(trymove(randomRow,randomCol)){
				counter2--;
			}
		}
	}	

	private void initmap(){ //Resets the map, and doing that by setting all the fields to false
		for(int row=0;row<rowMax;row++){
			initmap2(row);
		}
	}

	private void initmap2(int row){ //Splitting it
		for(int col=0;col<colMax;col++){
			mines[row][col]=false;
			visible[row][col]=false;
		}
	}

	private boolean trymove(int randomRow, int randomCol){ //Testing a given field
		if(mines[randomRow][randomCol]){
			return false;
		}
		else{
			mines[randomRow][randomCol]=true;
			return true;
		}
	}
	private void boom() { //A bomb has exploded so the whole field is shown
		for(int row=0;row<rowMax;row++){
			boom2(row);
		}
		boom=true;
		show();
	}

	private void boom2(int row){
		for(int col=0;col<colMax;col++){
			if(mines[row][col]){
				visible[row][col]=true;
			}
		}
	}

	private char drawChar(int row, int col){ //Prints the a char depending on what it is and what is around it
		int count=0; //This is a count that counts the amount of bombs around a given spot
		if(visible[row][col]){
			if(mines[row][col]) return '*';
			for(int irow=row-1;irow<=row+1;irow++){
				count += externCounter(irow, col);
			}
		}
		else{
			if(boom){
				return '-';
			}
			{
				
				
				return '?';
			}
		}
		switch(count){ //Returns char which is equal to the number
		case 0:return '0';
		case 1:return '1';
		case 2:return '2';
		case 3:return '3';
		case 4:return '4';
		case 5:return '5';
		case 6:return '6';
		case 7:return '7';
		case 8:return '8';
		
		
		default:return 'X';
		}
	}

	private int externCounter(int irow, int col){
		int count = 0;
		for(int icol=col-1;icol<=col+1;icol++){
			if(icol>=0&&icol<colMax&&irow>=0&&irow<rowMax){
				if(mines[irow][icol]) count++;
			}
		}
		return count;
	}

	public boolean getBoom(){ //Returns boom, which indicates if the player has choosen a spot that contains a bomb
		return boom;
	}


	public boolean legalMoveString(String input){ //Tests if the move the user have tried is valid
		String[] separated=input.split(" ");
		int row;
		int col;
		try{		
			row=Integer.parseInt(separated[0]);
			col=Integer.parseInt(separated[1]);
			if(row<0||col<0||row>=rowMax||col>=colMax){
				throw new java.io.IOException();
			}
		}
		catch(Exception e){
			System.out.println("\nInvalid Input!");
			return false;
		}
		
		if(legalMoveValue(row,col)){
			return true;
		}
		else{
			return false;
		}
	}

	private boolean legalMoveValue(int row, int col) { //Checks if player can check a given spot
		if(visible[row][col]){
			System.out.println("You stepped in allready revealed area!");
			return false;
		}
		else{
			visible[row][col]=true;
		}
		
		if(mines[row][col]){
			boom();
			return false;
		}
		
		return true;
	}
	public void show() { //Shows the field
		System.out.println("\n    0 1 2 3 4 5 6 7 8 9 ");
		System.out.println("   ---------------------");
		for(int row=0;row<rowMax;row++){
			System.out.print(row+" |");
			show2(row);
			System.out.println(" |");
		}
		System.out.println("   ---------------------");
	}
	
	private void show2(int row){
		for(int col=0;col<colMax;col++){
			System.out.print(" "+drawChar(row,col));	
		}
	}

}
