package com.battaglino.santiago.ubykuo.ui.main.mvvm.view;

import com.battaglino.santiago.ubykuo.base.mvvm.view.BaseView;
import com.battaglino.santiago.ubykuo.ui.main.activity.MainActivity;
import com.battaglino.santiago.ubykuo.ui.main.mvvm.viewmodel.MainViewModel;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Santiago Battaglino.
 */
public class MainView extends BaseView<MainActivity, MainViewModel> {

    public MainView(@NotNull MainActivity activity, @NotNull MainViewModel viewModel) {
        super(activity, viewModel);
    }

    @Override
    protected void subscribeUiToLiveData() {

    }

    @Override
    protected void showDataInUi() {

    }
}
