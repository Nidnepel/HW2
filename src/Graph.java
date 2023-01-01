import java.util.*;

public class Graph {
    private final Map<String, Vertex> getVertexByName;
    private boolean cycleFlag;
    private String cycleStart;
    private String cycleEnd;
    private List<Vertex> vertexList;
    private Map<String, Integer> visitedVertex;
    private Map<String, String> parents;

    public Graph() {
        getVertexByName = new HashMap<>();
        vertexList = new ArrayList<>();
    }

    public String getCycleStart() {
        return cycleStart;
    }

    public String getCycleEnd() {
        return cycleEnd;
    }

    public String getParentName(String vertexName) {
        return parents.get(vertexName);
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

    public boolean topologicalSort(List<Vertex> sortingList) {
        visitedVertex = new HashMap<>();
        parents = new HashMap<>();
        for (var vertex : vertexList) {
            visitedVertex.put(vertex.getName(), 0);
        }
        for (var vertex : vertexList) {
            if (visitedVertex.get(vertex.getName()) == 0) {
                dfs(vertex.getName(), sortingList);
            }
            if (isCycleFlag()) {
                break;
            }
        }
        Collections.reverse(sortingList);
        return !isCycleFlag();
    }

    private void dfs(String vertexName, List<Vertex> result) {
        visitedVertex.replace(vertexName, 1);
        for (var requiredVertexName : getVertexByName.get(vertexName).getEdges()) {
            if (!getVertexByName.containsKey(requiredVertexName)) {
                System.out.printf("File %s contains require to file %s which not exist\n", vertexName, requiredVertexName);
                continue;
            }
            if (visitedVertex.get(requiredVertexName) == 0) {
                parents.put(requiredVertexName, vertexName);
                dfs(requiredVertexName, result);
            } else if (visitedVertex.get(requiredVertexName) == 1) {
                cycleFlag = true;
                cycleEnd = vertexName;
                cycleStart = requiredVertexName;
            }
        }
        visitedVertex.replace(vertexName, 2);
        result.add(getVertexByName.get(vertexName));
    }
}
