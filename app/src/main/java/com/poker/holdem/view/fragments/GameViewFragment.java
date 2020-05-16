package com.poker.holdem.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.poker.holdem.GameContract;
import com.poker.holdem.PokerApplicationManager;
import com.poker.holdem.Presenter;
import com.poker.holdem.R;
import com.poker.holdem.constants.Constants;
import com.poker.holdem.logic.player.Player;
import com.poker.holdem.view.activity.MainActivity;
import com.poker.holdem.view.customparts.seekbar.RaiseSeekBar;
import com.poker.holdem.view.grafic.CardView;
import com.poker.holdem.view.grafic.PictureView;
import com.poker.holdem.view.util.ViewControllerActionCode;

import java.util.Locale;
import java.util.Objects;
import java.util.logging.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameViewFragment extends Fragment implements GameContract.View {

    private GameContract.Presenter presenter;

    private String ROOM_NAME = "";

    public GameViewFragment(String roomName){
        this.ROOM_NAME = roomName;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_view, container, false);
        ButterKnife.bind(this, view);
        raiseSeekBar.setOnSeekBarChangeListener(changeListener());
        return view;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        presenter = new Presenter(this, this.ROOM_NAME);
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


    //TODO: посылать не прогресс, а деньги
    @OnClick(R.id.set_rate_button)
    void setRate() {
        if (setRateLayout.getVisibility() == View.VISIBLE){
            setRateLayout.setVisibility(View.INVISIBLE);
            setRateTextView.setText("");
            presenter.raiseButtonClicked(raiseSeekBar.getValue());
            //студия подсказывает сделать так
            //"%d" - целое
            //локаль - способ вывлда
            youRate.setText(String.format(Locale.ENGLISH,"%d",raiseSeekBar.getValue()));
        }
    }

    @OnClick(R.id.game_info_button)
    void info(){
        //хуйня
    }

    private SeekBar.OnSeekBarChangeListener changeListener(){
        return new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setRateTextView.setText(String.format(Locale.ENGLISH,"%d",progress));
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
        if(setRateLayout.getVisibility() == View.VISIBLE && Objects.requireNonNull(getView()).getId() != R.id.set_rate_layout)
            setRateLayout.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.exit_button)
    void exit(){
        //TODO: ACHTUNG! здесь мы выходим в MainActivity
        Objects.requireNonNull(getActivity()).runOnUiThread(() -> Toast.makeText(getContext(), "Left lobby", Toast.LENGTH_SHORT).show());
        //Intent intent = new Intent(getActivity(), MainActivity.class);
        //intent.putExtra("money", presenter.exitButtonClicked());
        //startActivity(intent);
        getActivity().finish();

    }

    @Override
    public void updatePlayerMoney(int pos, Integer money){
        Objects.requireNonNull(getActivity()).runOnUiThread(()->{
            switch (pos){
                case ViewControllerActionCode.POSITION_OPPONENT_FIRST:
                    firstOpponentMoney.setText(String.format(Locale.ENGLISH,"%d",money));
                    break;
                case ViewControllerActionCode.POSITION_OPPONENT_SECOND:
                    secondOpponentMoney.setText(String.format(Locale.ENGLISH,"%d",money));
                    break;
                case ViewControllerActionCode.POSITION_OPPONENT_THIRD:
                    thirdOpponentMoney.setText(String.format(Locale.ENGLISH,"%d",money));
                    break;
                case ViewControllerActionCode.POSITION_OPPONENT_FOURTH:
                    fourthOpponentMoney.setText(String.format(Locale.ENGLISH,"%d",money));
                    break;
                case ViewControllerActionCode.POSITION_MAIN_PLAYER:
                    playerMoney.setText(String.format(Locale.ENGLISH,"%d",money));
                    break;
            }

        });
    }
    @Override
    public void clearCards(int typeOfClear) {
        Objects.requireNonNull(getActivity()).runOnUiThread(()->{
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
        });
    }
    @Override
    public void setCardView(int action, int card) {
        Objects.requireNonNull(getActivity()).runOnUiThread(()->{
            if (card == ViewControllerActionCode.NONE){
                setInvisibleOpponentCard(action);
            } else if (action < 10){
                setYouCard(action, card);
            } else if (action < 100){
                setOpponentCard(action, card);
            } else if(action < 200){
                setCommunityCard(action, card);
            }
        });
    }

    @Override
    public void setOpponentView(int pos, Player player){
        String name = player.getName();
        int money = player.getMoney();
        int picture = player.getNumOfPicture();
        Objects.requireNonNull(getActivity()).runOnUiThread(()->{

            switch (pos){
                case ViewControllerActionCode.POSITION_OPPONENT_FIRST:
                    firstOpponentLayout.setVisibility(View.VISIBLE);

                    Logger.getAnonymousLogger().info("is start");
                    firstOpponentName.setText(name);
                    firstOpponentMoney.setText(String.format(Locale.ENGLISH,"%d",money));
                    firstOpponentIcon.setBackground(PictureView.getPic(getContext(), picture));
                    if(player.isActive()){
                        setInvisibleOpponentCard(ViewControllerActionCode.ADD_FIRST_OPPONENT_FIRST_CARD);
                        setInvisibleOpponentCard(ViewControllerActionCode.ADD_FIRST_OPPONENT_SECOND_CARD);
                    }
                    break;
                case ViewControllerActionCode.POSITION_OPPONENT_SECOND:
                    secondOpponentLayout.setVisibility(View.VISIBLE);
                    secondOpponentName.setText(name);
                    secondOpponentMoney.setText(String.format(Locale.ENGLISH,"%d",money));
                    firstOpponentIcon.setBackground(PictureView.getPic(getContext(), picture));
                    if(player.isActive()){
                        setInvisibleOpponentCard(ViewControllerActionCode.ADD_SECOND_OPPONENT_FIRST_CARD);
                        setInvisibleOpponentCard(ViewControllerActionCode.ADD_SECOND_OPPONENT_SECOND_CARD);
                    }
                    break;
                case ViewControllerActionCode.POSITION_OPPONENT_THIRD:
                    thirdOpponentLayout.setVisibility(View.VISIBLE);
                    thirdOpponentName.setText(name);
                    thirdOpponentMoney.setText(String.format(Locale.ENGLISH,"%d",money));
                    firstOpponentIcon.setBackground(PictureView.getPic(getContext(), picture));
                    if(player.isActive()){
                        setInvisibleOpponentCard(ViewControllerActionCode.ADD_THIRD_OPPONENT_FIRST_CARD);
                        setInvisibleOpponentCard(ViewControllerActionCode.ADD_THIRD_OPPONENT_SECOND_CARD);
                    }
                    break;
                case ViewControllerActionCode.POSITION_OPPONENT_FOURTH:
                    fourthOpponentLayout.setVisibility(View.VISIBLE);
                    fourthOpponentName.setText(name);
                    fourthOpponentMoney.setText(String.format(Locale.ENGLISH,"%d",money));
                    firstOpponentIcon.setBackground(PictureView.getPic(getContext(), picture));
                    if(player.isActive()){
                        setInvisibleOpponentCard(ViewControllerActionCode.ADD_FOURTH_OPPONENT_FIRST_CARD);
                        setInvisibleOpponentCard(ViewControllerActionCode.ADD_FOURTH_OPPONENT_SECOND_CARD);
                    }
                    break;
            }
        });
    }
    @Override
    public void clearOpponentView(int pos){
        Objects.requireNonNull(getActivity()).runOnUiThread(()->{
            switch (pos){
                case ViewControllerActionCode.POSITION_OPPONENT_FIRST:
                    firstOpponentLayout.setVisibility(View.INVISIBLE);
                    firstOpponentName.setText("");
                    firstOpponentMoney.setText("");
                    firstOpponentIcon.setBackground(null);
                    break;
                case ViewControllerActionCode.POSITION_OPPONENT_SECOND:
                    secondOpponentLayout.setVisibility(View.INVISIBLE);
                    secondOpponentName.setText("");
                    secondOpponentMoney.setText("");
                    secondOpponentIcon.setBackground(null);
                    break;
                case ViewControllerActionCode.POSITION_OPPONENT_THIRD:
                    thirdOpponentLayout.setVisibility(View.INVISIBLE);
                    thirdOpponentName.setText("");
                    thirdOpponentMoney.setText("");
                    thirdOpponentIcon.setBackground(null);
                    break;
                case ViewControllerActionCode.POSITION_OPPONENT_FOURTH:
                    fourthOpponentLayout.setVisibility(View.INVISIBLE);
                    fourthOpponentName.setText("");
                    fourthOpponentMoney.setText("");
                    fourthOpponentIcon.setBackground(null);
                    break;
            }
        });
    }
    @Override
    public void setPlayerView(Player player) {
        Objects.requireNonNull(getActivity()).runOnUiThread(()-> {
            //Integer money = new Integer(player.getMoney());
            playerMoney.setText(String.format(Locale.ENGLISH,"%d",player.getMoney()));
            //поскольку картинка игрока - постоянная,
            //то при отображении берём её из шариков
            int playerPicCode = PokerApplicationManager
                    .getInstance()
                    .getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE)
                    .getInt(Constants.PLAYER_PICTURE, 1);
            playerIcon.setBackground(PictureView.getPic(
                    getContext()
                    , playerPicCode
            ));
            if (!player.getCards().isEmpty()) {
                setYouCard(
                        ViewControllerActionCode.ADD_FIRST_PLAYER_CARD
                        , player.getCards().get(0)
                );
                setYouCard(
                        ViewControllerActionCode.ADD_SECOND_PLAYER_CARD
                        , player.getCards().get(1)
                );
            }
        });
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
            case ViewControllerActionCode.ADD_FIRST_OPPONENT_FIRST_CARD:
                firstOpponentFirstCard.setBackground(CardView.initDrawableInvisibleCard(context));
                break;
            case ViewControllerActionCode.ADD_FIRST_OPPONENT_SECOND_CARD:
                firstOpponentSecondCard.setBackground(CardView.initDrawableInvisibleCard(context));
                break;
            case ViewControllerActionCode.ADD_SECOND_OPPONENT_FIRST_CARD:
                secondOpponentFirstCard.setBackground(CardView.initDrawableInvisibleCard(context));
                break;
            case ViewControllerActionCode.ADD_SECOND_OPPONENT_SECOND_CARD:
                secondOpponentSecondCard.setBackground(CardView.initDrawableInvisibleCard(context));
                break;
            case ViewControllerActionCode.ADD_THIRD_OPPONENT_FIRST_CARD:
                thirdOpponentFirstCard.setBackground(CardView.initDrawableInvisibleCard(context));
                break;
            case ViewControllerActionCode.ADD_THIRD_OPPONENT_SECOND_CARD:
                thirdOpponentSecondCard.setBackground(CardView.initDrawableInvisibleCard(context));
                break;
            case ViewControllerActionCode.ADD_FOURTH_OPPONENT_FIRST_CARD:
                fourthOpponentFirstCard.setBackground(CardView.initDrawableInvisibleCard(context));
                break;
            case ViewControllerActionCode.ADD_FOURTH_OPPONENT_SECOND_CARD:
                fourthOpponentSecondCard.setBackground(CardView.initDrawableInvisibleCard(context));
                break;
        }
    }

    //перенёс, чтобы не мешалось
    //Кнопки
    @BindView(R.id.exit_button)                 Button exitButton;
    @BindView(R.id.check_button)                Button checkButton;
    @BindView(R.id.game_info_button)            Button gameInfoButton;
    @BindView(R.id.raise_button)                Button raiseButton;
    @BindView(R.id.fold_button)                 Button foldButton;
    @BindView(R.id.set_rate_button)             Button setRateButton;

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
}
