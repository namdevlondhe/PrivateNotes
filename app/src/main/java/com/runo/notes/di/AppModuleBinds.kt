package com.runo.notes.di

import com.runo.notes.data.source.DefaultTaskRepository
import com.runo.notes.data.source.TaskRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton


@Module
abstract class AppModuleBinds {

    @Singleton
    @Binds
    abstract fun getRepository(taskRepository: DefaultTaskRepository) : TaskRepository
}