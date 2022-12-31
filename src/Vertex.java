import java.util.List;

public class Vertex {
    final private String data;
    final private List<String> edges;
    final private String name;

    Vertex(String name, String data, List<String> edges) {
        this.name = name;
        this.data = data;
        this.edges = edges;
    }

    public String getData() {
        return data;
    }

    public List<String> getEdges() {
        return edges;
    }

    public String getName() {
        return name;
    }

}


