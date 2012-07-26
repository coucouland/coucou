package org.mnode.coucou

import javax.swing.JFrame

import org.mnode.ousia.OusiaBuilder
import org.mnode.ousia.SlidingCardLayout

class MainWindow extends JFrame {

	OusiaBuilder swing = []

//	/def actionContext = [:] as ObservableMap
	
	MainWindow() {
		add swing.panel(id: 'contentPane1') {
			cardLayout(new SlidingCardLayout(), id: 'slider')
			
//			actionContext.showPane2 = {
//				slider.show(contentPane, 'pane2')
//			}
			
//			panel(new ConfigurationPane(actionContext), constraints: 'pane1')
			panel(constraints: 'pane1') {
				button(text: 'Click 1', actionPerformed: {
					slider.show(contentPane1, 'pane2')
				})
			}
			panel(constraints: 'pane2') {
				button(text: 'Click 2', actionPerformed: {
					slider.show(contentPane1, 'pane1')
				})
			}
		}
	}

	static void main(def args) {
		new OusiaBuilder().edt {
			frame(new MainWindow(), id: 'frame', title: 'Coucou', size: [600, 400], locationRelativeTo: null, visible: true, defaultCloseOperation: JFrame.EXIT_ON_CLOSE)
		}
	}
}
