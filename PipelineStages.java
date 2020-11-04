import java.util.function.IntBinaryOperator;

public class PipelineStages{
    private int pc;
    private Instruction[] stages = new Instruction[4];
    private boolean stallFlag = false; //stall flag for reading and use by CommandRunner
    private boolean squashFlag = false; //stall flag for reading and use by CommandRunner
    private final int IF_ID = 0;
    private final int ID_EXE = 1;
    private final int EXE_MEM = 2;
    private final int MEM_WB = 3;
    private int cycles = 0;
    private int instructions = 0;



    public PipelineStages(){
        this.pc = 0;
        for(int i = 0; i < 4; i+=1){
            stages[i] = new InvalidInstruction("empty");
        }
    }

    public void setPc(int newpc){
        pc = newpc;
    }

    public void step(){
        //check each stage for their special cases
        // at ex/mem, if load, add a stall instruction
        boolean tempStallFlag = false;
        Instruction newIns = InstructionMemory.getInstruction(pc);
        for(int stage = MEM_WB; stage >= IF_ID; stage -= 1){
            switch (stage){
                case MEM_WB:
                    handleMEM_WB();
                    break;
                case EXE_MEM:
                    handleEXE_MEM();
                    break;
                case ID_EXE:
                    handleID_EXE();
                    break;
                case IF_ID:
                    handleIF_ID(newIns);
                    break;
                default:
                    System.out.println("Stage is in a bad place");
                    break;
            }
        }
    }

    private void handleMEM_WB(){
        stages[MEM_WB] = stages[EXE_MEM];
        if(stages[MEM_WB].evaluateBranch()) {
            stages[IF_ID] = new InvalidInstruction("squash");
            stages[ID_EXE] = new InvalidInstruction("squash");
            //cycles += 2;
            instructions -= 2;
            squashFlag = true;
            int target = stages[MEM_WB].getTargetPcCount();
            if(target == -1) System.out.println("trying to jump to a non-branch ins!");
            pc = target;
        }
    }

    private void handleEXE_MEM() {
        // check for stall case here, because by the time
        // id_exe comes around it'll be too late for the simulator
        // to keep its pcCount accurate
        // ... ^^ this was dumb, but I don't see a reason to change it
        // ........ ^^ This was ALL dumb, but keeping it here through the refactor
        //             for posterity
        stages[EXE_MEM] = stages[ID_EXE];
        boolean lwInID_EXE = stages[ID_EXE].getIns().equals("lw");
        boolean IFDependsOnIDLW = stages[IF_ID].dependsOn(stages[ID_EXE].getRT());
        stallFlag = lwInID_EXE && IFDependsOnIDLW;
    }

    private void handleID_EXE(){

        if(stages[IF_ID] instanceof JTypeInstruction
                || stages[IF_ID] instanceof JrTypeInstruction ){

            squashFlag = true;
            pc = stages[IF_ID].getTargetPcCount();

        }
        if(stallFlag){
            stages[ID_EXE] = new InvalidInstruction("stall");
        } else {
            stages[ID_EXE] = stages[IF_ID];
            if(!squashFlag)
                pc += 1;
        }

    }

    private void handleIF_ID(Instruction newIns){
        if(!stallFlag){
            stages[IF_ID] = newIns;
            System.out.println("Adding ins " + newIns.getIns() + " ins count " + instructions);
            instructions += 1;
            
        } else {
            //cycles += 1;
        }
        if(squashFlag) {
            stages[IF_ID] = new InvalidInstruction("squash");
            //cycles += 1;
            squashFlag = false;
            instructions -= 1;
        }
        cycles += 1;
        System.out.println("Adding cycles " + cycles);
       

    }

    public boolean caughtUpWithSim(){
        return InstructionMemory.pcCount == pc;
    }

    public void printStages()
    {
        System.out.println(" ");
        // sorry I deleted the stuff you already had here D:
        System.out.println("pc      if/id   id/exe  exe/mem mem/wb");
        //temporary
        System.out.print(pc + "      ");
        for(Instruction ins : stages){
            System.out.print(ins.getIns() + "    ");
        }
        System.out.println(" ");
        System.out.println(" ");
        //System.out.println(this.pc + "\t" + this.ifId + " " + this.idExe + " " + this.exeMem + " " + this.memWb);
    }

    public boolean getStallFlag() {
        return stallFlag;
    }

    public void setStallFlag(boolean stallFlag) {
        this.stallFlag = stallFlag;
    }

    public int getCycles() {
        return cycles;
    }

    public int getInstructions() {
        return instructions;
    }
}
