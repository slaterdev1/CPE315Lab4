import java.util.ArrayList;
import java.util.List;

public class InstructionMemory {

    public static List<Instruction> instructions = new ArrayList<>();
    public static int pcCount = 0;
    public static int totalCycles = 0;

    public static void loadInstructions(List<Instruction> ins, int cycles){
        instructions = ins;
        totalCycles = cycles;
        

    }

    public static int getTotalInstructions() {
        return instructions.size();
    }

    public static boolean hasNextInstruction() {
        return pcCount < instructions.size();
    }

    public static Instruction getNextInstruction(PipelineStages ps){
        if(hasNextInstruction()){
            Instruction retIns = instructions.get(pcCount);
            if(ps.getStallFlag()) {
                System.out.println("stalling at pcCount: " + pcCount);
                ps.setStallFlag(false);
            } else {
                pcCount += 1;
            }
            return retIns;
        } else return new InvalidInstruction("EOF");
    }

    public static void printPC(){
        System.out.print("pc = " + pcCount);

    }

}
