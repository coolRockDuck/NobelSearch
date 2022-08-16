package com.example.nobelprizesearch.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.nobelprizesearch.R;
import com.example.nobelprizesearch.databinding.FragmentNobelsearchBinding;
import com.example.nobelprizesearch.di.Provider;
import com.example.nobelprizesearch.model.domain.Field;
import com.example.nobelprizesearch.ui.viewModels.NobelPrizesViewModel;
import com.example.nobelprizesearch.ui.viewModels.factories.NobelPrizesViewModelFactory;

public class SearchForNobelFragment extends Fragment {

    private FragmentNobelsearchBinding binding;
    private NobelPrizesViewModel viewModel;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (!(context.getApplicationContext() instanceof Provider)) {
            throw new IllegalStateException("Activity must implement Provider interface");
        }

        NobelPrizesViewModelFactory factory = new NobelPrizesViewModelFactory(((Provider) context.getApplicationContext()).provideGetNobelUseCase());
        viewModel = new ViewModelProvider(requireActivity(), factory).get(NobelPrizesViewModel.class);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentNobelsearchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        createListeners();
        creteFieldPicker();
        subscribe();
    }

    private void createListeners() {
        binding.btnSearch.setOnClickListener(v -> {
            String nameOfChosenField = binding.dpmFieldPicker.getEditText().getText().toString();
            Field chosenFiled = Field.getFieldFromName(nameOfChosenField);
            String yearStart = binding.edtYearStart.getText().toString();
            String endYear = binding.edtYearEnd.getText().toString();

            if (!endYear.isEmpty()) {
                viewModel.fetchNobelPrizesForRangeOfYears(yearStart, endYear);
            } else {
                viewModel.fetchNobelPrizesForYear(yearStart, chosenFiled);
            }
        });
    }

    private void subscribe() {
        viewModel.getListOfPrizes().observe(getViewLifecycleOwner(), (state) -> {
            state.resolve((success) -> {
                System.out.println("Recived success state " + success);
            }, () -> {
                System.out.println("-------LOADING-----");
            }, (error) -> {
                System.out.println("Error: " + error);
            });
        });


    }


    private void creteFieldPicker() {
        String[] arrayOfFields = getResources().getStringArray(R.array.nobel_prize_fields);
        ArrayAdapter<String> a = new ArrayAdapter<>(
                requireContext(),
                R.layout.item_field_layout,
                arrayOfFields
        );

        AutoCompleteTextView picker = ((AutoCompleteTextView) binding.dpmFieldPicker.getEditText());
        picker.setAdapter(a);
        picker.setText(arrayOfFields[0], false);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}