package de.andrena.ktv.rcp.views;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.SWTResourceManager;

import com.sun.jersey.api.client.ClientHandlerException;

import de.andrena.ktv.rcp.dao.TeamDao;
import de.andrena.ktv.rcp.domain.InputValidation;
import de.andrena.ktv.rcp.domain.Team;

import org.eclipse.swt.widgets.Combo;

public class DefaultView extends ViewPart {
	public DefaultView() {
	}

	public static final String ID = "de.andrena.ktv.rcp.views.tableview";

	private Group groupListOfTeams;
	private Group groupAddNewTeam;
	private TableViewer viewerTeams;
	private Table table;
	private Button buttonRefreshTable;
	private Button buttonAddNewTeam;
	private Text textPlayer2NameAdd;
	private Text textPlayer1NameAdd;
	private Text textTeamNameAdd;
	private Label labelTeamNameToAdd;
	private Label labelPlayer1NameToAdd;
	private Label labelPlayer2NameToAdd;
	private Label messagePlaceholderLabel;
	private Text textNameTeamToEdit;
	private Text textNamePlayer1ToEdit;
	private Text textNamePlayer2ToEdit;
	private Button buttonDeleteSelectedTeam;
	private Button buttonUpdateSelectedTeam;
	private TabFolder tabFolder;
	private TabItem tabTeams;
	private Composite composite;
	private TabItem tabSpielplan;
	private Composite compositeSpielplan;
	private Group groupKriterien;
	private Label labelAnzahlKickertische;
	private Label labelSpielmodus;
	private Combo comboBoxSpielmodi;
	private Group grpAktuellerSpielplan;
	private Table tableSpielplan;
	private TableColumn columnTischNr;
	private TableColumn columnSpielNr;
	private TableColumn columnTeam1;
	private TableColumn columnTeam2;
	private Combo comboBoxTischanzahl;
	private Button btnErstelleSpielplan;
	private Label labelNameTeamToEdit;
	private Label labelNamePlayer1ToEdit;
	private Label labelNamePlayer2ToEdit;
	private TableColumn columnTeamName;
	private TableColumn columnPlayer1;
	private TableColumn columnPlayer2;
	private Group grpTeamBearbeiten;

	@Override
	public void createPartControl(Composite parent) {
		this.setPartName(Messages.TeamsTableView_this_partName);
		parent.setLayout(null);

		this.tabFolder = new TabFolder(parent, SWT.NONE);
		this.tabFolder.setBounds(0, 0, 594, 512);

		this.tabTeams = new TabItem(this.tabFolder, SWT.NONE);
		this.tabTeams.setText(Messages.TeamsTableView_tbtmVerwaltungTeams_text);

		this.composite = new Composite(this.tabFolder, SWT.NONE);
		this.composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		this.tabTeams.setControl(this.composite);
		this.composite.setLayout(null);
		this.groupListOfTeams = new Group(this.composite, SWT.NONE);
		this.groupListOfTeams.setLocation(0, 0);
		this.groupListOfTeams.setSize(586, 303);
		this.groupListOfTeams.setText(Messages.TeamsTableView_groupListOfTeams_text);

		this.messagePlaceholderLabel = new Label(this.groupListOfTeams, SWT.NONE);
		this.messagePlaceholderLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		this.messagePlaceholderLabel.setBounds(10, 273, 218, 15);

		this.table = new Table(this.groupListOfTeams, SWT.FULL_SELECTION | SWT.V_SCROLL);
		this.table.addSelectionListener(this.createListenerTableItemSelected());
		this.table.setBounds(10, 21, 566, 228);
		this.table.setHeaderVisible(true);
		this.table.setLinesVisible(true);

		this.columnTeamName = new TableColumn(this.table, SWT.LEFT);
		this.columnTeamName.setImage(null);
		this.columnTeamName.setResizable(false);
		this.columnTeamName.setText(Messages.TeamsTableView_columnTeamName_text);
		this.columnTeamName.setWidth(180);

		this.columnPlayer1 = new TableColumn(this.table, SWT.LEFT);
		this.columnPlayer1.setImage(null);
		this.columnPlayer1.setResizable(false);
		this.columnPlayer1.setText(Messages.TeamsTableView_columnPlayer1_text);
		this.columnPlayer1.setWidth(180);

		this.columnPlayer2 = new TableColumn(this.table, SWT.LEFT);
		this.columnPlayer2.setImage(null);
		this.columnPlayer2.setResizable(false);
		this.columnPlayer2.setText(Messages.TeamsTableView_columnPlayer2_text);
		this.columnPlayer2.setWidth(180);

		this.buttonRefreshTable = new Button(this.groupListOfTeams, SWT.NONE);
		this.buttonRefreshTable.setBounds(400, 268, 160, 25);
		this.buttonRefreshTable.setText(Messages.TeamsTableView_refreshButton_text);

		this.buttonDeleteSelectedTeam = new Button(this.groupListOfTeams, SWT.NONE);
		this.buttonDeleteSelectedTeam.setBounds(234, 268, 160, 25);
		this.buttonDeleteSelectedTeam.setText(Messages.TeamsTableView_btnDeleteSelectedTeam_text);

		this.groupAddNewTeam = new Group(this.composite, SWT.NONE);
		this.groupAddNewTeam.setLocation(0, 314);
		this.groupAddNewTeam.setSize(282, 160);
		this.groupAddNewTeam.setText(Messages.TeamsTableView_groupAddNewTeam_text);

		this.labelTeamNameToAdd = new Label(this.groupAddNewTeam, SWT.NONE);
		this.labelTeamNameToAdd.setBounds(15, 30, 109, 15);
		this.labelTeamNameToAdd.setText(Messages.TeamsTableView_labelTeamNameToAdd_text);

		this.labelPlayer1NameToAdd = new Label(this.groupAddNewTeam, SWT.NONE);
		this.labelPlayer1NameToAdd.setBounds(15, 60, 109, 15);
		this.labelPlayer1NameToAdd.setText(Messages.TeamsTableView_labelPlayer1NameToAdd_text);

		this.labelPlayer2NameToAdd = new Label(this.groupAddNewTeam, SWT.NONE);
		this.labelPlayer2NameToAdd.setBounds(15, 90, 109, 15);
		this.labelPlayer2NameToAdd.setText(Messages.TeamsTableView_labelPlayer2NameToAdd_text);

		this.textTeamNameAdd = new Text(this.groupAddNewTeam, SWT.BORDER);
		this.textTeamNameAdd.setBounds(130, 30, 140, 21);

		this.textPlayer1NameAdd = new Text(this.groupAddNewTeam, SWT.BORDER);
		this.textPlayer1NameAdd.setBounds(130, 60, 140, 21);

		this.textPlayer2NameAdd = new Text(this.groupAddNewTeam, SWT.BORDER);
		this.textPlayer2NameAdd.setBounds(130, 90, 140, 21);

		this.buttonAddNewTeam = new Button(this.groupAddNewTeam, SWT.NONE);
		this.buttonAddNewTeam.setBounds(110, 125, 160, 25);
		this.buttonAddNewTeam.setText(Messages.TeamsTableView_addNewTeamButton_text);

		this.grpTeamBearbeiten = new Group(this.composite, SWT.NONE);
		this.grpTeamBearbeiten.setLocation(294, 314);
		this.grpTeamBearbeiten.setSize(282, 160);
		this.grpTeamBearbeiten.setText(Messages.TeamsTableView_grpTeamBearbeiten_text);

		this.labelNameTeamToEdit = this.createLabel(this.grpTeamBearbeiten, "Name des Teams:", 10, 30, 109, 15);

		this.labelNamePlayer1ToEdit = new Label(this.grpTeamBearbeiten, SWT.NONE);
		this.labelNamePlayer1ToEdit.setText("Name von Spieler 1:");
		this.labelNamePlayer1ToEdit.setBounds(10, 60, 109, 15);

		this.labelNamePlayer2ToEdit = new Label(this.grpTeamBearbeiten, SWT.NONE);
		this.labelNamePlayer2ToEdit.setText("Name von Spieler 2:");
		this.labelNamePlayer2ToEdit.setBounds(10, 90, 109, 15);

		this.textNameTeamToEdit = new Text(this.grpTeamBearbeiten, SWT.BORDER);
		this.textNameTeamToEdit.setBounds(125, 30, 140, 21);

		this.textNamePlayer1ToEdit = new Text(this.grpTeamBearbeiten, SWT.BORDER);
		this.textNamePlayer1ToEdit.setBounds(125, 60, 140, 21);

		this.textNamePlayer2ToEdit = new Text(this.grpTeamBearbeiten, SWT.BORDER);
		this.textNamePlayer2ToEdit.setBounds(125, 90, 140, 21);

		this.buttonUpdateSelectedTeam = new Button(this.grpTeamBearbeiten, SWT.NONE);
		this.buttonUpdateSelectedTeam.setBounds(105, 125, 160, 25);
		this.buttonUpdateSelectedTeam.setText(Messages.TeamsTableView_btnNewButton_text);

		this.viewerTeams = new TableViewer(this.table);
		this.viewerTeams.setContentProvider(new TeamsTableContentProvider(this));
		this.viewerTeams.setLabelProvider(new TeamsTableLabelProvider(this));

		this.buttonUpdateSelectedTeam.addSelectionListener(this.addListenerUpdateButtonPressed());
		this.buttonAddNewTeam.addSelectionListener(this.getListenerNewTeamButtonPressed());
		this.buttonRefreshTable.addSelectionListener(this.getListenerRefreshButtonPressed());
		this.buttonDeleteSelectedTeam.addSelectionListener(this.getListenerDeleteButtonPressed());

		Team[] currentTeams = this.tryGettingAllCurrentTeams();
		if (currentTeams != null) {
			this.viewerTeams.setInput(currentTeams);
		}

		// Tab Spielplan
		this.tabSpielplan = new TabItem(this.tabFolder, SWT.NONE);
		this.tabSpielplan.setText(Messages.TeamsTableView_tbtmSpielplan_text_1);

		this.compositeSpielplan = new Composite(this.tabFolder, SWT.NONE);
		this.compositeSpielplan.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		this.tabSpielplan.setControl(this.compositeSpielplan);

		this.groupKriterien = new Group(this.compositeSpielplan, SWT.NONE);
		this.groupKriterien.setText(Messages.TeamsTableView_grpSdafgse_text);
		this.groupKriterien.setBounds(10, 10, 566, 123);

		this.labelAnzahlKickertische = new Label(this.groupKriterien, SWT.NONE);
		this.labelAnzahlKickertische.setBounds(10, 21, 114, 15);
		this.labelAnzahlKickertische.setText(Messages.TeamsTableView_lblAnzahlKickertische_text);

		this.comboBoxTischanzahl = new Combo(this.groupKriterien, SWT.NONE);
		this.comboBoxTischanzahl.setBounds(131, 13, 167, 23);
		this.comboBoxTischanzahl.setItems(new String[] { "1", "2", "3", "4", "5" });

		this.labelSpielmodus = new Label(this.groupKriterien, SWT.NONE);
		this.labelSpielmodus.setBounds(10, 42, 114, 15);
		this.labelSpielmodus.setText(Messages.TeamsTableView_lblSpielmodus_text);

		this.comboBoxSpielmodi = new Combo(this.groupKriterien, SWT.NONE);
		this.comboBoxSpielmodi.setBounds(131, 42, 167, 23);
		this.comboBoxSpielmodi.setItems(new String[] { "Jeder gegen Jeden", "2 Gruppen und KO-Phase" });

		this.btnErstelleSpielplan = new Button(this.groupKriterien, SWT.NONE);
		this.btnErstelleSpielplan.setBounds(417, 88, 139, 25);
		this.btnErstelleSpielplan.setText(Messages.TeamsTableView_btnErstelleSpielplan_text_1);

		this.grpAktuellerSpielplan = new Group(this.compositeSpielplan, SWT.NONE);
		this.grpAktuellerSpielplan.setText(Messages.TeamsTableView_grpAktuellerSpielplan_text);
		this.grpAktuellerSpielplan.setBounds(10, 139, 566, 335);

		this.tableSpielplan = new Table(this.grpAktuellerSpielplan, SWT.BORDER | SWT.FULL_SELECTION);
		this.tableSpielplan.setBounds(10, 22, 546, 303);
		this.tableSpielplan.setHeaderVisible(true);
		this.tableSpielplan.setLinesVisible(true);

		this.columnTischNr = this.createTableColumn(this.tableSpielplan, Messages.TeamsTableView_tblclmnTischNr_text);
		this.columnSpielNr = this.createTableColumn(this.tableSpielplan, Messages.TeamsTableView_tblclmnSpielNr_text);
		this.columnTeam1 = this.createTableColumn(this.tableSpielplan, Messages.TeamsTableView_tblclmnTeam_1_text);
		this.columnTeam2 = this.createTableColumn(this.tableSpielplan, Messages.TeamsTableView_tblclmnTeam_2_text);

		TableViewer spielplanViewer = new TableViewer(this.tableSpielplan);
		spielplanViewer.setContentProvider(new SpielplanContentProvider(this));
		spielplanViewer.setLabelProvider(new SpielplanLabelProvider(this));

		// TODO set inital content

	}

	private SelectionAdapter createListenerTableItemSelected() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DefaultView.this.clearMessagePlaceholder();
				TableItem[] selection = DefaultView.this.table.getSelection();
				DefaultView.this.textNameTeamToEdit.setText(selection[0].getText(0));
				DefaultView.this.textNamePlayer1ToEdit.setText(selection[0].getText(1));
				DefaultView.this.textNamePlayer2ToEdit.setText(selection[0].getText(2));
			}
		};
	}

	private Label createLabel(Composite parent, String text, int x, int y, int width, int height) {
		Label labelNameTeamToEdit = new Label(parent, SWT.NONE);
		labelNameTeamToEdit.setText(text);
		labelNameTeamToEdit.setBounds(x, y, width, height);

		return labelNameTeamToEdit;
	}

	private SelectionAdapter addListenerUpdateButtonPressed() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DefaultView.this.clearMessagePlaceholder();
				String teamName = DefaultView.this.textNameTeamToEdit.getText();
				String player1Name = DefaultView.this.textNamePlayer1ToEdit.getText();
				String player2Name = DefaultView.this.textNamePlayer2ToEdit.getText();

				TableItem[] selection = DefaultView.this.table.getSelection();

				if (selection.length == 0) {
					DefaultView.this.messagePlaceholderLabel.setText("Es ist kein Team ausgewählt!");
					return;
				}

				if (!InputValidation.validate(teamName)) {
					DefaultView.this.messagePlaceholderLabel.setText("Name für Spieler 1 ist nicht korrekt.");
					return;
				}

				if (!InputValidation.validate(player1Name)) {
					DefaultView.this.messagePlaceholderLabel.setText("Name für Spieler 1 ist nicht korrekt.");
					return;
				}
				if (!InputValidation.validate(player2Name)) {
					DefaultView.this.messagePlaceholderLabel.setText("Name für Spieler 2 ist nicht korrekt.");
					return;
				}

				Team team = (Team) selection[0].getData();
				team.setName(teamName);
				team.setPlayer1(player1Name);
				team.setPlayer2(player2Name);

				boolean successful = TeamDao.updateTeam(team);

				if (successful) {
					DefaultView.this.refreshTable();
					DefaultView.this.resetAddTeamTextFields();
				}
			}
		};
	}

	private SelectionAdapter getListenerNewTeamButtonPressed() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DefaultView.this.clearMessagePlaceholder();
				String teamName = DefaultView.this.textTeamNameAdd.getText();
				String player1Name = DefaultView.this.textPlayer1NameAdd.getText();
				String player2Name = DefaultView.this.textPlayer2NameAdd.getText();

				if (!InputValidation.validate(teamName)) {
					DefaultView.this.messagePlaceholderLabel.setText("Teamname ist nicht korrekt.");
					return;
				}

				if (teamNameAlreadyExists(teamName)) {
					DefaultView.this.messagePlaceholderLabel.setText("Teamname existiert bereits!");
					return;
				}

				if (!InputValidation.validate(player1Name)) {
					DefaultView.this.messagePlaceholderLabel.setText("Name für Spieler 1 ist nicht korrekt.");
					return;
				}
				if (!InputValidation.validate(player2Name)) {
					DefaultView.this.messagePlaceholderLabel.setText("Name für Spieler 2 ist nicht korrekt.");
					return;
				}
				boolean successful = TeamDao.addNewTeam(new Team(teamName, player1Name, player2Name));
				if (successful) {
					DefaultView.this.refreshTable();
					DefaultView.this.resetAddTeamTextFields();
				}
			}

		};
	}

	private SelectionAdapter getListenerRefreshButtonPressed() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DefaultView.this.clearMessagePlaceholder();
				DefaultView.this.viewerTeams.setInput(DefaultView.this.tryGettingAllCurrentTeams());
			}
		};
	}

	private SelectionAdapter getListenerDeleteButtonPressed() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DefaultView.this.clearMessagePlaceholder();
				TableItem[] selection = DefaultView.this.table.getSelection();
				if (selection.length == 0) {
					DefaultView.this.messagePlaceholderLabel.setText("Kein Team ausgewählt!");
					return;
				}

				Team team = (Team) selection[0].getData();
				boolean successful = TeamDao.deleteTeam(team.getKey());
				if (successful) {
					DefaultView.this.refreshTable();
					DefaultView.this.resetEditTeamTextFields();
				}
			}
		};
	}

	private TableColumn createTableColumn(Table table, String text) {
		TableColumn column = new TableColumn(table, SWT.NONE);
		column.setWidth(100);
		column.setText(text);
		return column;
	}

	public static boolean teamNameAlreadyExists(String teamName) {
		for (Team team : TeamDao.getCurrentTeams()) {
			if (team.getName().equals(teamName)) {
				return true;
			}
		}
		return false;
	}

	protected void clearMessagePlaceholder() {
		this.messagePlaceholderLabel.setText("");
	}

	@Override
	public void setFocus() {
		this.viewerTeams.getControl().setFocus();
	}

	private void refreshTable() {
		this.viewerTeams.setInput(this.tryGettingAllCurrentTeams());
	}

	Team[] tryGettingAllCurrentTeams() {
		Team[] result = null;

		try {
			result = TeamDao.getCurrentTeams();
		} catch (ClientHandlerException e) {
			e.printStackTrace();
			this.messagePlaceholderLabel.setText("Keine Verbindung zum Server!");
			return null;
		}
		return result;
	}

	private void resetAddTeamTextFields() {
		this.textTeamNameAdd.setText("");
		this.textPlayer2NameAdd.setText("");
		this.textPlayer1NameAdd.setText("");
	}

	private void resetEditTeamTextFields() {
		this.textNameTeamToEdit.setText("");
		this.textNamePlayer1ToEdit.setText("");
		this.textNamePlayer2ToEdit.setText("");
	}
}