package com.elsy.simpleinstagram.app.dispatch;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import com.elsy.simpleinstagram.Injection;
import com.elsy.simpleinstagram.R;
import com.elsy.simpleinstagram.app.home.HomeActivity;
import com.elsy.simpleinstagram.utils.ActivityHelper;


public class DispatchActivity extends AppCompatActivity implements DispatchContract.DispatchView {

    private DispatchPresenter dispatchPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setupPresenter();
        dispatchPresenter.begin();
    }

    @Override
    public void onError(String error) {
        Snackbar.make(getWindow().getDecorView().getRootView(), error, Snackbar.LENGTH_LONG).show();
        sendHome();
    }

    @Override
    public void sendToHome() {
        sendHome();
    }

    private void setupPresenter() {
        dispatchPresenter = new DispatchPresenter(
                this,
                Injection.providePostsRepository(this)
        );
    }

    private void sendHome() {
        ActivityHelper.sendAndFinish(this, HomeActivity.class);
    }


}
