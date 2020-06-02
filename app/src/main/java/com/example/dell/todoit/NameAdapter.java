package com.example.dell.todoit;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

public class NameAdapter extends RecyclerView.Adapter<NameAdapter.MyViewHolder> {
    private ArrayList<NewMission>Mision;
    public CardView cardss;
    int i;
    public DeleteCallback callback;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final CardView cardss;
        public TextView Name;
        public  TextView Date;
        public TextView Important;
        public TextView Time;
        public TextView About;
        public View view;
        public MyViewHolder(View v) {
            super(v);
            Name = v.findViewById(R.id.TexetMission);
            Date=v.findViewById(R.id.Date);
            cardss = v.findViewById(R.id.cv_todo);
             Time=(TextView)v.findViewById(R.id.Time);
            About=v.findViewById(R.id.About);
            Important=v.findViewById(R.id.Important);
            view = v;
        }
    }
    public NameAdapter(ArrayList<NewMission>Mision, DeleteCallback callback) {
        this.Mision=Mision;
        this.callback = callback;
    }
    @Override
    public NameAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout, parent, false);
        return new MyViewHolder(v);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.Name.setText(Mision.get(position).getName());
        holder.Date.setText(Mision.get(position).getDate());
        holder.Time.setText(Mision.get(position).getTime());
        holder.About.setText(Mision.get(position).getAbout());
        i=Mision.get(position).getImportancelevel();
        holder.Important.setText(String.valueOf("Important level"+":"+" "+i));
        holder.view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                callback.onLongClicked(position);
                return true;
            }
        });
        if (i==2){
            holder.cardss.setCardBackgroundColor(Color.RED);
        }else if (i==1){
          holder.cardss.setCardBackgroundColor(Color.YELLOW);
        }else {
            holder.cardss.setCardBackgroundColor(Color.WHITE);
        }


         }


    @Override
    public int getItemCount() {
        return Mision.size();
    }

    interface DeleteCallback{
        void onLongClicked(int i);
    }

}

