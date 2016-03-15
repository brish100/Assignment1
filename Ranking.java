import java.util.Scanner;

public class Ranking{

	private final int MAX_PEOPLE_LIMIT=5; //Max number of players at ranklist
	private String[] name;
	private int[] record;
	private int last;
	
	Ranking(){ //Constructor that sets up the arrays for name and points
		name=new String[MAX_PEOPLE_LIMIT];
		record=new int[MAX_PEOPLE_LIMIT];
		
		last=0;
	}

	public void recordName(int result) { //Takes a score from a player after the game has ended, and asks for name
		System.out.print("\n Please enter your name -");
		Scanner in=new Scanner(System.in);
		String newName=in.nextLine();
		if((last==MAX_PEOPLE_LIMIT)&&record[MAX_PEOPLE_LIMIT-1]>result){ //Checks that the points are enough to get into the ranking table
			System.out.println("\nSorry you cannot enter top "+(MAX_PEOPLE_LIMIT)+" players");
			return;
		}
		else if(last==MAX_PEOPLE_LIMIT){
			recordName2(result, newName);
		}
		else{
			name[last]=newName;{
				record[last]=result;{
					last++;
				}
			}
		}
		
		sort(); //After the changes the table will sort, incase there are some irregularities
		show(); //Prints the ranking table
	}

	private void recordName2(int result, String newName){
		name[MAX_PEOPLE_LIMIT-1]=newName;{
			record[MAX_PEOPLE_LIMIT-1]=result;
		}
	}

	public void show() { //Simply prints the table
		if(last==0){ //A small test if there are no results in the table yet.
			System.out.println("Still no results");
			return;
		}
		System.out.println("N Name\t\tresult");
		for(int i=0;i<last;i++){
			System.out.println((i+1)+" "+name[i]+"\t"+record[i]);
		}
	}
	
	private void sort(){ //Is a sorting method that orders the ranking table
		if(last<2) return;
		boolean unsorted=true; 
		while(unsorted){
			unsorted=false;
			sort2(unsorted);
		}
	}

	private void sort2(boolean unsorted) { // Sub method of sort, to lower the depth 
		for(int i=0;i<(last-1);i++){
			if(record[i+1]>record[i]){ 
				sort3(unsorted, i); // makes a call on the sort3 method 
			}
		}
	}

	private void sort3(boolean unsorted, int i){  // Sub method of sort, to lower the depth 
		int swapR=record[i];{
			record[i]=record[i+1];{
				record[i+1]=swapR;
				String swapN=name[i];
				name[i]=name[i+1];
				name[i+1]=swapN;
				unsorted=true; 
			}
		}
	}
}
