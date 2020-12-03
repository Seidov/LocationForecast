package com.sultanseidov.locationforecast.view.fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sultanseidov.locationforecast.R;
import com.sultanseidov.locationforecast.repository.model.AutoCompleteSerachModel;
import com.sultanseidov.locationforecast.repository.room.DatabaseClient;
import com.sultanseidov.locationforecast.view.adapter.AutoCompleteAdapter;
import com.sultanseidov.locationforecast.viewModel.AutocompleteSearchViewModel;

import java.util.ArrayList;
import java.util.List;

public class AutoCompleteSearchFragment extends Fragment {

    View view;
    SearchView srvAutocompleteSearch;
    AutocompleteSearchViewModel viewModel;
    AutoCompleteAdapter adapter = new AutoCompleteAdapter(new ArrayList<>());
    RecyclerView recyclerView;
    LinearLayout linearBack;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_auto_complete_search, container, false);

        initUIComponent();
        initLastSearchesLoacationRecyclerView();

        viewModel = ViewModelProviders.of(getActivity()).get(AutocompleteSearchViewModel.class);

        getLastQuery();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        srvAutocompleteSearch.setOnQueryTextFocusChangeListener((view, hasFocus) -> {
            if (hasFocus) {
                showInputMethod(view.findFocus());
            }
        });

        srvAutocompleteSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.length() >= 2) {
                    if (isNetworkConnected()) {
                        refreshSearchResponce(query);
                    }
                } else {
                    adapter.updateAutoCompleteSearchData(new ArrayList<>());

                }


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() >= 2) {
                    if (isNetworkConnected()) {
                        refreshSearchResponce(newText);
                    }
                } else {
                    adapter.updateAutoCompleteSearchData(new ArrayList<>());

                }

                return false;
            }
        });

        linearBack.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.action_autoCompleteSearchFragment_pop);
        });

    }


    private void initLastSearchesLoacationRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    private void initUIComponent() {
        srvAutocompleteSearch = view.findViewById(R.id.srvAutocompleteSearch);
        recyclerView = view.findViewById(R.id.rvAutocompleteSearch);
        linearBack = view.findViewById(R.id.linearBack);
    }

    private boolean isNetworkConnected() {
        ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        //progressDialog.dismiss();
        //showSnackBar();
        return networkInfo != null && networkInfo.isConnected();
    }

    private void refreshSearchResponce(String query) {

        viewModel.refreshAutocompleteSearchModelData(query);
        observerAutocompleteSearchViewModel();

    }

    private void observerAutocompleteSearchViewModel() {

        viewModel.resAutocompleteSearchModelMutableLiveData.observe(this, resAutocompleteSearchModel -> {

            if (resAutocompleteSearchModel != null) {
                adapter.updateAutoCompleteSearchData(resAutocompleteSearchModel);
            }

        });

        viewModel.resAutocompleteSearchModelMutableLiveDataLoadError.observe(this, isError -> {
            if (isError != null) {

            }
        });

        viewModel.resAutocompleteSearchModelMutableLiveDataLoading.observe(this, isLoading -> {

        });


    }

    private void setLastQuery(AutoCompleteSerachModel resAutocompleteSearchModel) {
        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {


                //adding to database
                DatabaseClient.getInstance(getContext()).getAppDatabase()
                        .autoCompleteSearchDao()
                        .insert(resAutocompleteSearchModel);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                //finish();
                //startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(getContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveTask st = new SaveTask();
        st.execute();
    }

    private void getLastQuery() {
        class GetTasks extends AsyncTask<Void, Void, List<AutoCompleteSerachModel>> {

            @Override
            protected List<AutoCompleteSerachModel> doInBackground(Void... voids) {
                List<AutoCompleteSerachModel> taskList = DatabaseClient
                        .getInstance(getContext())
                        .getAppDatabase()
                        .autoCompleteSearchDao()
                        .getAllautoCompleteSerachModel();
                return taskList;
            }

            @Override
            protected void onPostExecute(List<AutoCompleteSerachModel> autoCompleteSerachModels) {
                super.onPostExecute(autoCompleteSerachModels);
                adapter.updateAutoCompleteSearchData(autoCompleteSerachModels);
            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();
    }

    private void showInputMethod(View view) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(view, 0);
        }
    }
}