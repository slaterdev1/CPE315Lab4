public class lab3
{
    public static void main(String[] args)
    {
        SimulatorInterface cmd = new SimulatorInterface(args);
        if(args.length == 1)
            cmd.parseCommandUser(); 
        if(args.length == 2)
            cmd.parseCommandScript();   
    }
}
