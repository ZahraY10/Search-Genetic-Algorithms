package p1;

import java.util.Vector;

public class AStar_T {

	Graph g;
	Vector<Node> f;
	int fn;
	int en;
	int total;

	// Vector<String> e;

	public AStar_T() {
		g = new Graph();
		f = new Vector<Node>();
		f.addElement(g.init);
		fn = 1;
		total = 1;
		en = 0;
	}

	public void search() {
		if(g.Goal_Test(f.elementAt(0).state)){
			System.out.println();
			g.print(f.elementAt(0));
			System.out.println("created nodes: " + fn);
			System.out.println("expanded nodes: "+ en);
			System.out.println("max memory: "+ total);
			return;
		}
		System.out.println("visited nodes:");
		while (f.size() > 0) {
			if(total < f.size())
				total = fn;
			int a = 0;
			for (int i = 0; i < f.size(); i++) {
				if(f.elementAt(a).f > f.elementAt(i).f)
					a = i;
			}
			Node n = f.elementAt(a);
			en++;
			System.out.print(n.id + "        ");
			f.removeElementAt(a);
			n.next_node();
			for (int i = 0; i < n.next_nodes.size(); i++) {
				if (g.Goal_Test(n.next_nodes.elementAt(i).state)) {
					System.out.println();
					g.print(n.next_nodes.elementAt(i));
					System.out.println("created nodes: " + fn);
					System.out.println("expanded nodes: "+ en);
					System.out.println("max memory: "+ total);
					return;
				}
			}
			for (int i = 0; i < n.next_nodes.size(); i++) {
				f.addElement(n.next_nodes.elementAt(i));
				fn++;
			}

		}
	}

	public static void main(String[] args) {
		AStar_T a = new AStar_T();
		a.search();
	}
}
