package org.equinoxosgi.toast.dev.airbag.fake.internal.bundle;

import org.equinoxosgi.toast.dev.airbag.IAirbag;
import org.equinoxosgi.toast.dev.airbag.fake.internal.FakeAirbag;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {
	private ServiceRegistration registration;
	private FakeAirbag airbag;

	public void start(BundleContext context) {
		airbag = new FakeAirbag();
		airbag.startUp();
		registration = context.registerService(IAirbag.class.getName(), airbag, null);
	}

	public void stop(BundleContext context) {
		registration.unregister();
		airbag.shutDown();
	}
}