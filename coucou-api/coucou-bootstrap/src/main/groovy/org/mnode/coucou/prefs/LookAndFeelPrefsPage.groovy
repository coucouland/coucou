package org.mnode.coucou.prefs

import org.jdesktop.swingx.JXPanel
import org.mnode.coucou.LookAndFeelInfoRenderer
import org.mnode.coucou.Main
import org.mnode.ousia.OusiaBuilder
import org.pushingpixels.substance.api.SubstanceLookAndFeel
import org.pushingpixels.substance.api.fonts.SubstanceFontUtilities

import javax.swing.*
import javax.swing.UIManager.LookAndFeelInfo
import java.awt.*

class LookAndFeelPrefsPage extends JXPanel {
    
    def currentLookAndFeelInfo = {
        for (laf in UIManager.installedLookAndFeels) {
            if (UIManager.lookAndFeel.name == laf.name) {
                return laf
            }
        }
        return new LookAndFeelInfo(UIManager.lookAndFeel.name, UIManager.lookAndFeel.ID)
    }

    LookAndFeelPrefsPage(def swing = new OusiaBuilder()) {
        layout = swing.borderLayout()
        
        add swing.panel {
            borderLayout()
            panel(constraints: BorderLayout.NORTH, border: emptyBorder(10)) {
                label(text: rs('Look and Feel'))
                comboBox(items: UIManager.installedLookAndFeels, editable: false, renderer: new LookAndFeelInfoRenderer(), selectedItem: currentLookAndFeelInfo(),
                    itemStateChanged: { e->
                        doLater {
                            lookAndFeel(e.source.selectedItem.className, 'system')
                            def frame = SwingUtilities.getWindowAncestor(e.source)
                            SwingUtilities.updateComponentTreeUI(frame)
    //                                            SwingUtilities.updateComponentTreeUI(preferencesDialog)
                            e.source.selectedItem = currentLookAndFeelInfo()
                            prefs(Main).put('lookAndFeel', e.source.selectedItem.className)
                            if (UIManager.lookAndFeel instanceof SubstanceLookAndFeel) {
                                scalingSlider.enabled = true
                                scalingSlider.value = prefs(Main).getInt('scalingFactor', 10)
                            }
                            else {
                                scalingSlider.value = 10
                                scalingSlider.enabled = false
                            }
                        }
                    })
            }
            panel(border: emptyBorder(10)) {
                label(text: rs('Scaling Factor'))
                slider(id: 'scalingSlider', minimum: 8, maximum: 20, value: prefs(Main).getInt('scalingFactor', 10),
                     paintTicks: true, snapToTicks: true, majorTickSpacing: 1, enabled: UIManager.lookAndFeel instanceof SubstanceLookAndFeel,
                     stateChanged: { e->
                         if (!e.source.valueIsAdjusting) {
                             doLater {
                                 if (UIManager.lookAndFeel instanceof SubstanceLookAndFeel) {
                                     SubstanceLookAndFeel.fontPolicy = SubstanceFontUtilities.getScaledFontPolicy(e.source.value / 10)
                                 }
                                 prefs(Main).putInt('scalingFactor', e.source.value)
                             }
                         }
                     })
            }
        }
    }
}
