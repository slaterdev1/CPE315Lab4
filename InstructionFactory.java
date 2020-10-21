
import java.util.Arrays;
import java.util.List;
import java.lang.*;

public class InstructionFactory {

    private static final List<String> rTypes = Arrays.asList("and", "or", "add", "sub", "slt");
    private static final List<String> iTypes = Arrays.asList("addi");
    private static final List<String> jumpTypes = Arrays.asList("j", "jal");
    private static final List<String> jrType = Arrays.asList("jr");
    private static final List<String> branchTypes = Arrays.asList("beq", "bne");
    private static final List<String> shiftTypes = Arrays.asList("sll");
    private static final List<String> memTypes = Arrays.asList("sw", "lw");
    
    public static boolean isBlank(String str)
    {
        return str.trim().isEmpty();
    }

    public static Instruction createInstruction(Integer pcCount, String instructionLine){
        String ins = getInsFromLine(instructionLine);
        if(rTypes.contains(ins))
            return new RTypeInstruction(ins, instructionLine);
        if(iTypes.contains(ins))
            return new ITypeInstruction(ins, instructionLine);
        if(jumpTypes.contains(ins))
            return new JTypeInstruction(ins, instructionLine);
        if(jrType.contains(ins))
            return new JrTypeInstruction(ins, instructionLine);
        if(branchTypes.contains(ins))
            return new BranchTypeInstruction(ins, instructionLine, pcCount);
        if(shiftTypes.contains(ins))
            return new ShiftTypeInstruction(ins, instructionLine);
        if(memTypes.contains(ins))
            return new MemTypeInstruction(ins, instructionLine);
        return new InvalidInstruction(ins);
    }

    public static String getInsFromLine(String instructionLine){
        if(instructionLine == null || isBlank(instructionLine)){
            // return null to get to invalid instruction case
            return null;
        }
        if(instructionLine.charAt(0) == 'j') {
            if(instructionLine.indexOf("jal") == 0){
                return "jal";
            }
            if(instructionLine.indexOf("jr") == 0){
                return "jr";
            }
            return "j";
        } else {
            // pretty sure this works for all cases if it's not j type
            // please do check me though
            String stripped = instructionLine.replaceAll("\\s+","");
            String[] tokens = stripped.split(",");
            String[] insTokens = tokens[0].split("\\$");
            return insTokens[0];
        }
    }
}
