import java.util.List;

class CommandHandler
{
    public String cmd = null;
    public String arg0 = null;
    public String arg1 = null;
    private List<String> args = null;
    private SimulatorInterface sim;

    public CommandHandler(SimulatorInterface sim)
    {
        this.sim = sim;
    }

    public void setArgs(List<String> args){
        this.args = args;
        this.cmd = args.get(0);
        if(args.size() >= 2)
            arg0 = args.get(1);
        if(args.size() >= 3)
            arg1 = args.get(2);

    }

    public static void hCommand()
    {
        /* Prints help prompt*/

        System.out.print("h = show help\n" +
            "d = dump register state\n" +
            "s = single step through the program (i.e. execute 1 instruction and stop)\n" +
            "s num = step through num instructions of the program\n" +
            "r = run until the program ends\n" +
            "m num1 num2 = display data memory from location num1 to num2\n" +
            "c = clear all registers, memory, and the program counter to 0\n"+
            "q = exit the program\n"
            );

    }

    public void dCommand()
    {
        System.out.println(); 
        InstructionMemory.printPC();
        RegisterFile.printReg();
    }

    public void sCommand()
    {
        int count  = 0;

        if(arg0 != null){
            while(count < Integer.parseInt(arg0))
            {
                if(InstructionMemory.hasNextInstruction()){
                    CommandRunner.step();
                } else {
                    cmd = "q";
                }
                count++;
            }
            System.out.println("\t\t" + arg0 + " instruction(s) executed");

        }
        if(arg0 == null)
        {
            count+=1;
            if(InstructionMemory.hasNextInstruction()){
                CommandRunner.step();
                System.out.println("\t\t1 instruction(s) executed");
            } else {
                cmd = "q";
            }

        }
        
    }

    public void rCommand()
    {
        try {
            while(InstructionMemory.hasNextInstruction()){
                CommandRunner.step();
            }
        } catch (Exception e){
            System.out.println("caught exception: " + e);
        }
        //MemoryFile.debugPrintMem();
        //RegisterFile.printReg();
        //cmd = "q";
    }

    public void mCommand()
    {
        MemoryFile.printMem(Integer.parseInt(arg0),Integer.parseInt(arg1));
    }
 
    public void cCommand()
    {
        System.out.println("\t\tSimulator reset\n");
        RegisterFile.clearRegisters();
        MemoryFile.clearMem();
        InstructionMemory.pcCount = 0;
    }


    public void handleCommand(){
        if(cmd.charAt(0) == 'h')
        {
            hCommand();
        }
        if(cmd.charAt(0) == 'd')
        {
            dCommand();
        }
        if(cmd.charAt(0) == 's')
        {
            sCommand();
        }
        if(cmd.charAt(0) == 'r')
        {
            rCommand();
        }
        if(cmd.charAt(0) == 'm')
        {
            mCommand();
        }
        if(cmd.charAt(0) == 'c')
        {
            cCommand();
        }
        if(cmd.charAt(0) == 'q')
        {
            sim.running = false;
        }
    }
}
