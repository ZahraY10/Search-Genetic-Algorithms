package p1;

//class for Breadth-First-Search(Tree)
import java.util.Vector;

public class BFS_T {

	Graph g;
	Vector<Node> f;
//	Vector<String> e;
	int fn = 1;
	int total = 1;
	int en = 0;
	
	public BFS_T() {
		g = new Graph();
		f = new Vector<Node>();
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
			if(f.size() > total)
				total = f.size();
			Node n = f.elementAt(0);
			en++;
			System.out.print(n.id + "        ");
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
				f.addElement(n.next_nodes.elementAt(i));
				fn++;
			}
		}
	}
	
	public static void main(String[] args) {
		BFS_T b = new BFS_T();
		b.search();
	}
}
