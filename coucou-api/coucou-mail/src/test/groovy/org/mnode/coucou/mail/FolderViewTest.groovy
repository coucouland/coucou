package org.mnode.coucou.mail

import groovy.swing.SwingBuilder

import javax.swing.*

SwingBuilder swing = []
swing.edt {
	frame(title: 'Test FolderView', pack: true, locationRelativeTo: null, visible: true, defaultCloseOperation: JFrame.EXIT_ON_CLOSE) {
		panel(new FolderView())
	}
}