
public class JTypeInstruction implements Instruction {
    /***
     * Data class for the j type instructions:
     * j jal
     */

    private String ins;
    private String target;

    public JTypeInstruction(String ins, String insStr){
        this.ins = ins;
        target = insStr.substring(ins.length());
    }

    public String toBinary(){
        StringBuilder res = new StringBuilder();
        res.append(InstructionLookup.getOpCode(ins) + " ");
        res.append(DecimalToBinary.convertToBinary(
                LabelTable.getLabel(target).toString(),
                26));
        return res.toString();
    }

    @Override
    public void run() {
        switch(ins) {
            case "jal":
                RegisterFile.writeReg("$ra", InstructionMemory.pcCount);
                InstructionMemory.pcCount = LabelTable.getLabel(target);
                break;
            case "j":
                InstructionMemory.pcCount =  LabelTable.getLabel(target);
                break;
        }
    }

    @Override
    public String toString() {
        return "JTypeInstruction{" +
                "ins='" + ins + '\'' +
                ", target='" + target + '\'' +
                '}';
    }
}
