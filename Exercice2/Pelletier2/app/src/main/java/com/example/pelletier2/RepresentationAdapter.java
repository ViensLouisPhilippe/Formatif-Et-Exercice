package com.example.pelletier2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RepresentationAdapter extends RecyclerView.Adapter<RepresentationAdapter.ViewHolder> {

    private List<Representation> representations = new ArrayList<>();

    //Méthode pour mettre à jour les données des représentations
    public void setData(List<Representation> representations) {
        this.representations = representations;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_representation, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Representation representation = representations.get(position);
        holder.nombreTextView.setText(String.valueOf(representation.getNombre()));
        holder.descriptionTextView.setText(representation.getDescription());
        holder.representationTextView.setText(representation.getRepresentation());
    }

    @Override
    public int getItemCount() {
        return representations.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombreTextView;
        TextView descriptionTextView;
        TextView representationTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.nombreTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            representationTextView = itemView.findViewById(R.id.representationTextView);
        }
    }
}
