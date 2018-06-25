package com.elsy.simpleinstagram.app.Dispatch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.elsy.simpleinstagram.R;
import com.elsy.simpleinstagram.app.Home.HomeActivity;
import com.elsy.simpleinstagram.utils.ActivityHelper;


public class DispatchActivity extends AppCompatActivity implements DispatchContract.DispatchView {

    private DispatchPresenter dispatchPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setupPresenter();
        dispatchPresenter.doDispatch();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void sendHome() {
        ActivityHelper.sendAndFinish(this, HomeActivity.class);
    }

    private void setupPresenter() {
        dispatchPresenter = new DispatchPresenter(
                this
        );
    }

}
