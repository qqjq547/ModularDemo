package com.hjq.base.base;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.hjq.base.utils.PrefUtil;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseFragment extends Fragment {
    private PrefUtil pref;
    private Dialog proDialog;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        proDialog=getLoadingDialog();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        onUnsubscribe();
    }

    private CompositeSubscription mCompositeSubscription;

    public void onUnsubscribe() {
        //取消注册，以避免内存泄露
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }

    public void addSubscription(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    public void showLoadingDialog(int resid) {
        String msg = getResources().getString(resid);
        showLoadingDialog(msg);
    }

    public void showLoadingDialog(String title) {
        if (proDialog != null && proDialog.isShowing()) {
            proDialog.cancel();
        }
        if (isVisible())
        proDialog.show();
    }

    public void showLongToast(String msg) {
        if (isVisible())
        Toast.makeText(getActivity().getApplication(), msg, Toast.LENGTH_LONG).show();
    }

    public void showLongToast(int resId) {
        if (isVisible())
        Toast.makeText(getActivity().getApplication(), resId, Toast.LENGTH_LONG).show();
    }

    public void showShortToast(String msg) {
        if (isVisible())
        Toast.makeText(getActivity().getApplication(), msg, Toast.LENGTH_SHORT).show();
    }

    public void showShortToast(int resId) {
        if (isVisible())
        Toast.makeText(getActivity().getApplication(), resId, Toast.LENGTH_SHORT).show();
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
