import java.io.File;
import java.util.List;

class VertexBuilder {
    private final TextReader reader;
    private final TextAnalyzer analyzer;

    public VertexBuilder() {
        reader = new TextReader();
        analyzer = new TextAnalyzer();
    }

    public Vertex build(File file, File rootDirectory) {
        var rootPathLen = rootDirectory.getPath().length();
        String name = file.getPath().substring(rootPathLen + 1);
        String data = reader.getTextFromFile(file);
        List<String> edges = analyzer.getRequiredFileNames(data);
        return new Vertex(name, data, edges);
    }
}