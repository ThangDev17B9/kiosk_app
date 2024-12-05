package com.example.apptestkztek.client;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class UdpClient {
    public static DatagramSocket socket;
    public InetAddress inetAddress;
    public int port;

    // Kết nối đến server
    public void connect(String ip, int serverPort) throws Exception {
        socket = new DatagramSocket();
        inetAddress = InetAddress.getByName(ip);
        port = serverPort;
    }

    // Gửi yêu cầu đến server
    public String require(String data) {
        try {

                byte[] buffer = data.getBytes(StandardCharsets.UTF_8);
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, inetAddress, port);
                socket.send(packet);
        } catch (IOException e) {
            return "Error: " + e.getMessage();
        }

        return data;
    }
    public static String response() {
        try {
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.setSoTimeout(3000);
            socket.receive(packet);
            return new String(packet.getData(), 0, packet.getLength());
        }
        catch(java.net.SocketTimeoutException e){
            return "TimeoutException";
        }catch (IOException e) {
            return "Error: " + e.getMessage();
        }
    }
}

