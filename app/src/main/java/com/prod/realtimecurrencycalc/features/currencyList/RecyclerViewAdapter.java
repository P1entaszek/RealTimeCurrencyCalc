package com.prod.realtimecurrencycalc.features.currencyList;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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
    private RecyclerViewAdapter.ClickListener clickListener;

    @Inject
    public RecyclerViewAdapter(ClickListener clickListener) {
        this.clickListener = clickListener;
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

        ViewHolder(View itemView) {
            super(itemView);

            currencyFullname = itemView.findViewById(R.id.currencyName);
            currencyShortcut = itemView.findViewById(R.id.currencyShortcut);
            currencyValue = itemView.findViewById(R.id.currencyValue);



           currencyValue.setOnClickListener(new View.OnClickListener() {
               @Override
                public void onClick(View v) {

                   clickListener.updateData(data.get(getAdapterPosition()).getCurrencyShortcut());
               }
           });
        }
    }

    public interface ClickListener {
        void updateData(String currencyName);
    }

    public void setData(List<CurrencyViewModel> data) {
        this.data = data;
        notifyDataSetChanged();
    }
}



