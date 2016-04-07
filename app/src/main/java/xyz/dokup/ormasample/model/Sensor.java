package xyz.dokup.ormasample.model;

import android.support.annotation.NonNull;

import com.github.gfx.android.orma.annotation.Column;
import com.github.gfx.android.orma.annotation.PrimaryKey;
import com.github.gfx.android.orma.annotation.Setter;
import com.github.gfx.android.orma.annotation.Table;

/**
 * Created by e10dokup on 2016/04/07
 **/
@Table
public class Sensor {
    private static final String TAG = Sensor.class.getSimpleName();
    private final Sensor self = this;

    @PrimaryKey
    public String macAddress;

    @Column
    public String uuid;

    @Column
    public String name;

    @Column
    public String passCode;

    @Setter
    public Sensor(@NonNull String macAddress, @NonNull String uuid, @NonNull String name, @NonNull String passCode) {
        this.macAddress = macAddress;
        this.uuid = uuid;
        this.name = name;
        this.passCode = passCode;
    }
}