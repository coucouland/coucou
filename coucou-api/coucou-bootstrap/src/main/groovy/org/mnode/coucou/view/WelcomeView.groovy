package org.mnode.coucou.view

import org.jdesktop.swingx.JXHyperlink
import org.jdesktop.swingx.JXPanel
import org.mnode.ousia.OusiaBuilder

class WelcomeView extends JXPanel {

    WelcomeView(swing = new OusiaBuilder()) {
		layout = swing.borderLayout()
        add swing.vbox {
            label(text: 'Mail', font: font.deriveFont(16f))
			button(new JXHyperlink(action(name: rs('Add account..'))))
        }
    }
}
