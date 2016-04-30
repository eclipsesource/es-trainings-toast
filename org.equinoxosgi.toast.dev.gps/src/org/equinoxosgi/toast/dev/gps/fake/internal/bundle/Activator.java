package org.equinoxosgi.toast.dev.gps.fake.internal.bundle;

import org.equinoxosgi.toast.dev.gps.IGps;
import org.equinoxosgi.toast.dev.gps.fake.internal.FakeGps;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

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