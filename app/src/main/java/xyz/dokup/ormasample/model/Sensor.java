package xyz.dokup.ormasample.model;

import com.github.gfx.android.orma.annotation.Column;
import com.github.gfx.android.orma.annotation.PrimaryKey;
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
    public byte passCode[];
}