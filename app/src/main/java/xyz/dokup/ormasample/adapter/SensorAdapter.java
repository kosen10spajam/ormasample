package xyz.dokup.ormasample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import xyz.dokup.ormasample.R;
import xyz.dokup.ormasample.model.Sensor;

/**
 * Created by e10dokup on 2016/04/08
 **/
class SensorViewHolder {
    TextView macAddressText;
    TextView uuidText;
    TextView nameText;
    TextView passCodeText;
}

public class SensorAdapter extends BaseAdapter {
    private static final String TAG = SensorAdapter.class.getSimpleName();
    private final SensorAdapter self = this;

    Context mContext;
    LayoutInflater mLayoutInflater = null;
    List<Sensor> mSensorList;

    public SensorAdapter(Context context, List<Sensor> sensorList) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mSensorList = sensorList;
    }

    public void setSensorList(List<Sensor> sensorList) {
        mSensorList = sensorList;
    }

    @Override
    public int getCount() {
        return mSensorList.size();
    }

    @Override
    public Object getItem(int i) {
        return mSensorList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        SensorViewHolder viewHolder = new SensorViewHolder();
        if(view == null) {
            view = mLayoutInflater.inflate(R.layout.row_sensor, viewGroup, false);
            viewHolder.macAddressText = (TextView) view.findViewById(R.id.text_mac_address);
            viewHolder.uuidText = (TextView) view.findViewById(R.id.text_uuid);
            viewHolder.nameText = (TextView) view.findViewById(R.id.text_name);
            viewHolder.passCodeText = (TextView) view.findViewById(R.id.text_pass_code);
            view.setTag(viewHolder);
        } else {
            viewHolder = (SensorViewHolder) view.getTag();
        }

        viewHolder.macAddressText.setText(mSensorList.get(i).macAddress);
        viewHolder.uuidText.setText(mSensorList.get(i).uuid);
        viewHolder.nameText.setText(mSensorList.get(i).name);
        viewHolder.passCodeText.setText(mSensorList.get(i).passCode);

        return view;
    }
}