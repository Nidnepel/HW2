import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextAnalyzer {
    public List<String> getRequiredFileNames(String data) {
        var requiredFileNames = new ArrayList<String>();
        Pattern pattern = Pattern.compile("require '.*'");
        Matcher matcher = pattern.matcher(data);
        while (matcher.find()) {
            String request = matcher.group();
            request = request.substring(9, request.length() - 1);
            request = request.replace('/', File.separatorChar);
            requiredFileNames.add(request);
        }
        return requiredFileNames;
    }
}
