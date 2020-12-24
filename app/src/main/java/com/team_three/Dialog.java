package com.team_three;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

import com.team_three.appstorage.profile_db;

public class Dialog extends AppCompatDialogFragment {

    EditText name, income;
  //  DialogInterface dialogInterface;
    Dialoglistener dialoglistener;
    profile_db profileDb;

    Context ctx;
    public Dialog(Context ctx) {
        this.ctx= ctx;
        profileDb= new profile_db(ctx);
    }
    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        LayoutInflater inflater= getActivity().getLayoutInflater();
        View view= inflater.inflate(R.layout.layout_dialpg, null);
        builder.setView(view)
        .setTitle("Set profile")
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        })
        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String name_1= name.getText().toString().trim();
                String income_1= income.getText().toString().trim();
                dialoglistener.applyTexts(name_1,income_1);
            }
        })
        .setNeutralButton("View", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                AlertDialog.Builder a1= new AlertDialog.Builder(ctx);
                a1.setTitle("Profile viewer");
                a1.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

               String msg="";

                SQLiteDatabase db= profileDb.getReadableDatabase();
                String s1= "select * from profiler";
                Cursor data= db.rawQuery(s1,null);
                if (data.getCount() > 0 && data!=null) {
                    if (data.moveToFirst()) {
                        do {
                            msg= "Profile name: "+ data.getString(0) + "\n" + "Income money: "+ data.getString(1);
                        } while (data.moveToNext());
                    }
                }

                a1.setMessage(msg);
                AlertDialog a11= a1.create();
                a11.show();
            }
        });
        name= view.findViewById(R.id.name_dialog_id);
        income= view.findViewById(R.id.income_dialog_id);
        return builder.create();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            dialoglistener = (Dialoglistener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "must miplement example dialog listener");
        }
    }

    public interface Dialoglistener {
        public void applyTexts(String name, String income);
    }

}
