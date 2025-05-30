import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.Scanner;
import java.io.*;

public class Polynomial {
	double[] coefficients;
	int[] exponents;
	
	public Polynomial() {
		coefficients = new double[] {0};
		exponents = new int[] {0};
	}
	
	public Polynomial(double[] coefficients, int[] exponents) {
		this.coefficients = Arrays.copyOf(coefficients, coefficients.length);
		this.exponents = Arrays.copyOf(exponents, exponents.length);
	}
	
	public Polynomial(File f) {
		try {
			Scanner scan = new Scanner(f);
			String a = scan.nextLine();
			scan.close();
			int start = 0;
			ArrayList <String> list = new ArrayList <String>();
			for(int i = 0; i<a.length(); i++) {
				if((a.charAt(i) == '-' || a.charAt(i) == '+') && i != 0) {
//					System.out.println(a.substring(start,i));
					list.add(a.substring(start,i));
					start = i;
				}
				if(i == a.length()-1) {
//					System.out.println(a.substring(start));
					list.add(a.substring(start));
				}
			}
			double[] coeff = new double[list.size()];
			int[] exponents = new int[list.size()];
			
			for(int i = 0; i<list.size(); i++) {
				String[] b = list.get(i).split("x");
				coeff[i] = Double.parseDouble(b[0]);
				if(b.length == 2) {
					exponents[i] = Integer.parseInt(b[1]);
				}
			}
			this.coefficients = Arrays.copyOf(coeff, coeff.length);
			this.exponents = Arrays.copyOf(exponents,  exponents.length);
		}catch (FileNotFoundException ex) {
			System.out.println("No file found");
		}
	}
		
	public Polynomial add(Polynomial p) {
		int len1 = exponents.length;
		int len2 = p.exponents.length;
//		double[] new_coeff = new double[len];
//		int[] new_exponents = new int[len];
		HashMap <Integer, Double> pairs = new HashMap <Integer, Double>();
		for(int i = 0; i<len1;i++) {
			pairs.put(exponents[i], coefficients[i]);
		}
		for(int i = 0; i<len2; i++) {
			if(pairs.get(p.exponents[i]) == null) {
				pairs.put(p.exponents[i], p.coefficients[i]);
			}
			else {
				pairs.put(p.exponents[i], pairs.get(p.exponents[i])+p.coefficients[i]);
			}
		}
		Set <Integer> exponents_set = pairs.keySet();
		double[] new_coeff = new double[exponents_set.size()];
		int[] new_exponents = new int[exponents_set.size()];
		int i = 0;
		for(int h:exponents_set) {
			new_exponents[i] = h;
			new_coeff[i] = pairs.get(h);
			i++;
		}
		
		Polynomial q = new Polynomial(new_coeff, new_exponents);
		return q;
	}
	
	public void saveToFile(String s) throws IOException{
		//System.out.println("AAAAAAAAAAAAAAHHHHHHH I HATE FILEWRITER");
		String poly = "";
		for(int i = 0; i<coefficients.length; i++) {
			if(coefficients[i]>0 && i != 0) {
				poly += "+";
			}
			poly += coefficients[i];
			if(exponents[i] != 0) {
				poly += ("x"+exponents[i]);
			}
		}
		String filename = s+".txt";
		FileWriter writer = new FileWriter(filename);
		writer.write(poly);
		writer.close();
	}
	
	public Polynomial multiply(Polynomial p){
		int len1 = exponents.length;
		int len2 = p.exponents.length;
		HashMap <Integer, Double> pairs = new HashMap <Integer, Double>();
		for(int i = 0; i<len1; i++) {
			for(int j = 0; j<len2; j++) {
				if(pairs.get(exponents[i]+p.exponents[j]) == null) {
					pairs.put(exponents[i]+p.exponents[j],coefficients[i]*p.coefficients[j]);
				}
				else {
					pairs.put(exponents[i]+p.exponents[j], pairs.get(exponents[i]+p.exponents[j])+coefficients[i]*p.coefficients[j]);
				}
			}
		}
		Set <Integer> keySet = pairs.keySet();
		double[] new_coeff = new double[keySet.size()];
		int[] new_exp = new int[keySet.size()];
		int i = 0;
		for(int h:keySet) {
			new_exp[i] = h;
			new_coeff[i] = pairs.get(h);
			i++;
		}
		
		Polynomial q = new Polynomial(new_coeff, new_exp);
		return q;
	}
	
	public double evaluate(double num) {
		double a = 0;
		for(int i = 0; i<exponents.length; i++) {
			a += coefficients[i]*(Math.pow(num, exponents[i]));
		}
		return a;
	}
	
	public Boolean hasRoot(double num) {
		if(evaluate(num) == 0) {
			return true;
		}
		return false;
	}
	
//	@Override 
//	public String toString() {
//		String s = "";
//		for(int i = 0; i<exponents.length; i++) {
//			if(coefficients[i] > 0) {
//				s += "+"+coefficients[i]+"x^"+exponents[i]+" ";
//			}
//			else if(coefficients[i]<0) {
//				s += coefficients[i]+"x^"+exponents[i]+" ";
//			}
//		}
//		return s;
//	}
}
