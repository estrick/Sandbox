import Network.Network;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;

public class GameApp {

    public static void main(String[] args) {
        try {
            Network server = new Network("228.5.6.7", 48642);
            List<String> players = new ArrayList<>();

            // Wait for requests to join from players
            int numberOfPlayers = getNumberOfPlayers();
            for(int i = 0; i < numberOfPlayers; ++i) {
                // Read a command from client
                String command = server.read();
                // If the command starts with add player, add the player
                if (command.startsWith("Add player")) {
                    String[] tokens = command.split(" ");
                    String userName = tokens[2];
                    // If the userName is already in players list, error
                    if (players.contains(userName)) {
                        server.send(server.getLatestReadIP(), server.getLatestReadPort(), "Name already in use.");
                    } else {
                        players.add(userName);
                        server.send(server.getLatestReadIP(), server.getLatestReadPort(), "okay");
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getNumberOfPlayers() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Enter the number of players");
        return scanner.nextInt();
    }

}
