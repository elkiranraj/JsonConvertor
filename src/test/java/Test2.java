package test.java;
import java.util.Scanner;

public class Test2 {

	public static void main(String[] args) {
		 Scanner scanner = new Scanner(System.in);
	        System.out.println("Enter a Number:");

	       int n = scanner.nextInt();
	        System.out.println(n);
	        if(n%2 !=0 ){
	            System.out.println("Weird");
	        }else if((n%2==0) && n>=2 || n<=5 && (n>= 20) ){
	            
	             System.out.println("Not Weird");
	        }

	}

}
