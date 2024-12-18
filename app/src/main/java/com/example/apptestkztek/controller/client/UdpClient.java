package com.example.apptestkztek.controller.client;

import android.util.Log;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;

public class UdpClient {
    public DatagramSocket socket;
    public InetAddress inetAddress;
    public int port;

    public void connect(String ip, int serverPort) throws Exception {
        socket = new DatagramSocket();
        inetAddress = InetAddress.getByName(ip);
        port = serverPort;
    }
    public String require(String data) {
        try {
            byte[] buffer = data.getBytes(StandardCharsets.UTF_8);
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, inetAddress, port);
            socket.setSoTimeout(3000);
            socket.send(packet);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
        return data;
    }

    public String response() {
        try {
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.setSoTimeout(3000);
            socket.receive(packet);
            return new String(packet.getData(), 0, packet.getLength());
        }
        catch(java.net.SocketTimeoutException e){
            return "Error: TimeoutException";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    // Quét dải IP và gửi gói UDP tới mỗi thiết bị
    public String scanSingleDevice(String ip, int port, String message) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
            socket.setSoTimeout(3000); // 3 giây timeout

            InetAddress address = InetAddress.getByName(ip);
            byte[] sendData = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
            socket.send(sendPacket);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            Log.e("Response from " + ip, response);
            return ip;
        } catch (SocketTimeoutException e) {
            Log.e("No response: ", ip);
            return "Error: TimeoutException";
        } catch (Exception e) {
            return "Error " + e.getMessage();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}

