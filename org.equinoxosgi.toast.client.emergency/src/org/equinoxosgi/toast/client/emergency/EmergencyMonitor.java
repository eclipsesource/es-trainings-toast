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
package org.equinoxosgi.toast.client.emergency;

import org.equinoxosgi.toast.dev.airbag.Airbag;
import org.equinoxosgi.toast.dev.airbag.IAirbagListener;
import org.equinoxosgi.toast.dev.gps.Gps;

// tag::type[]
public class EmergencyMonitor implements IAirbagListener {
	private Airbag airbag;
	private Gps gps;

	public void deployed() {
		System.out.println("Emergency occurred at lat=" + gps.getLatitude()
				+ " lon=" + gps.getLongitude() + " heading=" + gps.getHeading()
				+ " speed=" + gps.getSpeed());
	}

	public void setAirbag(Airbag value) {
		airbag = value;
	}

	public void setGps(Gps value) {
		gps = value;
	}

	public void shutdown() {
		airbag.removeListener(this);
	}

	public void startup() {
		airbag.addListener(this);
	}
}
// end::type[]
