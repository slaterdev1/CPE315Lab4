
import java.util.HashMap;
import java.util.Map;

public class LabelTable {
    private static Map<String, Integer> labels = new HashMap<>();

    public static void putLabel(String label, Integer pcCount){
        labels.put(label, pcCount);
    }

    public static Integer getLabel(String label){
        return labels.get(label);
    }

}
