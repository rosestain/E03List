package net.skhu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RecyclerView3Adapter extends RecyclerView.Adapter<RecyclerView3Adapter.ViewHolder> {
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
        TextView textView1, textView2;
        CheckBox checkBox;

        public ViewHolder(View view) {
            super(view);
            textView1 = view.findViewById(R.id.textView1);
            textView2 = view.findViewById(R.id.textView2);
            checkBox = view.findViewById(R.id.checkBox);
            textView1.setOnClickListener(this);
            checkBox.setOnCheckedChangeListener(this);

        }

        public void setData() {
            Memo memo = arrayList.get(getAdapterPosition());
            textView1.setText(memo.getTitle());
            textView2.setText(memo.getDateFormatted());
            checkBox.setChecked(memo.isChecked());
        }

        @Override
        public void onClick(View view)
        {
            int index = super.getAdapterPosition();
            listener.onMemoClicked(index);
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
        {
            Memo memo = arrayList.get(super.getAdapterPosition());
            memo.setChecked(isChecked);
            if(isChecked) ++checkedCount;
            else --checkedCount;
            onCheckCountChangeListener.onCheckCountChanged(checkedCount);
        }

    }

    LayoutInflater layoutInflater;
    ArrayList<Memo> arrayList;
    int checkedCount = 0;
    OnMemoClickListener listener;
    OnCheckCountChangeListener onCheckCountChangeListener;

    public RecyclerView3Adapter(Context context, ArrayList<Memo> arrayList, OnMemoClickListener listener,
                                OnCheckCountChangeListener onCheckCountChangeListener) {
        this.layoutInflater = LayoutInflater.from(context);
        this.arrayList = arrayList;
        this.listener = listener;
        this.onCheckCountChangeListener = onCheckCountChangeListener;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = layoutInflater.inflate(R.layout.memo2, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int index) {
        viewHolder.setData();
    }
}

