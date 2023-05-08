// Warranty Confidential
// Copyright WhyQ Pte Ltd. 2016, 2023.
package com.warranty.base;


import com.warranty.ui.login.viewmodel.LoginRepository;
import com.warranty.ui.login.viewmodel.LoginRepositoryImpl;
import com.warranty.ui.otp.viewmodel.OTPVerifyRepository;
import com.warranty.ui.otp.viewmodel.OTPVerifyRepositoryImpl;

public class DataManager {

    private static DataManager sInstance;

    private DataManager() {
        // This class is not publicly instantiable
    }

    public static synchronized DataManager getInstance() {
        if (sInstance == null) {
            sInstance = new DataManager();
        }
        return sInstance;
    }

//    public Preference getPrefs() {
//        return PowerPreference.getDefaultFile();
//    }

    public LoginRepository getMobileInputRepository() {
        return LoginRepositoryImpl.INSTANCE.getInstance();
    }


    public OTPVerifyRepository getOTPVerifyRepository() {
        return OTPVerifyRepositoryImpl.INSTANCE.getInstance();
    }




}
