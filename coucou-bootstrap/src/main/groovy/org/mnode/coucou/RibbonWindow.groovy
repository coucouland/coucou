package org.mnode.coucou

import java.awt.Color
import java.awt.Dimension;
import java.awt.Font
import java.awt.event.KeyEvent

import org.mnode.ousia.OusiaBuilder
import org.mnode.ousia.SlidingCardLayout
import org.pushingpixels.flamingo.api.common.JCommandButton.CommandButtonKind;
import org.pushingpixels.flamingo.api.common.icon.ImageWrapperResizableIcon;
import org.pushingpixels.flamingo.api.ribbon.JRibbonFrame

class RibbonWindow extends JRibbonFrame {

	OusiaBuilder swing = []
	
	RibbonWindow() {
		swing.build {
			actions {
				action id: 'newAction', name: rs('New Item'), accelerator: shortcut('N'), closure: {
//					System.exit(0)
				}
				action id: 'exitAction', name: rs('Exit'), accelerator: shortcut('Q'), closure: {
					System.exit(0)
				}
				action id: 'aboutAction', name: rs('About'), accelerator: 'F1', closure: {
//					System.exit(0)
					slider.show(contentPane1, 'pane1')
				}
				action id: 'preferencesAction', name: rs('Preferences'), closure: {
				}
			}
		}
		
		ribbon.applicationMenu = swing.build {
			def newIcon = ImageWrapperResizableIcon.getIcon(Main.getResource('/add.png'), [16, 16] as Dimension)
			def exitIcon = ImageWrapperResizableIcon.getIcon(Main.getResource('/exit.png'), [16, 16] as Dimension)
			
			ribbonApplicationMenu(id: 'appMenu') {
				ribbonApplicationMenuEntryPrimary(id: 'newMenu', icon: newIcon, text: rs('New'), kind: CommandButtonKind.POPUP_ONLY)
//				newMenu.addSecondaryMenuGroup 'Create a new item', newAction
				appMenu.addMenuSeparator()
				
				ribbonApplicationMenuEntryPrimary(id: 'saveAsMenu', text: rs('Save As'), kind: CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION)
				appMenu.addMenuSeparator()
				
				ribbonApplicationMenuEntryPrimary(id: 'importMenu', text: rs('Import'), kind: CommandButtonKind.POPUP_ONLY)
				ribbonApplicationMenuEntryPrimary(id: 'exportMenu', text: rs('Export'), kind: CommandButtonKind.POPUP_ONLY)
				appMenu.addMenuSeparator()
				
				ribbonApplicationMenuEntryPrimary(icon: exitIcon, text: rs('Exit'), kind: CommandButtonKind.ACTION_ONLY, actionPerformed: exitAction)
				
				ribbonApplicationMenuEntryFooter(text: rs('Preferences'), actionPerformed: preferencesAction)
			}
		}
		
		def helpIcon = ImageWrapperResizableIcon.getIcon(Main.getResource('/add.png'), [16, 16] as Dimension)
		ribbon.configureHelp helpIcon, swing.aboutAction
		
		ribbon.addTask swing.build {
			ribbonTask('Home', bands: [
				ribbonBand('Quick Search', id: 'quickSearchBand', resizePolicies: ['mirror']) {
					ribbonComponent([
						component: textField(id: 'quickSearchField', columns: 14, enabled: false, prompt: 'Search..', promptFontStyle: Font.ITALIC, promptForeground: Color.LIGHT_GRAY,
							keyPressed: {e-> if (e.keyCode == KeyEvent.VK_ESCAPE) e.source.text = null}) {
							
	//                        quickSearchField.addActionListener quickSearchAction
	//                        quickSearchField.addBuddy commandButton(searchIcon, enabled: false, actionPerformed: quickSearchAction, id: 'quickSearchButton'), BuddySupport.Position.RIGHT
						},
						rowSpan: 1
					])
		
					ribbonComponent([
						component: checkBox(text: 'Include Archived'),
						rowSpan: 1
					])
					ribbonComponent([
						component: checkBox(text: 'Include Deleted'),
						rowSpan: 1
					])
				}
			])
		}
		
		ribbon.addTask swing.build {
			ribbonTask('View', bands: [
				ribbonBand('Group By', id: 'groupByBand', resizePolicies: ['mirror']),
				ribbonBand('Sort By', id: 'sortBand', resizePolicies: ['mirror']),
				ribbonBand('Filter', id: 'filterBand', resizePolicies: ['mirror']),
				ribbonBand('Show/Hide', id: 'showHideBand', resizePolicies: ['mirror']),
			])
		}
		
		ribbon.addTask swing.build {
			ribbonTask('Tools', bands: [
				ribbonBand('Advanced', id: 'advancedToolsBand', resizePolicies: ['mirror']),
			])
		}
		
		ribbon.addTask swing.build {
			ribbonTask('Action', bands: [
				ribbonBand('Action1', id: 'action1Band', resizePolicies: ['mirror']),
			])
		}
		
		add swing.panel(new ViewPane(), id: 'contentPane1')
	}
}
