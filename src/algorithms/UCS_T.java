package algorithms;

import java.util.Vector;

public class UCS_T {

	Graph g;
	Vector<Node> f;
	int fn = 1;
	int en = 0;
	int total = 1;
	// Vector<String> e;

	public UCS_T() {
		g = new Graph();
		f = new Vector<Node>();
		f.addElement(g.init);
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
			if(f.size() > total)
				total = f.size();
			int a = 0;
			for (int i = 0; i < f.size(); i++) {
				if(f.elementAt(a).pathCost > f.elementAt(i).pathCost)
					a = i;
			}
			Node n = f.elementAt(a);
			en++;
			System.out.println(n.id + "        ");
			f.removeElementAt(a);
			if (g.Goal_Test(n.state)) {
				System.out.println();
				g.print(n);
				return;
			}
			n.next_node();
			for (int i = 0; i < n.next_nodes.size(); i++) {
				f.addElement(n.next_nodes.elementAt(i));
				fn++;
			}

		}
	}

	public static void main(String[] args) {
		UCS_T u = new UCS_T();
		u.search();
	}
}
