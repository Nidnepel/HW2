import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Program component manipulation class.
 */
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
        graph.initialize(vertexList);
        List<Vertex> sortedVertexList = new ArrayList<>();

        if (!graph.topologicalSort(sortedVertexList)) {
            // If graph have cycle.
            System.out.println("Error, file requires have cycle!\n");
            for (var i = graph.getCycleEnd(); !Objects.equals(i, graph.getCycleStart()); i = graph.getParentName(i)) {
                System.out.println(i);
            }
            System.out.println(graph.getCycleStart());
        } else {
            String answer = FileConcatenator.concatenateFiles(sortedVertexList);
            if (!answer.equals("")) {
                AnswerWriter.consoleWriteAnswer(answer);
            }
        }
    }
}

