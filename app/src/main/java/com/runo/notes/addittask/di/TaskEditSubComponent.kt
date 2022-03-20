package com.runo.notes.addittask.di

import com.runo.notes.addittask.TaskEditFragment
import dagger.Subcomponent


@Subcomponent (modules = [TaskEditModule::class])
interface TaskEditSubComponent {

    @Subcomponent.Factory
    interface factory {
        fun create() : TaskEditSubComponent
    }

    fun inject(taskEditFragment: TaskEditFragment)
}