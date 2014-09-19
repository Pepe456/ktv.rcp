package de.andrena.ktv.rcp.views;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import de.andrena.ktv.rcp.domain.Team;

class TeamsTableContentProvider implements IStructuredContentProvider {

	@SuppressWarnings("unused")
	private final DefaultView ViewContentProvider;

	public TeamsTableContentProvider(DefaultView view) {
		this.ViewContentProvider = view;
	}

	@Override
	public void inputChanged(Viewer v, Object oldInput, Object newInput) {
	}

	@Override
	public void dispose() {
	}

	@Override
	public Object[] getElements(Object parent) {
		if (parent instanceof Team[]) {
			return (Object[]) parent;
		}
		return new Object[0];
	}
}