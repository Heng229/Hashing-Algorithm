import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class HashingApp {
	
	String[] arr;
	int arrSize;
	int hashval;
	
	public static void main(String[] args) {
		
		String opt = "0";
		int optnum = 0;
		boolean conti = true;
		Scanner scan = new Scanner(System.in);
		
		do {
		System.out.println("\n-----------------");
		System.out.println("  Hashing Demo");
		System.out.println("-----------------");
		System.out.println("Please choose whether to manually assign size for hash table & insert elements into the array.");
		System.out.println("Random Phone numbers have a hash table with the size you assign, it will generate & insert random phone numbers into the hash table automatically.\n1. Manual.\n2. Random Phone numbers.\n-1. Exit");
		System.out.print("Options :  ");
		
		
		opt = scan.nextLine();
		
		try {
			optnum = Integer.parseInt(opt);
			conti = true;
		}catch(Exception e){
			conti = true;
		}
		
		switch(optnum) {
		case 1:
			boolean errorDetect = true;
			int sizeNum = 0;
			System.out.println("===================Option 1 Manual===================\n");
			System.out.println("Please enter the size for hash table.");
			do {
				System.out.print("Size : ");
				String size = scan.nextLine();
					
				try {
					sizeNum =  Integer.parseInt(size);
					errorDetect = false;
				}catch(Exception e){
					System.out.println("Please enter a valid input.\n");
					errorDetect = true;
				}
			
			}while(errorDetect == true);
				
			HashingApp hashapp = new HashingApp(sizeNum);
			String[] valToAdd = new String[sizeNum];
			String tempInput = "0";
			int count =0;
			int counterShow = sizeNum;
			
			System.out.println("Enter value to assign into each array.");
			while(count < sizeNum) {
				counterShow = sizeNum-count;
				System.out.println("Left " + counterShow + " value to insert.  You can stop inserting by entering \"QUIT\", or wait until reach maximum size previously assign.\n");
				System.out.print("Value to be insert : ");
				tempInput = scan.nextLine();
				if(tempInput.equals("QUIT")) {
					break;
				}
				
				if(tempInput.equals("")) {
					System.out.println("Please enter valid value, null is not accepted.");
				}else {
					valToAdd[count] = tempInput;
					count++;
				}
			}
			hashapp.hashfunction(valToAdd,hashapp.arr);
			//Search after assign.
			boolean cont = true;
			
			do {
				System.out.println("\nYou can search the values you just inserted. Enter \"QUIT\" to quit searching.");
				System.out.print("Search : ");
				String search = scan.nextLine();
				if(search.equals("QUIT")) {
					System.out.println("You stopped searching.\n");
					cont = false;
				}else{
					System.out.println(hashapp.search(search));
				}
			}while(cont == true);
			break;
		case 2:
			String size = "0";
			int sizeNum1 = 0;
			boolean repeat = true;
			cont = true;
			
			System.out.println("===================Option 2 Random Phone Numbers===================\n");
			System.out.println("Please enter the size for the hash table, program will automatically generate random phone numbers to be inserted.");
			do {
			System.out.print("Size : ");
			size = scan.nextLine();
			
			try {
				sizeNum1 = Math.abs(Integer.parseInt(size));
				repeat = false;
			}catch(Exception e) {
				System.out.println("Please enter integer only.\n");
				repeat = true;
			}
			}while(repeat);
			
			HashingApp hashapp2 = new HashingApp(sizeNum1);
			String [] valToAdd2 = new String[sizeNum1];
			String temp = "0";
			Random randNum = new Random();
			
			for(int i = 0 ; i < sizeNum1;i++ ) {
				//generate random phone number(10 integer combine string)
				for(int z = 0 ; z < 10 ; z++) {
					if(z == 0 || z == 1) {
						if(z == 0) {
							temp = "0";
							valToAdd2[i] = temp;
						}else {
							temp = "1";
							valToAdd2[i] = valToAdd2[i] + temp;
						}
						
					}else {
					temp = String.valueOf(randNum.nextInt(10));
					
					
					valToAdd2[i] = valToAdd2[i] + temp;
					}
					
				}
				
			}
			hashapp2.hashfunction(valToAdd2,hashapp2.arr);
			System.out.println("\n=============================\n");
			do {
				System.out.println("\nYou can search the values you just inserted. Enter \"QUIT\" to quit searching.");
				System.out.print("Search : ");
				String search = scan.nextLine();
				
				if(search.equals("QUIT")) {
					System.out.println("You stopped searching.\n");
					cont = false;
				}else{
					System.out.println(hashapp2.search(search));
				}
			}while(cont == true);
			break;
		case -1:
			System.out.println("You exited the program.");
			conti = false;
			System.exit(0);
			break;
		default:
			System.out.println("\nPlease enter a valid option.");
			break;
		}
		}while(conti == true);
	}
	
	//Create Hash Table, initially filled with "-1" values.
	HashingApp(int size) {
		arrSize = size;
		arr = new String[size];
		Arrays.fill(arr,"-1");
	}
	
	//Search
	public String search(String data) {
		int hashval = 0;
		
		//Hash function search
		for(int z = 0 ; z < data.length();z++) {
			//cast into Ascii
			char tempchar = data.charAt(z);
			
			//sum the ascii
			hashval += (int)tempchar;
		}
		
		//hashval modul array size
		hashval %= arrSize;
		
		//Searching by comparing data, if reach maximum index, reset to zero, after reset if reach same hash value then break, prompt not found.
		
		int searchHash = hashval;
		//Compare only value that is not null
		while(arr[searchHash] != "-1") {
			if(arr[searchHash].equals(data)) {
				return "Value \""+ data +"\" found in the hash table with index number " + searchHash;
			}
			//linear probing to search
			searchHash++;
			
			if(searchHash >= arrSize) {
				searchHash = 0;
			} 	
			if(searchHash == hashval) {	
				break;
			}
			
		}
		return "Value \"" + data + "\" is not found in the hash table.";
	}
	
	//Hash Function
	public void hashfunction(String[] valuesArr,String[] arr) {
		
		System.out.println("\n--------------------------------------");
		System.out.println("Hash Value calculating & inserting.\n");
		for(int i = 0;i < valuesArr.length; i++) {
			String temp = valuesArr[i];
				// H(x)= Ascii value - index z 
				for(int z = 0 ; z < temp.length();z++) {
					//cast into Ascii
					char tempchar = temp.charAt(z);
				
					//sum the ascii
					hashval += (int)tempchar;	
					
				}
				
				//hashval modul array size
				hashval %= arrSize;
				
				//Show initially hash value calculated for the data to hash table.
				System.out.println("The hashval for \"" + temp + "\" is " + hashval);
			
				//Open addressing : Linear probing is applied to solve collision
				while(arr[hashval] != "-1") {
					hashval++;
					if(hashval >= arrSize) {
						hashval = 0;
						System.out.println("Index reach Maximum, reset back to 0, index = "+ hashval);
					}
					//+1 index linear probing
					System.out.println("Collision Occurs. Linear probing index + 1 , index = "+ hashval);
					//Jump back to 0 when reach the end of array/hashtable
				
				}
				//finally found the empty space in hashtable, insert the value into the hash table.
				arr[hashval] = temp; 
				System.out.println("The value \"" + temp + "\" is inserted into index " + hashval);
				System.out.println("-------------------");
			
				//reset the hash value back to 0, for next element hashing.
				hashval = 0;
		}
		
		//Display final hash table, after calculate hash value,inserted & solved collision
		System.out.println("\n---Final Hash Table---");
		System.out.println("-------------------\n");
		for(int i =0 ; i< arr.length;i++) {
			System.out.printf("|i=%3s| ",i);
			System.out.printf("|%13s| ",arr[i] );
			if((i+1)%5 == 0 ){
				System.out.println("\n");
			}
			
		}
		
		System.out.println("\n-------------------\n");
	}
}


