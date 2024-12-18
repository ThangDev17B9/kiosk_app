package com.example.apptestkztek.controller.root.shell;

import java.io.DataOutputStream;
import java.io.IOException;

public class OverscanHelper {
    public void setFullScreen() {
        try {
            // Tạo một tiến trình để chạy lệnh shell
            Process process = Runtime.getRuntime().exec("su"); // Yêu cầu quyền root
            try (DataOutputStream os = new DataOutputStream(process.getOutputStream())) {
                os.writeBytes("wm overscan 0,-50,0,-100" + "\n");
                os.writeBytes("exit\n");
                os.flush();
            }
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void resetDefaultScreen() {
        try {
            Process process = Runtime.getRuntime().exec("su");
            try (DataOutputStream os = new DataOutputStream(process.getOutputStream())) {
                os.writeBytes("wm overscan reset" + "\n");
                os.writeBytes("exit\n");
                os.flush();
            }
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
