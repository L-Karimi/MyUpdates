package com.moringaschool.myupdates.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.myupdates.R;
import com.moringaschool.myupdates.models.Article;
import com.moringaschool.myupdates.ui.NewsDetailActivity;
import com.moringaschool.myupdates.util.Constants;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;

public class FirebaseNewsUpdatesViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {


    @BindView(R.id.BookmarkButton)
    Button mBookmarkButton;

    Context mContext;
    public ImageView NewsImageView;
    public TextView TitleNameTextView;
    public TextView authorTextView;
//    public ImageView categoryTextView;

    public FirebaseNewsUpdatesViewholder(View itemView) {
        super(itemView);
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
//        mView = itemView;
//        mContext = itemView.getContext();
//        itemView.setOnClickListener(this);

        NewsImageView = itemView.findViewById(R.id.NewsImageView);
        TitleNameTextView = itemView.findViewById(R.id.TitleNameTextView);
//        categoryTextView = itemView.findViewById(R.id.categoryTextView);
        authorTextView = itemView.findViewById(R.id.authorTextView);
    }



    @Override
    public void onClick(View v) {

        final ArrayList<Article> top_headlines = new ArrayList<>();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference reference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_TOP_HEADLINES)
                .child(uid);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    top_headlines.add(snapshot.getValue(Article.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, NewsDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("top_headlines", Parcels.wrap(top_headlines));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}

