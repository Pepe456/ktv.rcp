package de.andrena.ktv.rcp.views;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import de.andrena.ktv.model.TeamImpl;
import de.andrena.ktv.model.util.InputValidation;
import de.andrena.ktv.rcp.dao.TeamDao;

public class TeamsTableView extends ViewPart {
	public TeamsTableView() {
	}

	public static final String ID = "de.andrena.ktv.rcp.views.tableview";

	private Group groupListOfTeams;
	private Group groupAddNewTeam;
	private TableViewer viewer;
	private Table table;
	private Button refreshButton;
	private Button addNewTeamButton;
	private Text textPlayer2Name;
	private Text textPlayer1Name;
	private Text textTeamName;
	private Label labelTeamNameToAdd;
	private Label labelPlayer1NameToAdd;
	private Label labelPlayer2NameToAdd;

	@Override
	public void createPartControl(Composite parent) {
		this.setPartName("Kicherturnierverwaltung");

		parent.setLayout(null);
		this.groupAddNewTeam = new Group(parent, SWT.NONE);
		this.groupListOfTeams = new Group(parent, SWT.NONE);
		this.groupAddNewTeam.setText("Neues Team hinzuf\u00FCgen");
		this.groupListOfTeams.setText("Liste aller Teams");
		this.groupAddNewTeam.setBounds(10, 319, 570, 126);
		this.groupListOfTeams.setBounds(10, 10, 570, 303);

		this.labelTeamNameToAdd = new Label(this.groupAddNewTeam, SWT.NONE);
		this.labelTeamNameToAdd.setBounds(15, 30, 109, 15);

		this.labelTeamNameToAdd.setText("Name des Teams:");

		this.labelPlayer1NameToAdd = new Label(this.groupAddNewTeam, SWT.NONE);
		this.labelPlayer1NameToAdd.setBounds(15, 60, 109, 15);
		this.labelPlayer1NameToAdd.setText("Name von Spieler 1:");
		this.labelPlayer2NameToAdd = new Label(this.groupAddNewTeam, SWT.NONE);
		this.labelPlayer2NameToAdd.setBounds(15, 90, 109, 15);
		this.labelPlayer2NameToAdd.setText("Name von Spieler 2:");
		this.textTeamName = new Text(this.groupAddNewTeam, SWT.BORDER);
		this.textTeamName.setBounds(130, 30, 140, 21);

		this.textPlayer1Name = new Text(this.groupAddNewTeam, SWT.BORDER);
		this.textPlayer1Name.setBounds(130, 60, 140, 21);
		this.textPlayer2Name = new Text(this.groupAddNewTeam, SWT.BORDER);
		this.textPlayer2Name.setBounds(130, 90, 140, 21);
		this.addNewTeamButton = new Button(this.groupAddNewTeam, SWT.NONE);
		this.addNewTeamButton.setBounds(471, 85, 89, 25);
		this.addNewTeamButton.setText("Add new team");

		this.table = new Table(this.groupListOfTeams, SWT.FULL_SELECTION | SWT.V_SCROLL);
		this.table.setBounds(10, 24, 550, 228);
		this.viewer = new TableViewer(this.table);

		this.table.setHeaderVisible(true);
		this.table.setLinesVisible(true);

		TableColumn columnTeamName = new TableColumn(this.table, SWT.LEFT);
		columnTeamName.setImage(null);
		columnTeamName.setResizable(false);
		columnTeamName.setText("Teamname");
		columnTeamName.setWidth(180);
		TableColumn columnPlayer1 = new TableColumn(this.table, SWT.LEFT);
		columnPlayer1.setImage(null);
		columnPlayer1.setResizable(false);
		columnPlayer1.setText("Player 1");
		columnPlayer1.setWidth(180);
		TableColumn columnPlayer2 = new TableColumn(this.table, SWT.LEFT);
		columnPlayer2.setImage(null);
		columnPlayer2.setResizable(false);
		columnPlayer2.setText("Player 2");
		columnPlayer2.setWidth(180);

		this.refreshButton = new Button(this.groupListOfTeams, SWT.NONE);
		this.refreshButton.setBounds(449, 268, 111, 25);
		this.refreshButton.setText("Aktualisiere Teams");

		Button btnDeleteSelectedTeam = new Button(this.groupListOfTeams, SWT.NONE);
		btnDeleteSelectedTeam.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] selection = TeamsTableView.this.table.getSelection();
				for (TableItem tableItem : selection) {
					System.out.println(tableItem.getText());
				}
				boolean successful = TeamDao.deleteTeam(selection[0].getText());
				if (successful) {
					TeamsTableView.this.refreshTable();
				}
			}
		});
		btnDeleteSelectedTeam.setBounds(287, 268, 156, 25);
		btnDeleteSelectedTeam.setText("L\u00F6sche ausgew\u00E4hltes Team");

		final Label messagePlaceholder = new Label(this.groupListOfTeams, SWT.NONE);
		messagePlaceholder.setBounds(10, 278, 271, 15);

		this.refreshButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TeamsTableView.this.viewer.setInput(TeamDao.getCurrentTeams());
			}
		});

		this.viewer.setContentProvider(new TeamsTableContentProvider(this));
		this.viewer.setLabelProvider(new TableLabelProvider(this));
		this.viewer.setInput(TeamDao.getCurrentTeams());

		this.addNewTeamButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				String teamName = TeamsTableView.this.textTeamName.getText();
				String player1Name = TeamsTableView.this.textPlayer1Name.getText();
				String player2Name = TeamsTableView.this.textPlayer2Name.getText();

				if (!InputValidation.validate(teamName)) {
					messagePlaceholder.setText("Teamname ist nicht korrekt.");
					return;
				}

				if (TeamDao.getTeam(teamName) != null) {
					messagePlaceholder.setText("Team existiert bereits!");
					return;
				}

				if (!InputValidation.validate(player1Name)) {
					messagePlaceholder.setText("Name für Spieler 1 ist nicht korrekt.");
					return;
				}
				if (!InputValidation.validate(player2Name)) {
					messagePlaceholder.setText("Name für Spieler 2 ist nicht korrekt.");
					return;
				}

				boolean successful = TeamDao.addNewTeam(new TeamImpl(teamName, player1Name, player2Name));
				if (successful) {
					TeamsTableView.this.refreshTable();
					TeamsTableView.this.resetAddTeamTextFields();
				}
			}
		});
	}

	@Override
	public void setFocus() {
		this.viewer.getControl().setFocus();
	}

	private void refreshTable() {
		this.viewer.setInput(TeamDao.getCurrentTeams());
	}

	private void resetAddTeamTextFields() {
		this.textPlayer2Name.setText("");
		this.textPlayer1Name.setText("");
		this.textTeamName.setText("");
	}
}