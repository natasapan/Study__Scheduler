package com.project.study__scheduler;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by natas on 4/19/2018.
 */

public class TaskListAdapter extends ArrayAdapter<Task> {
    private Context mContext;
    private ArrayList<Task> tasks;
    private DatabaseHelper dbHelper;
    private  Map<Integer, View> taskListViews;

     TaskListAdapter(Context context, ArrayList<Task> tasks) {
        super(context, 0, tasks);
        this.mContext = context;
        this.tasks = tasks;
        this.dbHelper = new DatabaseHelper(context);
        this.taskListViews = taskListViews;
    }

    @Override
    public View getView(final int position, View listItemView, @NonNull ViewGroup parent) {
        final Task task = tasks.get(position);
        ViewHolder holder = null;

        if (listItemView == null) {
            holder = new ViewHolder();
            listItemView = LayoutInflater.from(mContext).inflate(R.layout.task_list_item, parent, false);

            final TextView taskTextView = (TextView) listItemView.findViewById(R.id.ItemCaption);
            CheckBox checkBox = (CheckBox) listItemView.findViewById(R.id.checkbox);
            Button removeButton = (Button) listItemView.findViewById(R.id.removeItem);

            taskTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showChangeLangDialog(task);
                }
            });

            holder.isChecked = checkBox;
            holder.task = taskTextView;

            taskTextView.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    task.setTask(charSequence.toString());
                }

                @Override
                public void afterTextChanged(Editable editable) { }
            });

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    task.setChecked(isChecked);
                }
            });

            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "Task Deleted", Toast.LENGTH_LONG).show();
                    dbHelper.deleteTask(task.getId());
                    tasks.remove(position);
                    notifyDataSetChanged();
                }
            });

            listItemView.setTag(holder);
        } else {
            holder = (ViewHolder) listItemView.getTag();
        }

        holder.task.setText(task.getTask());
        holder.isChecked.setChecked(task.isChecked());
        return listItemView;
    }

    public void showChangeLangDialog(final Task task) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this.mContext, R.style.EditTaskTheme);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View dialogView = inflater.inflate(R.layout.edit_task, null);
        dialogBuilder.setView(dialogView);

        final EditText editText = (EditText) dialogView.findViewById(R.id.edit);

        editText.setText(task.getTask());

        dialogBuilder.setTitle("Edit Task");
        dialogBuilder.setMessage("Enter task text below");
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                task.setTask(editText.getText().toString());
                notifyDataSetChanged();
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });

        AlertDialog builder = dialogBuilder.create();
        builder.show();
    }

    private static class ViewHolder {
        private TextView task;
        private CheckBox isChecked;
    }
}
