package org.mnode.coucou

import java.awt.BorderLayout
import java.awt.Color
import java.awt.Dimension
import java.awt.Font
import java.awt.event.ActionListener
import java.awt.event.KeyEvent

import org.mnode.ousia.OusiaBuilder
import org.pushingpixels.flamingo.api.common.JCommandButton.CommandButtonKind
import org.pushingpixels.flamingo.api.common.icon.ImageWrapperResizableIcon
import org.pushingpixels.flamingo.api.ribbon.JRibbonFrame
import org.pushingpixels.flamingo.api.ribbon.RibbonElementPriority

class RibbonWindow extends JRibbonFrame {

    def actionContext = [] as ObservableMap
    
	RibbonWindow(def swing = new OusiaBuilder()) {
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
//					slider.show(contentPane1, 'pane1')
                    contentPane1.show('about')
				}
				action id: 'preferencesAction', name: rs('Preferences'), closure: {
                    contentPane1.show('preferences')
				}
                action id: 'refreshAction', name: rs('Refresh'), closure: {
                }
			}
		}
		
		ribbon.applicationMenu = swing.build {
			def newIcon = ImageWrapperResizableIcon.getIcon(Main.getResource('/add.png'), [16, 16] as Dimension)
			def exitIcon = ImageWrapperResizableIcon.getIcon(Main.getResource('/exit.png'), [16, 16] as Dimension)
			def blankIcon = ImageWrapperResizableIcon.getIcon(Main.getResource('/blank.png'), [16, 16] as Dimension)
			
			ribbonApplicationMenu(id: 'appMenu') {
				ribbonApplicationMenuEntryPrimary(id: 'newMenu', icon: newIcon, text: rs('New'), kind: CommandButtonKind.POPUP_ONLY)
//				newMenu.addSecondaryMenuGroup 'Create a new item', newAction
                
				appMenu.addMenuSeparator()
				
				ribbonApplicationMenuEntryPrimary(id: 'saveAsMenu', icon: blankIcon, text: rs('Save As'), kind: CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION)
				appMenu.addMenuSeparator()
				
				ribbonApplicationMenuEntryPrimary(id: 'importMenu', icon: blankIcon, text: rs('Import'), kind: CommandButtonKind.POPUP_ONLY)
				ribbonApplicationMenuEntryPrimary(id: 'exportMenu', icon: blankIcon, text: rs('Export'), kind: CommandButtonKind.POPUP_ONLY)
				appMenu.addMenuSeparator()
				
				ribbonApplicationMenuEntryPrimary(icon: exitIcon, text: rs('Exit'), kind: CommandButtonKind.ACTION_ONLY, actionPerformed: exitAction)
				
				ribbonApplicationMenuEntryFooter(text: rs('Preferences'), actionPerformed: preferencesAction)
			}
		}
		
		def helpIcon = ImageWrapperResizableIcon.getIcon(Main.getResource('/add.png'), [16, 16] as Dimension)
		ribbon.configureHelp helpIcon, swing.aboutAction
        
        def taskIcon = ImageWrapperResizableIcon.getIcon(Main.getResource('/task.png'), [16, 16] as Dimension)
        def previousIcon = ImageWrapperResizableIcon.getIcon(Main.getResource('/task.png'), [16, 16] as Dimension)
        def nextIcon = ImageWrapperResizableIcon.getIcon(Main.getResource('/task.png'), [16, 16] as Dimension)
        def refreshIcon = ImageWrapperResizableIcon.getIcon(Main.getResource('/task.png'), [16, 16] as Dimension)
        def cancelLoadIcon = ImageWrapperResizableIcon.getIcon(Main.getResource('/task.png'), [16, 16] as Dimension)
        
		ribbon.addTask swing.build {
			ribbonTask('Home', bands: [
                ribbonBand(rs('Navigate'), icon: taskIcon, id: 'navigationBand', resizePolicies: ['mirror']) {
                    ribbonComponent(
                        component: commandButton(previousIcon, text: rs('Previous'), id: 'previousButton', actionPerformed: {actionContext.previousItem()} as ActionListener) {
                                bind(source: actionContext, sourceProperty: 'previousItem', target: previousButton, targetProperty: 'enabled', converter: {it != null})
                            },
                        priority: RibbonElementPriority.TOP
                    )
                    ribbonComponent(
                        component: commandButton(nextIcon, text: rs('Next'), id: 'nextButton', actionPerformed: {actionContext.nextItem()} as ActionListener) {
                                bind(source: actionContext, sourceProperty: 'nextItem', target: nextButton, targetProperty: 'enabled', converter: {it != null})
                            },
                        priority: RibbonElementPriority.TOP
                    )
                },
            
                ribbonBand(rs('Load'), icon: taskIcon, id: 'loadBand', resizePolicies: ['mirror']) {
                    ribbonComponent(
                        component: commandButton(refreshIcon, action: refreshAction),
                        priority: RibbonElementPriority.TOP
                    )
                    ribbonComponent(
                        component: commandButton(cancelLoadIcon, text: rs('Cancel')),
                        priority: RibbonElementPriority.TOP
                    )
                },
        
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
		
        add swing.panel {
            borderLayout()
            panel(new BreadcrumbPane(), id: 'breadcrumb', constraints: BorderLayout.NORTH)
            panel(new ViewPane(), id: 'contentPane1')
        }
	}
}
