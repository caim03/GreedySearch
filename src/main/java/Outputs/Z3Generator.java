package Outputs;

import Utils.Graph;
import Utils.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Z3Generator {
    private static Z3Generator z3Generator = null;
    private StringBuilder stringBuilder;

    private Z3Generator(){
        stringBuilder = new StringBuilder();
    }

    public static Z3Generator getInstance() {
        if(z3Generator == null){
            z3Generator = new Z3Generator();
        }
        return z3Generator;
    }

    public void generateVector(Graph graph, int i) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(new File("output/ist" + i + "/Z3.csv"));

        ArrayList<Node> nodeList = graph.getNodeList();

        stringBuilder.append("order;t\n");

        for(Node node : nodeList){
            if(node.getKey().contains("M")){
                break;
            }
            stringBuilder.append(node.getKey());
            stringBuilder.append(";");

            if(node.getWeight() == 3){
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
