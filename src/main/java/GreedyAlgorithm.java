import Config.Config;
import Utils.Node;
import Utils.Tuple;

import java.util.ArrayList;

public class GreedyAlgorithm {
    private static GreedyAlgorithm greedyAlgorithm = null;
    private int objective;
    private int deelayCounter1;
    private int deelayCounter2;
    private int deelayCounter3;
    private int cancelledCounter;

    private GreedyAlgorithm(){
        objective = 0;
        deelayCounter1 = 0;
        deelayCounter2 = 0;
        deelayCounter3 = 0;
        cancelledCounter = 0;
    }

    public static GreedyAlgorithm getInstance(){
        if(greedyAlgorithm == null){
            greedyAlgorithm = new GreedyAlgorithm();
        }
        return greedyAlgorithm;
    }

    public ArrayList<Integer> greedy(ArrayList<Node> sortedDelivery, ArrayList<Node> moovers){
        ArrayList<Node> completedOrder = new ArrayList<Node>();
        boolean firstPhase = true;
        ArrayList<Node> temp = new ArrayList<Node>();

        for(Node node : sortedDelivery){
            //deliveryCount++;

            if(moovers.size() == 0){
                firstPhase = false;
                for(Node t : temp){
                    moovers.add(t);
                }
                temp.clear();
                //deliveryCount = 0;
            }

            Tuple tuple = node.getNearest(firstPhase, moovers);
            Node nearest = tuple.getNode();
            int weight = tuple.getWeight();

            if(setRealTime(node, nearest, weight)){
                node.setCancelled(true);
            }
            else{
                node.setCancelled(false);
                node.setPrec(nearest);
                moovers.remove(nearest);
                temp.add(node);
                //moovers.add(node);
            }

            completedOrder.add(node);
            updateObjective(node);
        }

        ArrayList<Integer> values = new ArrayList<Integer>();
        values.add(objective);
        values.add(deelayCounter1);
        values.add(deelayCounter2);
        values.add(deelayCounter3);
        values.add(cancelledCounter);

        return values;
    }

    /* Return true only if order must be cancelled */
    private boolean setRealTime(Node node, Node nearest, int weight){
        if(nearest.getRealTime() + weight < node.getCommissionTime() - Config.threshold){
            node.setRealTime(node.getCommissionTime() - Config.threshold);
            return false;
        }
        else if (nearest.getRealTime() + weight > node.getCommissionTime() + 60){
            node.setRealTime(nearest.getRealTime() + weight);
            return true;
        }
        else{
            node.setRealTime(nearest.getRealTime() + weight);
            return false;
        }
    }

    private void updateObjective(Node node){
        int diff = node.getRealTime() - node.getCommissionTime();

        if(diff >= 3 && diff <= 6){
            objective = objective + Config.deelay1;
            deelayCounter1++;
            node.setWeight(1);

        }
        else if(diff > 6 && diff <= 9){
            objective = objective + Config.deelay2;
            deelayCounter2++;
            node.setWeight(2);
        }
        else if(diff > 9 && diff <= 12){
            objective = objective + Config.deelay3;
            deelayCounter3++;
            node.setWeight(3);
        }
        else if(diff > 12){
            objective = objective + Config.cancelled;
            cancelledCounter++;
            node.setWeight(10);
        }
    }

    public void clear(){
        this.objective = 0;
        this.deelayCounter1 = 0;
        this.deelayCounter2 = 0;
        this.deelayCounter3 = 0;
        this.cancelledCounter = 0;
    }
}
