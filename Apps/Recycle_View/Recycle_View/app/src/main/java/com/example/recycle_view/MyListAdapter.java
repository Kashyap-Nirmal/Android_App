package com.example.recycle_view;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {
    private MyListData[] listdata;
    private Context context;
    String case1="";
    int case2;
    public MyListAdapter(MyListData[] listdata) {
        this.listdata = listdata;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context=parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View listItem = layoutInflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) throws ArrayIndexOutOfBoundsException{
        try{
            final MyListData myListData = listdata[position];
            holder.textView.setText(listdata[position].getDescription());
            holder.imageView.setImageResource(listdata[position].getImgId());
            holder.cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                case1= myListData.getDescription();
                case2= myListData.getImgId();
                Intent i = new Intent(context,MainActivity.class);
                i.putExtra("Key",case1);
                i.putExtra("Key1",case2);
                context.startActivity(i);
                }
            });
        }
        catch (ArrayIndexOutOfBoundsException e) {
            Toast.makeText(context,"OutOfBound",Toast.LENGTH_SHORT);
        }
    }

    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public ConstraintLayout cl;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            cl =itemView.findViewById(R.id.cl);
        }
    }
}
