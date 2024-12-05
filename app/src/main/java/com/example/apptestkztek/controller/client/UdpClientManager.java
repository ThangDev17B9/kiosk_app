package com.example.apptestkztek.controller.client;

public class UdpClientManager {
    private static UdpClient udpClient;

    // Lấy instance của UdpClient
    public static UdpClient getInstance() {
        if (udpClient == null) {
            udpClient = new UdpClient();
        }
        return udpClient;
    }
    public static void connect(String ip, int port) throws Exception {
        getInstance().connect(ip, port);
    }
}

