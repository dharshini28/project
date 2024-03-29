package com.codinginflow.project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.sdsu.cs646.shameetha.alarmclocktest.R;

/**
 * Created by Shameetha on 4/28/15.
 */
@SuppressLint("NewApi")
public class AlarmToggleButton extends FrameLayout {

    private TextView label;
    private CompoundButton button;

    @SuppressLint("ResourceType")
    public AlarmToggleButton(Context context,AttributeSet attrs) {
        super(context, attrs);
        RelativeLayout layout = new RelativeLayout(context);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layout.setLayoutParams(layoutParams);
        layout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                button.toggle();
            }
        });
        layout.setBackgroundResource(R.drawable.view_touch_selector);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            button = new Switch(context);
        } else {
            button = new CheckBox(context);
        }
        button.setText("");
        RelativeLayout.LayoutParams buttonParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        buttonParams.topMargin = 16;
        buttonParams.bottomMargin = 16;
        buttonParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        buttonParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        buttonParams.addRule(RelativeLayout.ALIGN_RIGHT, 2);
        label = new TextView(context);
        RelativeLayout.LayoutParams labelParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        labelParams.leftMargin = 8;
        labelParams.addRule(RelativeLayout.ALIGN_BASELINE, 1);
        View emptyView = new View(context);
        RelativeLayout.LayoutParams emptyViewParams = new RelativeLayout.LayoutParams(0, 0);
        emptyViewParams.addRule(RelativeLayout.BELOW, button.getId());
        layout.addView(label, labelParams);
        layout.addView(button, buttonParams);
        layout.addView(emptyView, emptyViewParams);
        addView(layout);
        int[] attributeSet = {
                android.R.attr.text,
                android.R.attr.checked
        };
        TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, attributeSet, 0, 0);
        try {
            label.setText(a.getText(0));
            button.setChecked(a.getBoolean(1, false));
        } finally {
            a.recycle();
        }
    }
    public void setChecked(boolean isChecked) {
        button.setChecked(isChecked);
    }
    public boolean isChecked() {
        return button.isChecked();
    }
}
