package com.prod.realtimecurrencycalc.features.currencyList;

import android.annotation.SuppressLint;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.prod.realtimecurrencycalc.R;
import com.prod.realtimecurrencycalc.datasource.model.CurrencyViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<CurrencyViewModel> data;
    private TouchListener touchListener;

    @Inject
    public RecyclerViewAdapter(TouchListener touchListener) {
        this.touchListener = touchListener;
        data = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
            holder.currencyShortcut.setText(data.get(position).getCurrencyShortcut());
            holder.currencyValue.setText(data.get(position).getCurrencyValue().toString());
            holder.currencyFullname.setText(data.get(position).getCurrencyFullName());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView currencyImage;
        private TextView currencyShortcut;
        private TextView currencyFullname;
        private EditText currencyValue;
        private ConstraintLayout constraintLayoutContainer;

        @SuppressLint("ClickableViewAccessibility")
        ViewHolder(View itemView) {
            super(itemView);

            currencyFullname = itemView.findViewById(R.id.currencyName);
            currencyShortcut = itemView.findViewById(R.id.currencyShortcut);
            currencyValue = itemView.findViewById(R.id.currencyValue);



           currencyValue.setOnTouchListener(new View.OnTouchListener() {
               @Override
               public boolean onTouch(View v, MotionEvent event) {
                   if(getAdapterPosition()==0){
                       touchListener.recalculateData(data.get(0).getCurrencyValue());
                   }
                   else{
                       touchListener.updateData(data.get(getAdapterPosition()).getCurrencyShortcut());
                   }

               return true;
               }
           });
        }
    }

    public interface TouchListener {
        void updateData(String currencyName);
        void recalculateData(Double currencyMultiplier);
    }

    public void setData(List<CurrencyViewModel> data) {
        this.data = data;
        notifyDataSetChanged();
    }
}



