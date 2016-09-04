package org.mnode.coucou.view

import org.jdesktop.swingx.JXPanel
import org.mnode.ousia.OusiaBuilder

import javax.swing.*
import java.awt.*

class AboutView extends JXPanel {

    OusiaBuilder swing = []
    
    AboutView() {
        layout = swing.borderLayout()
        add swing.panel {
            borderLayout()
//            label(text: "${rs('Coucou')} 1.0", constraints: BorderLayout.NORTH, border: emptyBorder(10))
            panel(constraints: BorderLayout.CENTER, border: emptyBorder(10)) {
                borderLayout()
                scrollPane(horizontalScrollBarPolicy: JScrollPane.HORIZONTAL_SCROLLBAR_NEVER, border: null) {
                    table(gridColor: Color.LIGHT_GRAY) {
                        def systemProps = []
                        for (propName in System.properties.keySet()) {
                            systemProps.add([property: propName, value: System.properties.getProperty(propName)])
                        }
                        tableModel(list: systemProps) {
                            propertyColumn(header: rs('Property'), propertyName: 'property', editable: false)
                            propertyColumn(header: rs('Value'), propertyName: 'value', editable: false)
                        }
                    }
                }
            }
        }
    }
}
