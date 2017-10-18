package com.hjq.base.base;

import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;


import com.hjq.base.app.BaseApp;
import com.hjq.base.utils.PrefUtil;

import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseActivity extends AppCompatActivity {
    private CompositeSubscription mCompositeSubscription;
    private static PrefUtil pref;
    private Dialog proDialog;
    private int orientation= ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        setRequestedOrientation(orientation);
        ButterKnife.bind(this);
        BaseApp.getInstance().addActivity(this);
        proDialog=getLoadingDialog();
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        onUnsubscribe();
        super.onDestroy();
        BaseApp.getInstance().removeActivity(this);
    }


    public void addSubscription(Observable observable, Subscriber subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }

    public void addSubscription(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    public void onUnsubscribe() {

        //取消注册，以避免内存泄露
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions())
            mCompositeSubscription.unsubscribe();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();//返回
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void showLoadingDialog(int resid) {
        String msg = getResources().getString(resid);
        showLoadingDialog(msg);
    }

    public void showLoadingDialog(String title) {
        if (proDialog != null && proDialog.isShowing()) {
            proDialog.cancel();
        }
        proDialog.show();
    }

    public void showLongToast(String msg) {
        Toast.makeText(getApplication(), msg, Toast.LENGTH_LONG).show();
    }

    public void showLongToast(int resId) {
        Toast.makeText(getApplication(), resId, Toast.LENGTH_LONG).show();
    }

    public void showShortToast(String msg) {
        Toast.makeText(getApplication(), msg, Toast.LENGTH_SHORT).show();
    }

    public void showShortToast(int resId) {
        Toast.makeText(getApplication(), resId, Toast.LENGTH_SHORT).show();
    }

    public void closeLoadingDialog() {
        try {
            if (proDialog != null && proDialog.isShowing()) {
                proDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PrefUtil getPref() {
        if (pref == null) {
            pref = PrefUtil.getInstance();
        }
        return pref;
    }

    public abstract int getLayout();

    public abstract void initViewAndData();
    
    public abstract Dialog getLoadingDialog();

}
