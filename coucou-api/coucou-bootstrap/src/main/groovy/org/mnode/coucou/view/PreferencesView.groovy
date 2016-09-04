package org.mnode.coucou.view

import org.jdesktop.swingx.JXPanel
import org.mnode.coucou.prefs.LookAndFeelPrefsPage
import org.mnode.ousia.OusiaBuilder

import java.awt.*

class PreferencesView extends JXPanel {
    
    PreferencesView(def swing = new OusiaBuilder()) {
        layout = swing.borderLayout()
        add swing.panel {
            borderLayout()
//            label(text: "${rs('Preferences')} 1.0", constraints: BorderLayout.NORTH, border: emptyBorder(10))
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
                        panel(id: 'prefsPage') {
                            cardLayout()
                            panel(new LookAndFeelPrefsPage(), constraints: 'lookAndFeel')
                        }
                    }
                }
            }
        }
    }
}
