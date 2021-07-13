package com.cuti.online.karyawan.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.cuti.online.karyawan.R;
import com.cuti.online.karyawan.model.Cuti;

import java.util.ArrayList;

public class CekCutiAdapter extends RecyclerView.Adapter<CekCutiAdapter.ViewHolder> {
    Context context;
    ArrayList<Cuti> list;

    public CekCutiAdapter(Context context, ArrayList<Cuti> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cek_pengajuan_cuti, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.perihal.setText(list.get(position).getJenisCuti());
        holder.selesai.setText(list.get(position).getSelesai());
        holder.mulai.setText(list.get(position).getMulai());
        if (String.valueOf(list.get(position).getStatus()).equals("-1")) {
            holder.status.setText("Ditolak");
            holder.status.setBackgroundColor(Color.RED);
        } else if (String.valueOf(list.get(position).getStatus()).equals("0")) {
            holder.status.setText("Diproses");
            holder.status.setBackgroundColor(Color.YELLOW);
        } else if (String.valueOf(list.get(position).getStatus()).equals("1")) {
            holder.status.setText("Diterima");
            holder.status.setBackgroundColor(Color.GREEN);
        } else {
            holder.status.setText("Invalid");
            holder.status.setBackgroundColor(Color.GREEN);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView status, mulai, selesai, perihal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            init(itemView);
        }

        private void init(View itemView) {
            status = itemView.findViewById(R.id.tvItemStatusCekPengajuan);
            mulai = itemView.findViewById(R.id.tvTglMulaiCekCuti);
            selesai = itemView.findViewById(R.id.tvTglSelesaiCekCuti);
            perihal = itemView.findViewById(R.id.tvJenisCekCuti);
        }
    }
}
