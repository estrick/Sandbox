import Network.Network;

import java.io.IOException;

public class PlayerApp {

    public static void main(String[] args) {
        try {
            Network client = new Network("228.5.6.7", 48642);

            // Read and record server info
            String[] serverInfo = client.listen().split(",");
            String serverIP = serverInfo[0];
            int serverPort = Integer.parseInt(serverInfo[1]);

            // SETUP Player Username
            while (true) {
                // TODO Create getUserName()
                String userName = "Elliot"; //getUserName()
                client.send(serverIP, serverPort, "add player " + userName);

                String status = client.read();
                if (status.equals("okay")) {
                    break;
                } else {
                    System.out.println(status);
                }
            }

            // TODO WAIT for game to start...



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private static String getUserName() {
//
//    }


}
