package p2;

//Class for defining nodes

import java.util.Vector;

public class Node {

	private int nodeNo;
	private int nodeColor;
	private Vector<Node> neighborNodes;
	private int k; //number of neighbors which have the same color as our node
	private int distance; //0 for not visited 1 for visited

	/////////////////////////////////////////////////////////////////////////////////////////

	public int getNodeNo() {
		return nodeNo;
	}

	public void setNodeNo(int nodeNo) {
		this.nodeNo = nodeNo;
	}

	public int getNodeColor() {
		return nodeColor;
	}

	public void setNodeColor(int nodeColor) {
		this.nodeColor = nodeColor;
	}

	public Vector<Node> getNeighborNodes() {
		return neighborNodes;
	}

	public void setNeighborNodes(Vector<Node> neighborNodes) {
		this.neighborNodes = neighborNodes;
	}

	public int getK() {
		return k;
	}

	public void setK(int k) {
		this.k = k;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int visited) {
		this.distance = visited;
	}

	/////////////////////////////////////////////////////////////////////////////////////////

	public Node(int nodeNo, int initialColor) {
		this.nodeNo = nodeNo;
		this.nodeColor = initialColor;
		neighborNodes = new Vector<Node>(5, 5);
		this.k = 0;
	}

	/////////////////////////////////////////////////////////////////////////////////////////

	public int calcK() {
		this.k = 0;
		for(int i = 0 ; i < neighborNodes.size(); i++) {
			if(neighborNodes.elementAt(i).getNodeColor() == this.getNodeColor()) {
				this.k++;
			}
		}
		return this.k;
	}
}
