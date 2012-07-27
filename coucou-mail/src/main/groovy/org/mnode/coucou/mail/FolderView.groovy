package org.mnode.coucou.mail

import java.awt.BorderLayout;
import java.awt.Color
import java.awt.Font
import java.awt.event.KeyEvent

import javax.mail.Folder
import javax.swing.JScrollPane

import org.jdesktop.swingx.JXPanel
import org.mnode.ousia.OusiaBuilder
import org.mnode.ousia.glazedlists.DateExpansionModel
import org.mnode.ousia.glazedlists.JCheckboxMatcherEditor

import ca.odell.glazedlists.BasicEventList
import ca.odell.glazedlists.Filterator
import ca.odell.glazedlists.TextFilterator
import ca.odell.glazedlists.TreeList.Format
import ca.odell.glazedlists.event.ListEventListener
import ca.odell.glazedlists.gui.TableFormat
import ca.odell.glazedlists.matchers.CompositeMatcherEditor
import ca.odell.glazedlists.matchers.MatcherEditor
import ca.odell.glazedlists.swing.EventTableModel
import ca.odell.glazedlists.swing.TextComponentMatcherEditor

class FolderView extends JXPanel {
	
	BasicEventList<?> activities = []
	
	OusiaBuilder ousia = []
	
	def sortComparators = [:]
	
	def selectedGroup = 'Date'
	def selectedSort = ousia.rs('Date')
	
	FolderView(Folder folder) {
		
		def dateGroup = { date ->
			def today = Calendar.instance
			today.clearTime()
			def yesterday = Calendar.instance
			yesterday.add Calendar.DAY_OF_YEAR, -1
			yesterday.clearTime()
			if (date < yesterday.time) {
				return 'Older Items'
			}
			else if (date < today.time) {
				return 'Yesterday'
			}
			else {
				return 'Today'
			}
		}
		
		def dateGroupComparator = {a, b ->
			def groups = ['Today', 'Yesterday', 'Older Items']
			groups.indexOf(a) - groups.indexOf(b)
		} as Comparator
		
		def groupComparators = [:]
		groupComparators['Date'] = {a, b -> dateGroupComparator.compare(dateGroup(a.date), dateGroup(b.date))} as Comparator
		groupComparators['Source'] = {a, b -> a.source <=> b.source} as Comparator
		
		sortComparators[ousia.rs('Date')] = {a, b ->
			int groupSort = groupComparators[selectedGroup].compare(a, b)
			(groupSort != 0) ? groupSort : b.date <=> a.date
		} as Comparator
		sortComparators[ousia.rs('Title')] = {a, b ->
			int groupSort = groupComparators[selectedGroup].compare(a, b)
			groupSort = (groupSort != 0) ? groupSort : a.title <=> b.title
			(groupSort != 0) ? groupSort : b.date <=> a.date
		} as Comparator
		sortComparators[ousia.rs('Source')] = {a, b ->
			int groupSort = groupComparators[selectedGroup].compare(a, b)
			groupSort = (groupSort != 0) ? groupSort : b.source <=> a.source
			(groupSort != 0) ? groupSort : b.date <=> a.date
		} as Comparator
		
		def buildTreeList = {
			ousia.build {
				def filters = new BasicEventList<MatcherEditor<?>>()
				filters << new TextComponentMatcherEditor(filterTextField, { baseList, e ->
					baseList << e['title']
				} as TextFilterator, true)
				filters << new JCheckboxMatcherEditor(unreadFilterCheckbox, { baseList, e ->
					baseList << !e.seen()
				} as Filterator)
				filters << new JCheckboxMatcherEditor(importantFilterCheckbox, { baseList, e ->
					baseList << e.flagged()
				} as Filterator)
		
				CompositeMatcherEditor filterMatcherEditor = [filters]
				filterMatcherEditor.mode = CompositeMatcherEditor.AND
				filterList(activities, id: 'filteredActivities', matcherEditor: filterMatcherEditor)
				
				filteredActivities.addListEventListener({
					doLater {
						activityTable.clearSelection()
						if (filteredActivities.size() > 0) {
							statusMessage.text = "${filteredActivities.size()} ${rs('items')}"
						}
						else {
							statusMessage.text = rs('Nothing to see here')
						}
					}
				} as ListEventListener)
		
				treeList(sortedList(filteredActivities, comparator: sortComparators[selectedSort], id: 'sortedActivities'),
					 expansionModel: new DateExpansionModel(), format: [
						allowsChildren: {element -> true},
						getComparator: {depth -> },
						getPath: {path, element ->
							path << dateGroup(element.date)
							path << element
						 }
					] as Format<?>, id: 'activityTree')
			}
		}

		def buildActivityTableModel = {
			ousia.build {
				new EventTableModel<?>(activityTree,
					[
						getColumnCount: {3},
						getColumnName: {column -> switch(column) {
								case 0: return 'Source'
								case 1: return 'Title'
								case 2: return 'When'
								default: return null
							}
						},
						getColumnValue: {object, column -> switch(column) {
							case 0: if (object instanceof String) {
								return object
							} else {
								return object['source']
							}
							case 1: if (!(object instanceof String)) {
								return object['title']
							}
							case 2: if (!(object instanceof String)) {
								return object['date']
							}
						}}
					] as TableFormat)
			}
		}
		
		layout = ousia.borderLayout()
		add ousia.panel {
			borderLayout()
			panel(constraints: BorderLayout.NORTH) {
				textField(columns: 14, prompt: rs('Type To Filter..'), promptFontStyle: Font.ITALIC, promptForeground: Color.LIGHT_GRAY, id: 'filterTextField', keyPressed: {e-> if (e.keyCode == KeyEvent.VK_ESCAPE) e.source.text = null})
				checkBox(text: rs('Unread Items'), id: 'unreadFilterCheckbox')
				checkBox(text: rs('Important Items'), id: 'importantFilterCheckbox')
			}
			
			scrollPane(horizontalScrollBarPolicy: JScrollPane.HORIZONTAL_SCROLLBAR_NEVER, border: null, viewportBorder: null, id: 'sp') {
				table(gridColor: Color.LIGHT_GRAY, showHorizontalLines: false, opaque: false, border: null, id: 'messageTable') {
					buildTreeList()
					messageTable.model = buildActivityTableModel()
					
					messageTable.tableHeader.visible = false
					messageTable.tableHeader.preferredSize = [-1, 0]
//					ttsupport = TreeTableSupport.install(activityTable, activityTree, 0)
//					ttsupport.arrowKeyExpansionEnabled = true
//					ttsupport.delegateRenderer.background = Color.WHITE
					
					messageTable.columnModel.getColumn(0).maxWidth = 250
					messageTable.columnModel.getColumn(0).preferredWidth = 200
					messageTable.columnModel.getColumn(2).maxWidth = 150
					messageTable.columnModel.getColumn(2).preferredWidth = 150

				}
			}
		}
	}
}
