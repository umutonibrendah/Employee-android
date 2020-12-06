package com.example.highrestau;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class myadapter extends FirebaseRecyclerAdapter<model,myadapter.myviewholder> {

      Context context;

    public myadapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull model model) {

        holder.img.setOnLongClickListener(new View.OnLongClickListener() {



            @Override
            public boolean onLongClick(View view) {

                new AlertDialog.Builder(context)
                        .setTitle("Delete Account")
                        .setMessage("Are you sure you want to delete this Account")

                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                                Query applesQuery = ref.child("users").orderByChild("users").equalTo(model.getEmail());
                                applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                                            appleSnapshot.getRef().removeValue();
                                            Toast.makeText(context, "deleted well! ", Toast.LENGTH_SHORT).show();

//                                            listData.remove(position);
                                            dialog.dismiss();
                                        }
                                    }
                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                        Log.e("log", "Something went wrong ", databaseError.toException());
                                    }
                                });

                            }
                        })
                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

                return false;
            }
        });
        holder.fullnume.setText(model.getFullname());
        holder.email.setText(model.getEmail());
        holder.phone.setText(model.getPhone());
        holder.dept.setText(model.getDept());
        holder.address.setText(model.getAddress());
        Glide.with(holder.img.getContext()).load(model.getPhone()).into(holder.img);

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singleerow,parent,false);
       return new myviewholder(view);


    }

    class  myviewholder extends RecyclerView.ViewHolder{

        CircleImageView img;
        TextView fullnume,email,phone,dept,address;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            img = (CircleImageView)itemView.findViewById(R.id.img1);
            fullnume = (TextView)itemView.findViewById(R.id.fullname);
            email = (TextView)itemView.findViewById(R.id.Email);
            phone = (TextView)itemView.findViewById(R.id.phone);
            dept = (TextView)itemView.findViewById(R.id.dept);
            address = (TextView)itemView.findViewById(R.id.Address);

        }
    }
}
