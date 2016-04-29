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
package org.equinoxosgi.toast.internal.dev.airbag.fake;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.equinoxosgi.toast.dev.airbag.IAirbag;
import org.equinoxosgi.toast.dev.airbag.IAirbagListener;

public class FakeAirbag implements IAirbag {
	private List listeners = new ArrayList();
	private Job job;
	private boolean isRunning;

	public synchronized void addListener(IAirbagListener listener) {
		listeners.add(listener);
	}

	private synchronized void deploy() {
		for (Iterator i = listeners.iterator(); i.hasNext();)
			((IAirbagListener) i.next()).deployed();
	}

	public synchronized void removeListener(IAirbagListener listener) {
		listeners.remove(listener);
	}

	public synchronized void shutdown() {
		isRunning = false;
		job.cancel();
		try {
			job.join();
		} catch (InterruptedException e) {
			// shutting down, safe to ignore
		}
	}

	public synchronized void startup() {
		isRunning = true;
		job = new Job("FakeAirbag") {
			protected IStatus run(IProgressMonitor monitor) {
				deploy();
				if (isRunning)
					schedule(5000);
				return Status.OK_STATUS;
			}
		};
		job.schedule(5000);
	}
}
