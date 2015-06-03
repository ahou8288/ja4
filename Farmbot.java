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
        
        int width = 3;
        int height = 3;
        day=0;

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
    }
    
    // Displays the current day and amount of coins held
    public static void statusCommand() {
        
        // STATUS
        
        System.out.println("TODO");
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

        // WATER <x1> <y1> [<x2> <y2>]
        
        if (tokens.length == 3) {
            int plot[] = parsePlot(tokens);
    
            if (plot == null) {
                System.out.println("Invalid arguments");
                return;
            }
    
            // Water crop plot
            // - x1, y1 maps to plot[0], plot[1]

            System.out.println("TODO");

        } else if (tokens.length == 5) {
            int region[] = parseRegion(tokens);
            
            if (region == null) {
                System.out.println("Invalid arguments");
                return;
            }
            
            // Water region of crop plots
            // - x1, y1 maps to region[0], region[1]
            // - x2, y2 maps to region[2], region[3]

            System.out.println("TODO");

        } else {
            System.out.println("Invalid arguments");
        }
    }
    
    // Sprays a crop plot or region of crop plots
    public static void sprayCommand(String[] tokens) {
        
        // SPRAY <x1> <y1> [<x2> <y2>]
        
        System.out.println("TODO");
    }
    
    // Infests a crop or region of crop plots
    public static void infestCommand(String[] tokens) {
        
        // INFEST <x1> <y1> [<x2> <y2>]
        
        System.out.println("TODO");
    } 
    
    // Harvests a crop plot or region of crop plots
    public static void harvestCommand(String[] tokens) {
        
        // HARVEST <x1> <y1> [<x2> <y2>]
        
        System.out.println("TODO");
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
