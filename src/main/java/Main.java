import Config.Config;
import Outputs.*;
import Utils.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        CsvReader csvReader = CsvReader.getCsvReader();
        Graph graph = Graph.getInstance();
        GreedyAlgorithm greedyAlgorithm = GreedyAlgorithm.getInstance();

        YGenerator yGenerator = YGenerator.getInstance();
        XGenerator xGenerator = XGenerator.getInstance();
        WGenerator wGenerator = WGenerator.getInstance();
        Z1Generator z1Generator = Z1Generator.getInstance();
        Z2Generator z2Generator = Z2Generator.getInstance();
        Z3Generator z3Generator = Z3Generator.getInstance();

        ArrayList<String[]> deliveryTimes;
        ArrayList<String[]> distanceMatrix;
        ArrayList<Node> sortedDelivery = new ArrayList<Node>();
        ArrayList<Node> moovers = new ArrayList<Node>();
        ArrayList<Integer> objective;

        for(int i = 2; i <= 35; i++){
            long startProgram = System.nanoTime();

            deliveryTimes = csvReader.readCsv(Config.deliveryTime + i + ".csv", ",", Config.numMoovers[i]);
            distanceMatrix = csvReader.readCsv(Config.distanceMatrix + i + ".csv", ",", Config.numMoovers[i]);

            initializeGraph(graph, distanceMatrix, deliveryTimes, sortedDelivery, moovers);

            long startAlg = System.nanoTime();
            objective = greedyAlgorithm.greedy(sortedDelivery, moovers);
            long timeAlgElapsed = (System.nanoTime() - startAlg)/1000000; // millisecond

            new File("output/ist" + i).mkdirs();

            yGenerator.generateMatrix(graph, i);
            xGenerator.generateVector(graph, i);
            wGenerator.generateVector(graph, i);
            z1Generator.generateVector(graph, i);
            z2Generator.generateVector(graph, i);
            z3Generator.generateVector(graph, i);

            long timeProgElapsed = (System.nanoTime() - startProgram)/1000000; // millisecond

            System.out.println("Objective: " + objective.get(0));
            System.out.println("Z1: " + objective.get(1));
            System.out.println("Z2: " + objective.get(2));
            System.out.println("Z3: " + objective.get(3));
            System.out.println("W: " + objective.get(4));

            System.out.println("Algorithm time: " + timeAlgElapsed);
            System.out.println("Program time: " + timeProgElapsed);

            System.out.println("\n");

            clear(graph, deliveryTimes, distanceMatrix, sortedDelivery, moovers, objective);
            greedyAlgorithm.clear();
        }
    }

    private static void initializeGraph(Graph graph, ArrayList<String[]> distanceMatrix,
                                        ArrayList<String[]> deliveryTimes, ArrayList<Node> sortedDelivery,
                                        ArrayList<Node> moovers){
        distanceMatrix.remove(0);
        deliveryTimes.remove(0);
        int size = distanceMatrix.size();

        for(int i = 0; i < size; i++){
            if(distanceMatrix.get(i)[0].contains("M")){
                Node node = new Node(distanceMatrix.get(i)[0]);
                graph.addNode(node);
                moovers.add(node);
            }
            else{
                Node node = new Node(distanceMatrix.get(i)[0], (int)((double)Double.valueOf(deliveryTimes.get(i)[1])));
                graph.addNode(node);
                sortedDelivery.add(node);
            }
        }

        Collections.sort(sortedDelivery);

        ArrayList<Node> nodeList = graph.getNodeList();

        for(int i = 0; i < size; i++){
            for(int j = 1; j < distanceMatrix.get(i).length; j++){
                int weight = (int)((double)Double.valueOf(distanceMatrix.get(i)[j]));
                if(weight != 0){
                    graph.addEdge(nodeList.get(i), nodeList.get(j-1), weight);
                }
            }
        }
    }

    /* Clear all objects */
    private static void clear(Graph graph, ArrayList<String[]> delivery, ArrayList<String[]> distance,
                       ArrayList<Node> sorted, ArrayList<Node> moovers, ArrayList<Integer> objective){

        graph.getNodeList().clear();
        graph.getEdgeList().clear();
        delivery.clear();
        distance.clear();
        sorted.clear();
        moovers.clear();
        objective.clear();
    }
}
