package com.hadimusthafa.meridian;

import android.widget.EditText;

public interface Validation {

    void input(EditText txt, String s);

    boolean isNotNull();

    boolean checkEmail(String email);

}

