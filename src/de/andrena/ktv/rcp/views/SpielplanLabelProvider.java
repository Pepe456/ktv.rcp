package de.andrena.ktv.rcp.views;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import de.andrena.ktv.rcp.domain.GameDetails;

public class SpielplanLabelProvider extends LabelProvider implements ITableLabelProvider {

	@SuppressWarnings("unused")
	private final DefaultView teamsTableView;

	public SpielplanLabelProvider(DefaultView teamsTableView) {
		this.teamsTableView = teamsTableView;
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	@Override
	public String getColumnText(Object object, int columnIndex) {
		if (object == null) {
			return "";
		}
		if (object instanceof GameDetails) {
			if (columnIndex == 0) {
				return ((GameDetails) object).getTableId();
			}
			if (columnIndex == 1) {
				return ((GameDetails) object).getGameId();
			}
			if (columnIndex == 2) {
				return ((GameDetails) object).getTeamName1();
			}
			if (columnIndex == 3) {
				return ((GameDetails) object).getTeamName2();
			}
		}
		return "";
	}

}
