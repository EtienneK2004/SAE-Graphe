package graph.ihm;

public class Arc {
	private int source, valuation, destination;
	public Arc () { 
		this(0,0,0);
	}
	public Arc (int source, int valeur, int cible) {
		this.source = source;
		this.valuation = valeur;
		this.destination = cible;
	}
	public int getSource() {
		return source;
	}
	public int getValuation() {
		return valuation;
	}
	public int getDestination() {
		return destination;
	}
	@Override
	public String toString() {
		return source+ " =="+valuation+ "==> "+destination;
	}
	public void set(Arc a) {
		this.source = a.source;
		this.valuation =a.valuation;
		this.destination = a.destination;
	}
}
