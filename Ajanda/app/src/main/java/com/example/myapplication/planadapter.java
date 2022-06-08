package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class planadapter extends RecyclerView.Adapter<planadapter.temsilidolder> {
    public List<plan> planList;
    Context context;

    public planadapter(List<plan> planList, Context context) {
        this.planList = planList;
        this.context = context;
    }
    @NonNull
    @Override
    public temsilidolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.planholder,parent,false);
        return new temsilidolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull temsilidolder holder, int position) {
        plan plan=planList.get(position);
        holder.basliktext.setText(plan.getBaslik());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(holder.cardView.getContext(), pkaydetActivity.class);
                intent.putExtra("info", "old");
                intent.putExtra("planId", planList.get(holder.getAdapterPosition()).getBaslik());




                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return planList.size();
    }

    public class temsilidolder extends RecyclerView.ViewHolder{

        private TextView basliktext;
        private CardView cardView;
        public temsilidolder(@NonNull View itemView) {
            super(itemView);
            basliktext=itemView.findViewById(R.id.baslik_text);
            cardView=itemView.findViewById(R.id.cardview);

        }
    }
}
