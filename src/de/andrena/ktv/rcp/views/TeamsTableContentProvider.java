package de.andrena.ktv.rcp.views;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import de.andrena.ktv.model.Team;

class TeamsTableContentProvider implements IStructuredContentProvider {

	private final TeamsTableView ViewContentProvider;

	public TeamsTableContentProvider(TeamsTableView view) {
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