package Outputs;

import Utils.Graph;
import Utils.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Z2Generator {
    private static Z2Generator z2Generator = null;
    private StringBuilder stringBuilder;

    private Z2Generator(){
        stringBuilder = new StringBuilder();
    }

    public static Z2Generator getInstance(){
        if(z2Generator == null){
            z2Generator = new Z2Generator();
        }
        return z2Generator;
    }

    public void generateVector(Graph graph, int i) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(new File("output/ist" + i + "/Z2.csv"));

        ArrayList<Node> nodeList = graph.getNodeList();

        stringBuilder.append("order;t\n");

        for(Node node : nodeList){
            if(node.getKey().contains("M")){
                break;
            }
            stringBuilder.append(node.getKey());
            stringBuilder.append(";");

            if(node.getWeight() == 2){
                stringBuilder.append(1);
            }
            else{
                stringBuilder.append(0);
            }

            stringBuilder.append("\n");
        }

        printWriter.write(stringBuilder.toString());
        printWriter.close();
        stringBuilder.setLength(0);
    }
}
