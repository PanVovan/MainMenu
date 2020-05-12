package com.poker.holdem.view.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.poker.holdem.LobbyContract;
import com.poker.holdem.R;
import com.poker.holdem.constants.Constants;

import java.util.logging.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateAccFragment extends Fragment {

    private View view;

    @BindView(R.id.name_et_create) EditText name_et_create;
    @BindView(R.id.password_et_create) EditText password_et_create;
    @BindView(R.id.choose_image_iv) ImageView choose_image_iv;
    @BindView(R.id.create_account_btn) Button create_acc_btn;

    public CreateAccFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_create_acc, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.create_account_btn)
    void createAcc(){
        String name = name_et_create.getText().toString().trim();
        String password = password_et_create.getText().toString().trim();
        Logger.getAnonymousLogger().info("<------------"+name+"  "+ password);
        Integer picture = 1; //TODO: добавить выбор фото
        ((LobbyContract.Registrator)getActivity()).sendMessageOnServerRegister(name, password, picture, this);
    }
}
