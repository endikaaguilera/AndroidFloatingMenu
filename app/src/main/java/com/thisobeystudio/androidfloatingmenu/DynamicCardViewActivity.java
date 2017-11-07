package com.thisobeystudio.androidfloatingmenu;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.transition.TransitionManager;
import android.view.View;

/**
 * Created by thisobeystudio on 21/10/17.
 * Copyright: (c) 2017 ThisObey Studio
 * Contact: thisobeystudio@gmail.com
 */

public class DynamicCardViewActivity extends AppCompatActivity {

    private ConstraintLayout mParent;

    private int defaultWidth;
    private int defaultHeight;
    private float defaultElevation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_card_view);
        mParent = (ConstraintLayout) findViewById(R.id.dynamic_card_view_parent);
        CardView mCardView01 = (CardView) findViewById(R.id.dynamic_card_view_01);
        CardView mCardView02 = (CardView) findViewById(R.id.dynamic_card_view_02);
        CardView mCardView03 = (CardView) findViewById(R.id.dynamic_card_view_03);
        CardView mCardView04 = (CardView) findViewById(R.id.dynamic_card_view_04);

        defaultElevation = ViewCompat.getElevation(mCardView01);
        defaultWidth = mCardView01.getLayoutParams().width;
        defaultHeight = mCardView01.getLayoutParams().height;

        setOnclickListener(mCardView01);
        setOnclickListener(mCardView02);
        setOnclickListener(mCardView03);
        setOnclickListener(mCardView04);

    }

    private void setOnclickListener(final CardView cardView) {
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pw = mParent.getLayoutParams().width;
                int ph = mParent.getLayoutParams().height;
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(mParent);
                TransitionManager.beginDelayedTransition(mParent);
                if (cardView.getLayoutParams().width == pw) {
                    constraintSet.constrainWidth(cardView.getId(), defaultWidth);
                    constraintSet.constrainHeight(cardView.getId(), defaultHeight);
                    ViewCompat.setElevation(cardView, defaultElevation);
                } else {
                    constraintSet.constrainWidth(cardView.getId(), pw);
                    constraintSet.constrainHeight(cardView.getId(), ph);
                    ViewCompat.setElevation(cardView, defaultElevation + 300);
                }
                constraintSet.applyTo(mParent);
            }
        });
    }

}
