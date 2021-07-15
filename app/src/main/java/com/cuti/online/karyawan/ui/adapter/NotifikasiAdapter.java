package com.cuti.online.karyawan.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cuti.online.karyawan.R;
import com.cuti.online.karyawan.model.Cuti;

import java.util.ArrayList;

public class NotifikasiAdapter extends RecyclerView.Adapter<NotifikasiAdapter.ViewHolder> {
    Context context;
    ArrayList<Cuti> list;

    public NotifikasiAdapter(Context context, ArrayList<Cuti> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notif, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (String.valueOf(list.get(position).getStatus()).equals("-1")) {
            holder.notif.setText("Maaf Permohonan Cuti Anda untuk tanggal "+list.get(position).getMulai()+" ditolak");
        }
        else if (String.valueOf(list.get(position).getStatus()).equals("1")) {
            holder.notif.setText("Selamat Permohonan Cuti Anda untuk tanggal "+list.get(position).getMulai()+" diterima");

        } else {
            holder.notif.setText("Tidak ada notifikasi");

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView notif;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            init(itemView);
        }

        private void init(View itemView) {
            notif = itemView.findViewById(R.id.tvNotifikasi);
        }
    }
}
