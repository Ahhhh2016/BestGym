package com.student.student_base_project.net;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.student.student_base_project.manager.AppManager;
import com.student.student_base_project.utils.ToastUtils;

import java.lang.reflect.ParameterizedType;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/****
 * 自定义网络处理
 * @param <T>
 */
public abstract class ApiSubscriber<T> implements Observer<T> {

    Context context = AppManager.getInstance().getPeekActivity();

    public abstract void onSuccess(T t);

    public abstract void onFailure(Throwable e);

    public abstract void onStart();

    public abstract void onDisposable(Disposable d);

    @Override
    public void onError(Throwable e) {
        if (context == null) {
            return;
        }
        Log.d("Dong", "ApiSbscriber---->  401" + e.getMessage());
        if (!TextUtils.isEmpty(e.getMessage()) && e.getMessage().contains("code")) {
            if (!TextUtils.isEmpty(e.getMessage())) {
                Toast.makeText(context, e.getMessage().split("--")[1], Toast.LENGTH_LONG).show();
            }

        } else {
            if (e instanceof Exception) {
                //访问获得对应的Exception
                e = ExceptionHandle.handleException(e);
            } else {
                //将Throwable 和 未知错误的status code返回
                e = new ExceptionHandle.ResponeThrowable(e, ExceptionHandle.ERROR.UNKNOWN);
            }
            if (!TextUtils.isEmpty(e.getMessage())) {

                ToastUtils.SingleToastUtil(context, e.getMessage());
            }

        }


        onFailure(e);
    }

    @Override
    public void onComplete() {
    }

    @Override
    public void onSubscribe(Disposable d) {
        onStart();
        onDisposable(d);

    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    private Class getCurrentClassPt() {
        ParameterizedType ptClass = (ParameterizedType) this.getClass().getGenericSuperclass();
        //获得运行期的泛型类型
        return (Class) ptClass.getActualTypeArguments()[0];
    }
}
