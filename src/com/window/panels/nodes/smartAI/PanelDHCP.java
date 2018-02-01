package com.window.panels.nodes.smartAI;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.engine.components.Console;
import com.engine.components.TextArea;
import com.engine.components.TextField;
import com.engine.handlers.LanguageHandler;
import com.engine.smartAI.SmartAIDHCP;
import com.window.panels.PanelProtocol;

public class PanelDHCP extends PanelProtocol {

	private static final long serialVersionUID = -6059841989678237769L;

	private JLabel labelPoolName;
	private JLabel labelNetwork;
	private JLabel labelExcludedIPs;
	private JLabel labelDNSServer;
	
	private TextField txtPoolName;
	private TextField txtNetwork;
	private TextArea txtExcludedIPs;
	private TextField txtDNSServer;
	
	private JButton btnCreate;
	
	private Console console;
	private SmartAIDHCP smartAI;
	
	public PanelDHCP(LanguageHandler languageHandler) {
		super(languageHandler, "DHCP");
	}

	@Override
	protected void initComponents() {
		labelPoolName = new JLabel(" Pool name: ");
		labelNetwork = new JLabel(" Network: ");
		labelExcludedIPs = new JLabel(" Excluded IPs: ");
		labelDNSServer = new JLabel(" DNS Server: ");
		
		txtPoolName = new TextField("ex: Clients");
		txtNetwork = new TextField("ex: 192.168.0.1/24");
		txtExcludedIPs = new TextArea("ex: 192.168.0.0 - 192.168.0.1, 192.168.0.255");
		txtDNSServer = new TextField("ex: 8.8.8.8");
		
		btnCreate = new JButton("Create Command List");
		console = new Console();
		smartAI = new SmartAIDHCP(console);
	}

	@Override
	protected void layoutComponents() {
		JPanel components = new JPanel(new BorderLayout());
		
		JPanel panelDetails = new JPanel(new BorderLayout());
		
		JPanel panelDetailsNorth = new JPanel(new GridLayout(2, 2));
		setEmptyFieldSet(panelDetails);		
		JPanel panelDetailsPool = new JPanel(new BorderLayout());
		panelDetailsPool.add(labelPoolName, BorderLayout.WEST);
		panelDetailsPool.add(txtPoolName, BorderLayout.CENTER);
		panelDetailsNorth.add(panelDetailsPool);
		JPanel panelDetailsNetwork = new JPanel(new BorderLayout());
		panelDetailsNetwork.add(labelNetwork, BorderLayout.WEST);
		panelDetailsNetwork.add(txtNetwork, BorderLayout.CENTER);
		panelDetailsNorth.add(panelDetailsNetwork);
		JPanel panelDetailsDNSServer = new JPanel(new BorderLayout());
		panelDetailsDNSServer.add(labelDNSServer, BorderLayout.WEST);
		panelDetailsDNSServer.add(txtDNSServer, BorderLayout.CENTER);
		panelDetailsNorth.add(panelDetailsDNSServer);
		
		JPanel panelDetailsSouth = new JPanel(new BorderLayout());
		panelDetailsSouth.add(labelExcludedIPs, BorderLayout.WEST);
		panelDetailsSouth.add(txtExcludedIPs, BorderLayout.CENTER);
		
		panelDetails.add(panelDetailsNorth, BorderLayout.NORTH);
		panelDetails.add(panelDetailsSouth, BorderLayout.CENTER);
		panelDetails.add(btnCreate, BorderLayout.SOUTH);
		
		JPanel panelOutput = new JPanel(new BorderLayout());
		setEmptyFieldSet(panelOutput);
		panelOutput.add(console);
		
		components.add(setTitle("DHCP"), BorderLayout.NORTH);
		components.add(panelDetails, BorderLayout.CENTER);
		
		add(components, BorderLayout.NORTH);
		add(panelOutput, BorderLayout.CENTER);
	}

	@Override
	protected void initListeners() {
		btnCreate.addActionListener(e -> {
			smartAI.clearConsole();
			smartAI.exec(txtPoolName.getText(), 
						 txtNetwork.getText(), 
						 txtDNSServer.getText(), 
						 txtExcludedIPs.getText());
		});
	}

}