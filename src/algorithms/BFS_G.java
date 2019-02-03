package algorithms;

import java.util.Vector;

public class BFS_G {

	Graph g;
	Vector<Node> f;
	Vector<String> e;
	int fn = 1;
	int en = 0;
	int total = 1;
	
	public BFS_G() {
		g = new Graph();
		f = new Vector<Node>();
		e = new Vector<String>();
		f.addElement(g.init);
	}
	
	public void search(){
		if(g.Goal_Test(f.elementAt(0).state)){
			System.out.println();
			g.print(f.elementAt(0));
			System.out.println("created nodes: " + fn);
			System.out.println("expanded nodes: "+ en);
			System.out.println("max memory: "+ total);
			return;
		}
		System.out.println("visited nodes:");
		while(f.size() > 0){
			if(f.size()+e.size() > total)
				total = f.size()+e.size();
			Node n = f.elementAt(0);
			e.addElement(n.id);
			en++;
			System.out.print(n.id+"        ");
			f.removeElementAt(0);
			n.next_node();
			for(int i = 0; i < n.next_nodes.size(); i++){
				if(g.Goal_Test(n.next_nodes.elementAt(i).state)){
					System.out.println();
					g.print(n.next_nodes.elementAt(i));
					System.out.println("created nodes: " + fn);
					System.out.println("expanded nodes: "+ en);
					System.out.println("max memory: "+ total);
					return;
				}
			}
			for(int i = 0; i < n.next_nodes.size(); i++){
				if(!e.contains(n.next_nodes.elementAt(i).id)){
					f.addElement(n.next_nodes.elementAt(i));
					fn++;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		BFS_G b = new BFS_G();
		b.search();
	}
}
