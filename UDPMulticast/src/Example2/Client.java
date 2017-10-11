package Example2;

import jdk.nashorn.internal.runtime.ECMAException;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Client {

    private InetAddress address = InetAddress.getByName("228.5.6.7");
    private int port = 46842;
    private MulticastSocket socket = new MulticastSocket(port);

    public Client() throws Exception {
        socket.joinGroup(address);
        receiveData();
        closeSession();
    }

    private void receiveData() throws Exception {
        while (true) {
            byte[] msgBuf = new byte[1024];
            DatagramPacket rcvPacket = new DatagramPacket(msgBuf, msgBuf.length);
            socket.receive(rcvPacket);
            String resultString = new String(msgBuf).trim();
            if (resultString.equals("END")) {
                break;
            }
            System.out.printf("Result: " + resultString);
        }
    }

    private void closeSession() throws Exception{
        socket.leaveGroup(address);
        socket.close();
    }

    public static void main(String[] args) throws Exception {
        Client client = new Client();
    }

}
