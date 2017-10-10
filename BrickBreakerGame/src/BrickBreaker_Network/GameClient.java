package BrickBreaker_Network;

import com.BrickBreaker.Gameplay;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class GameClient extends Thread {

    private InetAddress ipAddress;
    private DatagramSocket socket;
    private Gameplay game;


    public GameClient(Gameplay game, String ipAddress) {
        this.game = game;
        try {
            this.socket = new DatagramSocket(); // Can enter a port address to listen to
            this.ipAddress = InetAddress.getByName(ipAddress);
        } catch (SocketException | UnknownHostException e) {
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
            System.out.println("SERVER > " + new String(packet.getData()));
        }
    }

    public void sendData(byte[] data) {
        DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 1331);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
