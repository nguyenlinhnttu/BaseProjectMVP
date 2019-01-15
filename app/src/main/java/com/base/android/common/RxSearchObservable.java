package com.base.android.common;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by NguyenLinh on 27,July,2018
 */
public class RxSearchObservable {

    public static Observable<String> fromView(EditText editText) {

        final PublishSubject<String> subject = PublishSubject.create();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                subject.onNext(charSequence.toString());
                //subject.onComplete();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        return subject;
    }
}
