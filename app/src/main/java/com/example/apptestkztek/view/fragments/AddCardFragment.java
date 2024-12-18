package com.example.apptestkztek.view.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.example.apptestkztek.controller.generate.GenerateDoorHex;
import com.example.apptestkztek.R;
import com.example.apptestkztek.view.adapter.CheckboxDoorAdapter;
import com.example.apptestkztek.domain.api.Constant;
import com.example.apptestkztek.controller.client.UdpClientManager;
import com.example.apptestkztek.model.Door;
import java.util.ArrayList;
import java.util.List;

public class AddCardFragment extends Fragment {
    public EditText edtUserId, edtMode, edtPassword, edtTimezone;
    public AutoCompleteTextView actvCard, actvLenCard;
    public CheckBox cbDoorModeAll;
    public Button btnSaveCard;
    public RecyclerView recyclerViewDoor;
    public CheckboxDoorAdapter adapter;
    public ImageView ivDrop;
    public List<Door> doorList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_card, container, false);
        edtUserId = view.findViewById(R.id.edtUserId);
        edtMode = view.findViewById(R.id.edtMode);
        edtPassword = view.findViewById(R.id.edtPassword);
        edtTimezone = view.findViewById(R.id.edtTimezone);
        actvCard = view.findViewById(R.id.actvCardId);
        actvLenCard = view.findViewById(R.id.actvLenCard);
        btnSaveCard = view.findViewById(R.id.btnSaveCardUser);
        recyclerViewDoor = view.findViewById(R.id.recyclerViewDoor);
        ivDrop = view.findViewById(R.id.ivDropDown);
        ivDrop.setOnClickListener(v->ShowRecyclerView());
        showAdapterCheckboxDoor();
        btnSaveCard.setOnClickListener(v -> saveCardUser());
        actvCard.setOnClickListener(v->ListItemCard());
        actvLenCard.setOnClickListener(v->ListItemLenCard());
        return view;
    }

    private void ShowRecyclerView() {
        if(recyclerViewDoor.getVisibility() == View.GONE){
            recyclerViewDoor.setVisibility(View.VISIBLE);
        }else{
            recyclerViewDoor.setVisibility(View.GONE);
        }
    }

    private void ListItemLenCard() {
        String[] items = {"4","7"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_dropdown_item_1line,items);
        actvLenCard.setAdapter(adapter);
        actvLenCard.showDropDown();
    }

    private void ListItemCard() {
        String[] items = {"7C19F640","15683FEA1BC4FE"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_dropdown_item_1line,items);
        actvCard.setAdapter(adapter);
        actvCard.showDropDown();

    }

    private void saveCardUser() {
        new Thread(() -> {
            String userId = edtUserId.getText().toString();
            String mode = edtMode.getText().toString();
            String password = edtPassword.getText().toString();
            String timezone = edtTimezone.getText().toString();
            String card = actvCard.getText().toString();
            String lenCard = actvLenCard.getText().toString();
            // Lấy danh sách trạng thái của các cửa từ Adapter
            List<Boolean> doorStates = adapter.getDoorState();
            GenerateDoorHex generate = new GenerateDoorHex();
            // Chuyển trạng thái thành chuỗi hex
            String hexString = generate.generateDoorHex(doorStates);
            Log.e("hex: ", hexString);
            if (userId.isEmpty()
                    || mode.isEmpty()
                    || password.isEmpty()
                    || timezone.isEmpty()
                    || card.isEmpty()
                    || lenCard.isEmpty())
            {
                requireActivity().runOnUiThread(() ->
                        Toast.makeText(getContext(), "Chưa nhập đủ thông tin thẻ", Toast.LENGTH_SHORT).show());
            }
            else if(password.length() != 8){
                Toast.makeText(getContext(), "Mật khẩu phải đủ 8 số", Toast.LENGTH_SHORT).show();
            }
            else {
                UdpClientManager.getInstance().require(
                        Constant.downUserCard
                                + Constant.userID + userId
                                + Constant.lenCard + lenCard
                                + Constant.card + card
                                + Constant.pin + password
                                + Constant.mode + mode
                                + Constant.timeZone + timezone
                                + Constant.door + hexString
                );
                String dataUserDownload = UdpClientManager.getInstance().response();
                if (dataUserDownload.equals("DownloadUser?/OK")) {
                    requireActivity().runOnUiThread(() ->{
                        Toast.makeText(getContext(), "Thêm thẻ người dùng thành công", Toast.LENGTH_SHORT).show();
                        edtUserId.setText("");
                        edtMode.setText("");
                        edtPassword.setText("");
                        edtTimezone.setText("");
                        actvLenCard.setText("");
                        actvCard.setText("");

                    });
                } else if (dataUserDownload.equals("DownloadUser?/ERR")) {
                    requireActivity().runOnUiThread(() ->
                            Toast.makeText(getContext(), "Thêm thẻ người dùng không thành công", Toast.LENGTH_SHORT).show());
                } else {
                    requireActivity().runOnUiThread(() ->
                            Toast.makeText(getContext(), "Lỗi kết nối", Toast.LENGTH_SHORT).show());
                }
            }
        }).start();

    }

    private void showAdapterCheckboxDoor() {
        new Thread(() -> {
            doorList = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                doorList.add(new Door(String.valueOf(i + 1), false));
            }
            requireActivity().runOnUiThread(() -> {
                if (isAdded()) {
                    adapter = new CheckboxDoorAdapter(doorList);
                    recyclerViewDoor.setAdapter(adapter);
                }
            });
        }).start();
    }

}