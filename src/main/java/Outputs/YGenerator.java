package Outputs;


import Utils.Graph;
import Utils.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class YGenerator {
    private static YGenerator yGenerator = null;
    private StringBuilder stringBuilder;

    private YGenerator(){
        stringBuilder = new StringBuilder();
    }

    public static YGenerator getInstance(){
        if(yGenerator == null){
            yGenerator = new YGenerator();
        }
        return yGenerator;
    }

    public void generateMatrix(Graph graph, int i) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(new File("output/ist" + i + "/Y.csv"));

        ArrayList<Node> nodeList = graph.getNodeList();
        stringBuilder.append("D_bar");
        stringBuilder.append((";"));

        for(Node node : nodeList){
            if(node.getKey().contains("M")){
                break;
            }
            stringBuilder.append(node.getKey());
            stringBuilder.append(";");
        }
        stringBuilder.append("\n");

        for(Node node : nodeList){
            stringBuilder.append(node.getKey());
            stringBuilder.append(";");
            for(Node col : nodeList){
                if(col.getKey().contains("M")){
                    break;
                }
                else if(col.getPrec() == null){
                    stringBuilder.append(0);
                    stringBuilder.append(";");
                }
                else if(col.getPrec().equals(node)){
                    stringBuilder.append(1);
                    stringBuilder.append(";");
                }
                else{
                    stringBuilder.append(0);
                    stringBuilder.append(";");
                }
            }
            stringBuilder.append("\n");
        }

        printWriter.write(stringBuilder.toString());
        printWriter.close();
        stringBuilder.setLength(0);
    }
}
