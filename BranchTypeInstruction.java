
public class BranchTypeInstruction extends PipelineInstruction{
    /***
     * Data class for the Branch type instructions:
     * bne beq
     *
     */

    private String ins;
    private String rs;
    private String rt;
    private String label;
    private Integer offset;
    private int CPI = 4;

    public BranchTypeInstruction(String ins, String insStr, Integer pcCount){
        this.ins = ins;
        String[] tokens = insStr.split(",");
        rs = tokens[0].substring(ins.length());
        rt = tokens[1];
        label = tokens[2];
        // add one to pcCount to point to instruction after this one
        offset = LabelTable.getLabel(label) - (pcCount + 1);
        squashCount = 3;
    }

    public String toBinary(){
        StringBuilder res = new StringBuilder();
        res.append(InstructionLookup.getOpCode(ins) + " ");
        res.append(InstructionLookup.getReg(rs) + " ");
        res.append(InstructionLookup.getReg(rt) + " ");
        res.append(DecimalToBinary.convertToBinary(
                offset.toString(),
                16));
        return res.toString();
    }

    @Override
    public void run() {
        //System.out.println("Running branch instruction!");
        switch(ins){
            case "bne":
                if(!RegisterFile.getReg(rs).equals(RegisterFile.getReg(rt))){
                    InstructionMemory.pcCount += offset;
                }
                break;
            case "beq":
                if(RegisterFile.getReg(rs).equals(RegisterFile.getReg(rt))) {
                    InstructionMemory.pcCount += offset;
                }
                break;
        }
    }

    @Override
    public boolean dependsOn(String register) {
        return register.equals(rs) || register.equals(rt);
    }

    @Override
    public String getIns() {
        return ins;
    }

    @Override
    public String toString() {
        return "BranchTypeInstruction{" +
                "ins='" + ins + '\'' +
                ", rs='" + rs + '\'' +
                ", rt='" + rt + '\'' +
                ", label='" + label + '\'' +
                ", offset=" + offset +
                '}';
    }

    public int getCPI(){}
}
