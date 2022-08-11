package com.example.nobelprizesearch.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;

import com.example.nobelprizesearch.data.NobelPrizeClient;
import com.example.nobelprizesearch.databinding.FragmentFirstBinding;
import com.example.nobelprizesearch.di.Provider;
import com.example.nobelprizesearch.model.domain.Field;
import com.example.nobelprizesearch.model.domain.NobelPrize;
import com.example.nobelprizesearch.data.NobelPrizeService;
import com.example.nobelprizesearch.ui.viewModels.NobelPrizesViewModel;
import com.example.nobelprizesearch.ui.viewModels.NobelPrizesViewModelFactory;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private NobelPrizesViewModel viewModel;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (!(context instanceof Provider)) {
            throw new IllegalStateException("Activity must implement Provider interface");
        }

        NobelPrizesViewModelFactory factory = new NobelPrizesViewModelFactory(((Provider) context).provideClient());
        viewModel = new ViewModelProvider(requireActivity(), factory).get(NobelPrizesViewModel.class);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(ignored -> viewModel.fetchNobelPrizesForYear("1900", Field.CHE));

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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}