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
package org.equinoxosgi.toast.internal.client.emergency.bundle;

import org.equinoxosgi.toast.dev.airbag.IAirbag;
import org.equinoxosgi.toast.dev.gps.IGps;
import org.equinoxosgi.toast.internal.client.emergency.EmergencyMonitor;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {
	private IAirbag airbag;
	private ServiceReference airbagRef;
	private IGps gps;
	private ServiceReference gpsRef;
	private EmergencyMonitor monitor;

// tag::start[]
	public void start(BundleContext context) throws Exception {
		System.out.println("Launching");
		monitor = new EmergencyMonitor();
		gpsRef = context.getServiceReference(IGps.class.getName());
		airbagRef = context.getServiceReference(IAirbag.class.getName());
		if (gpsRef == null || airbagRef == null) {
			System.err.println("Unable to acquire GPS or airbag!");
			return;
		}
		gps = (IGps) context.getService(gpsRef);
		airbag = (IAirbag) context.getService(airbagRef);
		if (gps == null || airbag == null) {
			System.err.println("Unable to acquire GPS or airbag!");
			return;
		}
		monitor.setGps(gps);
		monitor.setAirbag(airbag);
		monitor.startup();
	}
// end::start[]

// tag::stop[]
	public void stop(BundleContext context) throws Exception {
		monitor.shutdown();
		if (gpsRef != null)
			context.ungetService(gpsRef);
		if (airbagRef != null)
			context.ungetService(airbagRef);
		System.out.println("Terminating");
	}
// end::stop[]
}
