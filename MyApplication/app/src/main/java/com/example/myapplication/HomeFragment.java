package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
    public String textFromFile= "";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_home, container, false);
        Button login_btn = root.findViewById(R.id.button);
        Button register_btn = root.findViewById(R.id.button2);
        EditText login = root.findViewById(R.id.editTextText);
        EditText password = root.findViewById(R.id.editTextText2);

        login_btn.setOnClickListener(v -> {
            String inputLogin = login.getText().toString();
            String inputPass = password.getText().toString();
            boolean found = false;
            File testFile = new File(requireContext().getExternalFilesDir(null), "testFile.txt");
            if (!testFile.exists()) {
                return;
            }

            try (BufferedReader br = new BufferedReader(new FileReader(testFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(";");
                    if (parts.length == 2) {
                        String fileLogin = parts[0];
                        String filePass = parts[1];

                        if (fileLogin.equals(inputLogin) && filePass.equals(inputPass)) {
                            found = true;
                            break;
                        }
                    }
                }
                if (found) {
                    Navigation.findNavController(v).navigate(R.id.action_homeFragment_to_blankFragment);
                } else {
                    Toast.makeText(getActivity(), "Błędny login lub hasło", Toast.LENGTH_SHORT).show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });




        register_btn.setOnClickListener(v -> {
            String userLogin = login.getText().toString();
            String userPass = password.getText().toString();

            if (userLogin.isEmpty() || userPass.isEmpty()) {
                Toast.makeText(getActivity(), "Uzupełnij dane!", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                File testFile = new File(requireContext().getExternalFilesDir(null), "testFile.txt");
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(testFile, true));

                bufferedWriter.write(userLogin + ";" + userPass);
                bufferedWriter.newLine();
                bufferedWriter.close();

                Toast.makeText(getActivity(), "Zarejestrowano!", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(v).navigate(R.id.action_homeFragment_to_blankFragment);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return root;
    }
}