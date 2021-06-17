package com.example.homework3forandroidgrup2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

  public   List <TaskMOdel> list = new ArrayList<>();
    public OnMoveTo onMoveTo;



    public void setOnMoveTo(OnMoveTo onMoveTo) {
        this.onMoveTo = onMoveTo;
    }

     public void addData (TaskMOdel taskMOdel){
        list.add(taskMOdel);
        notifyDataSetChanged();
     }

     // метод для принятия модели и внуттри него есть позиция и сразу понятна какая имееноо моделька
     public void editAdd(TaskMOdel taskMOdel, int position){
       list.set(position, taskMOdel);
        notifyDataSetChanged();
     }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_for, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( TaskAdapter.ViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

         TextView txtTitle, txtDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.title_text);
            txtDescription =  itemView.findViewById(R.id.desscription_text);

        }
         public void onBind (TaskMOdel taskMOdel){
            txtTitle.setText(taskMOdel.getTitle());
            txtDescription.setText(taskMOdel.getDescription());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override

                // с помощью интерфейса обрабатываем клик на item
                public void onClick(View v) {
                          // есть такой код getAdapterPosition принимает данные по нажатию на него по позицию
                    onMoveTo.onMoveTo(getAdapterPosition());
                }
            });

         }
    }
}
