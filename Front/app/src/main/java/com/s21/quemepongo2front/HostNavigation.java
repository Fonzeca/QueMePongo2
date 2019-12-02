package com.s21.quemepongo2front;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

public interface HostNavigation {
	int changeFragment(@NotNull Fragment fragment, boolean addToBackStack);
}
