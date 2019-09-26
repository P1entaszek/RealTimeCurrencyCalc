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
import com.prod.realtimecurrencycalc.R;
import com.prod.realtimecurrencycalc.datasource.model.CurrencyViewModel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<CurrencyViewModel> data;
    private LongClickListener longClickListener;
    private ClickListener clickListener;
    private Map<String, Double> currenciesMultipliedMap;

    @Inject
    public RecyclerViewAdapter(LongClickListener longClickListener, ClickListener clickListener) {
        this.clickListener = clickListener;
        this.longClickListener = longClickListener;
        data = new ArrayList<>();
        currenciesMultipliedMap = new HashMap<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Double currencyValue = currenciesMultipliedMap.get(data.get(position).getCurrencyShortcut());
        holder.currencyShortcut.setText(data.get(position).getCurrencyShortcut());
        holder.currencyValue.setText(roundTheValue(currencyValue).toString());
        holder.currencyFullname.setText(data.get(position).getCurrencyFullName());
        if(data.get(position).getCurrencyShortcut().equalsIgnoreCase("EUR")) holder.currencyImage.setImageResource(R.drawable.eu);
        else holder.currencyImage.setImageResource(World.getFlagOf(data.get(position).getCountryCode()));
    }

    private Double roundTheValue(Double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
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


            constraintLayoutContainer.setOnLongClickListener((v) -> {
                if (getAdapterPosition() != 0) {
                    longClickListener.updateData(data.get(getAdapterPosition()).getCurrencyShortcut());
                }
                return true;
            });
            currencyValue.setOnEditorActionListener((v, actionId, event) -> {
                if (getAdapterPosition() == 0)
                    clickListener.recalculateData(Double.valueOf(currencyValue.getText().toString()));
                return true;
            });
        }
    }


    public interface ClickListener {
        void recalculateData(Double currencyMultiplier);
    }

    public interface LongClickListener {
        void updateData(String currencyName);
    }

    public void setData(List<CurrencyViewModel> data, final Double currencyMultiplier) {
        this.data = data;
        currenciesMultipliedMap.clear();

        for (int i = 1; i < data.size(); i++) {
            Double currencyValue = data.get(i).getCurrencyValue();
            currencyValue *= currencyMultiplier;
            currenciesMultipliedMap.put(data.get(i).getCurrencyShortcut(), currencyValue);
        }
        currenciesMultipliedMap.put(data.get(0).getCurrencyShortcut(), currencyMultiplier);
        notifyDataSetChanged();
    }
}



