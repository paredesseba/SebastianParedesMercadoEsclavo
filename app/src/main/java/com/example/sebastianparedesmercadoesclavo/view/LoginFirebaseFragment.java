package com.example.sebastianparedesmercadoesclavo.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sebastianparedesmercadoesclavo.R;
import com.example.sebastianparedesmercadoesclavo.databinding.FragmentLoginFirebaseBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFirebaseFragment extends Fragment {

    private Boolean newUser;
    private FragmentLoginFirebaseBinding binding;
    private LoginFirebaseFragmentListener listener;
    private String mail;
    private String password;
    private String repeatpassword;

    public LoginFirebaseFragment() {
        // Required empty public constructor
    }

    public LoginFirebaseFragment(Boolean newUser, LoginFirebaseFragmentListener listener) {
        this.newUser = newUser;
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginFirebaseBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        if (!newUser){
            binding.btniniciarsesion.setText("Iniciar Sesion");
            binding.TILrepeatpassword.setVisibility(View.INVISIBLE);
            binding.TIETrepeatpassword.setVisibility(View.INVISIBLE);

            binding.btniniciarsesion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mail = binding.TIETusername.getText().toString();
                    password = binding.TIETpassword.getText().toString();

                    if (mail != null && password != null){
                        listener.onClickLoginFirebase(mail, password, false);
                    }
                    else {
                        Toast.makeText(LoginFirebaseFragment.this.getContext(),"Username o password no validos", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else{
            binding.btniniciarsesion.setText("Registrarme");
            binding.TILrepeatpassword.setVisibility(View.VISIBLE);
            binding.TIETrepeatpassword.setVisibility(View.VISIBLE);

            binding.btniniciarsesion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mail = binding.TIETusername.getText().toString();
                    password = binding.TIETpassword.getText().toString();
                    repeatpassword = binding.TIETrepeatpassword.getText().toString();

                    if ((mail != null) && (password != null) && (repeatpassword != null)){
                        if (password.equals(repeatpassword)){
                            listener.onClickLoginFirebase(mail, password, true);
                        }
                    }
                }
            });
        }

        return view;
    }

    public interface LoginFirebaseFragmentListener {
        void onClickLoginFirebase(String mail, String password, Boolean newUser);
    }
}