package com.kalai.todo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;



public class EditDialogFragment extends DialogFragment {

    RatingBar priorityFragRatingBar;
    EditText todoFragEditText;
    Button editFragButton;
    EditDialogFragmentListener listener;
    public static final String TAG = "com.kalai.todo.EditDialogFragment";


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof EditDialogFragmentListener) {
            this.listener = (EditDialogFragmentListener) context;
        } else {
            throw new ClassCastException("Fragment called from wrong Activity "+context.toString());
        }
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.dialog_edit_todo,container,false);
        priorityFragRatingBar =view.findViewById(R.id.priorityFragRatingBar);
        todoFragEditText =view.findViewById(R.id.todoFragEditText);
        editFragButton=view.findViewById(R.id.editFragButton);
        Bundle args=getArguments();
        final String existingTodoText=args.getString(MainActivity.TODO_TAG);
        final float existingTodoPriority=args.getFloat(MainActivity.TODO_PRIORITY_TAG);
        final int position=args.getInt(MainActivity.TODO_POSITION);
        todoFragEditText.setText(existingTodoText);
        priorityFragRatingBar.setRating(existingTodoPriority);

        editFragButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updatedTodoText=todoFragEditText.getText().toString();
                Float  updatedTodoPriority=priorityFragRatingBar.getRating();
                if(updatedTodoText.isEmpty()
                        ||listener==null||(updatedTodoPriority.equals(existingTodoPriority) && updatedTodoText.equals(existingTodoText))){
                    if(listener!=null){
                        listener.noUpdate();
                    }
                    getDialog().dismiss();
                }
                else{
              listener.receiveEditDialogFragment(position,updatedTodoText,updatedTodoPriority);
              getDialog().dismiss();
                }

            }
        });
        return view;
    }





}
