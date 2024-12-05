package com.example.apptestkztek;

import java.util.List;

public class GenerateDoorHex {
    public String generateDoorHex(List<Boolean> doorStates) {
        StringBuilder hexString = new StringBuilder();
        int byteValue = 0;

        for (int i = 0; i < doorStates.size(); i++) {
            int bitIndex = 7 - (i % 8); // Vị trí bit trong byte (từ trái sang phải)
            if (doorStates.get(i)) {
                byteValue |= (1 << bitIndex); // Bật bit nếu trạng thái là true (mở cửa)
            }

            // Khi đủ 8 trạng thái (1 byte) hoặc là trạng thái cuối cùng, thêm vào chuỗi hex
            if ((i % 8 == 7) || (i == doorStates.size() - 1)) {
                hexString.append(String.format("%02X", byteValue)); // Chuyển byte thành hex
                if (i < doorStates.size() - 1) {
                    hexString.append("."); // Thêm dấu `.`
                }
                byteValue = 0; // Reset byteValue
            }
        }

        return hexString.toString();
    }

}
