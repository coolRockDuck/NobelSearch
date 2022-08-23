package com.example.nobelprizesearch.ui.adapters;

import com.example.nobelprizesearch.model.domain.NobelPrize;

import java.util.List;
/** ''Infinite'' scroll version of {@link NobelPrizesArrayAdapter}.*/
public class AllNobelPrizesArrayAdapter extends NobelPrizesArrayAdapter{
    public AllNobelPrizesArrayAdapter(List<NobelPrize> listOfPrizes) {
        super(listOfPrizes);
    }

    public void addNobelPrizes(List<NobelPrize> newNobelPrizes) {
        int indexBeforeChange = listOfPrizes.size();
        listOfPrizes.addAll(newNobelPrizes);
        notifyItemChanged(indexBeforeChange, listOfPrizes.size());
    }
}
