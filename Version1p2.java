import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

public class Version1p2 {

	int gridSizeX = 0;
	int gridSizeY = 0;
	
	String closestExchange;
	
	int maxX = 100000;
	int maxY = 100000;
	
	int numExchanges = 0;
	int countNumClosest = 0;
	
	int tempNumID = 0;
	int tempxLoc = 0;
	int tempyLoc = 0;
	
	int exchangeCounter = 0;
	boolean uniqueID = true;
	int finalClosestDistance = 0;
	String finalExchange = "";
	
	int tempNum1 = 0;
	int tempNum2 = 0;
	
	Exchange[] exchangeArray;
	int[] distanceArray;
	
	public static void main(String[] args) {
		Version1p2 nearestExchange = new Version1p2();
		
		nearestExchange.setGridSizeX();
		nearestExchange.setGridSizeY();
		
		nearestExchange.setNumExchanges();
		nearestExchange.createExchanges();
				
		nearestExchange.findNearest();
	}
	
	public void setGridSizeX() {
	  Scanner gridInput = new Scanner(System.in);
	  System.out.println("To set the size of the X axis, please enter a positive integer no bigger than " + maxX + "."  + "\n");  
	  try {
		  gridSizeX = Integer.parseInt(gridInput.nextLine());
		  if (gridSizeX > maxX || gridSizeX < 0) {
			  setGridSizeX();
		  }
		  else {	  
			  System.out.println("The grid size for the X axis is " + gridSizeX  + "\n");
		  }
	  }
	  catch (Exception ex) {
		  setGridSizeX();
	  }
	}
	
	public void setGridSizeY() {
		  Scanner gridInput = new Scanner(System.in);
		  System.out.println("To set the size of the Y axis, please enter a positive integer no bigger than " + maxY + "." + "\n");  
		  try {
			  gridSizeY = Integer.parseInt(gridInput.nextLine());
			  if (gridSizeY > maxY || gridSizeY < 0) {
				  setGridSizeY();
			  }
			  else {	  
				  System.out.println("The grid size for the Y axis is " + gridSizeY + "\n");
			  }
		  }
		  catch (Exception ex) {
			  setGridSizeY();
		  }
	}
	
	public void setNumExchanges() {
		  Scanner exchangesInput = new Scanner(System.in);
		  System.out.println("To set the number of exchanges, please enter a positive integer no bigger than 99 and no smaller than 2." + "\n");  
		  try {
			  numExchanges = Integer.parseInt(exchangesInput.nextLine());
			  if (numExchanges > 99 || numExchanges < 2) {
				  setNumExchanges();
			  }
			  else {	  
				  System.out.println("The number of exchanges is " + numExchanges +"." + "\n");
			  }
		  }
		  catch (Exception ex) {
			  setNumExchanges();
		  }
	}
	
	public Exchange[] createExchanges() {
		exchangeArray = new Exchange[numExchanges];
		distanceArray = new int[numExchanges];
		// have to instantiate the array elements! This is tricky to see!

		for (int i = 0; i < numExchanges; i++) {
			exchangeArray[i] = new Exchange();
		}
		
		for (int i = 0; i < numExchanges; i++) {
			setID(exchangeArray, i);
			setxLocation(i);
			setyLocation(i);
			computeDistance(i);
		}
		return exchangeArray;
	}
	
	public Exchange[] setID(Exchange[] inputArray, int indexExchange) {
		Scanner idInput = new Scanner(System.in);
		System.out.println("Set ID of exchange " + exchangeCounter + 
				". It must be unique. Please enter a postitive integer "
				+ "no bigger than 99." + "\n");  
				
		try {
			  tempNumID = Integer.parseInt(idInput.nextLine());
			  if (tempNumID > 99 || tempNumID < 1) {
				  setID(inputArray, indexExchange);
			  }
			  else {	  
				  if (uniqueID(indexExchange) == true) {
				  tempNum2 = tempNumID % 10;
				  tempNum1 = (tempNumID - tempNum2)/10;
				  inputArray[indexExchange].setNumID(tempNumID);
				  inputArray[indexExchange].setExchangeID("ex:" + Integer.toString(tempNum1) + ":" + Integer.toString(tempNum2));
				  System.out.println("The ID of this exchange is " + inputArray[indexExchange].getExchangeID() + "." + "\n");
				  exchangeCounter++;
				  }
				  else {
					  setID(inputArray, indexExchange);
				  }
			  }
		    }
		  catch (Exception ex) {
			  System.out.println("Here!");
			  setID(inputArray, indexExchange);
		  }
		return inputArray;
	}
	
	public boolean uniqueID(int indexExchange) {
		uniqueID = true;
		for (int j = 0; j < indexExchange; j++) {
			if (exchangeArray[j].getNumID() == tempNumID) {
				return uniqueID = false;	
			}
		}
		return uniqueID;
	}
	
	public void setxLocation(int indexExchange) {
		Scanner locInput = new Scanner(System.in);
		System.out.println("To set the x location of exchange " + 
				exchangeArray[indexExchange].getExchangeID() +
				", please enter a positive integer no bigger than " +
				gridSizeX + ".");  
		 try {
			  tempxLoc = Integer.parseInt(locInput.nextLine());
			  if (tempxLoc > gridSizeX || tempxLoc < 0) {
				  setxLocation(indexExchange);
			  }
			  else {
				  exchangeArray[indexExchange].setxLoc(tempxLoc);
				  System.out.println("The x location of exchange " + 
				  	exchangeArray[indexExchange].getExchangeID() +
				  	" is " + tempxLoc + ".");
			  }
		  }
		  catch (Exception ex) {
			  setxLocation(indexExchange);
		  } 
	}
	
	public void setyLocation(int indexExchange) {
		Scanner locInput = new Scanner(System.in);
		System.out.println("To set the y location of exchange " + 
				exchangeArray[indexExchange].getExchangeID() +
				", please enter a positive integer no bigger than " +
				gridSizeY + ".");  
		 try {
			  tempyLoc = Integer.parseInt(locInput.nextLine());
			  if (tempyLoc > gridSizeY || tempyLoc < 0) {
				  setyLocation(indexExchange);
			  }
			  else {
				  exchangeArray[indexExchange].setyLoc(tempyLoc);
				  System.out.println("The y location of exchange " + 
				  	exchangeArray[indexExchange].getExchangeID() +
				  	" is " + tempyLoc + ".");
			  }
		  }
		  catch (Exception ex) {
			  setyLocation(indexExchange);
		  } 
	}
	
	public void computeDistance(int indexExchange) {
		int tempDistance = exchangeArray[indexExchange].getxLoc() + 
			exchangeArray[indexExchange].getyLoc();
			exchangeArray[indexExchange].setCustomerDistance(tempDistance);
			distanceArray[indexExchange] = tempDistance;
	}
	
	public String findNearest() {
		closestExchanges();
				
		if (countNumClosest == 1) {
			for (int i = 0; i < numExchanges; i++) {
				if (exchangeArray[i].getCustomerDistance() == finalClosestDistance);
					finalExchange = exchangeArray[i].getExchangeID();
					break;
			}			
			System.out.println("The closest exchange is " + finalExchange + "."  + "\n");
		}
		else {
			randomClosest();
		}
		System.out.println("The distance between this exchange and the customer is " + finalClosestDistance + "."  + "\n");
		return finalExchange;
	} 
	
	public void closestExchanges() {
		int currentClosestDistance = maxX + maxY + 1;		
		for (int n = 0; n < numExchanges; n++) {
			if (distanceArray[n] < currentClosestDistance) {
				currentClosestDistance = distanceArray[n];
				countNumClosest = 1;
			}
			else if (distanceArray[n] == currentClosestDistance) {
				countNumClosest++;				
			}
		}
		finalClosestDistance = currentClosestDistance;
	}
	
	public void randomClosest() {
		Random rand = new Random();
		int randInt = rand.nextInt(countNumClosest) + 1;
		
		int randomCounter = 0;
		
		for (int j = 0; j < numExchanges; j++) {
			if (exchangeArray[j].getCustomerDistance() == finalClosestDistance) {
				randomCounter++;
				if (randomCounter == randInt) {
					System.out.println("There are " + countNumClosest + " equally close exchanges"
							+ " to the customer."  + "\n");
					System.out.println("The system suggests you use exchange " + exchangeArray[j].getExchangeID() + "."  + "\n");
					break;
				}
			}
		}
	}		
	
}