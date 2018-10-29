package com.example.joao.notificationreader.view;

import android.arch.persistence.room.RoomDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import com.example.joao.notificationreader.model.NotificationDatabase;

/**
 * Created by João Carlos on 10/29/18.
 * Biox Pecuária Moderna
 * desenvolvedorberrante@bioxbr.com
 */
public class FuckFragment extends Fragment {

    public static FuckFragment newInstance() {

        Bundle args = new Bundle();

        FuckFragment fragment = new FuckFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
