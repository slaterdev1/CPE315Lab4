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


    public PipelineStages(){
        this.pc = 0;
        for(int i = 0; i < 4; i+=1){
            stages[i] = new InvalidInstruction("empty");
        }
    }

    public void setPc(int newpc){
        pc = newpc;
    }

    public Instruction step(){
        //check each stage for their special cases
        // at ex/mem, if load, add a stall instruction
        boolean tempStallFlag = false;
        for(int stage = MEM_WB; stage >= IF_ID; stage -= 1){
            switch (stage){
                case MEM_WB:
                    stages[MEM_WB] = stages[EXE_MEM];
                    break;
                case EXE_MEM:
                    handleEXE_MEM();
                    break;
                case ID_EXE:
                    handleID_EXE();
                    break;
                case IF_ID:
                    return handleIF_ID();
                default:
                    System.out.println("Stage is in a bad place");
                    break;
            }
        }
        return new InvalidInstruction("NO_OP");
    }

    private void handleEXE_MEM() {
        // check for stall case here, because by the time
        // id_exe comes around it'll be too late for the simulator
        // to keep its pcCount accurate
        // ... ^^ this was dumb, but I don't see a reason to change it
        stages[EXE_MEM] = stages[ID_EXE];
        boolean lwInID_EXE = stages[ID_EXE].getIns().equals("lw");
        boolean IFDependsOnIDLW = stages[IF_ID].dependsOn(stages[ID_EXE].getDestReg());
        stallFlag = lwInID_EXE && IFDependsOnIDLW;
    }

    private void handleID_EXE(){

        if(stages[ID_EXE] instanceof JTypeInstruction
                || stages[ID_EXE] instanceof JrTypeInstruction )
            squashFlag = true;
        stages[ID_EXE].run();
        stages[ID_EXE] = stallFlag ? new InvalidInstruction("stall") : stages[IF_ID];

    }

    private Instruction handleIF_ID(){
        // set it so that we have a no_op for the runner to run if we need to stall
        Instruction newIns = new InvalidInstruction("NO_OP");
        if(!stallFlag){
            newIns = InstructionMemory.getNextInstruction(this);
            pc = InstructionMemory.pcCount;
            stages[IF_ID] = newIns;
        }
        if(squashFlag) {
            newIns = new InvalidInstruction("squash");
            stages[IF_ID] = newIns;
        }
        return newIns;
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
}
