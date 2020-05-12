package com.poker.holdem.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.poker.holdem.LobbyContract;
import com.poker.holdem.PokerApplicationManager;
import com.poker.holdem.R;
import com.poker.holdem.constants.Constants;
import com.poker.holdem.server.deserialization.MyDeserializer;
import com.poker.holdem.server.deserialization.auth.AuthResponce;
import com.poker.holdem.server.deserialization.registration.RegResp;
import com.poker.holdem.server.serialization.GetJSON;
import com.poker.holdem.view.fragments.AuthFragment;
import com.poker.holdem.view.fragments.CreateAccFragment;
import com.poker.holdem.view.fragments.MainMenuFragment;
import com.poker.holdem.view.util.NavigationHost;

import java.util.logging.Logger;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class LoginActivity extends AppCompatActivity implements LobbyContract.Registrator, NavigationHost {
    Logger logger = Logger.getAnonymousLogger();

    private String playerName;
    private String authToken;
    private String playerPassword;

    private Socket socket;

    private SharedPreferences prefs;
    private SharedPreferences.Editor prefsEditor;
    PokerApplicationManager applicationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        prefs = getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE);

        applicationManager = (PokerApplicationManager) getApplication();
        if(applicationManager.getSocket() != null){
            socket = applicationManager.getSocket();
            socket.on("auth", onAuthListener);
            socket.on("registration", onRegListener);
            socket.connect();
        }


        if(prefs.contains(Constants.AUTH_TOKEN) && prefs.contains(Constants.PLAYER_NAME)){
            authToken = prefs.getString(Constants.AUTH_TOKEN, "");
            playerName = prefs.getString(Constants.PLAYER_NAME, "");
            if(!playerName.equals("")&&!authToken.equals("")){
                sendMessageOnServerAuthToken(playerName, authToken);
            }else
                navigateTo(new AuthFragment(), false);
        }else{
            navigateTo(new AuthFragment(), false);
        }
    }

    @Override
    public void sendMessageOnServerRegister(String name, String password, Integer picture, Fragment fragment) {
        //FragmentTransaction transaction =
        //        getSupportFragmentManager()
        //                .beginTransaction()
        //                .hide(fragment);
        //transaction.addToBackStack(null);
        //transaction.commit();
        if(!name.equals("")&&!password.equals("")){
            Logger.getAnonymousLogger().info(name+"<------------->"+password);
            playerPassword = password;
            playerName = name;
            Logger.getAnonymousLogger().info(playerName+"<------------->"+playerPassword);
            prefs = getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE);
            prefsEditor = prefs.edit();
            prefsEditor.putString(Constants.PLAYER_NAME, playerName);
            prefsEditor.apply();
            socket.emit("registration"
                    ,GetJSON.namePasswordPicture(
                            playerName
                            ,playerPassword
                            ,picture
                    )
            );
        }else
            runOnUiThread(() -> {
                Toast.makeText(
                        getApplication()
                        , "Name and password fields can't be empty!"
                        , Toast.LENGTH_LONG
                ).show();
            });

    }

    @Override
    public void sendMessageOnServerAuthToken(String name, String authToken) {
        socket.emit("authbytoken", GetJSON.nameAuthToken(name, authToken));
    }

    @Override
    public void sendMessageOnServerAuthPassword(String name, String password) {
        socket.emit("auth", GetJSON.namePassword(name, password));
    }

    @Override
    public void navigateTo(Fragment fragment, boolean addToBackstack) {
        FragmentTransaction transaction =
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.login_activity, fragment);

        if (addToBackstack) {
            //определяем, будет ли добавляться фрагмент в стек или нет
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }

    private Emitter.Listener onAuthListener = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            AuthResponce responce =  MyDeserializer.desAuthResponce(args[0].toString());
            Logger.getAnonymousLogger().info("<------------got "+responce.getFlag());
            if(responce.getFlag()){
                prefsEditor = prefs.edit();
                prefsEditor.putString(Constants.AUTH_TOKEN, responce.getNewauthtoken());
                prefsEditor.putString(Constants.SESSION_TOKEN, responce.getToken());
                prefsEditor.putString(Constants.PLAYER_NAME, responce.getAuthPlayer().getName());
                prefsEditor.apply();

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("picture", responce.getAuthPlayer().getPicture());
                intent.putExtra("name", responce.getAuthPlayer().getName());
                intent.putExtra("money", responce.getAuthPlayer().getMoney());
                startActivity(intent);
            }else
                navigateTo(new AuthFragment(), true);
        }
    };

    private Emitter.Listener onRegListener = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Logger.getAnonymousLogger().info("<--------"+args[0].toString());
            RegResp regResp = MyDeserializer.desRegistrationResponce(args[0].toString());
            runOnUiThread(() -> {
                if(!regResp.getIsReg())
                    sendMessageOnServerAuthPassword(playerName, playerPassword);
                else {
                    Toast.makeText(
                            getApplication()
                            , "Player with this name alreadt exists!"
                            , Toast.LENGTH_LONG
                    ).show();
                }
            });
        }
    };
}
