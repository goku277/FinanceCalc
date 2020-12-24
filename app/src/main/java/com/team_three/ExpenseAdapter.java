package com.team_three;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder>{
    private static final String TAG="ExpenseAdapter";

    ArrayList<String> description= new ArrayList<>(), money= new ArrayList<>(), note= new ArrayList<>();
    Context ctx;
    public ExpenseAdapter(Context ctx, ArrayList<String> description, ArrayList<String> money, ArrayList<String> note) {
        this.description= description;
        this.money= money;
        this.note= note;
        this.ctx= ctx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent,false);
        ViewHolder viewHolder= new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.desc.setText(description.get(position));
        holder.money.setText(money.get(position));
        holder.note.setText(note.get(position));
    }

    @Override
    public int getItemCount() {
        return description.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView desc, money, note;
        RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            desc= itemView.findViewById(R.id.desc_id_);
            money= itemView.findViewById(R.id.money_id_);
            note= itemView.findViewById(R.id.note_id_);
            relativeLayout= itemView.findViewById(R.id.parent_layout);
        }
    }
}
