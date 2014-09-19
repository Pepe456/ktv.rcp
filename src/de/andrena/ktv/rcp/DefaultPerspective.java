package de.andrena.ktv.rcp;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import de.andrena.ktv.rcp.views.DefaultView;

public class DefaultPerspective implements IPerspectiveFactory {

	public static final String ID = "de.andrena.ktv.rcp.defaultperspective";

	@Override
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
		layout.addStandaloneView(DefaultView.ID, false, IPageLayout.LEFT, 1.0f, layout.getEditorArea());
	}

}
