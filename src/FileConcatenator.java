import java.util.List;

public class FileConcatenator {
    public static String concatenateFiles(List<Vertex> vertexList) {
        StringBuilder result = new StringBuilder();
        for (var vertex : vertexList) {
            result.append(vertex.getData());
        }
        return result.toString();
    }
}
