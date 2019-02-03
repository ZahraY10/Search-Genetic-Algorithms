package p2;

//Class construction our map

import java.util.Random;
import java.util.Vector;

public class Graph {

	private int color1 = 0;
	private int color2 = 1;
	private int color3 = 2;
	private Node n0, n1, n2, n3, n4, n5, n6, n7, n8, n9, n10;
	private Vector<Node> population;
	private double fitness = 0;

	/////////////////////////////////////////////////////////////////////////////////////////

	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	public int getColor1() {
		return color1;
	}

	public void setColor1(int color1) {
		this.color1 = color1;
	}

	public int getColor2() {
		return color2;
	}

	public void setColor2(int color2) {
		this.color2 = color2;
	}

	public int getColor3() {
		return color3;
	}

	public void setColor3(int color3) {
		this.color3 = color3;
	}

	public Node getN0() {
		return n0;
	}

	public void setN0(Node n0) {
		this.n0 = n0;
	}

	public Node getN1() {
		return n1;
	}

	public void setN1(Node n1) {
		this.n1 = n1;
	}

	public Node getN2() {
		return n2;
	}

	public void setN2(Node n2) {
		this.n2 = n2;
	}

	public Node getN3() {
		return n3;
	}

	public void setN3(Node n3) {
		this.n3 = n3;
	}

	public Node getN4() {
		return n4;
	}

	public void setN4(Node n4) {
		this.n4 = n4;
	}

	public Node getN5() {
		return n5;
	}

	public void setN5(Node n5) {
		this.n5 = n5;
	}

	public Node getN6() {
		return n6;
	}

	public void setN6(Node n6) {
		this.n6 = n6;
	}

	public Node getN7() {
		return n7;
	}

	public void setN7(Node n7) {
		this.n7 = n7;
	}

	public Node getN8() {
		return n8;
	}

	public void setN8(Node n8) {
		this.n8 = n8;
	}

	public Node getN9() {
		return n9;
	}

	public void setN9(Node n9) {
		this.n9 = n9;
	}

	public Node getN10() {
		return n10;
	}

	public void setN10(Node n10) {
		this.n10 = n10;
	}

	public Vector<Node> getPopulation() {
		return population;
	}

	public void setPopulation(Vector<Node> population) {
		this.population = population;
	}

	/////////////////////////////////////////////////////////////////////////////////////////

	public Graph() {

		///graph initialization
		n0 = new Node(0, new Random().nextInt(3));
		n1 = new Node(1, new Random().nextInt(3));
		n2 = new Node(2, new Random().nextInt(3));
		n3 = new Node(3, new Random().nextInt(3));
		n4 = new Node(4, new Random().nextInt(3));
		n5 = new Node(5, new Random().nextInt(3));
		n6 = new Node(6, new Random().nextInt(3));
		n7 = new Node(7, new Random().nextInt(3));
		n8 = new Node(8, new Random().nextInt(3));
		n9 = new Node(9, new Random().nextInt(3));
		n10 = new Node(10, new Random().nextInt(3));

		//defining neighbors
		n0.getNeighborNodes().addElement(n2);
		n0.getNeighborNodes().addElement(n4);
		n0.getNeighborNodes().addElement(n6);
		n0.getNeighborNodes().addElement(n8);
		n0.getNeighborNodes().addElement(n10);

		n1.getNeighborNodes().addElement(n2);
		n1.getNeighborNodes().addElement(n5);
		n1.getNeighborNodes().addElement(n7);
		n1.getNeighborNodes().addElement(n10);

		n2.getNeighborNodes().addElement(n0);
		n2.getNeighborNodes().addElement(n1);
		n2.getNeighborNodes().addElement(n3);

		n3.getNeighborNodes().addElement(n2);
		n3.getNeighborNodes().addElement(n4);
		n3.getNeighborNodes().addElement(n7);
		n3.getNeighborNodes().addElement(n9);

		n4.getNeighborNodes().addElement(n0);
		n4.getNeighborNodes().addElement(n3);
		n4.getNeighborNodes().addElement(n5);

		n5.getNeighborNodes().addElement(n4);
		n5.getNeighborNodes().addElement(n6);
		n5.getNeighborNodes().addElement(n9);
		n5.getNeighborNodes().addElement(n1);

		n6.getNeighborNodes().addElement(n0);
		n6.getNeighborNodes().addElement(n5);
		n6.getNeighborNodes().addElement(n7);

		n7.getNeighborNodes().addElement(n1);
		n7.getNeighborNodes().addElement(n3);
		n7.getNeighborNodes().addElement(n6);
		n7.getNeighborNodes().addElement(n8);

		n8.getNeighborNodes().addElement(n0);
		n8.getNeighborNodes().addElement(n7);
		n8.getNeighborNodes().addElement(n9);

		n9.getNeighborNodes().addElement(n3);
		n9.getNeighborNodes().addElement(n5);
		n9.getNeighborNodes().addElement(n8);
		n9.getNeighborNodes().addElement(n10);

		n10.getNeighborNodes().addElement(n0);
		n10.getNeighborNodes().addElement(n1);
		n10.getNeighborNodes().addElement(n9);

		//creating first population
		population = new Vector<Node>(5, 5);
		population.addElement(n0);
		population.addElement(n1);
		population.addElement(n2);
		population.addElement(n3);
		population.addElement(n4);
		population.addElement(n5);
		population.addElement(n6);
		population.addElement(n7);
		population.addElement(n8);
		population.addElement(n9);
		population.addElement(n10);
//		print();
//		System.out.println(evalFunction(this));
	}

	public void print() {
		for(int i = 0; i < this.getPopulation().size(); i++) {
			System.out.println("Current node = " + this.getPopulation().elementAt(i).getNodeNo());
			System.out.println("Current node color = " + this.getPopulation().elementAt(i).getNodeColor());
			for(int j = 0; j < this.getPopulation().elementAt(i).getNeighborNodes().size(); j++) {
				System.out.println("Current neighbor = " + this.getPopulation().elementAt(i).getNeighborNodes().elementAt(j).getNodeNo());
			}
		}
	}

	public int evalFunction(Graph g) {
		int eval = 0;
		for(int i = 0; i < g.getPopulation().size(); i++) {
			eval = eval - g.getPopulation().elementAt(i).calcK();
		}
		return eval;
	}


}
