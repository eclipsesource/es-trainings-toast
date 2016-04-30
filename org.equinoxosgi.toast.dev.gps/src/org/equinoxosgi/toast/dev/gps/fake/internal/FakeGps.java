package org.equinoxosgi.toast.dev.gps.fake.internal;

import org.equinoxosgi.toast.dev.gps.IGps;

public class FakeGps implements IGps {
	public int getHeading() {
		return 90; // 90 degrees (east)
	}

	public int getLatitude() {
		return 3888746; // 38.88746 N
	}

	public int getLongitude() {
		return -7702192; // 77.02192 W
	}

	public int getSpeed() {
		return 50; // 50 kph
	}
}
