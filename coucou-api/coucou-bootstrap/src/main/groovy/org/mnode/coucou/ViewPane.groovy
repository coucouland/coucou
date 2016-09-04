package org.mnode.coucou

import org.jdesktop.swingx.JXPanel
import org.mnode.coucou.view.AboutView
import org.mnode.coucou.view.PreferencesView
import org.mnode.coucou.view.WelcomeView
import org.mnode.ousia.OusiaBuilder
import org.mnode.ousia.SlidingCardLayout

class ViewPane extends JXPanel {
	
	ViewPane(def swing = new OusiaBuilder()) {
		layout = swing.cardLayout(new SlidingCardLayout(), id: 'slider')

        add new WelcomeView(), 'welcome'
        /*
		add swing.panel {
			button(text: 'Click 1', actionPerformed: {
				show('pane2')
			})
		}, 'pane1'
		add swing.panel {
			button(text: 'Click 2', actionPerformed: {
				show('pane1')
			})
		}, 'pane2'
		*/
        add new AboutView(), 'about'
        add new PreferencesView(swing), 'preferences'
	}
	
	void show(String viewId) {
		layout.show(this, viewId)
	}
}
