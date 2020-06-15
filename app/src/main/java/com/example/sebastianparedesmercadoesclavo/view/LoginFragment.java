package com.example.sebastianparedesmercadoesclavo.view;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sebastianparedesmercadoesclavo.R;
import com.example.sebastianparedesmercadoesclavo.databinding.FragmentLoginBinding;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.SignInButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private LoginFragmentListener listener;


    public LoginFragment() {
        // Required empty public constructor
    }

    public LoginFragment(LoginFragmentListener listener){
        this.listener = listener;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.btnlogingoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickLoginGoogle();
            }
        });

        binding.btnloginfacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickLoginFacebook(binding.btnloginfacebook);
            }
        });

        binding.btniniciarsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickFirebase(false);
            }
        });

        binding.btnregistrarme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickFirebase(true);
            }
        });

        return view;
    }
    public interface LoginFragmentListener {
        void onClickLoginGoogle();
        void onClickLoginFacebook(LoginButton loginButton);
        void onClickFirebase(Boolean newUser);
        }

}