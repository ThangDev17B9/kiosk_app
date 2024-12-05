package com.example.apptestkztek.controller.root.shell;

import java.io.DataOutputStream;
import java.io.IOException;

public class OverscanHelper {
    public void setOverscan(String overscanValue) {
        try {
            // Tạo một tiến trình để chạy lệnh shell
            Process process = Runtime.getRuntime().exec("su"); // Yêu cầu quyền root
            try (DataOutputStream os = new DataOutputStream(process.getOutputStream())) {
                // Gửi lệnh wm overscan
                os.writeBytes("wm overscan " + overscanValue + "\n");
                os.writeBytes("exit\n"); // Thoát khỏi quyền root
                os.flush();
            }
            process.waitFor(); // Chờ lệnh thực thi hoàn tất
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
