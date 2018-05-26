package Utils;

import java.util.ArrayList;

public class Graph {
    private static Graph graph = null;
    private ArrayList<Node> nodeList;
    private ArrayList<Edge> edgeList;

    private Graph(){
        this.nodeList = new ArrayList<Node>();
        this.edgeList = new ArrayList<Edge>();
    }

    public static Graph getInstance(){
        if(graph == null){
            graph = new Graph();
        }
        return graph;
    }

    public ArrayList<Node> getNodeList() {
        return nodeList;
    }

    public void setNodeList(ArrayList<Node> nodeList) {
        this.nodeList = nodeList;
    }

    public ArrayList<Edge> getEdgeList() {
        return edgeList;
    }

    public void setEdgeList(ArrayList<Edge> edgeList) {
        this.edgeList = edgeList;
    }

    public void addNode(Node node){
        this.nodeList.add(node);
    }

    public void removeNode(Node node){
        this.nodeList.remove(node);
    }

    public void addEdge(Node src, Node dst, int weight){
        Edge newEdge = new Edge(src, dst, weight);
        this.edgeList.add(newEdge);
        //src.addAdj(dst);
        dst.addInc(newEdge);
    }

    public void removeEdge(Edge edge){
        this.edgeList.remove(edge);
        //edge.getSrc().removeAdj(edge.getDst());
        edge.getDst().removeInc(edge);
    }

    public int numNode(){
        return nodeList.size();
    }

    public int numEdge(){
        return edgeList.size();
    }

    public void printNodeList(){
        for(Node node : this.nodeList){
            System.out.println(node.getKey() + "\n");
        }
    }

    public void printEdgeList(){
        int count = 0;
        for(Edge edge : this.edgeList){
            if (count == 10){
                return;
            }
            System.out.println("(" + edge.getSrc().getKey() + ", " + edge.getDst().getKey() + ", "
                    + edge.getWeight() + ")\n");

            count++;
        }
    }
}
