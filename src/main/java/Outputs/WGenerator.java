package Outputs;

import Utils.Graph;
import Utils.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class WGenerator {
    private static WGenerator wGenerator = null;
    private StringBuilder stringBuilder;

    private WGenerator(){
        stringBuilder = new StringBuilder();
    }

    public static WGenerator getInstance(){
        if(wGenerator == null){
            wGenerator = new WGenerator();
        }
        return wGenerator;
    }

    public void generateVector(Graph graph, int i) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(new File("output/ist" + i + "/W.csv"));

        ArrayList<Node> nodeList = graph.getNodeList();

        stringBuilder.append("order;t\n");

        for(Node node : nodeList){
            if(node.getKey().contains("M")){
                break;
            }

            stringBuilder.append(node.getKey());
            stringBuilder.append(";");

            if(node.isCancelled()){
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
