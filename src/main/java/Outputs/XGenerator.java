package Outputs;

import Utils.Graph;
import Utils.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class XGenerator {
    private static XGenerator xGenerator = null;
    private StringBuilder stringBuilder;

    private XGenerator(){
        stringBuilder = new StringBuilder();
    }

    public static XGenerator getInstance() {
        if(xGenerator == null){
            xGenerator = new XGenerator();
        }
        return xGenerator;
    }

    public void generateVector(Graph graph, int i) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(new File("output/ist" + i + "/X.csv"));
        ArrayList<Node> nodeList = graph.getNodeList();

        stringBuilder.append("order;t\n");

        for(Node node : nodeList){
            if(node.getKey().contains("M")){
                break;
            }
            stringBuilder.append(node.getKey());
            stringBuilder.append(";");
            stringBuilder.append(node.getRealTime());
            stringBuilder.append("\n");
        }

        printWriter.write(stringBuilder.toString());
        printWriter.close();
        stringBuilder.setLength(0);
    }
}
