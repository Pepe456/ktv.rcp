package de.andrena.ktv.rcp.views;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import de.andrena.ktv.rcp.domain.GameDetails;

public class SpielplanContentProvider implements IStructuredContentProvider {

	@SuppressWarnings("unused")
	private final DefaultView teamsTableView;

	public SpielplanContentProvider(DefaultView teamsTableView) {
		this.teamsTableView = teamsTableView;
	}

	@Override
	public void dispose() {

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof GameDetails[]) {
			return (GameDetails[]) inputElement;
		}
		return new Object[0];
	}

}
