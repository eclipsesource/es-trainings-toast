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
package org.equinoxosgi.toast.internal.dev.gps.fake.bundle;

import org.equinoxosgi.toast.dev.gps.IGps;
import org.equinoxosgi.toast.internal.dev.gps.fake.FakeGps;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

// tag::type[]
public class Activator implements BundleActivator {
	private ServiceRegistration registration;

	public void start(BundleContext context) {
		FakeGps gps = new FakeGps();
		registration = context.registerService(IGps.class.getName(), gps, null);
	}

	public void stop(BundleContext context) {
		registration.unregister();
	}
}
// end::type[]
