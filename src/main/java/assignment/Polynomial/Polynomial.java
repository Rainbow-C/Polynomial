/**
 * 
 */
package assignment.Polynomial;

/**
 * @author bhargav
 *
 */

public class Polynomial {
	private int[] coef; 
	private int degree; 

	/**
     * Initializes a new polynomial a x^b
     * @param a the leading coefficient  
     * @param b the exponent
     * @throws IllegalArgumentException if b is negative
     */
	public Polynomial(int a, int b) {
		if (b < 0) {
			throw new IllegalArgumentException("exponent cannot be negative: " + b);
		}
		coef = new int[b + 1];
		coef[b] = a;
		reduce();
	}

	
	private void reduce() {
		degree = -1;
		for (int i = coef.length - 1; i >= 0; i--) {
			if (coef[i] != 0) {
				degree = i;
				return;
			}
		}
	}

	/**
	 * Returns the degree of this polynomial.
	 * 
	 * @return the degree of this polynomial, -1 for the zero polynomial.
	 */
	public int degree() {
		return degree;
	}

	/**
	* @param that 
	* @return Polynomial the value of polynomial after addition of polynomials
	*/
	public Polynomial plus(Polynomial that) {
		Polynomial poly = new Polynomial(0, Math.max(this.degree, that.degree));
		for (int i = 0; i <= this.degree; i++)
			poly.coef[i] += this.coef[i];
		for (int i = 0; i <= that.degree; i++)
			poly.coef[i] += that.coef[i];
		poly.reduce();
		return poly;
	}

	
	/**
	* @param that 
	* @return Polynomial the value of polynomial after Substarction of polynomials
	*/
	public Polynomial minus(Polynomial that) {
		Polynomial poly = new Polynomial(0, Math.max(this.degree, that.degree));
		for (int i = 0; i <= this.degree; i++)
			poly.coef[i] += this.coef[i];
		for (int i = 0; i <= that.degree; i++)
			poly.coef[i] -= that.coef[i];
		poly.reduce();
		return poly;
	}

	/**
	* @param that 
	* @return Polynomial the value of polynomial after multiplication of polynomials
	*/
	public Polynomial times(Polynomial that) {
		Polynomial poly = new Polynomial(0, this.degree + that.degree);
		for (int i = 0; i <= this.degree; i++)
			for (int j = 0; j <= that.degree; j++)
				poly.coef[i + j] += (this.coef[i] * that.coef[j]);
		poly.reduce();
		return poly;
	}

	/** 
	* @return Polynomial the value of polynomial after differentiating polynomials
	*/
	public Polynomial differentiate() {
		if (degree == 0)
			return new Polynomial(0, 0);
		Polynomial poly = new Polynomial(0, degree - 1);
		poly.degree = degree - 1;
		for (int i = 0; i < degree; i++)
			poly.coef[i] = (i + 1) * coef[i + 1];
		return poly;
	}

	public int evaluate(int x) {
		int p = 0;
		for (int i = degree; i >= 0; i--)
			p = coef[i] + (x * p);
		return p;
	}

	
	/**
	 * Return a string representation of this polynomial.
	 * 
	 * @return a string representation of this polynomial in the format 4x^5 - 3x^2
	 *         + 11x + 5
	 */
	@Override
	public String toString() {
		if (degree == -1)
			return "0";
		else if (degree == 0)
			return "" + coef[0];
		else if (degree == 1)
			return coef[1] + "x + " + coef[0];
		String s = coef[degree] + "x^" + degree;
		for (int i = degree - 1; i >= 0; i--) {
			if (coef[i] == 0)
				continue;
			else if (coef[i] > 0)
				s = s + " + " + (coef[i]);
			else if (coef[i] < 0)
				s = s + " - " + (-coef[i]);
			if (i == 1)
				s = s + "x";
			else if (i > 1)
				s = s + "x^" + i;
		}
		return s;
	}

	
	public static void main(String[] args) {
		Polynomial zero = new Polynomial(0, 0);

		//  p(x)   = 4x^3 + 3x^2 + 2x + 1
		//  q(x)   = 3x^2 + 5
		
		Polynomial p1 = new Polynomial(4, 3);
		Polynomial p2 = new Polynomial(3, 2);
		Polynomial p3 = new Polynomial(1, 0);
		Polynomial p4 = new Polynomial(2, 1);
		Polynomial p = p1.plus(p2).plus(p3).plus(p4); // 4x^3 + 3x^2 + 1

		Polynomial q1 = new Polynomial(3, 2);
		Polynomial q2 = new Polynomial(5, 0);
		Polynomial q = q1.plus(q2); // 3x^2 + 5
		

		Polynomial r = p.plus(q);
		Polynomial s = p.times(q);
		Polynomial u = p.minus(p);

		System.out.println("zero(x)     = " + zero);
		System.out.println("p(x)        = " + p);
		System.out.println("q(x)        = " + q);
		System.out.println("p(x) + q(x) = " + r);
		System.out.println("p(x) * q(x) = " + s);
		System.out.println("p(x) - p(x) = " + u);
		System.out.println("0 - p(x)    = " + zero.minus(p));
		System.out.println("p(3)        = " + p.evaluate(3));
		System.out.println("p'(x)       = " + p.differentiate());
		System.out.println("p''(x)      = " + p.differentiate().differentiate());
	}
}
