
public class MemTypeInstruction extends PipelineInstruction{
    /***
     * Data class for the Mem type instructions:
     * sw lw
     *
     */

    private String ins;
    private String rs;
    private String rt;
    private String offset;

    public MemTypeInstruction(String ins, String insStr){
        this.ins = ins;
        String[] tokens = insStr.split(",");
        rt = tokens[0].substring(ins.length());
        String[] subTokens = tokens[1].split("\\(");
        offset = subTokens[0];
        rs = subTokens[1].substring(0, subTokens[1].length() - 1);
        if(ins.equals("lw")){
            this.destReg = rt;
        }
    }

    public String toBinary(){
        StringBuilder res = new StringBuilder();
        res.append(InstructionLookup.getOpCode(ins) +" ");
        res.append(InstructionLookup.getReg(rs) + " ");
        res.append(InstructionLookup.getReg(rt) + " ");
        res.append(DecimalToBinary.convertToBinary(offset, 16));
        return res.toString();
    }


    @Override
    public String getIns() {
        return ins;
    }

    @Override
    public boolean dependsOn(String register) {
        return register.equals(rs);
    }

    @Override
    public void run() {
        //System.out.println("Running mem type instruction!");
        switch(ins){
            case "sw":
                int val = RegisterFile.getReg(rt);
                int dest = Integer.parseInt(offset) + RegisterFile.getReg(rs);
                MemoryFile.writeMem(dest, val);
                break;
            case "lw":
                int src = Integer.parseInt(offset) + RegisterFile.getReg(rs);
                RegisterFile.writeReg(rt, MemoryFile.getMem(src));
                break;
        }
    }

    public String getRS(){
        return this.rs;
    }

    public String getRT(){
        return this.rt;
    }

    @Override
    public String toString() {
        return "MemTypeInstruction{" +
                "ins='" + ins + '\'' +
                ", rs='" + rs + '\'' +
                ", rt='" + rt + '\'' +
                ", offset='" + offset + '\'' +
                '}';
    }
    
    public int getCPI(){
        return 1; //slater do plz :)
    }
}
