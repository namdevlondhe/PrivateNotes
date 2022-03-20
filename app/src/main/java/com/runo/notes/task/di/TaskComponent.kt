package com.runo.notes.task.di

import com.runo.notes.task.ui.TaskFragment
import dagger.Subcomponent


@Subcomponent(modules = [TaskModule::class])
interface TaskComponent {


    @Subcomponent.Factory
    interface factory {
        fun create() : TaskComponent
    }

   //Inject your fragment / Activity

    fun inject(taskFragment: TaskFragment)
}