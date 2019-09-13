package com.kalai.todo;

public interface EditDialogFragmentListener {
    void receiveEditDialogFragment(int tobeDeletedTodoPosition,String todo, Float priority);
}
