package com.window.panels.nodes.converter;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.engine.calculators.NetworkConverter;
import com.engine.calculators.VLSMSpecializedCalculator;
import com.engine.components.TextField;
import com.engine.handlers.LanguageHandler;
import com.engine.utils.Popup;
import com.window.panels.PanelProtocol;

public class PanelConverterRequestedHosts extends PanelProtocol {

	public PanelConverterRequestedHosts(LanguageHandler languageHandler) {
		super(languageHandler);
	}

	private static final long serialVersionUID = 2371930704559590937L;

	private JLabel labelHosts;
	private JLabel labelHostsResultCIDR;
	private JLabel labelHostsResultNetmask;
	private JLabel labelHostsClass;
	private JLabel labelHostsTotalHosts;
	private JLabel labelHostsTotalSubnets;
	private JLabel labelHostsWildcard;
	private TextField textfieldHosts;
	private JButton buttonHosts;
	private JButton buttonHelp;
	
	@Override
	protected void initComponents() {
		labelHosts = new JLabel(" " + languageHandler.getKey("converter_hosts_label_RequestedHosts") + ": ");
		labelHostsResultCIDR = new JLabel(" " + languageHandler.getKey("converter_hosts_label_CIDR") + ": ");
		labelHostsResultNetmask = new JLabel(" " + languageHandler.getKey("converter_hosts_label_Netmask") + ": ");
		labelHostsClass = new JLabel(" " + languageHandler.getKey("converter_hosts_label_Class") + ": ");
		labelHostsTotalSubnets = new JLabel(" " + languageHandler.getKey("converter_hosts_label_Subnets") + ": ");
		labelHostsTotalHosts = new JLabel(" " + languageHandler.getKey("converter_hosts_label_Hosts") + ": ");
		labelHostsWildcard = new JLabel(" " + languageHandler.getKey("converter_acl_wildcard") + ": ");
		textfieldHosts = new TextField(11, languageHandler.getKey("word_example(short)") + ": 60");
		buttonHosts = new JButton(languageHandler.getKey("converter_hosts_button_ConvertHosts"));
		buttonHelp = new JButton(languageHandler.getKey("converter_button_Help"));
	}

	@Override
	protected void layoutComponents() {
		JPanel panelHosts = new JPanel(new BorderLayout());
		JPanel panelHostsSub = new JPanel(new BorderLayout());
		JPanel panelHostsResult = new JPanel(new BorderLayout());
		JPanel panelHostsResult1 = new JPanel(new BorderLayout());
		JPanel panelButtons = new JPanel(new BorderLayout());
		panelButtons.add(buttonHosts, BorderLayout.CENTER);
		panelButtons.add(buttonHelp, BorderLayout.EAST);
		setEmptyFieldSet(panelHosts);
		panelHostsSub.add(labelHosts, BorderLayout.WEST);
		panelHostsSub.add(textfieldHosts, BorderLayout.CENTER);
		panelHostsResult.add(labelHostsResultCIDR, BorderLayout.NORTH);
		panelHostsResult.add(labelHostsResultNetmask, BorderLayout.CENTER);
		panelHostsResult.add(labelHostsClass, BorderLayout.SOUTH);
		panelHostsResult1.add(labelHostsTotalSubnets, BorderLayout.NORTH);
		panelHostsResult1.add(labelHostsTotalHosts, BorderLayout.CENTER);
		JPanel panelHostsResult2 = new JPanel(new BorderLayout());
		panelHostsResult2.add(labelHostsWildcard, BorderLayout.NORTH);
		panelHostsResult2.add(panelButtons, BorderLayout.CENTER);
		panelHostsResult1.add(panelHostsResult2, BorderLayout.SOUTH);
		panelHosts.add(panelHostsSub, BorderLayout.NORTH);
		panelHosts.add(panelHostsResult, BorderLayout.CENTER);
		panelHosts.add(panelHostsResult1, BorderLayout.SOUTH);
		add(panelHosts);
	}

	@Override
	protected void initListeners() {
		buttonHosts.addActionListener(e -> {
			if (!isValidInput())
				return;
			int value = Integer.parseInt(textfieldHosts.getText());
			labelHostsTotalHosts.setText(" " + languageHandler.getKey("converter_hosts_label_Hosts") + ": " + VLSMSpecializedCalculator.getValidHost(value));
			labelHostsResultCIDR.setText(" " + languageHandler.getKey("converter_hosts_label_CIDR") + ": " + VLSMSpecializedCalculator.getCIDR(value));
			labelHostsResultNetmask.setText(" " + languageHandler.getKey("converter_hosts_label_Netmask") + ": " + VLSMSpecializedCalculator.getNetmask(value));
			labelHostsClass.setText(" " + languageHandler.getKey("converter_hosts_label_Class") + ": " + NetworkConverter.getNetmaskClass(VLSMSpecializedCalculator.getNetmask(value)));
			labelHostsTotalSubnets.setText(" " + languageHandler.getKey("converter_hosts_label_Subnets") + ": " + NetworkConverter.getTotalValidSubnets(Integer.parseInt(VLSMSpecializedCalculator.getCIDR(value))));
			labelHostsWildcard.setText(" " + languageHandler.getKey("converter_acl_wildcard") + ": " + NetworkConverter.getWildCardMask(VLSMSpecializedCalculator.getNetmask(value)));
		});
		buttonHelp.addActionListener(e -> {
			showHelp();
		});
	}
	
	private boolean isValidInput() {
		int value = 0;
		try {
			value = Integer.parseInt(textfieldHosts.getText());
		} catch (NumberFormatException ex) {
			Popup.showErrorMessage(languageHandler.getKey("converter_hosts_error_invalidHosts"));
			return false;
		}
		if (value < 0) {
			Popup.showErrorMessage(languageHandler.getKey("converter_hosts_error_invalidHosts"));
			return false;
		}
		return true;
	}

	private void showHelp() {
		Popup.showHelpMessage(languageHandler.getKey("converter_hosts_help_WhatIsIt"), 
				languageHandler.getKey("converter_hosts_help_HowDoesItWork"),
				languageHandler.getKey("converter_hosts_help_Example"));
	}
}