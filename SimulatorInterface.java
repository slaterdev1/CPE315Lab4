import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class SimulatorInterface
{
    private String[] args;

    public SimulatorInterface(String[] args){
        this.args = args;
    }
    
    public void parseCommandUser()
    {
        FileParser fp = new FileParser(args);
        fp.run();
        Scanner cmd_o = new Scanner(System.in);
        System.out.print("mips> ");
        String cmdLine = cmd_o.nextLine();  // Read user input
        String [] interactiveArgs = cmdLine.split(" ");
        CommandHandler handler = new CommandHandler(interactiveArgs[0], null, null);

    
        if(interactiveArgs.length == 3)
            handler = new CommandHandler(interactiveArgs[0], interactiveArgs[1],interactiveArgs[2]);
        
        if(interactiveArgs.length == 2)
            handler = new CommandHandler(interactiveArgs[0], interactiveArgs[1],null);

        
        while(!(handler.cmd).equals("q"))
        {
            handler.HandleCommand();
            System.out.print("mips> ");
            cmdLine = cmd_o.nextLine();  // Read user input
            interactiveArgs = cmdLine.split(" ");
            handler.cmd = interactiveArgs[0];
            if(interactiveArgs.length >= 2)
                handler.arg0 = interactiveArgs[1];
 
            if(interactiveArgs.length == 3)
                handler.arg1 = interactiveArgs[2];

        }

    }
    public void parseCommandScript()
    {
        FileParser fp = new FileParser(args);
        fp.run();
        File file = new File(args[1]);
        Scanner cmd_o = null;
        try {
            cmd_o = new Scanner(file);
        } catch (FileNotFoundException e){
            System.out.println("Error creating scanner: " + e);
            System.exit(-1);
        }
 

        System.out.print("mips> ");
        String cmdLine = cmd_o.nextLine();  // Read user input
        System.out.println(cmdLine);
        String [] interactiveArgs = cmdLine.split(" ");
        CommandHandler handler = new CommandHandler(interactiveArgs[0], null, null);

    
        if(interactiveArgs.length == 3)
            handler = new CommandHandler(interactiveArgs[0], interactiveArgs[1],interactiveArgs[2]);
        
        if(interactiveArgs.length == 2)
            handler = new CommandHandler(interactiveArgs[0], interactiveArgs[1],null);

        
        while(!(handler.cmd).equals("q"))
        {
            handler.HandleCommand();
            System.out.print("mips> ");
            cmdLine = cmd_o.nextLine();  // Read user input
            System.out.println(cmdLine);
            interactiveArgs = cmdLine.split(" ");
            handler.cmd = interactiveArgs[0];
            if(interactiveArgs.length >= 2)
                handler.arg0 = interactiveArgs[1];
 
            if(interactiveArgs.length == 3)
                handler.arg1 = interactiveArgs[2];

        }
    }

}
