package com.example.apptestkztek.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.apptestkztek.R;
import com.example.apptestkztek.domain.api.Constant;
import com.example.apptestkztek.controller.client.UdpClientManager;
import com.example.apptestkztek.view.activity.ConnectDeviceActivity;

import java.util.Objects;

public class ChangeIpAddressFragment extends Fragment {
    public EditText edtIpAddress, edtSubnetMask, edtDefaultGateway, edtMacAddress;
    public Button btnReloadText, btnSave;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change_ip_address, container, false);
        edtIpAddress = view.findViewById(R.id.edtChangeIpAddress);
        edtSubnetMask = view.findViewById(R.id.edtChangeSubnetMask);
        edtDefaultGateway = view.findViewById(R.id.edtChangeDefaultGateway);
        edtMacAddress = view.findViewById(R.id.edtChangeMacAddress);
        btnReloadText = view.findViewById(R.id.btnReloadText);
        btnSave = view.findViewById(R.id.btnSave);
        btnReloadText.setOnClickListener(v -> setTextIsNull());
        btnSave.setOnClickListener(v->sendDataClientToDeveice());
        showAutoDirect();
        return view;
    }

    private void showAutoDirect() {
        new Thread(()->{
            try {
                UdpClientManager.getInstance().require(Constant.autoDetect);
                String dataRes = UdpClientManager.getInstance().response();
                Log.e( "showAutoDirect: ", dataRes);
                if(!dataRes.equals("Error: TimeoutException")){
                    String[] data = dataRes.split("/");
                    requireActivity().runOnUiThread(()->{
                        edtIpAddress.setText(data[1]);
                        edtSubnetMask.setText(data[3]);
                        edtDefaultGateway.setText(data[4]);
                        edtMacAddress.setText(data[5]);
                        Toast.makeText(getContext(), "Lấy dữ liệu thành công", Toast.LENGTH_SHORT).show();
                    });
                }else{
                    requireActivity().runOnUiThread(()->
                            Toast.makeText(getContext(), "Không lấy được dữ liệu trả về", Toast.LENGTH_SHORT).show());
                }
            }catch(Exception e){
                requireActivity().runOnUiThread(()->
                        Toast.makeText(getContext(), "exception timeout", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }

    private void setTextIsNull() {
        edtIpAddress.setText("");
        edtSubnetMask.setText("");
        edtDefaultGateway.setText("");
        edtMacAddress.setText("");
    }

    private void sendDataClientToDeveice() {
        new Thread(()->{
            String txtIp = edtIpAddress.getText().toString();
            String txtSubnetMask = edtSubnetMask.getText().toString();
            String txtDefaultGateway = edtDefaultGateway.getText().toString();
            String txtMacAddress = edtMacAddress.getText().toString();
            try {
                if(txtIp.isEmpty() || txtSubnetMask.isEmpty() ||
                        txtDefaultGateway.isEmpty() || txtMacAddress.isEmpty()){
                    requireActivity().runOnUiThread(()->
                            Toast.makeText(getContext(), "Chưa nhập đủ dữ liệu", Toast.LENGTH_SHORT).show());
                }else{
                    UdpClientManager.getInstance().require(Constant.changeIp
                            + Constant.ip + txtIp
                            + Constant.subnetMask + txtSubnetMask
                            + Constant.defaultGateWay + txtDefaultGateway
                            + Constant.hostMac + txtMacAddress);
                    String dataRes = UdpClientManager.getInstance().response();
                    if(dataRes.equals(Constant.changeIp + "OK")){
                        requireActivity().runOnUiThread(()->{
                            Toast.makeText(getContext(), "Cấu hình thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getContext(), ConnectDeviceActivity.class));
                        });
                    }else if(dataRes.equals(Constant.changeIp + "ERR")){
                        requireActivity().runOnUiThread(()->
                                Toast.makeText(getContext(), "Cấu hình thất bại", Toast.LENGTH_SHORT).show());
                    } else{
                        requireActivity().runOnUiThread(()->
                                Toast.makeText(getContext(), "Không nhận được phản hồi từ thiết bị", Toast.LENGTH_SHORT).show());
                    }

                }
            } catch (Exception e) {
                Log.e("Lỗi khi gửi yêu cầu", Objects.requireNonNull(e.getMessage()));
            }
        }).start();
    }
}
