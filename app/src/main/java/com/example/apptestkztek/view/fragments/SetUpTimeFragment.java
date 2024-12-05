package com.example.apptestkztek.view.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.apptestkztek.R;
import com.example.apptestkztek.controller.time.TimeNow;
import com.example.apptestkztek.domain.api.Constant;
import com.example.apptestkztek.controller.client.UdpClient;
import com.example.apptestkztek.controller.client.UdpClientManager;

public class SetUpTimeFragment extends Fragment {
    public EditText edtHour, edtMinute, edtSecond, edtDay, edtMonth, edtYear;
    public TextView tvHour, tvMinute, tvSecond, tvDay, tvMonth, tvYear;
    public Button btnSetTimeNow, btnSave, btnShowTime;
    public ImageView imgDatePicker;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_set_up_time, container, false);
        edtHour = view.findViewById(R.id.edtHour);
        edtMinute = view.findViewById(R.id.edtMinute);
        edtSecond = view.findViewById(R.id.edtSecond);
        edtDay = view.findViewById(R.id.edtDay);
        edtMonth = view.findViewById(R.id.edtMonth);
        edtYear = view.findViewById(R.id.edtYear);
        tvHour = view.findViewById(R.id.tvHour);
        tvMinute = view.findViewById(R.id.tvMinute);
        tvSecond = view.findViewById(R.id.tvSecond);
        tvYear = view.findViewById(R.id.tvYear);
        tvMonth = view.findViewById(R.id.tvMonth);
        tvDay = view.findViewById(R.id.tvDay);
        btnSetTimeNow = view.findViewById(R.id.btnSetTimeNow);
        btnSave = view.findViewById(R.id.btnSave);
        btnShowTime = view.findViewById(R.id.btnShowTimeDate);
        imgDatePicker = view.findViewById(R.id.imgDatePicker);
        btnSetTimeNow.setOnClickListener(v -> SetTimeNow());
        btnSave.setOnClickListener(v -> SaveDateTime());
        btnShowTime.setOnClickListener(v -> ShowDateTime());
        return view;
    }

    private void ShowDateTime() {
        new Thread(() -> {
            UdpClientManager.getInstance().require(Constant.getDateTime);
            String dateTime = UdpClient.response();
                if(!dateTime.equals("TimeoutException")){
                    int index = dateTime.indexOf("/");
                    String result = dateTime.substring(index + 1);
                    String year = result.substring(0, 4);
                    String month = result.substring(4, 6);
                    String day = result.substring(6, 8);
                    String hour = result.substring(8, 10);
                    String minute = result.substring(10, 12);
                    String second = result.substring(12, 14);
                    requireActivity().runOnUiThread(() -> {
                        tvYear.setText(year);
                        tvMonth.setText(month);
                        tvDay.setText(day);
                        tvHour.setText(hour);
                        tvMinute.setText(minute);
                        tvSecond.setText(second);
                        Toast.makeText(getContext(), "Lấy dữ liệu thành công", Toast.LENGTH_SHORT).show();
                    });
                }else{
                    requireActivity().runOnUiThread(() -> Toast.makeText(getContext(), "Lấy dữ liệu thất bại", Toast.LENGTH_SHORT).show());
                }
        }).start();
    }

    private void SaveDateTime() {
        String year = edtYear.getText().toString();
        String month = edtMonth.getText().toString();
        String day = edtDay.getText().toString();
        String hour = edtHour.getText().toString();
        String minute = edtMinute.getText().toString();
        String second = edtSecond.getText().toString();
        if (year.isEmpty()
                || month.isEmpty()
                || day.isEmpty()
                || hour.isEmpty()
                || minute.isEmpty()
                || second.isEmpty()) {
            Toast.makeText(getContext(), "Vui lòng nhập đủ dữ liệu", Toast.LENGTH_SHORT).show();
        } else {
            new Thread(() -> {
                try {
                    UdpClientManager.getInstance().require(Constant.setDateTime + year + month + day + hour + minute + second);
                    requireActivity().runOnUiThread(() -> Toast.makeText(getContext(), "Lưu thành công", Toast.LENGTH_SHORT).show());
                } catch (Exception e) {
                    Log.e("SaveDateTime: ", "fail", e);
                    requireActivity().runOnUiThread(() -> Toast.makeText(getContext(), "Lưu thất bại", Toast.LENGTH_SHORT).show());

                }
            }).start();
        }

    }

    private void SetTimeNow() {
        String dateTime = TimeNow.getCurrentTimeDate();
        String year = dateTime.substring(0, 4);
        String month = dateTime.substring(4, 6);
        String day = dateTime.substring(6, 8);
        String hour = dateTime.substring(8, 10);
        String minute = dateTime.substring(10, 12);
        String second = dateTime.substring(12, 14);
        edtYear.setText(year);
        edtMonth.setText(month);
        edtDay.setText(day);
        edtHour.setText(hour);
        edtMinute.setText(minute);
        edtSecond.setText(second);
    }
}
