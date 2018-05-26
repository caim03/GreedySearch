package Utils;

import java.util.ArrayList;

/* Reverse Incident list based Node */

public class Node implements Comparable{
    private String key;
    private ArrayList<Edge> incidents;
    private int commissionTime;
    private Node prec;
    private int realTime;
    private boolean cancelled;
    private int weight;

    public Node(String key){
        this.key = key;
        this.incidents = new ArrayList<Edge>();
        this.realTime = 0;
    }

    public Node(String key, int commissionTime){
        this.key = key;
        this.commissionTime = commissionTime;
        //this.adj = new ArrayList<Node>();
        this.incidents = new ArrayList<Edge>();
        this.realTime = 0;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getCommissionTime() {
        return commissionTime;
    }

    public void setCommissionTime(int commissionTime) {
        this.commissionTime = commissionTime;
    }

    public ArrayList<Edge> getIncidents() {
        return incidents;
    }

    public void setIncidents(ArrayList<Edge> incidents) {
        this.incidents = incidents;
    }

    public Node getPrec() {
        return prec;
    }

    public void setPrec(Node prec) {
        this.prec = prec;
    }

    public int getRealTime() {
        return realTime;
    }

    public void setRealTime(int realTime) {
        this.realTime = realTime;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void addInc(Edge edge){
        this.incidents.add(edge);
    }

    public void removeInc(Edge edge){
        this.incidents.remove(edge);
    }

    public boolean isIncident(Edge edge){
        return this.incidents.contains(edge);
    }

    public Tuple getNearest(boolean M, ArrayList<Node> free){
        int min = 999999;
        Node currNode = null;
        for(Edge edge : this.incidents){
            if(M){
                if(edge.getWeight() < min && edge.getSrc().getKey().contains("M") && free.contains(edge.getSrc())){
                    currNode = edge.getSrc();
                    min = edge.getWeight();
                }
            }
            else{
                if(edge.getWeight() < min && !edge.getSrc().getKey().contains("M") && free.contains(edge.getSrc())){
                    currNode = edge.getSrc();
                    min = edge.getWeight();
                }
            }

        }

        return new Tuple(currNode, min);
    }

    public int compareTo(Object o) {
        int compareTime = ((Node) o).getCommissionTime();
        return this.commissionTime - compareTime;
    }
}
