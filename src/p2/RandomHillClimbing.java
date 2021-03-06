package p2;

import java.util.Random;
import java.util.Vector;

public class RandomHillClimbing {

    private int visited_nodes = 0;
    private int expanded_nodes = 0;
    private int max_memory = 0;
    public RandomHillClimbing() {
        Graph localMaxima = execRandomHillClimbing();
        System.out.println("Evaluation function = " + evalFunction(localMaxima));
        System.out.println("Visited nodes = " + this.visited_nodes);
        System.out.println("Expanded nodes = " + this.expanded_nodes);
        this.max_memory = this.expanded_nodes + this.visited_nodes;
        System.out.println("Max memory =  " + this.max_memory);
        System.out.println("RHC Results = ");
        System.out.println("Number of node 0 = " + localMaxima.getN0().getNodeColor());
        System.out.println("Number of node 1 = " + localMaxima.getN1().getNodeColor());
        System.out.println("Number of node 2 = " + localMaxima.getN2().getNodeColor());
        System.out.println("Number of node 3 = " + localMaxima.getN3().getNodeColor());
        System.out.println("Number of node 4 = " + localMaxima.getN4().getNodeColor());
        System.out.println("Number of node 5 = " + localMaxima.getN5().getNodeColor());
        System.out.println("Number of node 6 = " + localMaxima.getN6().getNodeColor());
        System.out.println("Number of node 7 = " + localMaxima.getN7().getNodeColor());
        System.out.println("Number of node 8 = " + localMaxima.getN8().getNodeColor());
        System.out.println("Number of node 9 = " + localMaxima.getN9().getNodeColor());
        System.out.println("Number of node 10 = " + localMaxima.getN10().getNodeColor());
        System.out.println();

    }

    /////////////////////////////////////////////////////////////////////////////////////////

    public Graph execRandomHillClimbing() {
        Graph g = new Graph();
        boolean localMaxima = false;
        while(!localMaxima) {
            Vector<Graph> next = new Vector<Graph>(5, 5);
            //generate next states
            for(int i = 0; i < g.getPopulation().size(); i++) {
                Graph nextState = createNewState(g, i);
                next.addElement(nextState);
            }
            for(int i = 0; i < next.size(); i++) {
                localMaxima = true;
                if(evalFunction(g) < evalFunction(next.elementAt(i))) {
                    localMaxima = false;
                }
                this.visited_nodes++;
            }
            if(!localMaxima) {
                g = next.elementAt(new Random().nextInt(next.size()));
                this.expanded_nodes++;
            }
        }
        return g;
    }

    /////////////////////////////////////////////////////////////////////////////////////////

    public Graph createNewState(Graph g, int nodeNumber) {
        Graph result = new Graph();
        for(int i = 0; i < g.getPopulation().size(); i++) {
            result.getPopulation().elementAt(i).setNodeColor(g.getPopulation().elementAt(i).getNodeColor());
            result.getPopulation().elementAt(i).setNodeNo(g.getPopulation().elementAt(i).getNodeNo());
            result.getPopulation().elementAt(i).setK(g.getPopulation().elementAt(i).getK());
            result.getPopulation().elementAt(i).setDistance(g.getPopulation().elementAt(i).getDistance());

        }
        if(result.getPopulation().elementAt(nodeNumber).getNodeColor() == 0) {
            int p = new Random().nextInt(2);
            if(p == 0) {
                result.getPopulation().elementAt(nodeNumber).setNodeColor(1);
            }
            else {
                result.getPopulation().elementAt(nodeNumber).setNodeColor(2);
            }
        }
        else if(result.getPopulation().elementAt(nodeNumber).getNodeColor() == 1) {
            int p = new Random().nextInt(2);
            if(p == 0) {
                result.getPopulation().elementAt(nodeNumber).setNodeColor(0);
            }
            else {
                result.getPopulation().elementAt(nodeNumber).setNodeColor(2);
            }
        }
        else if(result.getPopulation().elementAt(nodeNumber).getNodeColor() == 2) {
            int p = new Random().nextInt(2);
            if(p == 0) {
                result.getPopulation().elementAt(nodeNumber).setNodeColor(0);
            }
            else {
                result.getPopulation().elementAt(nodeNumber).setNodeColor(1);
            }
        }
        return result;
    }

    /////////////////////////////////////////////////////////////////////////////////////////

    public int evalFunction(Graph g) {
        int eval = 0;
        for(int i = 0; i < g.getPopulation().size(); i++) {
            eval = eval - g.getPopulation().elementAt(i).calcK();
        }
        return eval;
    }
}
