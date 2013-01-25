import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;



public class GUI extends JFrame{
	private controller ctrl;
	private boolean NewIncident;
	private boolean InfoIncidents;
	private boolean Authors;
	private boolean Logged;
	private JTextField loginfield;
	private JPasswordField passwordField;
	private JButton login, newButton, seeButton, infoButton, exitButton, backButton, executeButton;
	private JPanel panel;
	private JRadioButton hardware, software;
	private ButtonGroup group;
	private JTextArea info, description;
	private JComboBox type, producer, model, device, developer, softname;
	private JLabel concernlabel, hardtypelabel, softtypelabel, loginlabel, 
					passwordlabel, producerlabel, modellabel, devicelabel, softnamelabel, descriptionlabel;
	private ArrayList<String> types, companies, models, devices, producers, softwares; 
	
	public GUI()
	{
		Logged = true;
		NewIncident = false;
		InfoIncidents = false;
		Authors = false;
		Draw();
	}
	public void setController(controller c){
		ctrl = c;
	}
	public void Draw()
	{
		if(panel != null)this.getContentPane().remove(panel);
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(400, 400));
		panel.setLayout(null);
		this.getContentPane().add(panel);
        
		if(Logged)
		{
			if(!NewIncident && !InfoIncidents && !Authors)
			{
				newButton = new JButton("Zg³oœ incydent");
				newButton.setBounds(100, 20, 200, 50);
				newButton.addActionListener(new ActionListener(){
		
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						NewIncident = true;
						Draw();
					}
					
				});
				panel.add(newButton);
				
		        seeButton = new JButton("Zobacz swoje incydenty");
		        seeButton.setBounds(100, 120, 200, 50);
		        seeButton.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						InfoIncidents = true;
						Draw();
					}
		        	
		        });
		        panel.add(seeButton);
		        
		        infoButton = new JButton("O autorach");
		        infoButton.setBounds(100, 220, 200, 50);
		        infoButton.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						Authors = true;
						Draw();
					}
		        	
		        });
		        panel.add(infoButton);
		        
		        exitButton = new JButton("WyjdŸ");
		        exitButton.setBounds(100, 320, 200, 50);
		        exitButton.addActionListener(new ActionListener(){
		
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						System.exit(0);
					}
		        });
		        panel.add(exitButton);
			}
			else if(NewIncident)
			{
				concernlabel = new JLabel("Incydent dotyczy:");
				concernlabel.setBounds(0, 0, 400, 20);
				panel.add(concernlabel);
				
				group = new ButtonGroup();
				hardware= new JRadioButton("Sprzêt", true);
				hardware.setBounds(0,  20, 200, 20);
				group.add(hardware);
				panel.add(hardware);
				software = new JRadioButton("Oprogramowanie");
				software.setBounds(200,  20, 200, 20);
				group.add(software);
				panel.add(software);
				
				hardtypelabel = new JLabel("Wybierz typ:");
				hardtypelabel.setBounds(0, 40, 200, 20);
				panel.add(hardtypelabel);
				
				type = new JComboBox();
				types = new ArrayList<String>();
				types = ctrl.getAllTypes();
				for(int i=0; i<types.size();i++){
					type.addItem(types.get(i));
				}
				type.setBounds(0, 60, 150, 20);
				panel.add(type);
				
				softtypelabel = new JLabel("Wybierz producenta:");
				softtypelabel.setBounds(200, 40, 200, 20);
				panel.add(softtypelabel);
				
				developer = new JComboBox();
				producers = ctrl.getAllProducers();
				for(int i=0; i<producers.size();i++){
					developer.addItem(producers.get(i));
				}
				developer.setBounds(200, 60, 150, 20);
				panel.add(developer);
				
				producerlabel = new JLabel("Wybierz markê:");
				producerlabel.setBounds(0, 80, 200, 20);
				panel.add(producerlabel);
				
				producer = new JComboBox();
				companies = ctrl.getAllCompanies();
				for(int i=0; i<companies.size();i++){
					producer.addItem(companies.get(i));
				}
				producer.setBounds(0, 100, 150, 20);
				panel.add(producer);
				
				modellabel = new JLabel("Wybierz model:");
				modellabel.setBounds(0, 120, 200, 20);
				panel.add(modellabel);
				
				model = new JComboBox();
				models = ctrl.getAllModels();
				for(int i=0; i<models.size();i++){
					model.addItem(models.get(i));
				}
				model.setBounds(0, 140, 150, 20);
				panel.add(model);
				
				devicelabel = new JLabel("Wybierz urz¹dzenie:");
				devicelabel.setBounds(0, 160, 200, 20);
				panel.add(devicelabel);
				
				device = new JComboBox();
				devices = ctrl.getAllDevices();
				for(int i=0; i<devices.size();i++){
					device.addItem(devices.get(i));
				}
				device.setBounds(0, 180, 150, 20);
				panel.add(device);
				
				softnamelabel = new JLabel("Wybierz oprogramowanie:");
				softnamelabel.setBounds(200, 80, 200, 20);
				panel.add(softnamelabel);
				
				softname = new JComboBox();
				softwares = ctrl.getAllSoftware();
				for(int i=0; i<softwares.size();i++){
					softname.addItem(softwares.get(i));
				}
				softname.setBounds(200, 100, 100, 20);
				panel.add(softname);
				
				descriptionlabel = new JLabel("Opis incydentu:");
				descriptionlabel.setBounds(0, 200, 400, 20);
				panel.add(descriptionlabel);
				
				description = new JTextArea();
				description.setBounds(10, 230, 380, 140);
				panel.add(description);
				
				executeButton = new JButton("Zg³oœ");
				executeButton.setBounds(0, 380, 100, 20);
				executeButton.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						NewIncident = false;
						//TODO - po³¹czenie z BD wstawiaj¹ce incydent z opisu
						//wszystkie pola bêd¹ dostêpne z poziomu klasy
						Draw();
					}
					
				});
				panel.add(executeButton);
				
				backButton = new JButton("Wstecz");
				backButton.setBounds(300, 380, 100, 20);
				backButton.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						NewIncident = false;
						Draw();
					}
					
				});
				panel.add(backButton);
			}
			else if(InfoIncidents)
			{
				 String[] columnNames = {"id", "numer seryjny", "typ", "marka", "model"};
				 Object data[][] = new Object[24][5];
				 data[0][0] = "id";
				 data[0][1] = "opis";
				 data[0][2] = "rodzaj";
				 data[0][3] = "status";
				 data[0][4] = "edytuj";
				 JTable table = new JTable(data, columnNames);
				 table.setBounds(0, 0, 400, 385);
				 panel.add(table);
				 
				backButton = new JButton("Wstecz");
				backButton.setBounds(150, 385, 100, 15);
				backButton.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					InfoIncidents = false;
						Draw();
					}
						
				});
				panel.add(backButton);

			}
			
			else if(Authors){
				info = new JTextArea();
				info.setBounds(50, 100, 300, 200);
				info.setText("Aplikacja kliencka projektu z przedmiotu Bazy Danych 2\nAutorzy:\nMarcin Janicki\nPiotr Kalinowski");
				panel.add(info);
				
				backButton = new JButton("Wstecz");
				backButton.setBounds(50, 350, 300, 50);
				backButton.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						Authors = false;
						Draw();
					}
					
				});
				panel.add(backButton);
			}
		}
		else
		{
			loginlabel = new JLabel("Login:");
			loginlabel.setBounds(60, 50, 40, 20);
			panel.add(loginlabel);
			
			loginfield = new JTextField(20);
			loginfield.setBounds(100, 50, 200, 20);
			panel.add(loginfield);
			
			passwordlabel = new JLabel("Has³o:");
			passwordlabel.setBounds(60, 100, 40, 20);
			panel.add(passwordlabel);
			
			passwordField = new JPasswordField(20);
			passwordField.setBounds(100, 100, 200, 20);
			panel.add(passwordField);
			
			login = new JButton("Zaloguj");
			login.setBounds(100, 150, 200, 20);
			login.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					Logged = ctrl.login(loginfield.getText(), String.valueOf(passwordField.getPassword()));
					Draw();
				}
				
			});
			panel.add(login);
			
	        exitButton = new JButton("WyjdŸ");
	        exitButton.setBounds(100, 200, 200, 20);
	        exitButton.addActionListener(new ActionListener(){
	
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					System.exit(0);
				}
	        });
	        panel.add(exitButton);
			
		}
		
        this.setTitle("BD2");
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
