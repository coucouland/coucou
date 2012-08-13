package org.mnode.coucou

import java.awt.BorderLayout

import org.jdesktop.swingx.JXPanel
import org.mnode.ousia.OusiaBuilder

class PreferencesView extends JXPanel {

    OusiaBuilder swing = []
    
    PreferencesView() {
        layout = swing.borderLayout()
        add swing.panel {
            borderLayout()
            label(text: "${rs('Preferences')} 1.0", constraints: BorderLayout.NORTH, border: emptyBorder(10))
            panel {
                borderLayout()
                panel(constraints: BorderLayout.WEST) {
                    scrollPane {
                        tree(id: 'prefsTree')
                    }
                }
                panel(border: emptyBorder(10)) {
                    borderLayout()
                    scrollPane {
                        panel(id: 'prefsPage')
                    }
                }
            }
        }
    }
}
