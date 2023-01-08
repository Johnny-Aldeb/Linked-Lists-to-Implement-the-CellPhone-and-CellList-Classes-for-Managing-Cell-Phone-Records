// -----------------------------------------------------
//Written by: Johnny Aldeb
//Student ID: 40187248
//COMP249 Assignment #3 - PART 2
//Due Date: Monday, NOV, 5, 2022
// -----------------------------------------------------

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

class CellPhone implements Cloneable{
	
	
	private long serialNum;
	private String brand;
	private int year;
	private double price;
	
	
	public CellPhone(long serialNum, String brand, int year, double price) {
		this.serialNum = serialNum;
		this.brand = brand;
		this.year = year;
		this.price = price;
	}
	
	public CellPhone(CellPhone c, long valueOfSerialNumber) {
		setBrand(c.brand);
		setPrice(c.price);
		setYear(c.year);
		this.serialNum = valueOfSerialNumber;
		
	}
	
	public CellPhone clone()
	{
		Scanner s = new Scanner(System.in);
		System.out.println("enter a new serial number");
		long NewSerialNumber = s.nextLong();
		return new CellPhone(this, NewSerialNumber);	
	}
	
	public String toString() {
		return "The Serial Number: " + this.serialNum +", The brand is: "+ this.brand+", The price is: "+this.price+
				", The year is: "+ this.year;
				}
	public boolean equals(Object x) {
		if(this == null || x == null || this.getClass()!= x.getClass()) {
			return false;
		}
		
		CellPhone c = (CellPhone) x;
		
		return this.year == c.year && this.brand == c.brand && this.price == c.price;
	}

	
	
	public long getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(long serialNum) {
		this.serialNum = serialNum;
	}
	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	

} // end of CellPhone Class


class CellList {
	

	public class CellNode implements Cloneable
	{
		private CellPhone cellphone;
		private CellNode next;	
		
		// Default constructors 
		public CellNode()
		{
			cellphone = null;
			next = null;
		}
		
		
		// Parameterized constructor 
		public CellNode(CellPhone c, CellNode t)
		{
			this.cellphone = c;		
			this.next = t;	
		}
		
		// A copy constructor for copy a node
		public  CellNode(CellNode cn)
		{
			this.cellphone = cn.cellphone.clone();	 // Deep copy the CellPhone contents of the node
			this.next = cn.next;					
		}	
		
		// mutator and accessor methods.
		public CellNode clone() {
			
			return new CellNode(this);
		}
		
		public CellPhone getCellphone() {
			return cellphone;
		}


		public void setCellphone(CellPhone cellphone) {
			this.cellphone = cellphone;
		}


		public CellNode getNext() {
			return next;
		}


		public void setNext(CellNode next) {
			this.next = next;
		}


	} // end of inner class
	
	private CellNode head;
	private int size;
	
	public int getSize() {
		
		int size = 0;
		CellNode temp = head;	
		while( temp!= null)
		{
			size++;
			temp = temp.next;
		}
		
		return size;
	}


	


	public CellList() {
		this.head = null;
		this.size = 0;
	}
	
	
	// A copy constructor for copy CellList object
	public CellList(CellList existList) {
		
		if(existList.head == null) {
			this.head = null;
		}
		
		else {
			
			this.head = null;
			CellNode t1, t2, t3;
			
			t1 = existList.head;
			t2 = t3 = null;
			
			while(t1 !=null) {
				
				if(this.head == null) {
					t2 = new CellNode(t1);
					this.head = t2;
				}
				else {
					t3 = new CellNode(t1);
					t2.next = t3;
					t2 = t3;
					
					
				}
				
				t1 = t1.next;
				
			}
			
			t2 = t3 = null;	
			
		}
		
	}
	
	// A addToStart() method
	public boolean addToStart(CellPhone newCellPhone) {
		
		if(this.head == null) {
			this.head = new CellNode(newCellPhone, head);
		}else {
			CellNode t = head;
			while(t !=null) {
				if(t.cellphone.getSerialNum() == newCellPhone.getSerialNum()) {
					return false;
				}
				t = t.next;
			}
			CellNode newNode = new CellNode(newCellPhone, head);
			this.head = newNode;
			newNode = null;
			
		}
		return true;
		
		
	}
	
	

	
	// A insertAtIndex() method  
	
	public boolean insertAtIndex(CellPhone newCellPhone, int index) {
		
	try {
		if(index > getSize() || index< 0) {
			throw new NoSuchElementException("Index not valid");
		}
		else if(head == null) {
			if(index == 0) {
				head = new CellNode(newCellPhone, head);
			}
			else {
				throw new NoSuchElementException();
			}
			
		}
		else {
			CellNode t = head;
			while(t != null) {
				if(t.cellphone.getSerialNum() == newCellPhone.getSerialNum()) {
					System.out.println("----------------------------------------------------------------------------");
					System.out.println("below the serial number of:"+ newCellPhone.getSerialNum() + " Can't be add because it's already exists" );
					return false;
				}
				t = t.next;
			}
			
			int counter = 0;
		
			if(index == counter) {
				CellNode newNode = new CellNode(newCellPhone, head);
				this.head = newNode;
				newNode = null;
			}else {
				 t = head;
				
				while(t != null) {
					
					
					counter++;
					if(counter == index) {
						t.next = new CellNode(newCellPhone, t.next);
						return true;
					
					}else {
						t = t.next;
					}
					
				}
			}
			
		}
		
		
	}catch(NoSuchElementException e){
		System.out.println("Index not valid. Program is terminated");
		System.exit(0);
	}
	return true;
		
	}
	
   //A deleteFromIndex() method 
	public boolean deleteFromIndex(int index) {
	try {	
		if(index > getSize()-1) {
			
			throw new NoSuchElementException("Index not valid");
		}
		if(this.head == null) {
			throw new NoSuchElementException("The List is Empty");
		}
		else {
			int counter =0;
			CellNode t = head;
			CellNode t2 = null;
			
			if(counter == index) {
				this.head = t.next;
				t.next = null;
				t = null;
				return true;
			}
			else {
				while(t.next != null) {
					counter++;
					t2 = t.next;
					if(index == counter) {
						t.next = t2.next;
						t2.next = null;
						t2 = null;
						return true;
					}
					t = t.next;
				
			}
	
			}
			return true;
			
		}
		
		
		
		
	}catch(NoSuchElementException e){
		System.out.println("Program is terminated");
		System.exit(0);
	}
	return false;
		
	}
	
	
	
	// A deleteFromStart() method
	public void deleteFromStart() {
		
		if(head == null) {
			System.out.println("List is Already Empty");
		}else {
			CellNode t = head;
			head = t.next;
			t.next = null;
			t = null;
		}
	}
	
	
	
	// A replaceAtIndex() method
	public void replaceAtIndex(CellPhone newCellPhone, int index) {
		
		if(index > getSize()-1) {
			System.out.println("Inavalid index input");
			return;
		}else if(this.head == null) {
			System.out.println("List is Already Empty");
		}
		else {
			CellNode t = head;
			int counter = 0;
			
			while(t != null) {
				if(counter == index) {
					t.cellphone = newCellPhone;
					break;
					
				}
				counter++;
				t = t.next;
			}
			
		}
		
	}
	
	// A find() method
	
	/*
	 *  note: this method will result in a privacy leak because we are returning a pointer which allows the user to use it
	 *        to change the attributes of cellPhone. For Example: use the method setPrice() using the pointer returned 
	 *        setPrice(-25000) 
	 */
	
	public CellNode find(long serialNumber) {
		int counter =1;
		if(head == null) {
			System.out.println("the number of iterations performed is:"+counter);
			return null;
			
		}else {
			CellNode t = head;
			while(t != null) {
				
				if(t.cellphone.getSerialNum() == serialNumber) {
					System.out.println("the number of iterations performed is:"+counter);
					
					// danger 
					return t;
				}
				counter++;
				t = t.next;
				
			}
			
		}
		
		return null;
	}
	
	// A contains() method
	public Boolean contains(long serialNumber) {
		
		
		if(head == null) {
			
			return false;
			
		}else {
			CellNode t = head;
			while(t != null) {
				
				if(t.cellphone.getSerialNum() == serialNumber) {
					
					return true;
				}
				
				t = t.next;
				
			}
			
		}
		
		return false;
	}
	
	// a showContents() Method
	
	public void showContents() {
		
		System.out.println("\nThe current size of the list is "+this.getSize()+". Here are the contents of the list:");
		System.out.println("======================================================================\n");
		
		if(this.head == null) {
			System.out.println("---> X");
		}else {
			CellNode t = this.head;
			while(t != null) {
				System.out.println("["+ t.cellphone.getSerialNum()+": "+t.cellphone.getBrand()+" "+t.cellphone.getPrice()+
						"$ "+t.cellphone.getYear()+"] --->");
				
				t = t.next;
			}System.out.print("X\n");
		}
	}
	
	// A equals() method 
	public boolean equals(CellList c) {
		
		
		
		if(this.head == null && c.head == null) {
			return true;
		}else if(this.getSize() != c.getSize()) {
			return false;
		}
		
		else {
			int counter = 0;
			CellNode t = this.head;
			CellNode t2 = c.head;
			
			while(t !=null && t2 !=null && counter < this.getSize()) {
				
				if(t.cellphone.equals(t2.cellphone) ) {
					
					if(counter < this.getSize()) {
						
						return true;
					}
					counter++;
					t = t.next;
					t2 = t2.next;
					
				}else {
					return false;
				}
				
			}
		}
		
		return false;
		
	}
	
	
	

}

public class CellListUtilization {

	public static void main(String[] args) {
		
		CellList c1 = new CellList();
		CellList c2 = new CellList();
		CellPhone cell1 = new CellPhone(9888, "Replace_One" , 2022, 1500.5);
		CellPhone cell2 = new CellPhone(77777, "Apple" , 2022, 1500.5);
		
		
		
		//-------------------------------------------------------------
		
		// Test the addToStart method and showContents method
		CellList c3 = new CellList();
		
		try {
			///Users/johnnyaldeb/Desktop/
			Scanner s = new Scanner(new FileInputStream("Cell_Info.txt"));
			
			while(s.hasNextLine()) {
				long serialNumber = s.nextLong();
				String brand = s.next();
				double price = s.nextDouble();
				int year = s.nextInt();

				
				CellPhone c = new CellPhone(serialNumber, brand , year, price);
				
				// addtToStart() method
				c3.addToStart(c);
				
				
				
			}
			// showContents method
			c3.showContents();
			System.out.println("\n");

			
		} catch (FileNotFoundException e) {
			System.out.println("File not found, Please make sure the file is exist. Program will terminate");
			System.exit(0);
			
		}
		
		//-------------------------------------------------------------
		
		
		// test find() method 
		
		// Prompt the user to enter a few serial numbers to search the c3 list
		
//		Scanner s2 = new Scanner(System.in);
//		
//		
//		for(int i=0; i<3; i++) {
//			System.out.print("\nPlease Enter a serial numbers and search the list:");
//			long serialNumber = s2.nextLong();
//			
//			CellList.CellNode s3 = c3.find(serialNumber);
//			if (s3 == null)
//			{
//				System.out.print("\nNo CellPhone with serial number " + serialNumber + " was found in the list");
//			}
//			else
//			{
//				System.out.print("\nCellPhone with serial number " + serialNumber + " was found in the list\n");
//				System.out.println("\nHere is the infromation about the CellPhone:");
//				System.out.println(s3.getCellphone());
//
//			}
//		}
		
		//-------------------------------------------------------------
		
		// test insertAtIndex() method
		
//		System.out.println("\n");
		
//		CellPhone cell1 = new CellPhone(2787985, "Replace_One" , 2022, 1500.5);
//		CellPhone cell2 = new CellPhone(77777, "Apple" , 2022, 1500.5);
		
//		c3.insertAtIndex(cell1, 2);
//		c3.insertAtIndex(cell2, 3);
//		c3.showContents();
		
		
//		c1.insertAtIndex(cell1, 0);
//		c1.insertAtIndex(cell2, 1);
//		
//		c1.showContents();
		
//		
		//-------------------------------------------------------------
		
		// test deletFromIndex() method
//		
//		System.out.println("\n");
//		
//		// non-empty linklist
//		c3.deleteFromIndex(1);
//		
//		// empty linklist
//		c1.deleteFromIndex(0);
//		
//		
//		c3.showContents();
//		c1.showContents();
		
		//-------------------------------------------------------------
		
		// test deletFromStart() method
		
//		System.out.println("\n");
//		c3.deleteFromStart();
//		c3.showContents();
//		System.out.println("\n");
//		
//		// delete from a linklist that is already empty
//		c1.deleteFromStart();
		
		//-------------------------------------------------------------
		
//		// test replaceAtIndex() method
//		
//		System.out.println("\n");
//		c3.replaceAtIndex(cell1, 22);
//		c3.showContents();
//		
//		System.out.println("\n");
//	
//		// replace from a linklist that is already empty
//		c1.replaceAtIndex(cell2, 0);
		
		//-------------------------------------------------------------

		 // test the  contains() method
		
//		System.out.println("\n");
//		// test a valid serial number
//		if(c3.contains(8888902)) {
//			System.out.println("yes it's there");
//		}else {
//			System.out.println("not it's not there");
//		}
//		
//		// test on a non-valid serial number
//		if(c3.contains(665566)) {
//			System.out.println("yes it's there");
//		}else {
//			System.out.println("not it's not there");
//		}
//		
//		// test on a valid serial number but in empty linklist
//		if(c1.contains(8888902)) {
//			System.out.println("yes it's there");
//		}else {
//			System.out.println("not it's not there");
//		}
		
		
		//-------------------------------------------------------------
		
		// test equals() method on two linklist
		
//		if(c3.equals(c3)) {
//			System.out.println("yes there are equal");
//		}else {
//			System.out.println("No there are not equal");
//		}
//		
//		if(c1.equals(c3)) {
//			System.out.println("yes there are equal");
//		}else {
//			System.out.println("No there are not equal");
//		}
//		
//		if(c1.equals(c1)) {
//			System.out.println("yes there are equal");
//		}else {
//			System.out.println("No there are not equal");
//		}
		
		
		

		
		
		
		
		

	}

}
