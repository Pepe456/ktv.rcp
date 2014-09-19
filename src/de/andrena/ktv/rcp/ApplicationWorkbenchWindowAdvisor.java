package de.andrena.ktv.rcp;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

	public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
		super(configurer);
	}

	@Override
	public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
		return new ApplicationActionBarAdvisor(configurer);
	}

	@Override
	public void preWindowOpen() {
		IWorkbenchWindowConfigurer configurer = this.getWindowConfigurer();
		configurer.setInitialSize(new Point(600, 500));
		configurer.setShowCoolBar(false);
		configurer.setShowPerspectiveBar(false);
		configurer.setShowStatusLine(false);
		configurer.setTitle("Kickerturnierverwaltung");
	}

	@Override
	public void postWindowCreate() {
		super.postWindowCreate();
		IWorkbenchWindowConfigurer configurer = this.getWindowConfigurer();
		Shell shell = configurer.getWindow().getShell();
		shell.setBounds(400, 100, 600, 550);
	}
}
