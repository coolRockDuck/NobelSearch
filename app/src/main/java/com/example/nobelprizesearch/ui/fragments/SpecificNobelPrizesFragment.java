package com.example.nobelprizesearch.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nobelprizesearch.databinding.FragmentSpecificNobelprizesBinding;
import com.example.nobelprizesearch.model.domain.NobelPrize;
import com.example.nobelprizesearch.model.domain.NobelPrizesSerializableWrapper;
import com.example.nobelprizesearch.ui.adapters.NobelPrizesArrayAdapter;

import java.util.List;

public class SpecificNobelPrizesFragment extends Fragment {
    public static final String SPECIFIC_NOBEL_PRIZES_KEY = "SPECIFIC_NOBEL_PRIZES_KEY";
    private List<NobelPrize> listOfSpecificNobelPrizes;
    private FragmentSpecificNobelprizesBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSpecificNobelprizesBinding.inflate(inflater, container, false);
        listOfSpecificNobelPrizes = ((NobelPrizesSerializableWrapper) getArguments().get(SPECIFIC_NOBEL_PRIZES_KEY)).prizeList;
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.rvSpecificNobelPrizes.setAdapter(new NobelPrizesArrayAdapter(listOfSpecificNobelPrizes));
    }
}
