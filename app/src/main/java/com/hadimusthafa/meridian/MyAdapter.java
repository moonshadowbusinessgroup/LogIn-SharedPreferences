package com.hadimusthafa.meridian;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private final LayoutInflater inflater;
    private final ArrayList<MyModel> dataModelArrayList;
    private final Context mContext;

    public MyAdapter(Context ctx, ArrayList<MyModel> dataModelArrayList) {
        inflater = LayoutInflater.from(ctx);
        this.dataModelArrayList = dataModelArrayList;
        mContext = ctx;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(mContext).load(dataModelArrayList.get(position).getAvatar()).into(holder.image);
        holder.name.setText(dataModelArrayList.get(position).getFirst_name());
        holder.email.setText(dataModelArrayList.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView email;
        CircleImageView image;

        public MyViewHolder(final View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
        }
    }
}
