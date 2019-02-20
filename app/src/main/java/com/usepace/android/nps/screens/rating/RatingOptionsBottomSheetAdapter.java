package com.usepace.android.nps.screens.rating;

import android.app.Dialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import com.usepace.android.nps.R;

/**
 * Created by MohammedNabil on 8/27/17.
 */

public class RatingOptionsBottomSheetAdapter extends BottomSheetDialogFragment {


    @Override
    public void setupDialog(Dialog dialog, int style) {
        View v = View.inflate(getContext(), R.layout.bottom_sheet_rating_options_buttons, null);
        dialog.setContentView(v);
        renderDialog(v);
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
        }
        catch (Exception e){}
    }


    public static RatingOptionsBottomSheetAdapter newInstance() {
        RatingOptionsBottomSheetAdapter fragment = new RatingOptionsBottomSheetAdapter();
        return fragment;
    }
}
