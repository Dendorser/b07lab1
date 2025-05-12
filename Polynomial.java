import java.util.Arrays;

public class Polynomial {
	double[] coeffs;
	
	public Polynomial() {
		coeffs = new double[] {0};
	}
	
	public Polynomial(double[] coeffs) {
		this.coeffs = Arrays.copyOf(coeffs, coeffs.length);
	}
	
	public Polynomial add(Polynomial p) {
		int len1 = coeffs.length;
		int len2 = p.coeffs.length;
		int len = Math.max(len1, len2);
		double[] new_coeff = Arrays.copyOf(coeffs, len);
		for(int i = 0; i<len2; i++) {
			new_coeff[i] += p.coeffs[i];
		}
		
		Polynomial q = new Polynomial(new_coeff);
		return q;
	}
	
	public double evaluate(double num) {
		double a = 0;
		for(int i = 0; i<coeffs.length; i++) {
			a += coeffs[i]*(Math.pow(num, i));
		}
		return a;
	}
	
	public Boolean hasRoot(double num) {
		if(evaluate(num) == 0) {
			return true;
		}
		return false;
	}
}
