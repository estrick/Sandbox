package Example2;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Server {

    private String msg = "Hey mate\n";
    private String endMsg = "END";
    private InetAddress address = InetAddress.getByName("228.5.6.7");
    private int port = 46842;
    private MulticastSocket socket = new MulticastSocket(port);

    public Server() throws Exception {
        socket.joinGroup(address);
        sendData(msg, address, port);
        closeSession();
    }

    private void sendData(String msg, InetAddress address, int port) throws Exception {
        DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.length(), address, port);
        DatagramPacket endPacket = new DatagramPacket(endMsg.getBytes(), endMsg.length(), address, port);
        for (int i = 0; i < 100; i++) {
            socket.send(packet);
        }
        socket.send(endPacket);
    }

    private void closeSession() throws Exception{
        socket.leaveGroup(address);
        socket.close();
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server();
    }

}
