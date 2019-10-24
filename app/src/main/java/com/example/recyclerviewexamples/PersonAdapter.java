package com.example.recyclerviewexamples;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder>
{

    private ArrayList<Person> people;
    ItemClicked activity;

    public interface  ItemClicked   //main aktivitye hangi item'in tiklandigini gondermek icin interface yarattik (setonclicklister'i daha verimli yaptik bu sayede)
    {
        void onItemClicked(int index);
    }

    public PersonAdapter (Context context, ArrayList<Person> list)
    {
        people = list;
        activity = (ItemClicked) context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        ImageView ivPref;
        TextView tvName,tvSurname;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivPref = itemView.findViewById(R.id.ivPref);
            tvName = itemView.findViewById(R.id.tvName);
            tvSurname = itemView.findViewById(R.id.tvSurname);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    activity.onItemClicked(people.indexOf((Person) view.getTag()));
                }
            });
        }
    }

    @NonNull
    @Override
    public PersonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_items, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonAdapter.ViewHolder viewHolder, int position) //this method runs for every item in arraylist
    {
        viewHolder.itemView.setTag(people.get(position));

        viewHolder.tvName.setText(people.get(position).getName());
        viewHolder.tvSurname.setText(people.get(position).getSurname());

        if(people.get(position).getPreference().equals("bus"))
        {
            viewHolder.ivPref.setImageResource(R.drawable.bus);
        }
        else
        {
            viewHolder.ivPref.setImageResource(R.drawable.plane);
        }
    }

    @Override
    public int getItemCount()
    {

        return people.size(); //size of the list
    }
}
