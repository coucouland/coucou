package org.mnode.coucou.mail

import org.jdesktop.swingx.JXPanel
import org.mnode.ousia.OusiaBuilder

class ConfigurationPane extends JXPanel {

	ConfigurationPane(def actionContext) {
		OusiaBuilder ousia = []
		layout = ousia.borderLayout()
		add ousia.panel {
			migLayout(layoutConstraints: 'fill')
		
			label(text: 'Mail Configuration', constraints: 'wrap')
			label(text: 'Email address: ')
			textField(columns: 15, constraints: 'wrap')
			label(text: 'Password: ')
			passwordField(columns: 15, constraints: 'wrap')

			button(text: 'Next', actionPerformed: {
				actionContext.showPane2()
			})
		}
	}
}
