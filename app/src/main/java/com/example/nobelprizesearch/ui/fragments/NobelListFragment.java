package com.example.nobelprizesearch.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nobelprizesearch.di.MainApplication;
import com.example.nobelprizesearch.databinding.FragmentNobelslistBinding;
import com.example.nobelprizesearch.di.ApplicationComponent;
import com.example.nobelprizesearch.ui.adapters.AllNobelPrizesArrayAdapter;
import com.example.nobelprizesearch.ui.viewModels.ListNobelPrizesViewModel;

import javax.inject.Inject;

public class NobelListFragment extends Fragment {


    @Inject
    ListNobelPrizesViewModel viewModel;

    private FragmentNobelslistBinding binding;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ApplicationComponent applicationComponent = ((MainApplication) requireActivity().getApplication()).applicationComponent;
        applicationComponent.inject(this);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentNobelslistBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getListOfPrizes().observe(getViewLifecycleOwner(), (list) -> {
            RecyclerView rvNobelPrizes = binding.rvListOfNobelPrizes;
            list.resolve((success) -> {
                System.out.println("Succes has size = " + success.size());
                if (rvNobelPrizes.getAdapter() == null) {
                    rvNobelPrizes.setAdapter(new AllNobelPrizesArrayAdapter(success));
                } else {
                    ((AllNobelPrizesArrayAdapter) rvNobelPrizes.getAdapter()).addNobelPrizes(success);
                }
            }, () -> {
                System.out.println("Waiting ");
            }, (erorr) -> {
                System.out.println("ERROR + " + erorr);
            });
        });


        binding.rvListOfNobelPrizes.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    System.out.println("Fetching next");
                    viewModel.fetchNextNobelPrizes();
                }
            }
        });

        if (viewModel.getListOfPrizes().getValue() == null) {
            viewModel.fetchNextNobelPrizes();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}