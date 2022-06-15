package comp3350.team10.presentation;

import comp3350.team10.R;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentDiaryCardContext extends Fragment {
    public FragmentDiaryCardContext() {
    } //Required empty public constructor


    public static FragmentDiaryCardContext newInstance() {
        FragmentDiaryCardContext fragment = new FragmentDiaryCardContext();
        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_diary_card_context, container, false);
    }
}