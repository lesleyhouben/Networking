package com.window.panels.nodes;

import com.window.panels.PanelProtocol;

public class PanelWelcome extends PanelProtocol {

	private static final long serialVersionUID = -7836952675902892073L;

	@Override
	protected void initComponents() {
		
	}

	@Override
	protected void layoutComponents() {
		add(setTitle("Welcome!"));
	}

	@Override
	protected void initListeners() {
		
	}

}
