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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SisaCutiAdapter extends RecyclerView.Adapter<SisaCutiAdapter.ViewHolder> {
    Context context;
    ArrayList<Cuti> list;

    public SisaCutiAdapter(Context context, ArrayList<Cuti> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sisa_cuti, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.perihal.setText(list.get(position).getJenisCuti());
        holder.selesai.setText(list.get(position).getSelesai());
        holder.mulai.setText(list.get(position).getMulai());
        Date dateToday = Calendar.getInstance().getTime();
        //current date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        try {
            Date objDate = dateFormat.parse(list.get(position).getSelesai());
            long todayDiff = objDate.getTime()-dateToday.getTime();
            long selisihToday = TimeUnit.DAYS.convert(todayDiff, TimeUnit.MILLISECONDS);
            if (selisihToday+1>0){
                holder.sisa.setText(String.valueOf(selisihToday+1)+ "hari");
            }else {
                holder.sisa.setText("Selesai");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView sisa, mulai, selesai, perihal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            init(itemView);
        }

        private void init(View itemView) {
            sisa = itemView.findViewById(R.id.tvItemSisaCuti);
            mulai = itemView.findViewById(R.id.tvTglMulaiCekCuti);
            selesai = itemView.findViewById(R.id.tvTglSelesaiCekCuti);
            perihal = itemView.findViewById(R.id.tvJenisCekCuti);
        }
    }
}
