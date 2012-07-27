package org.mnode.coucou.mail

import javax.swing.JFrame
import javax.swing.UIManager
import javax.swing.UIManager.LookAndFeelInfo

import org.mnode.ousia.DialogExceptionHandler
import org.mnode.ousia.OusiaBuilder
import org.mnode.ousia.SlidingCardLayout
import org.pushingpixels.substance.api.SubstanceConstants
import org.pushingpixels.substance.api.SubstanceLookAndFeel
import org.pushingpixels.substance.api.fonts.SubstanceFontUtilities

try {
	new Socket('localhost', 1337)
	println 'Already running'
	System.exit(0)
}
catch (Exception e) {
}

// Install look and feels..
UIManager.put(SubstanceLookAndFeel.TABBED_PANE_CONTENT_BORDER_KIND, SubstanceConstants.TabContentPaneBorderKind.SINGLE_FULL)
UIManager.installLookAndFeel(new LookAndFeelInfo('Nebula', 'substance-nebula'))
UIManager.installLookAndFeel(new LookAndFeelInfo('Office Blue 2007', 'substance-office-blue-2007'))
UIManager.installLookAndFeel(new LookAndFeelInfo('Office Silver 2007', 'substance-office-silver-2007'))
UIManager.installLookAndFeel(new LookAndFeelInfo('Mariner', 'substance-mariner'))
UIManager.installLookAndFeel(new LookAndFeelInfo('Business Black Steel', 'substance-business-black-steel'))
UIManager.installLookAndFeel(new LookAndFeelInfo('Business Blue Steel', 'substance-business-blue-steel'))
UIManager.installLookAndFeel(new LookAndFeelInfo('Raven', 'substance-raven'))

def currentLookAndFeelInfo = {
	for (laf in UIManager.installedLookAndFeels) {
		if (UIManager.lookAndFeel.name == laf.name) {
			return laf
		}
	}
	return new LookAndFeelInfo(UIManager.lookAndFeel.name, UIManager.lookAndFeel.ID)
}
///


OusiaBuilder ousia = []
Thread.start {
	ServerSocket server = [1337]
	while(true) {
		try {
			server.accept {}
		}
		finally {
			ousia.doLater {
				frame.visible = true
			}
		}
	}
}

def actionContext = [:] as ObservableMap

ousia.edt {
	lookAndFeel(prefs(CoucouMail).get('lookAndFeel', 'system')) //.fontPolicy = SubstanceFontUtilities.getScaledFontPolicy(1.2)
	
	frame(id: 'frame', title: 'Coucou Mail', size: [640, 400], locationRelativeTo: null, visible: true, defaultCloseOperation: JFrame.EXIT_ON_CLOSE) {
		panel(id: 'contentPane') {
			cardLayout(new SlidingCardLayout(), id: 'slider')
			
			actionContext.showPane2 = {
				slider.show(contentPane, 'pane2')
			}
			
			panel(new ConfigurationPane(actionContext: actionContext), constraints: 'pane1')
			panel(constraints: 'pane2') {
				button(text: 'Click 2', actionPerformed: {
					slider.show(contentPane, 'pane1')
				})
			}
		}
	}
	
	Thread.defaultUncaughtExceptionHandler = new DialogExceptionHandler(dialogOwner: frame)
	Authenticator.default = new org.mnode.ousia.DialogAuthenticator(frame)
}
