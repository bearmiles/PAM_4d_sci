package com.example.splashscreen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ekr5Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ekr5Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ekr5Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ekr5Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ekr5Fragment newInstance(String param1, String param2) {
        ekr5Fragment fragment = new ekr5Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_ekr5, container, false);
        SettingsContext context = new SettingsContext();
        Button bt = root.findViewById(R.id.button6);



        TextView timer;
        SeekBar sb;
        sb = root.findViewById(R.id.seekBar);
        timer = root.findViewById(R.id.timer);
        EditText czas;
        czas = root.findViewById(R.id.editTextText);
        RadioGroup radioGroup;
        radioGroup = root.findViewById(R.id.radio_group);
        Switch isDarkMode;
        isDarkMode = root.findViewById(R.id.switch1);


        bt.setOnClickListener(v -> {
                context.setProgress(sb.getProgress());
                double temp_czas = Double.parseDouble(czas.getText().toString());
                context.setTimer(temp_czas);
                context.setRadio_id(radioGroup.getCheckedRadioButtonId());
                context.setDarkMode(isDarkMode.isChecked());

            Navigation.findNavController(v).navigate(R.id.action_ekr5Fragment_to_ekr3Fragment);
        });

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                double value = (double) progress / 10;
                timer.setText(String.valueOf(value));
                context.setTimer(value);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        return root;
    }
}