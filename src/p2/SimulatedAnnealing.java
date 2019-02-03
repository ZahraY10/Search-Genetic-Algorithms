package p2;


import java.util.Random;

public class SimulatedAnnealing {

    private int visited_nodes = 0;
    private int expanded_nodes = 0;
    private int max_memory = 0;
    public SimulatedAnnealing() {
        Graph localMaxima = execSimulatedAnnealing();
        System.out.println("Evaluation function = " + evalFunction(localMaxima));
        System.out.println("Visited nodes = " + this.visited_nodes);
        System.out.println("Expanded nodes = " + this.expanded_nodes);
        this.max_memory = this.expanded_nodes + this.visited_nodes;
        System.out.println("Max memory =  " + this.max_memory);
        System.out.println("SA Results = ");
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

    public Graph execSimulatedAnnealing() {
        Graph initialGraph = new Graph();
        int old_cost = evalFunction(initialGraph);
        double T = 1.0;
        double T_min = 0.0001;
        double alpha = 0.9;
        while(T > T_min) {
            int i = 1;
            while(i < 100) {
                Graph newGraph = createNewState(initialGraph, new Random().nextInt(initialGraph.getPopulation().size()));
                this.visited_nodes++;
                int new_cost = evalFunction(newGraph);
                double ap = acceptance_probability(old_cost, new_cost, T);
                if(ap > Math.random()) {
                    initialGraph = newGraph;
                    old_cost = new_cost;
                    this.expanded_nodes++;
                }
                i++;
            }
            T = T * alpha;
        }
        return initialGraph;
    }

    /////////////////////////////////////////////////////////////////////////////////////////

    public double acceptance_probability(int old_cost, int new_cost, double T) {
        if(new_cost < old_cost) {
            return 1.0;
        }
        else {
            double a = Math.exp((new_cost - old_cost) / T); //a is acceptance probability
            return a;
        }
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

