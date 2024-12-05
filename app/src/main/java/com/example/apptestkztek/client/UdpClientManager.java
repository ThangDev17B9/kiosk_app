package com.example.apptestkztek.client;

public class UdpClientManager {
    private static UdpClient udpClient;

    // Lấy instance của UdpClient
    public static UdpClient getInstance() {
        if (udpClient == null) {
            udpClient = new UdpClient();
        }
        return udpClient;
    }

    // Kết nối đến server
    public static void connect(String ip, int port) throws Exception {
        getInstance().connect(ip, port);
    }
}

