package com.runo.notes.task.di

import androidx.lifecycle.ViewModel
import com.runo.notes.di.ViewModelKey
import com.runo.notes.task.TaskViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class TaskModule {

    @Binds
    @IntoMap
    @ViewModelKey(TaskViewModel::class)
    abstract fun provideViewModel(taskViewModel: TaskViewModel) : ViewModel

}