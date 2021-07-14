package com.cuti.online.karyawan.ui.adapter;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cuti.online.karyawan.R;
import com.cuti.online.karyawan.model.Cuti;
import com.cuti.online.karyawan.ui.DetailPermohonanCuti;

import java.util.List;

public class PermohonanCutiAdapter extends RecyclerView.Adapter<PermohonanCutiAdapter.ViewHolder> {
    Context context;
    List<Cuti> cutiList;
    List<String> key;

    public PermohonanCutiAdapter(Context context, List<Cuti> cutiList, List<String> key) {
        this.context = context;
        this.cutiList = cutiList;
        this.key = key;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_permohonan_cuti, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nama.setText(cutiList.get(position).getNama());
        holder.perihal.setText(cutiList.get(position).getJenisCuti());
        if (String.valueOf(cutiList.get(position).getStatus()).equals("-1")) {
            holder.status.setText("Ditolak");
            holder.status.setBackgroundColor(Color.RED);
        } else if (String.valueOf(cutiList.get(position).getStatus()).equals("0")) {
            holder.status.setText("Diproses");
            holder.status.setBackgroundColor(Color.YELLOW);
        } else if (String.valueOf(cutiList.get(position).getStatus()).equals("1")) {
            holder.status.setText("Diterima");
            holder.status.setBackgroundColor(Color.GREEN);
        } else {
            holder.status.setText("Invalid");
            holder.status.setBackgroundColor(Color.GREEN);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailPermohonanCuti.class);
                intent.putExtra("id", key.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cutiList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nama, perihal, status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            init(itemView);
        }

        private void init(View itemView) {
            nama = itemView.findViewById(R.id.tvItemNamaPemohon);
            perihal = itemView.findViewById(R.id.tvPerihalPemohon);
            status = itemView.findViewById(R.id.tvStatusPemohon);
        }
    }
}
