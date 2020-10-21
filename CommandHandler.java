class CommandHandler
{
    public String cmd;
    public String arg0;
    public String arg1;

    public CommandHandler(String cmd, String arg0, String arg1)
    {
        this.cmd = cmd;
        this.arg0 = arg0;
        this.arg1 = arg1;
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
            if(InstructionMemory.hasNextInstruction()){
                    CommandRunner.step();
                } else {
                    cmd = "q";
                }
                count++;
                System.out.println("\t\t1 instruction(s) executed");

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


    public void HandleCommand(){
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
            System.exit(0);   
        }
    }
}
