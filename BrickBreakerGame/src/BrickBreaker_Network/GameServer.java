package BrickBreaker_Network;

import com.BrickBreaker.Gameplay;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class GameServer extends Thread {

    private DatagramSocket socket;
    private Gameplay game;


    public GameServer(Gameplay game) {
        this.game = game;
        try {
            this.socket = new DatagramSocket(1331); // Listening on port 1331
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            byte[] data = new byte[1024]; // Array of bytes to Rx/Tx w/ server
            DatagramPacket packet = new DatagramPacket(data, data.length); // Putting data into the packet
            try {
                socket.receive(packet); // Receive a packet
            } catch (IOException e) {
                e.printStackTrace();
            }
            String message = new String(packet.getData());
            System.out.println("CLIENT > " + message);
            if (message.trim().equalsIgnoreCase("ping")) {
                sendData("pong".getBytes(), packet.getAddress(), packet.getPort());
            }
        }
    }

    public void sendData(byte[] data, InetAddress ipAddress, int port) {
        DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, port);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
