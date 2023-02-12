package com.example.eotassignment202302;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class MenuThanksFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final Intent intent = getActivity().getIntent();
        final String menuName = intent.getStringExtra("menuName");
        final String menuPrice = intent.getStringExtra("menuPrice");

        final View v = inflater.inflate(
                R.layout.fragment_menu_thanks,
                container, false);

        final TextView tvMenuName = v.findViewById(R.id.tvMenuName);
        tvMenuName.setText(menuName);
        final TextView tvMenuPrice = v.findViewById(R.id.tvMenuPrice);
        tvMenuPrice.setText(menuPrice);

        v.findViewById(R.id.btBackButton).
                setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(), FragmentActivity.class));
                    }
                });


        return v;
    }
}