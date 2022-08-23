package com.example.nobelprizesearch.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nobelprizesearch.databinding.ItemNobelprizeCardBinding;
import com.example.nobelprizesearch.model.domain.NobelPrize;

import java.util.List;
import java.util.stream.Collectors;

public class NobelPrizesArrayAdapter extends RecyclerView.Adapter<NobelPrizesArrayAdapter.NobelPrizesViewHolder> {

    protected final List<NobelPrize> listOfPrizes;

    public NobelPrizesArrayAdapter(List<NobelPrize> listOfPrizes) {
        this.listOfPrizes = listOfPrizes;
    }

    @NonNull
    @Override
    public NobelPrizesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNobelprizeCardBinding binding = ItemNobelprizeCardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new NobelPrizesViewHolder(binding);
    }

    @Override
    public int getItemCount() {
        return listOfPrizes.size();
    }

    @Override
    public void onBindViewHolder(@NonNull NobelPrizesViewHolder holder, int position) {
        NobelPrize activePrize = listOfPrizes.get(position);
        ItemNobelprizeCardBinding binding = holder.binding;
        binding.tvDate.setText(activePrize.getDateAwarded());

        List<String> namesOfLaureates = activePrize.getLaureateList().stream().map((l) -> l.getLaureateName().toString()).collect(Collectors.toList());
        binding.tvLaureates.setText(String.join(", ", namesOfLaureates));

        binding.tvCategory.setText(activePrize.getCategory().getCategoryFullName());
    }

    static class NobelPrizesViewHolder extends RecyclerView.ViewHolder {

        private ItemNobelprizeCardBinding binding;

        public NobelPrizesViewHolder(ItemNobelprizeCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
