import java.io.*;
import java.util.Scanner;

public class Driver {
	public static void main(String [] args) throws IOException{
			Polynomial p = new Polynomial();
			System.out.println(p.evaluate(3));
			double [] c1 = {3,-1,2,4};
			int [] e1 = {0,1,2,3};
			Polynomial p1 = new Polynomial(c1,e1);
			double [] c2 = {1, 2, 3};
			int [] e2 = {0,1,5};
			Polynomial p2 = new Polynomial(c2, e2);
			Polynomial s = p1.add(p2);
			Polynomial d = p1.multiply(p2);
			System.out.println(d);
			System.out.println("s(0.1) = " + s.evaluate(0.1));
			
			File poly = new File("polyfile.txt");
			Polynomial p3 = new Polynomial(poly);
			System.out.println(p3);
			p3.saveToFile("polyfile2");
			
			if(s.hasRoot(1))
				System.out.println("1 is a root of s");
			else
				System.out.println("1 is not a root of s");
	}
}