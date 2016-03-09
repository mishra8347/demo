package com.mishra.mock.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.mishra.mock.R;
import com.mishra.mock.appbase.BaseFragment;
import com.mishra.mock.utilities.GetDataFromServer;
import com.mishra.mock.utilities.NetworkResponseOuputVO;

import java.util.ArrayList;

/**
 * Created by Shailendra on 09-03-2016.
 */
public class FragmentDisignPartTwo extends BaseFragment implements GetDataFromServer.OnTaskComplete
{
    private Spinner spinner;
    private double latitude = 0;
    private double longitude = 0;
    private String label;

    @Override
    public int getFragmentLayoutId()
    {
        return R.layout.fragent_design_part_two;
    }

    @Override
    public int getContentContainerViewGroupIds()
    {
        return R.id.cantainerLinearlayout;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        initView();
        GetDataFromServer getDataFromServer = new GetDataFromServer("http://express-it.optusnet.com.au/sample.json", this);
        getDataFromServer.execute();
        super.onViewCreated(view, savedInstanceState);
    }

    private void initView()
    {
        spinner = (Spinner) getView().findViewById(R.id.spinner);
        Button navigate = (Button) getView().findViewById(R.id.navigateButton);
        navigate.setOnClickListener(this);
    }

    private void setDataToSpiner(Spinner spinner)
    {
        final ArrayList<NetworkResponseOuputVO> arrayList = GetDataFromServer.getDataList();
        ArrayList<String> spinnerArray = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            spinnerArray.add(arrayList.get(i).getName());
        }
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, spinnerArray); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                label = arrayList.get(position).getName();
                getTextViewById(R.id.car).setText("Car -" + arrayList.get(position).vehicalList.get(0).getCar());
                getTextViewById(R.id.train).setText("Train -" + arrayList.get(position).vehicalList.get(0).getTrain());
                latitude = Double.parseDouble(arrayList.get(position).locationList.get(0).getLat());
                longitude = Double.parseDouble(arrayList.get(position).locationList.get(0).getLng());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
    }

    private TextView getTextViewById(int resourceId)
    {
        return (TextView) getView().findViewById(resourceId);
    }

    @Override
    public void onClick(View v)
    {
        if (latitude != 0 && longitude != 0) {
            navigate();
        }
    }

    private void navigate()
    {
        String uriBegin = "geo:" + latitude + "," + longitude;
        String query = latitude + "," + longitude + "(" + label + ")";
        String encodedQuery = Uri.encode(query);
        String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
        Uri uri = Uri.parse(uriString);
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }


    @Override
    public void onTaskCompleted()
    {
        setDataToSpiner(spinner);
    }
}
