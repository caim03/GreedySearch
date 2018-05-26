package Outputs;

import Utils.Graph;
import Utils.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Z1Generator {
    private static Z1Generator z1Generator = null;
    private StringBuilder stringBuilder;

    private Z1Generator(){
        stringBuilder = new StringBuilder();
    }

    public static Z1Generator getInstance(){
        if(z1Generator == null){
            z1Generator = new Z1Generator();
        }
        return z1Generator;
    }

    public void generateVector(Graph graph, int i) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(new File("output/ist" + i + "/Z1.csv"));

        ArrayList<Node> nodeList = graph.getNodeList();

        stringBuilder.append("order;t\n");

        for(Node node : nodeList){
            if(node.getKey().contains("M")){
                break;
            }
            stringBuilder.append(node.getKey());
            stringBuilder.append(";");

            if(node.getWeight() == 1){
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
