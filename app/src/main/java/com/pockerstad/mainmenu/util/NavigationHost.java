package com.pockerstad.mainmenu.util;

import androidx.fragment.app.Fragment;

public interface NavigationHost {
    void navigateTo(Fragment fragment, boolean addToBackstack);
}

/*
* Создан для более простого перехода от одного фрагмента к другому
*/