package com.usepace.android.nps.screens.rating;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.widget.TextView;
import com.usepace.android.nps.R;
import com.usepace.android.nps.io.model.RatingModel;

/**
 * Created by MohammedNabil on 8/27/17.
 */

public class RatingOptionsBottomSheetAdapter extends BottomSheetDialogFragment {

    private RatingModel ratingModel;

    @Override
    public void setupDialog(Dialog dialog, int style) {
        View v = View.inflate(getContext(), R.layout.bottom_sheet_rating_options_buttons, null);
        dialog.setContentView(v);
        renderDialog(v);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ratingModel = (RatingModel) getArguments().getSerializable("RATING");
    }

    protected void renderDialog(View v) {
        try {
            ((View) v.getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));
            View parent = (View) v.getParent();
            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) parent.getLayoutParams();
            layoutParams.setMargins(
                    getResources().getDimensionPixelSize(R.dimen.bottom_sheet_margin_left_right),
                    0,
                    getResources().getDimensionPixelSize(R.dimen.bottom_sheet_margin_left_right),
                    getResources().getDimensionPixelSize(R.dimen.bottom_sheet_margin_bottom)
            );
            parent.setLayoutParams(layoutParams);
            renderUI(v);
        }
        catch (Exception e){}
    }

    private void renderUI(View v) {
        ((TextView)v.findViewById(R.id.tv_question)).setText(ratingModel != null && ratingModel.getQuestion() != null ? ratingModel.getQuestion() : "");
        ((TextView)v.findViewById(R.id.tv_high_hint)).setText(ratingModel != null && ratingModel.getHighestHint() != null ? ratingModel.getHighestHint() : "");
        ((TextView)v.findViewById(R.id.tv_low_hint)).setText(ratingModel != null && ratingModel.getLowestHint() != null ? ratingModel.getLowestHint() : "");
    }

    public static RatingOptionsBottomSheetAdapter newInstance(RatingModel ratingModel) {
        RatingOptionsBottomSheetAdapter fragment = new RatingOptionsBottomSheetAdapter();
        Bundle bundle = new Bundle();
        bundle.putSerializable("RATING", ratingModel);
        fragment.setArguments(bundle);
        return fragment;
    }
}
