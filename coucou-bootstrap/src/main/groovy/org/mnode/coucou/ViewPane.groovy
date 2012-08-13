package org.mnode.coucou

import org.jdesktop.swingx.JXPanel
import org.mnode.ousia.OusiaBuilder
import org.mnode.ousia.SlidingCardLayout

class ViewPane extends JXPanel {

	OusiaBuilder swing = []
	
	ViewPane() {
		layout = swing.cardLayout(new SlidingCardLayout(), id: 'slider')
		
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
        add new AboutView(), 'about'
        add new PreferencesView(), 'preferences'
	}
	
	void show(String viewId) {
		swing.edt {
			slider.show(this, viewId)
		}
	}
}
