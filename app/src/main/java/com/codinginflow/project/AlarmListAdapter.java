package com.codinginflow.project;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.sdsu.cs646.shameetha.alarmclocktest.R;

import java.util.List;


public class AlarmListAdapter extends BaseAdapter {

    private Context mContext;
    private List<AlarmModel> mAlarms;

    public AlarmListAdapter(Context context, List<AlarmModel> alarms) {
        mContext = context;
        mAlarms = alarms;
    }

    public void setAlarms(List<AlarmModel> alarms) {
        mAlarms = alarms;
    }

    @Override
    public int getCount() {
        if (mAlarms != null) {
            return mAlarms.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (mAlarms != null) {
            return mAlarms.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        if (mAlarms != null) {
            return mAlarms.get(position).id;
        }
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.alarm_list_item, parent, false);
        }

        AlarmModel model = (AlarmModel) getItem(position);

        TextView txtTime = (TextView) view.findViewById(R.id.alarm_item_time);
        txtTime.setText(String.format("%02d : %02d", model.timeHour, model.timeMinute));

        TextView txtName = (TextView) view.findViewById(R.id.alarm_item_name);
        txtName.setText(model.name);

        updateTextColor((TextView) view.findViewById(R.id.alarm_item_sunday), model.getRepeatingDay(AlarmModel.SUNDAY));
        updateTextColor((TextView) view.findViewById(R.id.alarm_item_monday), model.getRepeatingDay(AlarmModel.MONDAY));
        updateTextColor((TextView) view.findViewById(R.id.alarm_item_tuesday), model.getRepeatingDay(AlarmModel.TUESDAY));
        updateTextColor((TextView) view.findViewById(R.id.alarm_item_wednesday), model.getRepeatingDay(AlarmModel.WEDNESDAY));
        updateTextColor((TextView) view.findViewById(R.id.alarm_item_thursday), model.getRepeatingDay(AlarmModel.THURSDAY));
        updateTextColor((TextView) view.findViewById(R.id.alarm_item_friday), model.getRepeatingDay(AlarmModel.FRIDAY));
        updateTextColor((TextView) view.findViewById(R.id.alarm_item_saturday), model.getRepeatingDay(AlarmModel.SATURDAY));

        ToggleButton btnToggle = (ToggleButton) view.findViewById(R.id.alarm_item_toggle);
        btnToggle.setChecked(model.isEnabled);
        btnToggle.setTag(Long.valueOf(model.id));
        btnToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ((AlarmListActivity) mContext).setAlarmEnabled(((Long) buttonView.getTag()).longValue(), isChecked);
            }
        });

        view.setTag(Long.valueOf(model.id));
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ((AlarmListActivity) mContext).startDetailsActivity(((Long) view.getTag()).longValue());
            }
        });

        view.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                ((AlarmListActivity) mContext).deleteAlarm(((Long) view.getTag()).longValue());
                return true;
            }
        });

        return view;
    }

    /** Update the color of the selected days */
    private void updateTextColor(TextView view, boolean isOn) {
        if (isOn) {
            view.setTextColor(Color.GREEN);
        } else {
            view.setTextColor(Color.BLACK);
        }
    }



}
