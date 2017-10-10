package Example1;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Server{

    String msg = "Hey mate";
    InetAddress address = InetAddress.getByName("228.5.6.7");
    private int port = 46842;
    MulticastSocket socket = new MulticastSocket(port);

    public Server() throws Exception {
        socket.joinGroup(address);
        sendData(msg, address, port);
        byte[] data = receiveData();
        displayMessage(data);
    }

    private void sendData(String msg, InetAddress address, int port) throws Exception {
        DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.length(), address, port);
        socket.send(packet);
    }

    private byte[] receiveData() throws Exception {
        byte[] msgBuf = new byte[1024];
        DatagramPacket rcvPacket = new DatagramPacket(msgBuf, msgBuf.length);
        socket.receive(rcvPacket);
        return msgBuf;
    }

    private void displayMessage(byte[] data) {
        String resultString = new String(data);
        System.out.printf("Result: " + resultString);
    }


}
