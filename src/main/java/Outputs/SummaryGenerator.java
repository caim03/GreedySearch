package Outputs;

import Utils.Graph;
import Utils.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SummaryGenerator {
    private static SummaryGenerator summary = null;
    private StringBuilder stringBuilder;

    private SummaryGenerator(){
        stringBuilder = new StringBuilder();
    }

    public static SummaryGenerator getInstance(){
        if(summary == null){
            summary = new SummaryGenerator();
        }
        return summary;
    }

    public void generateSummary(ArrayList<Integer> objective, int i, long time) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(new File("output/ist" + i + "/summary.csv"));

        stringBuilder.append("Objective: " + objective.get(0) + "\n");
        stringBuilder.append("Z1: " + objective.get(1) + "\n");
        stringBuilder.append("Z2: " + objective.get(2) + "\n");
        stringBuilder.append("Z3: " + objective.get(3) + "\n");
        stringBuilder.append("W: " + objective.get(4) + "\n");
        stringBuilder.append("Algorithm Time: " + time);

        printWriter.write(stringBuilder.toString());
        printWriter.close();
        stringBuilder.setLength(0);
    }
}
