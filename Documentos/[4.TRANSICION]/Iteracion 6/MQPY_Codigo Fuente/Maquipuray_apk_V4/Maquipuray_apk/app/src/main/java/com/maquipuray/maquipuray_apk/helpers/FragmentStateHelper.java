package com.maquipuray.maquipuray_apk.helpers;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.HashMap;

/**
 * Created by rogergcc on 8/08/2020.
 * Copyright Ⓒ 2020 . All rights reserved.
 */
public class FragmentStateHelper {
    private HashMap<String, Fragment.SavedState> fragmentStates;
    private FragmentManager fragmentManager;

    public FragmentStateHelper(FragmentManager fragmentManager) {
        fragmentStates = new HashMap<>();
        this.fragmentManager = fragmentManager;
    }

    public void restoreState(Fragment fragment, String key) {
        Fragment.SavedState savedState = fragmentStates.get(key);
        if (savedState != null) {
            if (!fragment.isAdded()) {
                fragment.setInitialSavedState(savedState);
            }
        }
    }

    public void saveState(Fragment fragment, String key) {
        if (fragment.isAdded()) {
            fragmentStates.put(key, fragmentManager.saveFragmentInstanceState(fragment));
        }
    }
}
