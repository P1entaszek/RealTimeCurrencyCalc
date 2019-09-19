package com.prod.realtimecurrencycalc.features.currencyList;

import android.annotation.SuppressLint;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.blongho.country_data.World;
import com.bumptech.glide.Glide;
import com.prod.realtimecurrencycalc.R;
import com.prod.realtimecurrencycalc.datasource.model.CurrencyViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<CurrencyViewModel> data;
    private TouchListener touchListener;
    private ClickListener clickListener;


    @Inject
    public RecyclerViewAdapter(TouchListener touchListener, ClickListener clickListener) {
        this.clickListener = clickListener;
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
        holder.currencyImage.setImageResource(World.getFlagOf(data.get(position).getCountryCode()));
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
            currencyImage = itemView.findViewById(R.id.currencyImage);
            constraintLayoutContainer = itemView.findViewById(R.id.constraintLayout);


            constraintLayoutContainer.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (getAdapterPosition() != 0) {
                        touchListener.updateData(data.get(getAdapterPosition()).getCurrencyShortcut());
                    }
                    return true;
                }
            });
        currencyValue.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                clickListener.recalculateData(Double.valueOf(currencyValue.getText().toString()));
                return true;
            }
        });
        }
    }


    public interface ClickListener {
        void recalculateData(Double currencyMultiplier);
    }

    public interface TouchListener {
        void updateData(String currencyName);
    }

    public void setData(List<CurrencyViewModel> data) {
        this.data = data;
        notifyDataSetChanged();
    }
}



