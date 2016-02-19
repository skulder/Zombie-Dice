// Zombie Dice Game v0.06
import java.util.*;
class Assignment2
{
	public static void main(String[] args)
	{
		int[] dice 		= {0,0,0, 0,0,0, 0,0, 0,0,0}; // {G,Y,R, GF,YF,RF, Brain,Shots, TG,TY,TR} TG,TY,TR - total dice on table.
		int[] rollDice	= {0,0,0, 0,0,0, 0,0}; // {G,Y,R, X,X,X, Brain, Shots} rollDice output  
		
		int tempBrains = 0; int tempShots = 0;
		
		int currentPlayer = 0;
		int playerCount	= 0;
		int userAction = 0;
		
		System.out.println("game starting");
		System.out.print("many players: ");
		Scanner in = new Scanner(System.in);
		playerCount = in.nextInt();
		int[] playerScore = new int[playerCount];
		
		while (userAction != 3)
		{
			// menu drawing goes here
			System.out.println("\ncurrent player " + currentPlayer);
			System.out.println("1.Play  2.Stop  3.Exit");
			System.out.print("Selection: ");
			userAction = in.nextInt();
			
			// menu options
			if (userAction == 1) // play
			{
				pickDice(dice);	
				
				dice[8] = dice[8] + dice[0]; // adds to total dice player have on table 
				dice[9] = dice[9] + dice[1];
				dice[10] = dice[10] + dice[2];
						
				System.out.println("\npickDice output: " + dice[0] + " " + dice[1] + " " + dice[2] + "  " 
														 + dice[3] + " " + dice[4] + " " + dice[5] + "  "
														 + dice[6] + " " + dice[7] + "  " 
														 + dice[8] + " " + dice[9] + " " + dice[10]);
				
				rollDice = Dicevalue(dice);
				
				tempBrains = tempBrains + rollDice[6]; // brain // Change rollDice -> dice after alex method 
				tempShots = tempShots + rollDice[7]; // shots
				
				dice[3] = rollDice[3];
				dice[4] = rollDice[4];
				dice[5] = rollDice[5];
				
				System.out.println("Dicevalue output: " + rollDice[0] + " " + rollDice[1] + " " + rollDice[2] + "  " 
						+ rollDice[3] + " " + rollDice[4] + " " + rollDice[5] + "  " 
						+ rollDice[6] + " " + rollDice[7]);
				
				
				
				System.out.println("temporary stats   | player " + currentPlayer + " | Brain " + tempBrains + " | Shotguns " + tempShots + " |");
				
				if (tempShots >= 3) // Shotgun count. If shots=3 switch player. no points added.
				{
					System.out.println("You got 3 shots. Temp score lost. Switching player...");
					System.out.println("permanent stats   | player " + currentPlayer + " | Brain " + playerScore[currentPlayer] + " |");
					dice[8] = 0; dice[9] = 0; dice[10] = 0; // resets player dices on table 
					tempBrains = 0; tempShots = 0;
					
					currentPlayer++;
					if (currentPlayer == playerCount)
					{
						currentPlayer = 0;
					}	
				}	
			} 
			
			else if (userAction == 2) // stop
			{ 
				System.out.println("adding score and switching player");
				playerScore[currentPlayer] = playerScore[currentPlayer] + tempBrains;
				dice[8] = 0; dice[9] = 0; dice[10] = 0; // resets player dices on table 
				
				if (tempBrains >= 13) // Winner?
				{
					System.out.println("Congratulations player " + currentPlayer + " Won with " + playerScore[currentPlayer] + " brains");
					System.exit(1);
				}	
				
				System.out.println("permanent stats   | player " + currentPlayer + " | Brain " + playerScore[currentPlayer] + " |");
				tempBrains = 0; tempShots = 0;
				
				currentPlayer++; // switching player
				if (currentPlayer == playerCount)
				{
					currentPlayer = 0;
				}	
			} 
			
			else if (userAction == 3) // exit
			{ 
				System.out.println("> Exiting...");
				System.exit(1);
			}
			
			else 
				System.out.println("> Wrong menu option");
		}
		in.close();
	}

	public static int[] pickDice (int[] playerDice)
	{  
		int greenDice  = 6 - playerDice[8];
		int yellowDice = 4 - playerDice[9];
		int redDice    = 3 - playerDice[10];
		int manyPicks  = playerDice[3] + playerDice[4] + playerDice[5]; // foot input. many new dice? (dice-foot) 
		
		playerDice[0] = 0; playerDice[1] = 0; playerDice[2] = 0; // array reset. will use for output
		
		for (; manyPicks < 3; manyPicks++)
		{
			int totalDice = greenDice + yellowDice + redDice; // recalculation of bucked dice ranges
			int pick = (int) (Math.random() * totalDice + 1); // picks 1 dice
			
			if (pick <= greenDice) // checks range 1...n which color
			{ 
				playerDice[0]++; // putting picked dice into an array and readying for output
				greenDice--; // 1 dice less in a bucket. 
			}
				
				else if (pick > greenDice && pick <= totalDice - redDice)
				{ 
					playerDice[1]++;
					yellowDice--;
				}
			
				else if (pick > greenDice + yellowDice)
				{
					playerDice[2]++;
					redDice--;
				}
			
				else 
					System.out.println("Error in pickDice");
		}
		
		playerDice[0] = playerDice[0] + playerDice[3]; // adding foots picked last time to output
		playerDice[1] = playerDice[1] + playerDice[4];
		playerDice[2] = playerDice[2] + playerDice[5];
		
		return playerDice;
	}

	
	public static int [] Dicevalue (int[] pickDice){
		
		Random r = new Random();
			
		int PickGreen = pickDice[0];  //convert  the input array pickDice as Integers;
		int PickYellow= pickDice[1];  // The int PickGreen Pick... will allow me to know which dice to roll
		int PickRed= pickDice[2];
		
		int ftgreen= 0; 
		int ftyellow= 0;		// as it a new roll reset the value of the location of the footprint;
		int ftred = 0;
		
		int a=PickGreen;     // copy the value of the  color of the dice on the table as i would need it to pass it my array outvaluue
		int b=PickYellow;
		int c=PickRed;
		
		PickGreen=PickGreen+ftgreen;
		PickYellow=PickYellow+ftyellow;
		PickRed=PickRed+ftred;
	
	int Gunshot=0;
	int Brains=0;
	

	// Integer represent the side of the dice. It s needed to do a random number 1 to 6 when the dice roll.
	int GreenSide=0;
	int RedSide=0;
	int YellowSide=0;

while (PickGreen !=0) {
	
		// The integer green side correspond on which side the green dice face off
		GreenSide= r.nextInt(6)+1;
		
		// The Green Dice have 1 GunShot 3 Brains 2 FootPrint value 1 for gunshot			
	if (GreenSide==1){
			Gunshot++;
		}
		// value 2,3,4 for brains
	else if (GreenSide>1 && GreenSide<5){
			Brains++;
		}
		
	else{
			ftgreen++;
		}
		PickGreen--;
	}
	
while (PickRed !=0) {
					// The integer Red side correspond on which side the red dice face off
		RedSide= r.nextInt(6)+1;
					// The Red Dice have 1 Brain 3 Gunshot 2 FootPrint
		// value 1 for brain			
	if (RedSide==1){
			Brains++;
		}
		// value 2,3,4 for Gunshot
	else if (RedSide>1 && RedSide<5){
			Gunshot++;
		}
		
	else{
			ftred++;
		}
		
		PickRed--;
	}
	
while (PickYellow !=0) {
		// The integer Yellow side correspond on which side the yellow dice face off
		YellowSide= r.nextInt(6)+1;
		
					// The Yellow Dice have 2 GunShot 2 Brains 2 FootPrint value 1,2 for gunshot			
	if (YellowSide>0 && YellowSide<3){
			Gunshot++;
		}
		// value 3,4 for brains
	else if (YellowSide>2 && YellowSide<5){
			Brains++;
		}
		
	else{
			ftyellow++;
		}
		PickYellow--;

	}
	
		
	// create an array outvalue that will return the color of the dice G, Y, R,on which color of the footprint
	// and the value of the diced that just role ( eg if it s brains or gunshot)	
		
		int[] outvalue= new int[8];
		
		outvalue[0] = 0; // Green dice
		outvalue[1]= 0; // yellow dice
		outvalue[2]= 0; //red dice
		outvalue[3] =ftgreen;  // green foot
		outvalue[4] = ftyellow; // yellow foot
		outvalue[5] = ftred; // red foot
		outvalue[6] = Brains; 
		outvalue[7]= Gunshot;
	

	
	return (outvalue);
}
	
}