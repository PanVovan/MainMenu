package com.poker.holdem.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.poker.holdem.LobbyContract;
import com.poker.holdem.R;
import com.poker.holdem.view.grafic.PictureView;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateAccFragment extends Fragment {

    private View view;

    private List<Integer> pics = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7);
    //была идея сделать через итератор, но его методы возвращают
    //текущее значение и переходят на одно вперёд/назад
    //так оказалось проще
    private int pic_pos = 0;

    @BindView(R.id.name_et_create) EditText name_et_create;
    @BindView(R.id.password_et_create) EditText password_et_create;
    @BindView(R.id.choose_image_iv) ImageView choose_image_iv;
    @BindView(R.id.create_account_btn) Button create_acc_btn;
    @BindView(R.id.picture_layout_create_acc) FrameLayout picture_layout;
    @BindView(R.id.button_next_picture) Button next_pic_btn;
    @BindView(R.id.button_previous_picture) Button prev_pic_btn;

    public CreateAccFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_create_acc, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.button_next_picture)
    void newPic(){
        if(pic_pos == pics.size()-1)
            pic_pos = 0;
        else
            pic_pos++;
        choose_image_iv.setBackground(
                PictureView.getPic(
                        getContext()
                        ,pic_pos
                )
        );
    }
    @OnClick(R.id.button_previous_picture)
    void prevPic(){
        if(pic_pos == 0)
            pic_pos = pics.size()-1;
        else
            pic_pos--;
        choose_image_iv.setBackground(
                PictureView.getPic(
                        getContext()
                        ,pic_pos
                )
        );
    }

    @OnClick(R.id.create_account_btn)
    void createAcc(){
        String name = name_et_create.getText().toString().trim();
        String password = password_et_create.getText().toString().trim();
        Logger.getAnonymousLogger().info("<------------"+name+"  "+ password);
        ((LobbyContract.Registrator) Objects.requireNonNull(getActivity()))
                .sendMessageOnServerRegister(name, password, pic_pos, this);
    }
}
