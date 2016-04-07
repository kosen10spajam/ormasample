package xyz.dokup.ormasample;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import xyz.dokup.ormasample.adapter.SensorAdapter;
import xyz.dokup.ormasample.model.OrmaDatabase;
import xyz.dokup.ormasample.model.Sensor;
import xyz.dokup.ormasample.model.Sensor_Selector;

public class MainActivity extends AppCompatActivity {
    private final MainActivity self = this;
    static final String DB_NAME = "main.db";

    OrmaDatabase mOrmaDatabase;

    EditText mMacAddressEdit;
    EditText mUuidEdit;
    EditText mNameEdit;
    EditText mPassCodeEdit;
    Button mCreateButton;
    ListView mSensorListView;

    List<Sensor> mSensorList;

    SensorAdapter mSensorAdapter;

    public void setupV1Database() {
        deleteDatabase(DB_NAME);
        SQLiteDatabase database = openOrCreateDatabase(DB_NAME, 0, null);
        database.setVersion(1);
        database.execSQL("CREATE TABLE sensors (mac_address TEXT PRIMARY_KEY, uuid TEXT, name STRING, pass_code STRING)");
        database.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMacAddressEdit = (EditText) findViewById(R.id.edit_mac_address);
        mUuidEdit = (EditText) findViewById(R.id.edit_uuid);
        mNameEdit = (EditText) findViewById(R.id.edit_name);
        mPassCodeEdit = (EditText) findViewById(R.id.edit_pass_code);
        mCreateButton = (Button) findViewById(R.id.btn_create);
        mSensorListView = (ListView) findViewById(R.id.listView);

        mCreateButton.setOnClickListener(mCreateButtonListener);
        // setupV1Database();
        mOrmaDatabase = OrmaDatabase.builder(this).name(DB_NAME).build();

        mSensorList = selectSensor();
        mSensorAdapter = new SensorAdapter(this, mSensorList);
        mSensorListView.setAdapter(mSensorAdapter);
    }

    private void insertSensor(Sensor sensor) {
        mOrmaDatabase.insertIntoSensor(sensor);
    }

    private List<Sensor> selectSensor() {
        Sensor_Selector selector = mOrmaDatabase.selectFromSensor().orderByMacAddressAsc();
        return selector.toList();
    }

    private View.OnClickListener mCreateButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String macAddress = mMacAddressEdit.getText().toString();
            String uuid = mUuidEdit.getText().toString();
            String name = mNameEdit.getText().toString();
            String passCode = mPassCodeEdit.getText().toString();
            Sensor sensor = new Sensor(macAddress, uuid, name, passCode);
            insertSensor(sensor);
            mSensorList = selectSensor();
            mSensorAdapter = new SensorAdapter(self, mSensorList);
            mSensorListView.setAdapter(mSensorAdapter);
        }
    };
}
