package com.example.apptestkztek.view.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.apptestkztek.R;
import com.example.apptestkztek.model.User;
import com.example.apptestkztek.view.adapter.UserCardAdapter;
import com.example.apptestkztek.domain.api.Constant;
import com.example.apptestkztek.controller.client.UdpClient;
import com.example.apptestkztek.controller.client.UdpClientManager;
import java.util.ArrayList;
import java.util.List;

public class ListCardFragment extends Fragment {
    private SwipeRefreshLayout swipeRefreshLayout;
    public RecyclerView recyclerView;
    public List<User> userList = new ArrayList<>();
    private UserCardAdapter adapter;

    @SuppressLint("CutPasteId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_card, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewListUserCard);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayoutListCard);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Khởi tạo adapter một lần
        adapter = new UserCardAdapter(userList, getContext());
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(this::fetchDataListUser);

        // Fetch dữ liệu ban đầu
        fetchDataListUser();

        return view;
    }

    @SuppressLint("NotifyDataSetChanged")
    private void fetchDataListUser() {
        new Thread(() -> {
            try {
                String dataResponse;
                requireActivity().runOnUiThread(() -> swipeRefreshLayout.setRefreshing(true));
                UdpClientManager.getInstance().require(Constant.getAllUser);
                dataResponse = UdpClient.response();
                    if (dataResponse.isEmpty()) {
                        requireActivity().runOnUiThread(() ->
                                Toast.makeText(getContext(), "Không có dữ liệu trả về", Toast.LENGTH_SHORT).show()
                        );
                    }
                // Xử lý dữ liệu phản hồi
                List<User> newUsers = parseUserData(dataResponse);

                // Cập nhật danh sách người dùng và giao diện
                requireActivity().runOnUiThread(() -> {
                    userList.addAll(newUsers); // Thêm dữ liệu mới
                    adapter.notifyDataSetChanged(); // Thông báo adapter cập nhật
                });

            } catch (Exception e) {
                Log.e("fetchDataListUser", "Error: ", e);
                requireActivity().runOnUiThread(() ->
                        Toast.makeText(getContext(), "Lỗi kết nối", Toast.LENGTH_SHORT).show()
                );
            } finally {
                requireActivity().runOnUiThread(() -> swipeRefreshLayout.setRefreshing(false));
            }
        }).start();
    }

    private List<User> parseUserData(String response) {
        List<User> parsedUsers = new ArrayList<>();

        // Tách từng khối dữ liệu người dùng dựa trên "/UserID="
        String[] userChunks = response.split("/UserID=");

        for (int i = 1; i < userChunks.length; i++) { // Bỏ qua phần đầu không chứa dữ liệu
            String userData = userChunks[i];

            // Kiểm tra nếu dữ liệu không hợp lệ
            if (userData.startsWith("NULL")) continue;

            try {
                // Tạo đối tượng User từ dữ liệu
                User user = new User();

                // Lấy UserID
                String userId = userData.substring(0, userData.indexOf('/'));
                user.setUserId(Integer.parseInt(userId));

                // Gắn các giá trị khác
                user.setLenCard(Integer.parseInt(getValue(userData, "LenCard=")));
                user.setCard(Integer.parseInt(getValue(userData, "Card=")));
                user.setPin(Integer.parseInt(getValue(userData, "Pin=")));
                user.setMode(Integer.parseInt(getValue(userData, "Mode=")));
                user.setTimeZone(Integer.parseInt(getValue(userData, "TimeZone=")));
                user.setDoor(getValue(userData, "Door="));

                // Thêm vào danh sách
                parsedUsers.add(user);
            } catch (Exception e) {
                Log.e("parseUserData", "Error parsing user data: " + userData, e);
            }
        }

        return parsedUsers;
    }

    // Hàm để lấy giá trị từ chuỗi dựa trên key
    private String getValue(String data, String key) {
        int startIndex = data.indexOf(key);
        if (startIndex == -1) return null; // Không tìm thấy key
        startIndex += key.length();
        int endIndex = data.indexOf('/', startIndex);
        if (endIndex == -1) endIndex = data.length(); // Key ở cuối chuỗi
        return data.substring(startIndex, endIndex).trim();
    }
}
