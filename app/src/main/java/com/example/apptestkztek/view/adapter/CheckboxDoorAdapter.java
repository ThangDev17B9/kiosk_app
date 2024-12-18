package com.example.apptestkztek.view.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.apptestkztek.R;
import com.example.apptestkztek.model.Door;
import java.util.ArrayList;
import java.util.List;

public class CheckboxDoorAdapter  extends RecyclerView.Adapter<CheckboxDoorAdapter.ViewHolder>{
    private final List<Door> doorList;

    public CheckboxDoorAdapter(List<Door> doorList) {
        this.doorList = doorList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_checkbox_door, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (doorList == null || position >= doorList.size()) return;
        Door door = doorList.get(position);
        holder.tvDoorNumber.setText(door.getDoorNumber());
        holder.checkboxDoor.setChecked(door.isOpenDoor());
        holder.checkboxDoor.setOnCheckedChangeListener(
                (buttonView, isChecked) -> door.setOpenDoor(isChecked));

    }
    public List<Boolean> getDoorState (){
        List<Boolean> doorState = new ArrayList<>();
        for (Door door : doorList){
            doorState.add(door.isOpenDoor());
        }
        return doorState;
    }
    @SuppressLint("NotifyDataSetChanged")
    public void setAllChecked(boolean isChecked) {
        for (Door item : doorList) {
            item.setOpenDoor(isChecked);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return (doorList != null) ? doorList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDoorNumber;
        CheckBox checkboxDoor;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDoorNumber = itemView.findViewById(R.id.tvDoor);
            checkboxDoor = itemView.findViewById(R.id.checkboxDoor);
        }
    }

}
