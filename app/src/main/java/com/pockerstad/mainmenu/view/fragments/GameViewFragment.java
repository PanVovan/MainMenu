package com.pockerstad.mainmenu.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.pockerstad.mainmenu.GameContract;
import com.pockerstad.mainmenu.R;
import com.pockerstad.mainmenu.view.customparts.seekbar.RaiseSeekBar;
import com.pockerstad.mainmenu.view.grafic.CardView;
import com.pockerstad.mainmenu.view.util.ViewControllerActionCode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameViewFragment extends Fragment implements GameContract.View {

    GameContract.Presenter presenter;

    //Кнопки
    @BindView(R.id.exit_button)                 ImageButton exitButton;
    @BindView(R.id.check_button)                ImageButton checkButton;
    @BindView(R.id.game_info_button)            ImageButton gameInfoButton;
    @BindView(R.id.raise_button)                ImageButton raiseButton;
    @BindView(R.id.fold_button)                 ImageButton foldButton;
    @BindView(R.id.set_rate_button)             ImageButton setRateButton;

    //карты
    @BindView(R.id.first_community_card)        ImageView firstCommunityCard;
    @BindView(R.id.second_community_card)       ImageView secondCommunityCard;
    @BindView(R.id.third_community_card)        ImageView thirdCommunityCard;
    @BindView(R.id.fourth_community_card)       ImageView fourthCommunityCard;
    @BindView(R.id.fifth_community_card)        ImageView fifthCommunityCard;
    @BindView(R.id.first_hole_card)             ImageView firstHoleCard;
    @BindView(R.id.second_hole_card)            ImageView secondHoleCard;
    @BindView(R.id.first_opponent_first_card)   ImageView firstOpponentFirstCard;
    @BindView(R.id.first_opponent_second_card)  ImageView firstOpponentSecondCard;
    @BindView(R.id.second_opponent_first_card)  ImageView secondOpponentFirstCard;
    @BindView(R.id.second_opponent_second_card) ImageView secondOpponentSecondCard;
    @BindView(R.id.third_opponent_first_card)   ImageView thirdOpponentFirstCard;
    @BindView(R.id.third_opponent_second_card)  ImageView thirdOpponentSecondCard;
    @BindView(R.id.fourth_opponent_first_card)  ImageView fourthOpponentFirstCard;
    @BindView(R.id.fourth_opponent_second_card) ImageView fourthOpponentSecondCard;

    //Имена оппонентов
    @BindView(R.id.first_opponent_name)         TextView firstOpponentName;
    @BindView(R.id.second_opponent_name)        TextView secondOpponentName;
    @BindView(R.id.third_opponent_name)         TextView thirdOpponentName;
    @BindView(R.id.fourth_opponent_name)        TextView fourthOpponentName;

    //Деньги
    @BindView(R.id.first_opponent_money)        TextView firstOpponentMoney;
    @BindView(R.id.second_opponent_money)       TextView secondOpponentMoney;
    @BindView(R.id.third_opponent_money)        TextView thirdOpponentMoney;
    @BindView(R.id.fourth_opponent_money)       TextView fourthOpponentMoney;
    @BindView(R.id.player_money_textview)       TextView playerMoney;

    //Отображения
    @BindView(R.id.player_icon)                 ImageView playerIcon;
    @BindView(R.id.first_opponent_icon)         ImageView firstOpponentIcon;
    @BindView(R.id.second_opponent_icon)        ImageView secondOpponentIcon;
    @BindView(R.id.third_opponent_icon)         ImageView thirdOpponentIcon;
    @BindView(R.id.fourth_opponent_icon)        ImageView fourthOpponentIcon;

    //Банк
    @BindView(R.id.bank_textview)               TextView bankTextView;

    //Ставка
    @BindView(R.id.set_rate_textview)           TextView setRateTextView;
    @BindView(R.id.raise_seekbar)               RaiseSeekBar raiseSeekBar;
    @BindView(R.id.you_rate)                    TextView youRate;

    //Лейауты
    @BindView(R.id.first_opponent_layout)       ConstraintLayout firstOpponentLayout;
    @BindView(R.id.second_opponent_layout)      ConstraintLayout secondOpponentLayout;
    @BindView(R.id.third_opponent_layout)       ConstraintLayout thirdOpponentLayout;
    @BindView(R.id.fourth_opponent_layout)      ConstraintLayout fourthOpponentLayout;

    @BindView(R.id.set_rate_layout)             ConstraintLayout setRateLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_view, container, false);
        ButterKnife.bind(this, view);
        raiseSeekBar.setOnSeekBarChangeListener(changeListener());

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @OnClick(R.id.raise_button)
    void visibilitySetRateLayout(){
        if (setRateLayout.getVisibility() == View.INVISIBLE){
            setRateLayout.setVisibility(View.VISIBLE);
        }
        else {
            setRateLayout.setVisibility(View.INVISIBLE);
        }
    }

    @OnClick(R.id.set_rate_button)
    void setRate() {
        if (setRateLayout.getVisibility() == View.VISIBLE){
            setRateLayout.setVisibility(View.INVISIBLE);
            setRateTextView.setText("");
            youRate.setText(raiseSeekBar.getValue().toString());
            presenter.raiseButtonClicked(raiseSeekBar.getValue());
        }
    }

    @OnClick(R.id.game_info_button)
    void info(){
    }


    private SeekBar.OnSeekBarChangeListener changeListener(){
        return new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setRateTextView.setText(Integer.toString(progress));
                raiseSeekBar.setValue(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };
    }

    @OnClick(R.id.game_container)
    void clickOnContainer(){
        if(setRateLayout.getVisibility() == View.VISIBLE && getView().getId() != R.id.set_rate_layout){
            setRateLayout.setVisibility(View.INVISIBLE);
        }
    }


    @Override
    public void clearCards(int typeOfClear) {
        switch (typeOfClear) {
            case ViewControllerActionCode.CLEAR_PLAYER_CARDS:
                firstHoleCard.setBackground(null);
                secondHoleCard.setBackground(null);
                break;
            case ViewControllerActionCode.CLEAR_FIRST_OPPONENT_CARDS:
                firstOpponentFirstCard.setBackground(null);
                firstOpponentSecondCard.setBackground(null);
                break;
            case ViewControllerActionCode.CLEAR_SECOND_OPPONENT_CARDS:
                secondOpponentFirstCard.setBackground(null);
                secondOpponentSecondCard.setBackground(null);
                break;
            case ViewControllerActionCode.CLEAR_THIRD_OPPONENT_CARDS:
                thirdOpponentFirstCard.setBackground(null);
                thirdOpponentSecondCard.setBackground(null);
                break;
            case ViewControllerActionCode.CLEAR_FOURTH_OPPONENT_CARDS:
                fourthOpponentFirstCard.setBackground(null);
                fourthOpponentSecondCard.setBackground(null);
                break;
            case ViewControllerActionCode.CLEAR_COMMUNITY_CARDS:
                firstCommunityCard.setBackground(null);
                secondCommunityCard.setBackground(null);
                thirdCommunityCard.setBackground(null);
                fourthCommunityCard.setBackground(null);
                fifthCommunityCard.setBackground(null);
                break;
            case ViewControllerActionCode.CLEAR_ALL_CARDS:
                    clearAllCards();
                    break;
        }
    }

    @Override
    public void setCardView(int action, int card) {
        if (card == ViewControllerActionCode.NONE){
            setInvisibleOpponentCard(action);
        } else if (action < 10){
            setYouCard(action, card);
        } else if (action < 100){
            setOpponentCard(action, card);
        } else if(action < 200){
            setCommunityCard(action, card);
        }
    }

    @Override
    public void setOpponentView(int pos, String name, Integer money, int picture){
        switch (pos){
            case 1:
                firstOpponentLayout.setVisibility(View.VISIBLE);
                firstOpponentName.setText(name);
                firstOpponentMoney.setText(money.toString());
                break;
            case 2:
                secondOpponentLayout.setVisibility(View.VISIBLE);
                secondOpponentName.setText(name);
                secondOpponentMoney.setText(money.toString());
                break;
            case 3:
                thirdOpponentLayout.setVisibility(View.VISIBLE);
                thirdOpponentName.setText(name);
                thirdOpponentMoney.setText(money.toString());
                break;
            case 4:
                fourthOpponentLayout.setVisibility(View.VISIBLE);
                fourthOpponentName.setText(name);
                fourthOpponentMoney.setText(money.toString());
                break;
        }
    }

    @Override
    public void setPlayerView(Integer money, int picture) {
        playerMoney.setText(money.toString());
    }

    private void clearAllCards(){
        firstHoleCard.setBackground(null);
        secondHoleCard.setBackground(null);
        firstOpponentFirstCard.setBackground(null);
        firstOpponentSecondCard.setBackground(null);
        secondOpponentFirstCard.setBackground(null);
        secondOpponentSecondCard.setBackground(null);
        thirdOpponentFirstCard.setBackground(null);
        thirdOpponentSecondCard.setBackground(null);
        fourthOpponentFirstCard.setBackground(null);
        fourthOpponentSecondCard.setBackground(null);
        firstCommunityCard.setBackground(null);
        secondCommunityCard.setBackground(null);
        thirdCommunityCard.setBackground(null);
        fourthCommunityCard.setBackground(null);
        fifthCommunityCard.setBackground(null);
    }

    private void setCommunityCard(int action, int card){
        switch (action){
            case ViewControllerActionCode.ADD_COMMUNITY_CARD_FIRST:
                firstCommunityCard.setBackground(CardView.initDrawableVisibleCard(getContext(), card));
                break;
            case ViewControllerActionCode.ADD_COMMUNITY_CARD_SECOND:
                secondCommunityCard.setBackground(CardView.initDrawableVisibleCard(getContext(), card));
                break;
            case ViewControllerActionCode.ADD_COMMUNITY_CARD_THIRD:
                thirdCommunityCard.setBackground(CardView.initDrawableVisibleCard(getContext(), card));
                break;
            case ViewControllerActionCode.ADD_COMMUNITY_CARD_FOURTH:
                fourthCommunityCard.setBackground(CardView.initDrawableVisibleCard(getContext(), card));
                break;
            case ViewControllerActionCode.ADD_COMMUNITY_CARD_FIFTH:
                fifthCommunityCard.setBackground(CardView.initDrawableVisibleCard(getContext(), card));
                break;
        }
    }

    private void setYouCard(int action, int card){
        switch (action) {
            case ViewControllerActionCode.ADD_FIRST_PLAYER_CARD:
                firstHoleCard.setBackground(CardView.initDrawableVisibleCard(getContext(), card));
                break;
            case ViewControllerActionCode.ADD_SECOND_PLAYER_CARD:
                secondHoleCard.setBackground(CardView.initDrawableVisibleCard(getContext(), card));
                break;
        }
    }

    private void setOpponentCard(int action, int card){
        Context context = getContext();
        switch (action) {
            case ViewControllerActionCode.ADD_FIRST_OPPONENT_FIRST_CARD:
                firstOpponentFirstCard.setBackground(CardView.initDrawableVisibleCard(context, card));
                break;
            case ViewControllerActionCode.ADD_FIRST_OPPONENT_SECOND_CARD:
                firstOpponentSecondCard.setBackground(CardView.initDrawableVisibleCard(context, card));
                break;
            case ViewControllerActionCode.ADD_SECOND_OPPONENT_FIRST_CARD:
                secondOpponentFirstCard.setBackground(CardView.initDrawableVisibleCard(context, card));
                break;
            case ViewControllerActionCode.ADD_SECOND_OPPONENT_SECOND_CARD:
                secondOpponentSecondCard.setBackground(CardView.initDrawableVisibleCard(context, card));
                break;
            case ViewControllerActionCode.ADD_THIRD_OPPONENT_FIRST_CARD:
                thirdOpponentFirstCard.setBackground(CardView.initDrawableVisibleCard(context, card));
                break;
            case ViewControllerActionCode.ADD_THIRD_OPPONENT_SECOND_CARD:
                thirdOpponentSecondCard.setBackground(CardView.initDrawableVisibleCard(context, card));
                break;
            case ViewControllerActionCode.ADD_FOURTH_OPPONENT_FIRST_CARD:
                fourthOpponentFirstCard.setBackground(CardView.initDrawableVisibleCard(context, card));
                break;
            case ViewControllerActionCode.ADD_FOURTH_OPPONENT_SECOND_CARD:
                fourthOpponentSecondCard.setBackground(CardView.initDrawableVisibleCard(context, card));
                break;
        }
    }

    private void setInvisibleOpponentCard(int action){
        Context context = getContext();
        switch (action) {
            case ViewControllerActionCode.ADD_FIRST_OPPONENT_INVISIBLE_FIRST_CARD:
                firstOpponentFirstCard.setBackground(CardView.initDrawableInvisibleCard(context));
                break;
            case ViewControllerActionCode.ADD_FIRST_OPPONENT_INVISIBLE_SECOND_CARD:
                firstOpponentSecondCard.setBackground(CardView.initDrawableInvisibleCard(context));
                break;
            case ViewControllerActionCode.ADD_SECOND_OPPONENT_INVISIBLE_FIRST_CARD:
                secondOpponentFirstCard.setBackground(CardView.initDrawableInvisibleCard(context));
                break;
            case ViewControllerActionCode.ADD_SECOND_OPPONENT_INVISIBLE_SECOND_CARD:
                secondOpponentSecondCard.setBackground(CardView.initDrawableInvisibleCard(context));
                break;
            case ViewControllerActionCode.ADD_THIRD_OPPONENT_INVISIBLE_FIRST_CARD:
                thirdOpponentFirstCard.setBackground(CardView.initDrawableInvisibleCard(context));
                break;
            case ViewControllerActionCode.ADD_THIRD_OPPONENT_INVISIBLE_SECOND_CARD:
                thirdOpponentSecondCard.setBackground(CardView.initDrawableInvisibleCard(context));
                break;
            case ViewControllerActionCode.ADD_FOURTH_OPPONENT_INVISIBLE_FIRST_CARD:
                fourthOpponentFirstCard.setBackground(CardView.initDrawableInvisibleCard(context));
                break;
            case ViewControllerActionCode.ADD_FOURTH_OPPONENT_INVISIBLE_SECOND_CARD:
                fourthOpponentSecondCard.setBackground(CardView.initDrawableInvisibleCard(context));
                break;
        }
    }

}
