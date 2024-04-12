import java.util.*;

public class Main {

    private Scanner s = new Scanner(System.in);
    private ArrayList<Player> players = new ArrayList<>();
    private Player currentPlayer = new Player("Default");
    private Random Random = new Random();

    public void StartCoup() throws InterruptedException {

        System.out.println("“You are head of a family in an Italian city-state, a city run by a weak and corrupt court. \nYou need to manipulate, bluff and bribe your way to power. \nYour object is to destroy the influence of all the other families, forcing them into exile. \nOnly one family will survive…”");

        System.out.println("\n" + this.ANSI_PURPLE_BACKGROUND + "<- WELCOME TO COUP ->" + this.ANSI_RESET);

        System.out.println("Enter your name : ");
        String name = s.next() + s.nextLine();
        players.add(new Player(name));
        players.add(new Player("Player 1"));
        players.add(new Player("Player 2"));
        players.add(new Player("Player 3"));

        currentPlayer = players.get(0);

        while (true) {
            System.out.println("\nWelcome, " + currentPlayer.getName() + "!\nAre you ready to start the game? " + this.ANSI_PURPLE_BACKGROUND + "[ketikkan 'syap']" + this.ANSI_RESET);
            String ready = s.next() + s.nextLine();
            if (ready.equalsIgnoreCase("syap")) {
                break;
            }
        }

        System.out.println("\nPreparing the Family...");
        System.out.println("\nShuffling the Influence Cards...");

        System.out.println("\nThree (3) other Players have entered the Game as your " + this.ANSI_RED_BACKGROUND + "ENEMY" + this.ANSI_RESET + "...");

        while (true) {
            if (currentPlayer.getInfluenceCards().size() == 0) {
                System.out.println("\n" + this.ANSI_RED_BACKGROUND + currentPlayer.getName() + " has lost the game!" + this.ANSI_RESET);
                players.remove(currentPlayer);
                System.out.println("\n" + this.ANSI_RED_BACKGROUND + "GAME OVER" + this.ANSI_RESET);
                return;
            }

            if (players.size() == 1) {
                System.out.println("\n" + this.ANSI_GREEN_BACKGROUND + currentPlayer.getName() + " has won the game!" + this.ANSI_RESET);
                System.out.println("\n" + this.ANSI_GREEN_BACKGROUND + "Good Luck on your Next Journey!" + this.ANSI_RESET);
                return;
            }

            // YOUR TURN
            System.out.println("\n" + this.ANSI_CYAN_BACKGROUND + "It's " + currentPlayer.getName() + "'s turn" + this.ANSI_RESET + "\nYou have " + currentPlayer.getCoin() + " coins.");
            System.out.println("Here are your Influence Cards:");
            for (InfluenceCard card : currentPlayer.getInfluenceCards()) {
                System.out.println("@ " + card.getClass().getSimpleName());
            }
            System.out.println("What would you like to do?\n1. Income\n2. Foreign Aid\n3. Coup\n4. Use Influence Cards Action\n0. Exit Game\n");


            int choice = s.nextInt();
            if (choice == 1) {
                System.out.println("\n" + currentPlayer.getName() + " uses Income! Gets 1 coin!");
                currentPlayer.Income();
            } else if (choice == 2) {
                ForeignAid(currentPlayer);
            } else if (choice == 3) {
                Coup();
            } else if (choice == 4) {
                cardAction();
            } else if (choice == 0) {
                System.out.println("Exiting Game...");
                break;
            } else {
                System.out.println("\n" + this.ANSI_RED_BACKGROUND + "Invalid Input!" + this.ANSI_RESET);
            }

            // ENEMY TURN
            for (int i = 0; i < players.size(); i++) {
                if (i != 0) {
                    while (true) {
                        System.out.println("\n" + this.ANSI_RED_BACKGROUND + "It's " + players.get(i).getName() + "'s turn" + this.ANSI_RESET);
                        int random = Random.nextInt(4);
                        if (random == 0) {
                            System.out.println(players.get(i).getName() + " uses Income! Gets 1 coin!");
                            players.get(i).Income();
                            break;
                        } else if (random == 1) {
                            if (currentPlayer.hasInfluence("Duke")) {
                                System.out.println(players.get(i).getName() + " is about to use Foreign Aid, do you want to block with Duke? [Y/N]");
                                String blockChoice = s.next() + s.nextLine();
                                if (blockChoice.equalsIgnoreCase("Y")) {
                                    System.out.println("\nForeign Aid BLOCKED!");
                                } else {
                                    ForeignAid(players.get(i));
                                }
                            } else {
                                ForeignAid(players.get(i));
                            }
                            break;
                        } else if (random == 2 && players.get(i).getCoin() >= 7) {
                            int targetChoice = Random.nextInt(players.size());
                            System.out.println(players.get(i).getName() + " uses Coup against" + players.get(targetChoice).getName() + "!");
                            players.get(targetChoice).loseRandomInfluence();
                            System.out.println(players.get(i).getName() + " pays 7 coins!");
                            players.get(i).minusCoin(7);
                            break;

                        } else if (random == 3) {
                            int cardChoice = Random.nextInt(players.get(i).getInfluenceCards().size());
                            int targetChoice = Random.nextInt(players.size());
                            players.get(i).getInfluenceCards().get(cardChoice).Action(players.get(i), players.get(targetChoice));
                            break;
                        }
                    }
                }
            }
        }
    }

    public void ForeignAid(Player player) {
        System.out.println("\n" + player.getName() + " uses Foreign Aid!");
        Boolean block = false;
        for (int i = 0; i < players.size(); i++) {
            if (i != 0) {
                if (players.get(i).hasInfluence("Duke")) {
                    block = true;
                    System.out.println(players.get(i).getName() + " uses Duke against " + player.getName() + "! Blocks Foreign Aid!");
                    System.out.println("\nForeign Aid BLOCKED!");

                }
            }
        }
        if (!block) {
            player.ForeignAid();
            System.out.println(player.getName() + " gets 2 coins!");
        }
    }

    public void Coup() {
        if (currentPlayer.getCoin() >= 7) {
            System.out.println("\nWhich Player would you like to COUP?");
            for (int i = 0; i < players.size(); i++) {
                if (i != 0) {
                    System.out.println(i + ". " + players.get(i).getName());
                }
            }

            int targetChoice = s.nextInt();
            players.get(targetChoice).loseRandomInfluence();

            System.out.println("\n" + currentPlayer.getName() + " uses Coup! Pays 7 coins!");
            currentPlayer.minusCoin(7);
        } else {
            System.out.println("\n" + this.ANSI_RED_BACKGROUND + "You don't have enough coins to Coup!" + this.ANSI_RESET);
        }
    }

    public void cardAction() {
        System.out.println("Which Influence Card Action would you like to use?");
        for (int i = 0; i < currentPlayer.getInfluenceCards().size(); i++) {
            System.out.println(i + 1 + ". " + currentPlayer.getInfluenceCards().get(i).getClass().getSimpleName());
        }
        int cardChoice = s.nextInt();
        System.out.println("\nWhich Player would you like to target?");
        for (int i = 0; i < players.size(); i++) {
            if (i != 0) {
                System.out.println(i + ". " + players.get(i).getName());
            }
        }
        int targetChoice = s.nextInt();
        currentPlayer.getInfluenceCards().get(cardChoice - 1).Action(currentPlayer, players.get(targetChoice));
    }

    // INI KUNCI CHECK, buat yang nemu Githubku

    private String ANSI_CYAN_BACKGROUND = "\u001b[46m";
    private String ANSI_GREEN_BACKGROUND = "\u001b[42m";
    private String ANSI_BLUE_BACKGROUND = "\u001b[44m";
    private String ANSI_RED_BACKGROUND = "\u001b[41m";
    private String ANSI_PURPLE_BACKGROUND = "\033[45m";

    private String ANSI_WHITE = "\033[1;37m";
    private String ANSI_RESET = "\u001b[0m";
}