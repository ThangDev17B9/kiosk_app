package com.example.apptestkztek.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.apptestkztek.R;
import com.example.apptestkztek.api.Constant;
import com.example.apptestkztek.client.UdpClient;
import com.example.apptestkztek.client.UdpClientManager;
import com.example.apptestkztek.model.User;

import java.util.List;

public class UserCardAdapter extends RecyclerView.Adapter<UserCardAdapter.ViewHolder> {
    private final List<User> userList;
    private final Context context;

    public UserCardAdapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = userList.get(position);
        holder.tvUserID.setText(String.valueOf(user.getUserId()));
        holder.tvMode.setText(String.valueOf(user.getMode()));
        holder.tvPassword.setText(String.valueOf(user.getPin()));
        holder.tvTimezone.setText(String.valueOf(user.getTimeZone()));
        holder.tvCardID.setText(String.valueOf(user.getCard()));
        holder.tvLenCard.setText(String.valueOf(user.getLenCard()));
        holder.tvDoorStatus.setText(user.getDoor());
        holder.btnDeleteCard.setOnClickListener(v -> deleteUserCard(position));
    }
    private void deleteUserCard(int position) {
        new Thread(() -> {
            String dataReq = UdpClientManager.getInstance().require(Constant.deleteUserID + userList.get(position).getUserId());
            Log.e("deleteUserCard: ", dataReq);
            String statusRes = UdpClient.response();
            Log.e( "deleteUserCard: ", statusRes);
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(() -> {
                if (statusRes.equals("DeleteUser?/OK")) {
                    if (position < userList.size()) { // Kiểm tra vị trí hợp lệ
                        userList.remove(position);
                        notifyItemRemoved(position);
                    }
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                } else if (statusRes.equals("DeleteUser?/ERR")) {
                    Toast.makeText(context, "Xóa không thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
                }
            });
        }).start();
    }

    @Override
    public int getItemCount() {
        return (userList != null) ? userList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvUserID, tvPassword, tvCardID, tvLenCard, tvTimezone, tvMode, tvDoorStatus;
        Button btnDeleteCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserID = itemView.findViewById(R.id.tvUserID);
            tvPassword = itemView.findViewById(R.id.tvPassword);
            tvCardID = itemView.findViewById(R.id.tvCardID);
            tvLenCard = itemView.findViewById(R.id.tvLenCard);
            tvTimezone = itemView.findViewById(R.id.tvTimezone);
            tvMode = itemView.findViewById(R.id.tvMode);
            tvDoorStatus = itemView.findViewById(R.id.tvDoorStatus);
            btnDeleteCard = itemView.findViewById(R.id.btnDeleteCard);
        }
    }
}
