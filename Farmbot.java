/**
 * info1103 - assignment 5
 * Andrew Houghton
 * ahou8288
 */

import java.util.*;

public class Farmbot {

    private static Farm farm;
    private static Scanner scan;
    public static int day;
    
    public static void main(String[] args) {
        
        init(args);
        process();
    }
    
    // Initialises program based on the command line arguments
    public static void init(String[] args) {
        
    	if (args.length!=2){
    		System.out.printf("Invalid command line arguments\nUsage: java Farmbot <width> <height>\n");
    		System.exit(0);
    	}
    	int width=3;
    	int height=3;
    	try{
	        width = Integer.parseInt(args[0]);
	        height = Integer.parseInt(args[1]);
    	}catch (NumberFormatException e){
    		System.out.printf("Invalid command line arguments\nUsage: java Farmbot <width> <height>\n");
    		System.exit(0);
    	}
    	
    	if (width<=0||height<=0){
    		System.out.printf("Invalid command line arguments\nUsage: java Farmbot <width> <height>\n");
    		System.exit(0);
    	}
    	
        day=1;

        // Attempt to parse command line arguments
        // ...
        farm=new Farm(height,width);

        // Initialise scanner to read from standard input
        scan = new Scanner(System.in);
    }
    
    // Displays command line usage and terminates the program
    public static void usage() {
        
        System.out.println("Invalid command line arguments");
        System.out.println("Usage: java Farmbot <width> <height>");
        System.exit(1);
    }
    
    // Process commands
    public static void process() {
        
        // Display the prompt
		System.out.printf("> ");

		// Read from standard input until EOF
		while (scan.hasNextLine()) {

			// Parse command
            String line = scan.nextLine().toLowerCase();
			String[] tokens = line.split(" ");
			String command = tokens[0];

			switch (command) {
				case "": break;
				case "bye": byeCommand(); break;
				case "help": helpCommand(); break;
				case "next": nextCommand(); break;
				case "status": statusCommand(); break;
				case "show": showCommand(tokens); break;
				case "water": waterCommand(tokens); break;
				case "spray": sprayCommand(tokens); break;
				case "infest": infestCommand(tokens); break;
				case "harvest": harvestCommand(tokens); break;
				default: System.out.println("Invalid command");
			}

			// Display the prompt
			System.out.printf("\n> ");
		}

		System.out.printf("bye\n");
    }
    
    // Displays bye and terminates the program
    public static void byeCommand() {
        
        System.out.println("bye");
        System.exit(0);
    }
    
    // Displays help message
    public static void helpCommand() {
        
        System.out.println("BYE");
		System.out.println("HELP");
        System.out.println();
        System.out.println("NEXT");
		System.out.println("STATUS");
		System.out.println("SHOW <attribute>");
		System.out.println();
		System.out.println("WATER <x1> <y1> [<x2> <y2>]");
		System.out.println("SPRAY <x1> <y1> [<x2> <y2>]");
		System.out.println("INFEST <x1> <y1> [<x2> <y2>]");
		System.out.println("HARVEST <x1> <y1> [<x2> <y2>]");
    }
    
    // Advances the farm to the next day
    public static void nextCommand() {
        day++;
        farm.grow();
        statusCommand();
    }
    
    // Displays the current day and amount of coins held
    public static void statusCommand() {
        
        // STATUS
        
        System.out.printf("Day %d - %d coins\n",day,farm.getCoins());
    }
    
    // Displays the particular state of the crops
    public static void showCommand(String[] tokens) {
        
        // SHOW <attribute>
        
        if (tokens.length != 2) {
            System.out.println("Invalid arguments");
            return;
        }
        
        String attribute = tokens[1];
        
        switch (attribute) {
            case "water":
                farm.showWater();
                break;
            case "height": 
            	farm.showHeight();
                break;
            case "sprayed":
            	farm.showSprayed();
                break;
            case "infested":
            	farm.showInfected();
                break;
            default:
                System.out.println("Invalid arguments");
        }
    }
    
    // Waters a crop plot or region of crop plots
    public static void waterCommand(String[] tokens) {
        if (tokens.length == 3) {
            int plot[] = parsePlot(tokens);
    
            if (plot == null) {
                System.out.println("Invalid arguments");
                return;
            }
    
            int x1=Integer.parseInt(tokens[1]);
            int y1=Integer.parseInt(tokens[2]);
            if(x1<1||y1<1||x1>farm.getHeight()||y1>farm.getWidth()){
            	System.out.println("Invalid arguments");
            	return;
            }
            farm.waterOne(x1,y1);
            farm.payCoins(10);
            
        } else if (tokens.length == 5) {
            int region[] = parseRegion(tokens);
            
            if (region == null) {
                System.out.println("Invalid arguments");
                return;
            }
            
            int x1=Integer.parseInt(tokens[1]);
            int y1=Integer.parseInt(tokens[2]);
            int x2=Integer.parseInt(tokens[3]);
            int y2=Integer.parseInt(tokens[4]);
            
            if(x1<1||y1<1||x1>farm.getHeight()||y1>farm.getWidth()){
                if(x2<x1||y2<y1||x2>farm.getHeight()||y2>farm.getWidth()){
	            	System.out.println("Invalid arguments");
	            	return;
                }
            }
            if (farm.payCoins((x2-x1+1)*(y2-y1+1)*10)){
            	for (int i=x1;i<=x2;i++){
                 	for (int j=y1;j<=y2;j++){
                         farm.waterOne(j,i);
                 	}
                 }
            } else {
            	System.out.println("Not enough coins");
            	return;
            }

        } else {
            System.out.println("Invalid arguments");
        }
    }
    
    // Sprays a crop plot or region of crop plots
    public static void sprayCommand(String[] tokens) {
        
        if (tokens.length == 3) {
            int plot[] = parsePlot(tokens);
    
            if (plot == null) {
                System.out.println("Invalid arguments");
                return;
            }
    
            int x1=Integer.parseInt(tokens[1]);
            int y1=Integer.parseInt(tokens[2]);
            if(x1<1||y1<1||x1>farm.getHeight()||y1>farm.getWidth()){
            	System.out.println("Invalid arguments");
            	return;
            }
            farm.sprayOne(x1,y1);
            farm.payCoins(20);
            
        } else if (tokens.length == 5) {
            int region[] = parseRegion(tokens);
            
            if (region == null) {
                System.out.println("Invalid arguments");
                return;
            }
            
            int x1=Integer.parseInt(tokens[1]);
            int y1=Integer.parseInt(tokens[2]);
            int x2=Integer.parseInt(tokens[3]);
            int y2=Integer.parseInt(tokens[4]);
            
            if(x1<1||y1<1||x1>farm.getHeight()||y1>farm.getWidth()){
                if(x2<x1||y2<y1||x2>farm.getHeight()||y2>farm.getWidth()){
	            	System.out.println("Invalid arguments");
	            	return;
                }
            }
            if (farm.payCoins((x2-x1+1)*(y2-y1+1)*20)){
            	for (int i=x1;i<=x2;i++){
                 	for (int j=y1;j<=y2;j++){
                         farm.sprayOne(j,i);
                 	}
                 }
            } else {
            	System.out.println("Not enough coins");
            	return;
            }

        } else {
            System.out.println("Invalid arguments");
        }
    }
    
    // Infests a crop or region of crop plots
    public static void infestCommand(String[] tokens) {
                if (tokens.length == 3) {
            int plot[] = parsePlot(tokens);
    
            if (plot == null) {
                System.out.println("Invalid arguments");
                return;
            }
    
            int x1=Integer.parseInt(tokens[1]);
            int y1=Integer.parseInt(tokens[2]);
            if(x1<1||y1<1||x1>farm.getHeight()||y1>farm.getWidth()){
            	System.out.println("Invalid arguments");
            	return;
            }
            
            if (!farm.crops[x1-1][y1-1].isInfested()){
                farm.payCoins(-20);
            }
            farm.infestOne(x1,y1);
            
        } else if (tokens.length == 5) {
            int region[] = parseRegion(tokens);
            
            if (region == null) {
                System.out.println("Invalid arguments");
                return;
            }
            
            int x1=Integer.parseInt(tokens[1]);
            int y1=Integer.parseInt(tokens[2]);
            int x2=Integer.parseInt(tokens[3]);
            int y2=Integer.parseInt(tokens[4]);
            
            if(x1<1||y1<1||x1>farm.getHeight()||y1>farm.getWidth()){
                if(x2<x1||y2<y1||x2>farm.getHeight()||y2>farm.getWidth()){
	            	System.out.println("Invalid arguments");
	            	return;
                }
            }
            
            int alreadyInfested=0;
        	for (int i=x1;i<=x2;i++){
             	for (int j=y1;j<=y2;j++){
             	    if (farm.crops[i-1][j-1].isInfested()){
             	        alreadyInfested++;
             	    }
                     farm.infestOne(j,i);
             	}
             }
             farm.payCoins(((x2-x1+1)*(y2-y1+1)-alreadyInfested)*-20);

        } else {
            System.out.println("Invalid arguments");
        }
    } 
    
    // Harvests a crop plot or region of crop plots
    public static void harvestCommand(String[] tokens) {
        if (tokens.length == 3) {
            int plot[] = parsePlot(tokens);
    
            if (plot == null) {
                System.out.println("Invalid arguments");
                return;
            }
    
            int x1=Integer.parseInt(tokens[1]);
            int y1=Integer.parseInt(tokens[2]);
            if(x1<1||y1<1||x1>farm.getHeight()||y1>farm.getWidth()){
            	System.out.println("Invalid arguments");
            	return;
            }
            if (farm.crops[x1-1][y1-1].getHeight()>=1&&!farm.crops[x1-1][y1-1].isInfested()){
                farm.payCoins(-10*farm.crops[x1-1][y1-1].getHeight());
                farm.harvestOne(x1,y1);
            }
            
        } else if (tokens.length == 5) {
            int region[] = parseRegion(tokens);
            
            if (region == null) {
                System.out.println("Invalid arguments");
                return;
            }
            
            int x1=Integer.parseInt(tokens[1]);
            int y1=Integer.parseInt(tokens[2]);
            int x2=Integer.parseInt(tokens[3]);
            int y2=Integer.parseInt(tokens[4]);
            
            if(x1<1||y1<1||x1>farm.getHeight()||y1>farm.getWidth()){
                if(x2<x1||y2<y1||x2>farm.getHeight()||y2>farm.getWidth()){
	            	System.out.println("Invalid arguments");
	            	return;
                }
            }
            int amountHarvest=0;
            
        	for (int i=x1;i<=x2;i++){
             	for (int j=y1;j<=y2;j++){
             	    if (farm.crops[i-1][j-1].getHeight()>=1&&!farm.crops[i-1][j-1].isInfested()){
                     amountHarvest+=10*farm.crops[i-1][j-1].getHeight();
                     farm.harvestOne(j,i);
             	    }
             	}
             }
            farm.payCoins(-amountHarvest);

        } else {
            System.out.println("Invalid arguments");
        }
    }
    
    // Attempts to parse plot coordinate
    public static int[] parsePlot(String[] tokens) {
        
        int[] plot = new int[2];

        try {
            plot[0] = Integer.parseInt(tokens[1]);
            plot[1] = Integer.parseInt(tokens[2]);
        } catch (Exception e) {
            return null;    
        }
        
        return plot;
    }
    
    // Attempts to parse region coordinates
    public static int[] parseRegion(String[] tokens) {
        
        int[] region = new int[4];

        try {
            region[0] = Integer.parseInt(tokens[1]);
            region[1] = Integer.parseInt(tokens[2]);
            region[2] = Integer.parseInt(tokens[3]);
            region[3] = Integer.parseInt(tokens[4]);
        } catch (Exception e) {
            return null;    
        }
        
        return region;
    }
}
