package org.equinoxosgi.toast.dev.airbag.fake.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.equinoxosgi.toast.dev.airbag.IAirbag;
import org.equinoxosgi.toast.dev.airbag.IAirbagListener;

public class FakeAirbag implements IAirbag {
	private List listeners = new ArrayList();
	private Thread worker;

	public synchronized void startUp() {
		Runnable code = new Runnable() {
			public void run() {
				try {
					while (true) {
						Thread.sleep(5000);
						deploy();
					}
				} catch (InterruptedException e) {
				}
			}
		};
		worker = new Thread(code, "FakeAirbag");
		worker.start();
	}

	public synchronized void shutDown() {
		worker.interrupt();
		try {
			worker.join();
		} catch (InterruptedException e) {
			// safe to ignore
		}
		worker = null;
	}

	// IAirbag implementation
	public void addListener(IAirbagListener listener) {
		synchronized (listeners) {
			listeners.add(listener);
		}
	}

	public void removeListener(IAirbagListener listener) {
		synchronized (listeners) {
			listeners.remove(listener);
		}
	}

	// Private
	private void deploy() {
		synchronized (listeners) {
			for (Iterator i = listeners.iterator(); i.hasNext();)
				((IAirbagListener) i.next()).deployed();
		}
	}
}