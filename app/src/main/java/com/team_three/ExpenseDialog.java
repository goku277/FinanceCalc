package com.team_three;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class ExpenseDialog extends AppCompatDialogFragment {
    EditText desc, money, note;
    //  DialogInterface dialogInterface;
    ExpenseDialoglistener dialoglistener;
    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        LayoutInflater inflater= getActivity().getLayoutInflater();
        View view= inflater.inflate(R.layout.expense_dialog, null);
        builder.setView(view)
                .setTitle("Set expense")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String desc_1= desc.getText().toString().trim();
                        String money_1= money.getText().toString().trim();
                        String note_1= note.getText().toString().trim();
                        dialoglistener.applyTexts_1(desc_1,money_1,note_1);
                    }
                });
        desc= view.findViewById(R.id.description_dialog_id);
        money= view.findViewById(R.id.income_dialog_id);
        note= view.findViewById(R.id.note_dialog_id);
        return builder.create();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            dialoglistener = (ExpenseDialoglistener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "must miplement example dialog listener");
        }
    }

    public interface ExpenseDialoglistener {
        public void applyTexts_1(String description, String money, String note);
    }

}
