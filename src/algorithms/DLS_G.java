package algorithms;

import java.util.Vector;

public class DLS_G {

	Graph g;
	Vector<Node> f;
	Vector<String> e;
	int en = 0;
	int fn = 1;
	int total = 1;
	
	public DLS_G() {
		g = new Graph();
		f = new Vector<Node>();
		e = new Vector<String>();
		f.addElement(g.init);
	}
	
	public void search(int l){
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
			Node n = f.elementAt(f.size()-1);
			if(n.length < l){
				e.addElement(n.id);
				en++;
			}
			System.out.print(n.id+"        ");
			f.removeElementAt(f.size()-1);
			if(n.length<l){
				n.next_node();
				for(int i = 0; i < n.next_nodes.size(); i++){
					if(g.Goal_Test(n.next_nodes.elementAt(i).state)){
						System.out.println();
						g.print(n.next_nodes.elementAt(i));
						return;
					}
				}
				
				for(int i = 0; i < n.next_nodes.size(); i++){
					if(!e.contains(n.next_nodes.elementAt(i).id)){
						f.addElement(n.next_nodes.elementAt(i));
						fn++;
					}
				}
//			}
			}else{
				for(int i = 0; i < n.length; i++)
				for(int j = 0; j < e.size(); j++){
					if(n.visited_nodes.elementAt(i).id.equals(e.elementAt(j))){
						e.removeElementAt(j);
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		DLS_G d = new DLS_G();
		d.search(8);
	}
}
