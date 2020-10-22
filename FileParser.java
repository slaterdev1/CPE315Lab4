
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.lang.*;

public class FileParser {

    private String[] args;

    public FileParser(String[] args){
        this.args = args;
    }

    private List<String> rawInstructions = new ArrayList<>();
    public List<Instruction> parsedInstructions = new ArrayList<>();

    public static boolean isBlank(String str)
    {
        return str.trim().isEmpty();
    }

    public void run(){
        Scanner sc = getScanner(args);
        int pcCount = 0;
        // first pass
        while (sc.hasNextLine()){
            String rawLine = sc.nextLine();
            // get everything before the comment
            String strippedLine = stripComments(rawLine.replaceAll("\\s+",""));
            // returns the line without the label
            strippedLine = processLabels(pcCount, strippedLine);
            if(!isBlank(strippedLine)) {
                rawInstructions.add(strippedLine);
                pcCount += 1;
            }
        }
        pcCount = 0;
        for(String rawLine : rawInstructions){
            // get everything before the comment
            if(!isBlank(rawLine)){
                Instruction ins = InstructionFactory.createInstruction(pcCount, rawLine);
                parsedInstructions.add(ins);
                pcCount += 1;
            }
        }
        InstructionMemory.loadInstructions(parsedInstructions);
    }

    private String processLabels(int pcCount, String inString){
        int colonIndex = inString.indexOf(":");
        if(colonIndex == -1){
            return inString;
        } else {
            String label = inString.substring(0, colonIndex);
            LabelTable.putLabel(label, pcCount);
            String outString = inString.substring(colonIndex + 1);
            return outString;
        }
    }

    private String stripComments(String strippedLine){
        int commIndex = strippedLine.indexOf("#");
        if (commIndex == -1){
            return strippedLine;
        } else {
            return strippedLine.substring(0, commIndex);
        }
    }

    private Scanner getScanner(String[] args){
        if(args.length == 0){
            System.out.println("Usage: java lab3 filename.asm [scriptFile]");
            System.exit(-1);
        }
        File file = new File(args[0]);
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e){
            System.out.println("Error creating scanner: " + e);
            System.exit(-1);
        }
        return sc;
    }
}
