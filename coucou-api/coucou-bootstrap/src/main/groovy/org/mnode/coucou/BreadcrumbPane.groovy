package org.mnode.coucou

import org.jdesktop.swingx.JXHyperlink
import org.jdesktop.swingx.JXPanel
import org.mnode.ousia.OusiaBuilder

import javax.swing.*
import java.awt.*

class BreadcrumbPane extends JXPanel {

    OusiaBuilder swing = []
    
    BreadcrumbPane() {
        layout = swing.flowLayout(alignment: FlowLayout.LEADING)
        font = new Font('tahoma', Font.PLAIN, 16f)
        add swing.button(new JXHyperlink(swing.action(name: 'Mail')), font: getFont())
        add swing.label(text: '<html>&raquo;')
        add swing.button(new JXHyperlink(swing.action(name: 'Inbox')), font: getFont())
    }
    
    void addLink(Action action) {
        
    }
}
