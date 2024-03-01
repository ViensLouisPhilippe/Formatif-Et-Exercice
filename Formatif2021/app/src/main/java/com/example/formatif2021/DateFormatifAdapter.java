package com.example.formatif2021;

import android.content.ClipData;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.formatif2021.databinding.DateformatifItemBinding;
import com.example.formatif2021.transfert.DateFormatif;

import java.util.ArrayList;
import java.util.List;

public class DateFormatifAdapter extends RecyclerView.Adapter<DateFormatifAdapter.ViewHolder>{
    public List<DateFormatif> list;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public DateformatifItemBinding itemLongBinding;

        public ViewHolder(DateformatifItemBinding itemLongBinding) {
            super(itemLongBinding.getRoot());
            // Define click listener for the ViewHolder's View

            this.itemLongBinding = itemLongBinding;
        }
    }

    /**
     * Initialize the dataset of the Adapter
     */
    public DateFormatifAdapter() { list = new ArrayList<>(); }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        DateformatifItemBinding itemLongBinding = DateformatifItemBinding
                .inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        return new ViewHolder(itemLongBinding);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        DateFormatif dateFormatif = list.get(position);
        viewHolder.itemLongBinding.tvAnnee.setText(String.valueOf(dateFormatif.annee));
        viewHolder.itemLongBinding.tvMois.setText(String.valueOf(dateFormatif.mois));
        viewHolder.itemLongBinding.tvJour.setText(String.valueOf(dateFormatif.jour));
        viewHolder.itemLongBinding.tvJourdeLaSemaine.setText(dateFormatif.jourDeLaSemaine);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return list.size();
    }
}

