import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ApplicationManager {
    public static void start() {
        File rootDirectory = ConsoleReader.readRootDirectory();

        var fileReceiver = new FileReceiver(rootDirectory);

        List<Vertex> vertexList = new ArrayList<>();
        VertexBuilder builder = new VertexBuilder();
        for (var file : fileReceiver.getFiles()) {
            vertexList.add(builder.build(file, rootDirectory));
        }

        var graph = new Graph();
        graph.Initialize(vertexList);
        List<Vertex> sortedVertexList = graph.topologicalSort();

        if (graph.isCycleFlag()) {
            System.out.println("Error, file requires have cycle!\n");
            return;
        }

        String answer = FileConcatenator.concatenateFiles(sortedVertexList);
        if (!answer.equals("")) {
            AnswerWriter.consoleWriteAnswer(answer);
        }
    }
}

