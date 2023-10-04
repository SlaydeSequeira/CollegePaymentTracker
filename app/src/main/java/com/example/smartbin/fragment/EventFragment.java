package com.example.smartbin.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartbin.R;
import com.example.smartbin.adapter.DumpTruckAdapter;
import com.example.smartbin.adapter.EventAdapter;
public class EventFragment extends Fragment {
    RecyclerView recyclerView;
    String[] text1 = new String[100];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);

        // Update the array of college events
        String[] collegeEvents = {
                "Cresendo",
                "Synergy",
                "CRMD",
                "Athlos"
        };

        // Array of descriptions for each college event
        String[] eventDescriptions = {
                "Join us for Cresendo event description.",
                "Participate in Synergy event description.",
                "Join our CRMD event description.",
                "Volunteer for Euphoria event description."
        };

        // Array of image URLs corresponding to each college event
        String[] imageUrls ={
                "https://firebasestorage.googleapis.com/v0/b/smartbin-f251c.appspot.com/o/Screenshot_2023-10-04-07-48-04-191_com.instagram.android-edit.jpg?alt=media&token=4df0c623-51e0-4fe3-bf67-df0183a5a43a&_gl=1*m6bmar*_ga*MzUyMDc3MjA3LjE2NzAzMDU3MzI.*_ga_CW55HF8NVT*MTY5NjM4NjA3MC45NS4xLjE2OTYzODYzODQuMzQuMC4w",
                "https://firebasestorage.googleapis.com/v0/b/smartbin-f251c.appspot.com/o/Screenshot_2023-10-04-07-47-30-954_com.instagram.android-edit.jpg?alt=media&token=2e4ad144-80a1-4920-807a-7d696c56b263&_gl=1*1w7krta*_ga*MzUyMDc3MjA3LjE2NzAzMDU3MzI.*_ga_CW55HF8NVT*MTY5NjM4NjA3MC45NS4xLjE2OTYzODYzNTguNjAuMC4w",
                "https://firebasestorage.googleapis.com/v0/b/smartbin-f251c.appspot.com/o/Screenshot_2023-10-04-07-47-02-929_com.google.android.apps.docs-edit.jpg?alt=media&token=99df3901-c236-4bec-ac27-c13f36b37420&_gl=1*11ej0vb*_ga*MzUyMDc3MjA3LjE2NzAzMDU3MzI.*_ga_CW55HF8NVT*MTY5NjM4NjA3MC45NS4xLjE2OTYzODYzMDYuNDYuMC4w",
                "https://firebasestorage.googleapis.com/v0/b/smartbin-f251c.appspot.com/o/Screenshot_2023-10-04-07-50-04-262_com.instagram.android-edit.jpg?alt=media&token=3a30d9c5-3b36-4be5-b87a-bc5ec30cce3c&_gl=1*m4u708*_ga*MzUyMDc3MjA3LjE2NzAzMDU3MzI.*_ga_CW55HF8NVT*MTY5NjM4NjA3MC45NS4xLjE2OTYzODY0MDcuMTEuMC4w"
        };

        EventAdapter eventAdapter = new EventAdapter(getActivity(), collegeEvents, 4, imageUrls, eventDescriptions, text1);
        recyclerView.setAdapter(eventAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }
}
