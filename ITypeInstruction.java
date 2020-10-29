
public class ITypeInstruction implements Instruction {
    /***
     * Data class for the I type instructions:
     * addi
     *
     */

    private String ins;
    private String rs;
    private String rt;
    private String imm;
    private int CPI = 1;

    public ITypeInstruction(String ins, String insStr){
        this.ins = ins;
        String stripped = insStr.replaceAll("\\s+","");
        String[] tokens = stripped.split(",");
        rt = tokens[0].substring(ins.length());
        rs = tokens[1];
        imm = tokens[2];
    }

    public String toBinary(){
        StringBuilder res = new StringBuilder();
        res.append(InstructionLookup.getOpCode(ins) + " ");
        res.append(InstructionLookup.getReg(rs) + " ");
        res.append(InstructionLookup.getReg(rt) + " ");
        res.append(DecimalToBinary.convertToBinary(imm, 16));
        return res.toString();
    }

    @Override
    public void run() {
        int reg = RegisterFile.getReg(rs);
        RegisterFile.writeReg(rt, reg + Integer.parseInt(imm));

    }

    @Override
    public String toString() {
        return "ITypeInstruction{" +
                "ins='" + ins + '\'' +
                ", rs='" + rs + '\'' +
                ", rt='" + rt + '\'' +
                ", imm='" + imm + '\'' +
                '}';
    }
        
    public int getCPI(){
        return this.CPI;
    }
}
