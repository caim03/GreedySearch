package Utils;


public class Tuple {
    private Node node;
    private int weight;

    public Tuple(Node node, int weight){
        this.node = node;
        this.weight = weight;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
