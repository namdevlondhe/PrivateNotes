package com.runo.notes.addittask.di

import androidx.lifecycle.ViewModel
import com.runo.notes.addittask.TaskEditViewModel
import com.runo.notes.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class TaskEditModule {

    @IntoMap
    @ViewModelKey(TaskEditViewModel::class)
    @Binds
    abstract fun bindViewModel(taskEditViewModel: TaskEditViewModel) : ViewModel
}