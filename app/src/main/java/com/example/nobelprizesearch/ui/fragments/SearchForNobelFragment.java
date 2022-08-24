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
import androidx.navigation.fragment.NavHostFragment;

import com.example.nobelprizesearch.R;
import com.example.nobelprizesearch.databinding.FragmentNobelsearchBinding;
import com.example.nobelprizesearch.di.ApplicationComponent;
import com.example.nobelprizesearch.di.MainApplication;
import com.example.nobelprizesearch.model.domain.Field;
import com.example.nobelprizesearch.model.domain.NobelPrizesSerializableWrapper;
import com.example.nobelprizesearch.ui.viewModels.NobelPrizesViewModel;

import javax.inject.Inject;

public class SearchForNobelFragment extends Fragment {

    @Inject
    NobelPrizesViewModel viewModel;

    private FragmentNobelsearchBinding binding;

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
        binding.rbtnGroup.setOnCheckedChangeListener((v, checkedBtnID) -> {
            if (checkedBtnID == binding.rbtnSearchRange.getId()) {
                binding.edtYear.setHint(R.string.from);
                binding.edtYearEnd.setHint(R.string.until);
                binding.edtYearEnd.setEnabled(true);
            } else if (checkedBtnID == binding.rbtnSearchSpecific.getId()) {
                binding.edtYear.setHint(R.string.year);
                binding.edtYearEnd.setEnabled(false);
                binding.edtYearEnd.setHint("");
            }
        });

        binding.btnSearch.setOnClickListener(v -> {
            boolean isSearchingRange = binding.rbtnSearchRange.isChecked();

            String nameOfChosenField = binding.dpmFieldPicker.getEditText().getText().toString();
            Field chosenFiled = Field.getFieldFromName(nameOfChosenField);
            String yearStart = binding.edtYear.getText().toString();

            if (isSearchingRange) {
                String endYear = binding.edtYearEnd.getText().toString();
                viewModel.fetchNobelPrizesForRangeOfYears(yearStart, endYear); // todo add support for field
            } else {
                viewModel.fetchNobelPrizesForYear(yearStart, chosenFiled);
            }
        });
    }

    private void subscribe() {
        viewModel.getListOfPrizes().observe(getViewLifecycleOwner(), (state) -> {
            if (state == null) {
                return;
            }
            state.resolve((success) -> {
                binding.progressBar.setVisibility(View.GONE);

                Bundle args = new Bundle();
                args.putSerializable(SpecificNobelPrizesFragment.SPECIFIC_NOBEL_PRIZES_KEY, new NobelPrizesSerializableWrapper(success));
                NavHostFragment.findNavController(this).navigate(R.id.action_destination_SearchForNobelFragment_to_destination_SpecificNobelPrizesFragment, args);

                viewModel.nobelPrizesHasBeenConsumed();
            }, () -> {
                binding.progressBar.setVisibility(View.VISIBLE);
            }, (error) -> {
                binding.progressBar.setVisibility(View.GONE);
                System.out.println("Error: " + error);
            });
        });
    }

    private void creteFieldPicker() {
        String[] arrayOfFields = getResources().getStringArray(R.array.nobel_prize_fields);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                requireContext(),
                R.layout.item_field_layout,
                arrayOfFields
        );

        AutoCompleteTextView picker = ((AutoCompleteTextView) binding.dpmFieldPicker.getEditText());
        picker.setAdapter(arrayAdapter);
        picker.setText(arrayOfFields[0], false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}