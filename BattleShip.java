import java.util.Scanner;
import java.util.InputMismatchException;

public class BattleShip {

    // ANSI escape codes for colors
    public static final String RESET = "\u001B[0m";//reset colour back to default
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";

    public static String[][] playerViewMap = new String[10][10];
    public static String[][] oceanMap = new String[10][10];
    public static int playerShips = 5;
    public static int computerShips = 5;
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        boolean playAgain = true;  // To control whether to start a new game or exit

        while (playAgain) {
            // Reset the game state
            playerShips = 5;
            computerShips = 5;
            initializeMaps();

            // Welcome message and initial note
            System.out.println("**** Welcome to Battle Ships game ****");
            System.out.println("Right now, the sea is empty");

            printPlayerViewMap();  // Display the map at the start of the game
            deployPlayerShips();  // Player deploys ships
            deployComputerShip(oceanMap);  // Computer deploys ships
            printPlayerViewMap();  // Display the map after player deploys ships

            // Main game loop
            while (playerShips > 0 && computerShips > 0) {
                // Display ship counts for both player and computer
                System.out.println(GREEN + "Player Ships Remaining: " + playerShips + RESET + " | " +
                        RED + "Computer Ships Remaining: " + computerShips + RESET);
                System.out.println("---------------------------------------------------------------");

                playerTurn();  // Player's turn
                computerTurn(oceanMap);  // Computer's turn
                printPlayerViewMap();  // Display the updated map after each turn
            }

            // Game over messages
            if (playerShips == 0) {
                System.out.println(RED + "Game Over! Computer Wins!" + RESET);
            } else {
                System.out.println(GREEN + "Congratulations! You Win!" + RESET);
            }

            // Ask the player if they want to play again or exit
            playAgain = askToPlayAgain();
        }

        System.out.println("Thanks for playing! Goodbye.");
    }

    // Initialize both the player's view and the full ocean map
    public static void initializeMaps() {
        for (int i = 0; i < playerViewMap.length; i++) {
            for (int j = 0; j < playerViewMap[i].length; j++) {
                playerViewMap[i][j] = " ";  // Water in the player's view map
                oceanMap[i][j] = " ";  // Water in the ocean map
            }
        }
    }

    // Deploy the player's ships on the map
    public static void deployPlayerShips() {
        for (int i = 1; i <= playerShips; ) {
            int x = -1;
            int y = -1;

            // Get valid input for X coordinate
            while (true) {
                try {
                    System.out.print("Enter X coordinate for your ship: ");
                    x = input.nextInt();

                    // Ensure valid range for x
                    if (x >= 0 && x < 10) {
                        break; // valid input, exit loop
                    } else {
                        System.out.println("Invalid coordinate. Please enter a valid coordinate within the 10x10 map.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Error: Only integers are allowed.");
                    input.next(); // Clear invalid input
                }
            }

            // Get valid input for Y coordinate
            while (true) {
                try {
                    System.out.print("Enter Y coordinate for your ship: ");
                    y = input.nextInt();

                    // Ensure valid range for y
                    if (y >= 0 && y < 10) {
                        break; // valid input, exit loop
                    } else {
                        System.out.println("Invalid coordinate. Please enter a valid coordinate within the 10x10 map.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Error: Only integers are allowed.");
                    input.next(); // Clear invalid input
                }
            }

            // Now check if the coordinate is already occupied
            if (oceanMap[x][y].equals("1")) {
                System.out.println("You have placed a ship here before.");
            } else if (oceanMap[x][y].equals(" ")) {
                oceanMap[x][y] = "1";  // Store player's ship as "1"
                playerViewMap[x][y] = "1";  // Mark ship in playerViewMap but conceal as "@"
                i++;  // Move to the next ship
            }
        }
        printPlayerViewMap();
    }

    // Deploy the computer's ships on the map
    public static void deployComputerShip(String[][] oceanMap) {
        System.out.println("\n" + RED + "Computer is deploying ships" + RESET);
        for (int i = 1; i <= computerShips; ) {
            int x = (int) (Math.random() * 10);
            int y = (int) (Math.random() * 10);

            if (oceanMap[x][y].equals(" ")) {
                oceanMap[x][y] = "2";  // Store computer's ship as "2"
                System.out.println(i + ". ship DEPLOYED");  // Print deployment message
                i++;  // Move to next ship
            }
        }
        System.out.println(RED + "Computer has deployed its ships." + RESET);
    }

    // Print the player's view of the map, concealing ships
    public static void printPlayerViewMap() {
        System.out.println("\nCurrent Player's View Map:");

        // Print top number lane
        System.out.print("    ");
        for (int i = 0; i < playerViewMap[0].length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        // Print the map
        for (int i = 0; i < playerViewMap.length; i++) {
            System.out.print(i + " | ");
            for (int j = 0; j < playerViewMap[i].length; j++) {

                if (playerViewMap[i][j].equals("1")) {
                    System.out.print("@ "); // Conceal player's ships ("1") as "@"
                } else if (playerViewMap[i][j].equals("2")) {
                    System.out.print(" ");  // Conceal computer's ships as water
                } else {
                    System.out.print(playerViewMap[i][j] + " ");  // Print hits/misses
                }
            }
            System.out.println("| " + i);
        }

        // Print bottom number lane
        System.out.print("    ");
        for (int i = 0; i < playerViewMap[0].length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    // Handle the player's turn
    public static void playerTurn() {
        System.out.println("\n" + GREEN + "YOUR TURN" + RESET);
        int x = -1, y = -1;

        boolean validInput = false; // To check if input is valid

        // Loop until valid input is entered
        while (!validInput) {
            try {
                // Get X coordinate
                System.out.print("Enter X coordinate: ");
                x = input.nextInt();

                // Get Y coordinate
                System.out.print("Enter Y coordinate: ");
                y = input.nextInt();

                // Check if the coordinates are valid (within 0-9 range)
                if (x < 0 || x >= 10 || y < 0 || y >= 10) {
                    System.out.println("Invalid coordinate. Please enter a valid coordinate within the 10x10 map.");
                } else if (playerViewMap[x][y].equals("X") || playerViewMap[x][y].equals("!")) {
                    System.out.println("You have already attacked this location.");
                } else {
                    validInput = true; // Input is valid
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Only integers are allowed.");
                input.next(); // Clear the invalid input
            }
        }

        // Check if the player hit a computer's ship
        if (oceanMap[x][y].equals("2")) {
            System.out.println(GREEN + "Boom! You sunk the computer's ship!" + RESET);
            playerViewMap[x][y] = "!";  // Mark hit
            oceanMap[x][y] = "!";  // Update oceanMap
            computerShips--;
        }
        // Check if the player hit their own ship
        else if (oceanMap[x][y].equals("1")) {
            System.out.println(RED + "Oh no! You sunk your own ship!" + RESET);
            playerViewMap[x][y] = "X";  // Mark hit
            oceanMap[x][y] = "X";  // Update oceanMap
            playerShips--;
        }
        // Missed
        else {
            System.out.println("Sorry, you missed.");
            playerViewMap[x][y] = "-";  // Mark miss
        }
    }

    // Handle the computer's turn
    public static void computerTurn(String[][] oceanMap) {
        System.out.println("\n" + RED + "COMPUTER'S TURN" + RESET);

        int x = (int) (Math.random() * 10);
        int y = (int) (Math.random() * 10);

        boolean validShot = false;

        // Loop until the computer finds a valid target
        while (!validShot) {
            if (playerViewMap[x][y].equals("-") || playerViewMap[x][y].equals("X") || playerViewMap[x][y].equals("!")) {
                x = (int) (Math.random() * 10);  // Recalculate coordinates
                y = (int) (Math.random() * 10);
            } else {
                validShot = true;  // Valid target found
            }
        }

        // Check if the computer hit a player's ship
        if (oceanMap[x][y].equals("1")) {
            System.out.println(RED + "The computer sunk one of your ships!" + RESET);
            playerViewMap[x][y] = "X";  // Mark hit
            oceanMap[x][y] = "X";  // Update oceanMap
            playerShips--;
        }
        // Check if the computer hit its own ship
        else if (oceanMap[x][y].equals("2")) {
            System.out.println(GREEN + "The computer sunk one of its own ships!" + RESET);
            playerViewMap[x][y] = "!";  // Mark hit
            oceanMap[x][y] = "!";  // Update oceanMap
            computerShips--;
        }
        // Missed
        else {
            System.out.println("The computer missed.");
            playerViewMap[x][y] = "-";  // Mark miss
        }
    }

    // Ask the player if they want to play again or exit
    public static boolean askToPlayAgain() {
        System.out.print("\nDo you want to play again? (y/n): ");
        String choice = input.next().toLowerCase();
        return choice.equals("y");
    }
}



