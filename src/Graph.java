import java.util.*;

public class Graph {
    private final Map<String, Vertex> getVertexByName;
    private boolean cycleFlag;
    private List<Vertex> vertexList;
    private List<Vertex> sortingList;
    private Map<String, Integer> visitedVertex;

    public Graph() {
        getVertexByName = new HashMap<>();
        vertexList = new ArrayList<>();
    }

    public boolean isCycleFlag() {
        return cycleFlag;
    }

    public void Initialize(List<Vertex> vertexList) {
        this.vertexList = vertexList;
        for (var vertex : vertexList) {
            getVertexByName.put(vertex.getName(), vertex);
        }
    }


    public List<Vertex> topologicalSort() {
        visitedVertex = new HashMap<>();
        sortingList = new ArrayList<>();
        for (var vertex : vertexList) {
            visitedVertex.put(vertex.getName(), 0);
        }
        for (var vertex : vertexList) {
            if (visitedVertex.get(vertex.getName()) == 0) {
                dfs(vertex.getName());
            }
        }
        Collections.reverse(sortingList);
        return sortingList;
    }

    private void dfs(String vertexName) {
        visitedVertex.replace(vertexName, 1);
        for (var requiredVertexName : getVertexByName.get(vertexName).getEdges()) {
            if (!getVertexByName.containsKey(requiredVertexName)) {
                System.out.printf("File %s contains require to file %s which not exist\n", vertexName, requiredVertexName);
                continue;
            }
            if (visitedVertex.get(requiredVertexName) == 0) {
                dfs(requiredVertexName);
            } else if (visitedVertex.get(requiredVertexName) == 1) {
                cycleFlag = true;
            }
        }
        visitedVertex.replace(vertexName, 2);
        sortingList.add(getVertexByName.get(vertexName));
    }
}
