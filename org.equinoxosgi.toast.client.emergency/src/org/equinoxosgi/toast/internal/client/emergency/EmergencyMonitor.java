/*******************************************************************************
 * Copyright (c) 2009 Paul VanderLei, Simon Archer, Jeff McAffer and others. All
 * rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Toast is an Equinox/OSGi system developed for the book Eclipse, Equinox and
 * OSGi - Creating Highly Modular Java Applications See http://equinoxosgi.org
 * 
 * Contributors: Paul VanderLei, Simon Archer and Jeff McAffer - initial API and
 * implementation
 *******************************************************************************/
package org.equinoxosgi.toast.internal.client.emergency;

import org.equinoxosgi.toast.dev.airbag.IAirbag;
import org.equinoxosgi.toast.dev.airbag.IAirbagListener;
import org.equinoxosgi.toast.dev.gps.IGps;

public class EmergencyMonitor implements IAirbagListener {
	private IAirbag airbag;
	private IGps gps;

	public void deployed() {
		System.out.println("Emergency occurred at lat=" + gps.getLatitude()
				+ " lon=" + gps.getLongitude() + " heading=" + gps.getHeading()
				+ " speed=" + gps.getSpeed());
	}

	public void setAirbag(IAirbag value) {
		airbag = value;
	}

	public void setGps(IGps value) {
		gps = value;
	}

	public void shutdown() {
		if (airbag != null) {
			airbag.removeListener(this);
		}
	}

	public void startup() {
		if (airbag != null) {
			airbag.addListener(this);
		}
	}
}
