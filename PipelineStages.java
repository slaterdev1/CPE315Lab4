public class PipelineStages{
    private int pc;
    private String ifId;
    private String idExe;
    private String exeMem;
    private String memWb;

    public PipelineStages(int pc, String ifId, String idExe, String exeMem, String memWb){
        this.pc = pc;
        this.ifId = ifId;
        this.idExe = idExe;
        this.exeMem = exeMem;
        this.memWb = memWb;            
    

    }

    public static void printStages()
    {
        System.out.println("pc      if/id   id/exe  exe/mem mem/wb");
        System.out.println(pc + "\t" + ifId + " " + idExe + " " + exeMem + " " + memWeb);
    }

}
