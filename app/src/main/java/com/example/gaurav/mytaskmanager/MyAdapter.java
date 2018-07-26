package com.example.gaurav.mytaskmanager;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Alarm> alarmList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView task, time;

        public MyViewHolder(View itemView) {
            super(itemView);

            task = (TextView) itemView.findViewById(R.id.taskName);
            time = (TextView) itemView.findViewById(R.id.taskTime);
        }
    }

    public MyAdapter(List<Alarm> alarmList) {
        this.alarmList = alarmList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.alarm_list, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int i) {

        Alarm alarm = alarmList.get(i);

        holder.task.setText(alarm.getTask());
        holder.time.setText(alarm.getalarmTime());

    }

    @Override
    public int getItemCount() {
        return alarmList.size();
    }
}
