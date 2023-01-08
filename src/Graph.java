import java.util.*;

/**
 * Graph class.
 */
public class Graph {
    /**
     * Map for get vertex by file name
     */
    private final Map<String, Vertex> getVertexByName;
    /**
     * Is graph have a cycle
     */
    private boolean cycleFlag;
    /**
     * Cycle start vertex name
     */
    private String cycleStart;
    /**
     * Cycle end vertex name
     */
    private String cycleEnd;
    /**
     * List of all vertex
     */
    private List<Vertex> vertexList;
    /**
     * map colors vertex
     */
    private Map<String, Integer> visitedVertex;
    /**
     * parents of vertex in dfs order
     */
    private Map<String, String> parents;

    /**
     * Constructor
     */
    public Graph() {
        getVertexByName = new HashMap<>();
        vertexList = new ArrayList<>();
    }

    /**
     * Getter
     *
     * @return Cycle start vertex
     */
    public String getCycleStart() {
        return cycleStart;
    }

    /**
     * Getter
     *
     * @return Cycle end vertex
     */
    public String getCycleEnd() {
        return cycleEnd;
    }

    /**
     * @return Parent name in dfs order.
     */
    public String getParentName(String vertexName) {
        return parents.get(vertexName);
    }

    /**
     * Getter
     *
     * @return Cycle flag
     */
    public boolean isCycleFlag() {
        return cycleFlag;
    }

    /**
     * Initialize function.
     *
     * @param vertexList - list of vertex.
     */
    public void initialize(List<Vertex> vertexList) {
        this.vertexList = vertexList;
        for (var vertex : vertexList) {
            getVertexByName.put(vertex.getName(), vertex);
        }
    }

    /**
     * Topological sort function.
     *
     * @param sortingList - List to which the answer is written.
     * @return Is graph have cycle boolean variable.
     */
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

    /**
     * dfs
     *
     * @param vertexName - name of current processing vertex
     * @param result     - list for save topological order.
     */
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
