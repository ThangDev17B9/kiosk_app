package com.example.apptestkztek.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptestkztek.R;
import com.example.apptestkztek.model.Cabinet;

import java.util.List;

public class CheckboxStatusAdapter extends RecyclerView.Adapter<CheckboxStatusAdapter.ViewHolder> {
    private final List<Cabinet> cabinetList;

    public CheckboxStatusAdapter(List<Cabinet> cabinetList) {
        this.cabinetList = cabinetList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_checkbox_status, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (cabinetList == null || position >= cabinetList.size()) return;
        Cabinet cabinet = cabinetList.get(position);
        holder.tvNumber1.setText(cabinet.getName());
        holder.checkBox.setChecked(cabinet.getOpen());

    }

    @Override
    public int getItemCount() {
        return (cabinetList != null) ? cabinetList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNumber1;
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNumber1 = itemView.findViewById(R.id.tvNumber1);
            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }
}
