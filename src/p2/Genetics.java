package p2;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import java.util.Random;
import java.util.Vector;

public class Genetics {

    private double average[] = new double[50];
    private double best[] = new double[50];
    private double worst[] = new double[50];
    public Genetics() {
        System.out.println("Genetics Results: ");
        execGenetics();
        System.out.println();
    }

    /////////////////////////////////////////////////////////////////////////////////////////

    public void execGenetics() {
        Vector<Graph> population = generatePopulation();
        int numberOfGenerations = 50;
        for(int i = 0; i < numberOfGenerations; i++) {
            Vector<Graph> parents = tournamentSelection(population, 4);
            Vector<Graph> newChromosomes = createChromosomes(population, parents);
            population = mutation(newChromosomes);
            best[i] = bestChromosome(population);
            worst[i] = worstChromosome(population);
            average[i] = averageChromosome(population);
        }
        for(int i = 0; i < population.size(); i++) {
            System.out.println("Final graph = ");
            System.out.println("Number of node 0 = " + population.elementAt(i).getN0().getNodeColor());
            System.out.println("Number of node 1 = " + population.elementAt(i).getN1().getNodeColor());
            System.out.println("Number of node 2 = " + population.elementAt(i).getN2().getNodeColor());
            System.out.println("Number of node 3 = " + population.elementAt(i).getN3().getNodeColor());
            System.out.println("Number of node 4 = " + population.elementAt(i).getN4().getNodeColor());
            System.out.println("Number of node 5 = " + population.elementAt(i).getN5().getNodeColor());
            System.out.println("Number of node 6 = " + population.elementAt(i).getN6().getNodeColor());
            System.out.println("Number of node 7 = " + population.elementAt(i).getN7().getNodeColor());
            System.out.println("Number of node 8 = " + population.elementAt(i).getN8().getNodeColor());
            System.out.println("Number of node 9 = " + population.elementAt(i).getN9().getNodeColor());
            System.out.println("Number of node 10 = " + population.elementAt(i).getN10().getNodeColor());
            System.out.println();
        }
        System.out.println("Number of generations = " + numberOfGenerations);
        double axis[] = new double[numberOfGenerations];
        for(int i = 0; i < numberOfGenerations; i++) {
            axis[i] = i;
        }
        double[] yData = best;
        double[] xData = axis;

        // Create chart
        XYChart chart1 = QuickChart.getChart("Best Chart", "X", "Y", "y(x)", xData, yData);

        // Show it
        new SwingWrapper(chart1).displayChart();

        yData = worst;

        // Create chart
        XYChart chart2 = QuickChart.getChart("Worst Chart", "X", "Y", "y(x)", xData, yData);

        // Show it
        new SwingWrapper(chart2).displayChart();

        yData = average;

        // Create chart
        XYChart chart3 = QuickChart.getChart("Average Chart", "X", "Y", "y(x)", xData, yData);

        // Show it
        new SwingWrapper(chart3).displayChart();

    }

    /////////////////////////////////////////////////////////////////////////////////////////

    public double bestChromosome(Vector<Graph> generation) {
        double best = generation.elementAt(0).evalFunction(generation.elementAt(0));
        for(int i = 0;i < generation.size(); i++) {
            if(best > generation.elementAt(0).evalFunction(generation.elementAt(i))) {
                best = generation.elementAt(0).evalFunction(generation.elementAt(0));
            }
        }
        return best;
    }

    /////////////////////////////////////////////////////////////////////////////////////////

    public double worstChromosome(Vector<Graph> generation) {
        double worst = generation.elementAt(0).evalFunction(generation.elementAt(0));
        for(int i = 0;i < generation.size(); i++) {
            if(worst < generation.elementAt(0).evalFunction(generation.elementAt(i))) {
                worst = generation.elementAt(0).evalFunction(generation.elementAt(0));
            }
        }
        return worst;
    }

    /////////////////////////////////////////////////////////////////////////////////////////

    public double averageChromosome(Vector<Graph> generation) {
        double average = generation.elementAt(0).evalFunction(generation.elementAt(0));
        for(int i = 0;i < generation.size(); i++) {
            average = average + generation.elementAt(0).evalFunction(generation.elementAt(0));
        }
        return (average / generation.size());
    }

    /////////////////////////////////////////////////////////////////////////////////////////

    //function calculating fitness of our graph
//    public double fitnessFunction(Graph g) {
//        int SUM = 0;
//        for (int i = 0; i < g.getPopulation().size(); i++) {
//            Node current = g.getPopulation().elementAt(i);
//            for(int j = 0; j < current.getNeighborNodes().size(); j++) {
//                Node neighbor = current.getNeighborNodes().elementAt(j);
//                if(current.getNodeColor() == neighbor.getNodeColor()) {
//                    SUM++;
//                }
//            }
//        }
////        double fitness = (SUM / g.getPopulation().size());
//        return SUM;
//    }

    /////////////////////////////////////////////////////////////////////////////////////////

    public Vector<Graph> generatePopulation() {
        Vector<Graph> population = new Vector<Graph>(5, 5);
        for(int i = 0; i < 100; i++) {
            Graph g = new Graph();
            population.addElement(g);
        }
        return population;
    }

    /////////////////////////////////////////////////////////////////////////////////////////

    public Vector<Graph> tournamentSelection(Vector<Graph> population, int tournamentSize) {
        Vector<Graph> parents = new Vector<Graph>(5, 5);
        while(parents.size() < (population.size() / tournamentSize)) {
            Vector<Graph> random = new Vector<Graph>(5, 5);
            for(int i = 0; i < tournamentSize; i++) {
                random.addElement(population.elementAt(new Random().nextInt(population.size())));
            }
//            double best = fitnessFunction(random.elementAt(0));
            double best = random.elementAt(0).evalFunction(random.elementAt(0));
            Graph newParent = random.elementAt(0);
            for(int i = 0; i < random.size(); i++) {
//                double fitness = fitnessFunction(random.elementAt(i));
                double fitness = random.elementAt(i).evalFunction(random.elementAt(i));
                if(fitness > best) {
                    newParent = random.elementAt(i);
                    best = fitness;
                }
            }
            parents.addElement(newParent);
        }
        return parents;
    }

    /////////////////////////////////////////////////////////////////////////////////////////

    public Vector<Graph> createChromosomes(Vector<Graph> population, Vector<Graph> parents) {
        Vector<Graph> newChromosomes = new Vector<Graph>(5, 5);
        for(int i = 0; i < population.size(); i++) {
            Graph rndParent1 = parents.elementAt(new Random().nextInt(parents.size()));
            Graph rndParent2 = rndParent1;
            while(rndParent2 == rndParent1) {
                rndParent2 = parents.elementAt(new Random().nextInt(parents.size()));
            }
            Graph newChromosome = crossover(rndParent1, rndParent2);
            newChromosomes.addElement(newChromosome);
        }
        return newChromosomes;
    }

    /////////////////////////////////////////////////////////////////////////////////////////

    public Graph crossover(Graph g1, Graph g2) {
        Graph newChromosome = new Graph();
        for(int i = 0; i < 11; i++) {
            int rnd = new Random().nextInt(2);
            if(rnd == 0) {
                newChromosome.getPopulation().elementAt(i).setDistance(g1.getPopulation().elementAt(i).getDistance());
                newChromosome.getPopulation().elementAt(i).setNodeColor(g1.getPopulation().elementAt(i).getNodeColor());
            }
            else {
                newChromosome.getPopulation().elementAt(i).setDistance(g2.getPopulation().elementAt(i).getDistance());
                newChromosome.getPopulation().elementAt(i).setNodeColor(g2.getPopulation().elementAt(i).getNodeColor());
            }

        }
        for(int i = 0; i < newChromosome.getPopulation().size(); i++) {
            newChromosome.getPopulation().elementAt(i).calcK();
        }
        return newChromosome;
    }

    /////////////////////////////////////////////////////////////////////////////////////////

    public Vector<Graph> mutation(Vector<Graph> parents) {
        double populationLength = parents.size();
        double n = 11;
        double mutationRate = 0.03;
        double mutatedGenomes = populationLength * n * mutationRate;
        for(int i = 0; i < mutatedGenomes; i++) {
            Graph rndGraph = parents.elementAt(new Random().nextInt(parents.size()));
            Node rndNode = rndGraph.getPopulation().elementAt(new Random().nextInt(rndGraph.getPopulation().size()));
            rndNode.setNodeColor(new Random().nextInt(3));
        }
        return parents;
    }
}
