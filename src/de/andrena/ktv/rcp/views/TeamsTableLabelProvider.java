package de.andrena.ktv.rcp.views;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import de.andrena.ktv.rcp.domain.Team;

class TeamsTableLabelProvider extends LabelProvider implements ITableLabelProvider {

	@SuppressWarnings("unused")
	private final DefaultView ViewLabelProvider;

	TeamsTableLabelProvider(DefaultView view) {
		this.ViewLabelProvider = view;
	}

	@Override
	public String getColumnText(Object object, int columnIndex) {
		if (object == null) {
			return "";
		}
		if (object instanceof Team) {
			if (columnIndex == 0) {
				return ((Team) object).getName();
			}
			if (columnIndex == 1) {
				return ((Team) object).getPlayer1();
			}
			if (columnIndex == 2) {
				return ((Team) object).getPlayer2();
			}
		}
		return "";
	}

	@Override
	public Image getColumnImage(Object obj, int index) {
		return this.getImage(obj);
	}

	@Override
	public Image getImage(Object obj) {
		return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
	}
}