package com.tomasmacri.mytodoapp.addtasks.ui.domain.model

data class Task(var id: Int = 0, val task: String = "", var done: Boolean = false){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Task

        if (id != other.id) return false
        if (done != other.done) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
//        result = 31 * result + done.hashCode()
        return result
    }
}

