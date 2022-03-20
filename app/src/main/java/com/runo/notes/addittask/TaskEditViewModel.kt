package com.runo.notes.addittask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.runo.notes.Event
import com.runo.notes.data.Result
import com.runo.notes.data.Task
import com.runo.notes.data.source.TaskRepository
import com.runo.notes.utils.Utilities.Companion.getCurrentDateAndTime
import kotlinx.coroutines.launch
import javax.inject.Inject

class TaskEditViewModel @Inject constructor(
    private val repository: TaskRepository
        ) : ViewModel() {

    //Two way binding
    val title  = MutableLiveData<String>()

    val description = MutableLiveData<String>()
    val createdDateTime = MutableLiveData<String>()

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading : LiveData<Boolean> = _dataLoading

    private val _taskUpdateEvent = MutableLiveData<Event<Unit>>()
    val taskUpdateEvent : LiveData<Event<Unit>> = _taskUpdateEvent

    private var isNewTask : Boolean = false
    private var isDataLoaded = false
    private var taskCompleted = false
    private var taskId : String? = null


    private val _text = MutableLiveData<String>().apply {
        value = "This is gallery Fragment"
    }
    val text: LiveData<String> = _text

    fun start(taskId : String?) {
        if (_dataLoading.value == true) return

        this.taskId = taskId
        if (taskId == null) {
            isNewTask = true
            return
        }

        if (isDataLoaded) return

        isNewTask = false
        _dataLoading.value = true

        viewModelScope.launch {
            repository.getTask(taskId).let {
                if (it is Result.Success) {
                    onTaskLoaded(it.data)
                }else {
                    onDataNotAvailable()
                }
            }
        }

    }

    private fun onTaskLoaded(task : Task) {
        title.value = task.title
        description.value = task.description
        createdDateTime.value = task.created
        taskCompleted = task.isCompleted
        isDataLoaded = true
        _dataLoading.value = false
    }

    private fun onDataNotAvailable() {
        _dataLoading.value = false
    }

    fun saveTask() {



        val currentTitle = title.value
        val currentDescription = description.value
        val currentCreatedDateTime = createdDateTime.value

      //  Log.e("MY TAG", "YES I AM SAVE TASK "+ currentDescription + currentTitle)

        if (currentDescription == null || currentTitle == null) {
            return
        }

        if (currentTitle.isEmpty() || currentDescription.isEmpty()) return

        val currentTaskId = taskId

        if (isNewTask || currentTaskId == null) {
            val task = Task(currentTitle, currentDescription,getCurrentDateAndTime(),getCurrentDateAndTime())
            createTask(task)
        }else {

            val task = Task(currentTitle, currentDescription, currentCreatedDateTime!!,getCurrentDateAndTime(), isCompleted = taskCompleted, currentTaskId)
            updateTask(task)
        }
    }

    private fun createTask(task : Task) = viewModelScope.launch {
        repository.saveTask(task)
      //  Log.e("MY TAG", "YES I AM CALING")
        _taskUpdateEvent.value = Event(Unit)
    }

    private fun updateTask(task: Task) {

        if (isNewTask) {
            return
        }
        viewModelScope.launch {
            repository.saveTask(task)
            _taskUpdateEvent.value = Event(Unit)
        }
    }

    fun deleteTask()   {
        if (taskId == null) return

        val currentTaskId : String = this.taskId!!

        viewModelScope.launch {
            repository.deleteTask(currentTaskId)
            _taskUpdateEvent.value = Event(Unit)
        }
    }
}