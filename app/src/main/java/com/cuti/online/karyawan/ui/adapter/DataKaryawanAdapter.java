package com.cuti.online.karyawan.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cuti.online.karyawan.R;
import com.cuti.online.karyawan.model.User;
import com.cuti.online.karyawan.ui.KaryawanDetailActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DataKaryawanAdapter extends RecyclerView.Adapter<DataKaryawanAdapter.ViewHolder> {
    List<User> list;
    Context context;

    public DataKaryawanAdapter(List<User> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data_karyawan, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nama.setText(list.get(position).getNama());
        holder.email.setText(list.get(position).getEmail());
        Glide.with(context).load(list.get(position).getFoto()).into(holder.img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, KaryawanDetailActivity.class);
                intent.putExtra("nama", list.get(position).getNama());
                intent.putExtra("email", list.get(position).getEmail());
                intent.putExtra("noTelp", list.get(position).getNoTelp());
                intent.putExtra("foto", list.get(position).getFoto());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView img;
        TextView nama, email;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View itemView) {
            img = itemView.findViewById(R.id.imgItemDataKaryawan);
            nama = itemView.findViewById(R.id.tvNamaItemDataKaryawan);
            email = itemView.findViewById(R.id.tvEmailDataKaryawan);
        }
    }
}
