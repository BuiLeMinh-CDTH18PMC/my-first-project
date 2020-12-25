package com.example.practive;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TinTucAdapter extends RecyclerView.Adapter<TinTucAdapter.ItemViewHolder> {

    private LayoutInflater lInflater;
    public LinkedList<TinTuc> lst;

    public TinTucAdapter(Context context, LinkedList<TinTuc> _lst) {
        lInflater = LayoutInflater.from(context);
        lst = _lst;
    }

    @NonNull
    @Override
    public TinTucAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = lInflater.inflate(R.layout.item, parent, false);
        return new ItemViewHolder(itemView, this);
    }

    public void onBindViewHolder(ItemViewHolder holder, int position) {
        TinTuc mCurrent = lst.get(position);
        holder.txtTieuDe.setText(mCurrent.getTieuDe());
        holder.txtNoiDung.setText(mCurrent.getNoiDung());

        if(mCurrent.getLoaiDanhMuc() == 1)
            holder.im.setImageResource(R.drawable.icon1);
        if(mCurrent.getLoaiDanhMuc() == 2)
            holder.im.setImageResource(R.drawable.icon2);
        if(mCurrent.getLoaiDanhMuc() == 3)
            holder.im.setImageResource(R.drawable.icon3);
    }

    @Override
    public int getItemCount() {
        return lst.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public final TextView txtTieuDe;
        public final TextView txtNoiDung;
        public final ImageView im;
        final TinTucAdapter mAdapter;

        public ItemViewHolder(@NonNull View itemView, TinTucAdapter adapter) {
            super(itemView);
            txtTieuDe = itemView.findViewById(R.id.txtTieuDe);
            txtNoiDung = itemView.findViewById(R.id.txtNoiDung);
            im = itemView.findViewById(R.id.img);
            this.mAdapter = adapter;
        }
    }
}
