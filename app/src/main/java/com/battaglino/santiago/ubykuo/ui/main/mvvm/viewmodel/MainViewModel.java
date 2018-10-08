package com.battaglino.santiago.ubykuo.ui.main.mvvm.viewmodel;

import android.app.Application;

import com.battaglino.santiago.ubykuo.base.mvvm.viewmodel.BaseViewModel;
import com.battaglino.santiago.ubykuo.db.entity.Repo;
import com.battaglino.santiago.ubykuo.ui.main.repository.RepoRepository;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

/**
 * Created by Santiago Battaglino.
 */
public class MainViewModel extends BaseViewModel<Repo, RepoRepository> {

    @Inject
    public MainViewModel(@NotNull Application application) {
        super(application);
    }
}