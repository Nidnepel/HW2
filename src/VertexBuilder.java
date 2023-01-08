import java.io.File;
import java.util.List;

/**
 * Data processing class for Vertex.
 */
class VertexBuilder {
    /**
     * TextReader variable for read text from file.
     */
    private final TextReader reader;
    /**
     * TextAnalyzer variable for get requires from text.
     */
    private final TextAnalyzer analyzer;

    /**
     * Constructor
     */
    public VertexBuilder() {
        reader = new TextReader();
        analyzer = new TextAnalyzer();
    }

    /**
     * @param file          - Vertex file
     * @param rootDirectory - root directory
     * @return Vertex variable based on the received information.
     */
    public Vertex build(File file, File rootDirectory) {
        var rootPathLen = rootDirectory.getPath().length();
        String name = file.getPath().substring(rootPathLen + 1);
        String data = reader.getTextFromFile(file);
        List<String> edges = analyzer.getRequiredFileNames(data);
        return new Vertex(name, data, edges);
    }
}