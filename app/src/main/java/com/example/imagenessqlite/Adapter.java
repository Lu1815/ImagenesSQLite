package com.example.imagenessqlite;

import android.graphics.ColorSpace;
import android.text.Layout;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ImageHolder> {

    private ArrayList<ModelClass> mc;

    public Adapter(ArrayList<ModelClass> mc) {
        this.mc = mc;
    }

    @NonNull
    @NotNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        ImageHolder imageHolder = new ImageHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item,
                parent, false));
        return imageHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Adapter.ImageHolder holder, int position) {
        holder.tvImgName.setText("Nombre de la imagen: " + mc.get(position).getImgName());
        holder.ivImg.setImageBitmap(mc.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return mc.size();
    }

    public class ImageHolder extends RecyclerView.ViewHolder {
        private TextView tvImgName;
        private ImageView ivImg;

        public ImageHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvImgName = itemView.findViewById(R.id.tvImgDetails);
            ivImg = itemView.findViewById(R.id.imageTV);
        }
    }
}
