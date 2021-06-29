

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

public class Gui {

	protected Shell shell;
	List<Userbase> users = new ArrayList<Userbase>();  
	List<TaskDatabase> tasks = new ArrayList<TaskDatabase>();
	private Text textFirstName;
	private Text textLastName;
	private Text textID;
	private Text textDelete;
	private Text textCreateTaskName;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Gui window = new Gui();
			window.open();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createUser(String firstname, String lastname, String ID) {
		Userbase user = new Userbase();
		user.firstname = firstname;
		user.lastname = lastname;
		user.ID = ID;
		users.add(user);
	};

	public void deleteUser(String ID) {
		int index = -1;
		
		for (int i = 0; i < users.size(); i++) {
			if ((users.get(i).ID).equals(ID)) {
				index = i;
				break;
			}
		}
		
		users.remove(index);
	}
	
	public int createTask(String name) {
		TaskDatabase task = new TaskDatabase();
		task.name = name;
		task.assignee = "";
		
		for (int i = 0; i < tasks.size(); i++) {
			if ((tasks.get(i).name).compareTo(name) == 0)
				return -1;
		}
		
		tasks.add(task);
		return 1;

	}
	
	public void deleteTask(String[] selected) {
		TaskDatabase task = new TaskDatabase();
		
		for (int i = 0; i < selected.length; i++) {
			task.name = selected[i];
			
			for (int j = 0; j < tasks.size(); j++) {
				if (tasks.get(j).name.compareTo(selected[i]) == 0) {
					tasks.remove(j);
					break;
				}
			}
		}
	}
	
	public void assignTask(String username, String taskname) {

		TaskDatabase task = new TaskDatabase();

		task.assignee = username;
		task.name = taskname;
		
		for (int j = 0; j < tasks.size(); j++) {
			if (tasks.get(j).name.compareTo(taskname) == 0) {
				tasks.set(j, task);
				break;
			}
		}
	}
	public void unassignTask(String username, String taskname) {

		TaskDatabase task = new TaskDatabase();

		task.assignee = "";
		task.name = taskname;
		
		for (int j = 0; j < tasks.size(); j++) {
			if (tasks.get(j).name.compareTo(taskname) == 0) {
				tasks.set(j, task);
				break;
			}
		}
	}
	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		shell.setLayout(null);
		
		Composite screenMain = new Composite(shell, SWT.NONE);
		screenMain.setBounds(0, 0, 434, 261);
		
		Button btnCreateUser = new Button(screenMain, SWT.NONE);
		btnCreateUser.setLocation(50, 35);
		btnCreateUser.setSize(125, 50);
		btnCreateUser.setText("Create User");
		
		Button btnCreateTask = new Button(screenMain, SWT.NONE);
		btnCreateTask.setLocation(50, 105);
		btnCreateTask.setSize(125, 50);
		btnCreateTask.setText("Create Task");
		
		Button btnAssignTask = new Button(screenMain, SWT.NONE);
		btnAssignTask.setLocation(50, 175);
		btnAssignTask.setSize(125, 50);
		btnAssignTask.setText("Assign Task");
		
		Button btnDeleteUser = new Button(screenMain, SWT.NONE);
		btnDeleteUser.setLocation(250, 35);
		btnDeleteUser.setSize(125, 50);
		btnDeleteUser.setText("Delete User");
		
		Button btnDeleteTask = new Button(screenMain, SWT.NONE);
		btnDeleteTask.setLocation(250, 105);
		btnDeleteTask.setSize(125, 50);
		btnDeleteTask.setText("Delete Task");
		
		Button btnUnassignTask = new Button(screenMain, SWT.NONE);
		btnUnassignTask.setLocation(250, 175);
		btnUnassignTask.setSize(125, 50);
		btnUnassignTask.setText("Unassign Task");
		
		Composite screenCreateUser = new Composite(shell, SWT.NONE);
		screenCreateUser.setVisible(false);
		screenCreateUser.setBounds(0, 0, 434, 261);
		
		Button btnSubmit = new Button(screenCreateUser, SWT.NONE);
		btnSubmit.setLocation(217, 200);
		btnSubmit.setSize(100, 25);
		btnSubmit.setText("Submit");
		
		Label lblFirstName = new Label(screenCreateUser, SWT.NONE);
		lblFirstName.setBounds(100, 50, 75, 15);
		lblFirstName.setText("First Name");
		
		Label lblLastName = new Label(screenCreateUser, SWT.NONE);
		lblLastName.setBounds(100, 90, 75, 15);
		lblLastName.setText("Last Name");
		
		Label lblID = new Label(screenCreateUser, SWT.NONE);
		lblID.setBounds(145, 150, 55, 15);
		lblID.setText("ID");
		
		textFirstName = new Text(screenCreateUser, SWT.BORDER);
		textFirstName.setBounds(200, 50, 125, 21);
		
		textLastName = new Text(screenCreateUser, SWT.BORDER);
		textLastName.setBounds(200, 90, 125, 21);
		
		textID = new Text(screenCreateUser, SWT.BORDER);
		textID.setBounds(200, 150, 125, 21);
		
		Label lblError = new Label(screenCreateUser, SWT.NONE);
		lblError.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblError.setBounds(125, 25, 200, 19);
		
		Button btnCreateUserBack = new Button(screenCreateUser, SWT.NONE);
		btnCreateUserBack.setBounds(117, 200, 75, 25);
		btnCreateUserBack.setText("Back");
		
		Composite screenDeleteUser = new Composite(shell, SWT.NONE);
		screenDeleteUser.setVisible(false);
		screenDeleteUser.setBounds(0, 0, 434, 261);

		Label lblDeleteID = new Label(screenDeleteUser, SWT.NONE);
		lblDeleteID.setBounds(135, 87, 70, 15);
		lblDeleteID.setText("Enter User ID");
		
		textDelete = new Text(screenDeleteUser, SWT.BORDER);
		textDelete.setBounds(225, 85, 76, 21);
		
		Button btnDelete = new Button(screenDeleteUser, SWT.NONE);
		btnDelete.setBounds(220, 150, 75, 25);
		btnDelete.setText("Delete");
		
		Label lblError2 = new Label(screenDeleteUser, SWT.NONE);
		lblError2.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblError2.setBounds(125, 25, 175, 15);
		
		Button btnDeleteBack = new Button(screenDeleteUser, SWT.NONE);
		btnDeleteBack.setBounds(130, 150, 75, 25);
		btnDeleteBack.setText("Back");
		
		Composite screenCreateTask = new Composite(shell, SWT.NONE);
		screenCreateTask.setVisible(false);
		screenCreateTask.setBounds(0, 0, 434, 261);
		
		textCreateTaskName = new Text(screenCreateTask, SWT.BORDER);
		textCreateTaskName.setBounds(140, 100, 141, 21);
		
		Label lblCreateTaskName = new Label(screenCreateTask, SWT.NONE);
		lblCreateTaskName.setBounds(165, 70, 120, 15);
		lblCreateTaskName.setText("Enter Task Name");
		
		Button btnCreateTaskSubmit = new Button(screenCreateTask, SWT.NONE);
		btnCreateTaskSubmit.setBounds(220, 150, 75, 25);
		btnCreateTaskSubmit.setText("Create");
		
		Button btnCreateTaskBack = new Button(screenCreateTask, SWT.NONE);
		btnCreateTaskBack.setBounds(130, 150, 75, 25);
		btnCreateTaskBack.setText("Back");
		
		Label lblError3 = new Label(screenCreateTask, SWT.NONE);
		lblError3.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblError3.setBounds(155, 25, 114, 15);
		
		Composite screenDeleteTask = new Composite(shell, SWT.NONE);
		screenDeleteTask.setVisible(false);
		screenDeleteTask.setBounds(0, 0, 434, 261);
		
		org.eclipse.swt.widgets.List taskList = new org.eclipse.swt.widgets.List(screenDeleteTask, SWT.BORDER);
		taskList.setItems(new String[] {});
		taskList.setLocation(100, 73);
		taskList.setSize(225, 125);
		
		Button btnDeleteTaskSubmit = new Button(screenDeleteTask, SWT.NONE);
		btnDeleteTaskSubmit.setBounds(220, 215, 75, 25);
		btnDeleteTaskSubmit.setText("Delete");
		
		Button btnDeleteTaskBack = new Button(screenDeleteTask, SWT.NONE);
		btnDeleteTaskBack.setBounds(130, 215, 75, 25);
		btnDeleteTaskBack.setText("Back");
		
		Label lblError4 = new Label(screenDeleteTask, SWT.NONE);
		lblError4.setLocation(150, 44);
		lblError4.setSize(173, 15);
		lblError4.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		
		Label lblDeleteTask = new Label(screenDeleteTask, SWT.NONE);
		lblDeleteTask.setBounds(150, 22, 157, 37);
		lblDeleteTask.setText("Select task to delete");
		
		Composite screenAssignTask = new Composite(shell, SWT.NONE);
		screenAssignTask.setVisible(false);
		screenAssignTask.setBounds(0, 0, 434, 261);
		
		org.eclipse.swt.widgets.List listAssignTask = new org.eclipse.swt.widgets.List(screenAssignTask, SWT.BORDER);
		listAssignTask.setBounds(240, 54, 158, 126);
		
		org.eclipse.swt.widgets.List listAssignUser = new org.eclipse.swt.widgets.List(screenAssignTask, SWT.BORDER);
		listAssignUser.setLocation(36, 54);
		listAssignUser.setSize(158, 126);
		
		Label lblAssignUsers = new Label(screenAssignTask, SWT.NONE);
		lblAssignUsers.setVisible(true);
		lblAssignUsers.setBounds(95, 35, 55, 15);
		lblAssignUsers.setText("Users");
		
		Label lblAssignTasks = new Label(screenAssignTask, SWT.NONE);
		lblAssignTasks.setVisible(true);
		lblAssignTasks.setBounds(270, 35, 100, 15);
		lblAssignTasks.setText("Unassigned tasks");
		
		Label lblError5 = new Label(screenAssignTask, SWT.NONE);
		lblError5.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblError5.setBounds(175, 15, 116, 15);
		
		Button btnAssignSubmit = new Button(screenAssignTask, SWT.NONE);
		btnAssignSubmit.setVisible(true);
		btnAssignSubmit.setBounds(280, 200, 75, 25);
		btnAssignSubmit.setText("Assign");
		
		Button btnAssignBack = new Button(screenAssignTask, SWT.NONE);
		btnAssignBack.setVisible(true);
		btnAssignBack.setBounds(80, 200, 75, 25);
		btnAssignBack.setText("Back");
		
		Composite screenUnassignTask = new Composite(shell, SWT.NONE);
		screenUnassignTask.setVisible(true);
		screenUnassignTask.setBounds(0, 0, 434, 261);
		
		org.eclipse.swt.widgets.List listUnassignTask = new org.eclipse.swt.widgets.List(screenUnassignTask, SWT.BORDER);
		listUnassignTask.setVisible(true);
		listUnassignTask.setBounds(240, 54, 158, 126);
		
		org.eclipse.swt.widgets.List listUnassignUser = new org.eclipse.swt.widgets.List(screenUnassignTask, SWT.BORDER);
		listUnassignUser.setVisible(true);
		listUnassignUser.setLocation(36, 54);
		listUnassignUser.setSize(158, 126);
		
		Button btnUnassignSubmit = new Button(screenUnassignTask, SWT.NONE);
		btnUnassignSubmit.setVisible(true);
		btnUnassignSubmit.setBounds(280, 200, 75, 25);
		btnUnassignSubmit.setText("Unassign");
		
		Button btnUnassignBack = new Button(screenUnassignTask, SWT.NONE);
		btnUnassignBack.setVisible(true);
		btnUnassignBack.setBounds(80, 200, 75, 25);
		btnUnassignBack.setText("Back");
		
		Label lblUnassignUser = new Label(screenUnassignTask, SWT.NONE);
		lblUnassignUser.setVisible(true);
		lblUnassignUser.setBounds(95, 35, 55, 15);
		lblUnassignUser.setText("Users");
		
		Label lblUnassignTasks = new Label(screenUnassignTask, SWT.NONE);
		lblUnassignTasks.setVisible(true);
		lblUnassignTasks.setBounds(280, 35, 142, 15);
		lblUnassignTasks.setText("Assigned tasks");
		
		Label lblError6 = new Label(screenUnassignTask, SWT.NONE);
		lblError6.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblError6.setBounds(175, 15, 173, 15);
		lblError6.setText("no user selected");

		btnCreateUser.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				screenMain.setVisible(false);
				screenCreateUser.setVisible(true);

			}
		});
		btnCreateUserBack.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				
				lblError.setText("");
				screenMain.setVisible(true);
				screenCreateUser.setVisible(false);

			}
		});
		btnSubmit.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				lblError.setVisible(false);
				
				try {
					if (((textFirstName.getText()).equals("") == false) && ((textLastName.getText()).equals("") == false) && ((textID.getText()).equals("") == false)){
						createUser(textFirstName.getText(), textLastName.getText(), textID.getText());
						
						textFirstName.setText("");
						textLastName.setText("");
						textID.setText("");
						
						lblError.setText("");
						screenMain.setVisible(true);
						screenCreateUser.setVisible(false);
						lblError.setVisible(false);
					}
					else {
						lblError.setText("Make sure all fields are filled");
						lblError.setVisible(true);
					}

			
				}
				catch (Exception E) {
					
				}
			}
		});
		btnDeleteUser.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				screenMain.setVisible(false);
				screenDeleteUser.setVisible(true);
			}
		});
		btnDeleteBack.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				lblError2.setText("");
				textDelete.setText("");
				screenMain.setVisible(true);
				screenDeleteUser.setVisible(false);

			}
		});
		btnDelete.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				lblError2.setVisible(false);
				
				try{			
					deleteUser(textDelete.getText());

					textDelete.setText("");
					lblError2.setText("");
					screenMain.setVisible(true);
					screenDeleteUser.setVisible(false);
					lblError2.setVisible(false);
				}
				catch (Exception E) {
					lblError2.setVisible(true);
					lblError2.setText("User not found");
				}

			}
		});
		btnCreateTask.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				lblError2.setText("");
				screenMain.setVisible(false);
				screenCreateTask.setVisible(true);

			}
		});
		btnCreateTaskBack.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				lblError3.setText("");
				screenMain.setVisible(true);
				screenCreateTask.setVisible(false);
				textCreateTaskName.setText("");
			}
		});
		btnCreateTaskSubmit.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				if (textCreateTaskName.getText().compareTo("") == 0)
				{
					lblError3.setVisible(true);
					lblError3.setText("No name inputted");
					return;
				}
				try {
					if (createTask(textCreateTaskName.getText()) == 1) {
						lblError3.setText("");
						screenMain.setVisible(true);
						screenCreateTask.setVisible(false);
						textCreateTaskName.setText("");	
					}
					else {
						lblError3.setVisible(true);
						lblError3.setText("Could not create task");
					}
				}
				catch (Exception e)
				{
					lblError3.setVisible(true);
					lblError3.setText("Could not create task");
				}
			}
		});
		btnDeleteTask.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				for (int i = 0; i < tasks.size(); i++) {
					taskList.add(tasks.get(i).name);
				}
				lblError2.setText("");
				screenMain.setVisible(false);
				screenDeleteTask.setVisible(true);

			}
		});
		btnDeleteTaskBack.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				lblError4.setText("");
				screenMain.setVisible(true);
				screenDeleteTask.setVisible(false);
				taskList.removeAll();
			}
		});
		btnDeleteTaskSubmit.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				lblError4.setText("");

				try {
					if (taskList.getSelectionCount() < 1) {
						lblError4.setVisible(true);
						lblError4.setText("No tasks selected");
						return;
					}
					
					deleteTask(taskList.getSelection());
					
					taskList.removeAll();
					screenMain.setVisible(true);
					screenDeleteTask.setVisible(false);
				}
				catch (Exception e)
				{						
					lblError4.setVisible(true);
					lblError4.setText("No tasks selected");
				}
			}
		});
		btnAssignTask.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {				
				lblError5.setText("");
				screenMain.setVisible(false);
				screenAssignTask.setVisible(true);

				for (int i = 0; i < users.size(); i++) {
					listAssignUser.add(users.get(i).firstname + " " + users.get(i).lastname);
				}
				
				for (int i = 0; i < tasks.size(); i++) {
					if (tasks.get(i).assignee.compareTo("") == 0)
						listAssignTask.add(tasks.get(i).name);
				}
				
			}
		});
		btnAssignBack.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				lblError5.setText("");
				screenMain.setVisible(true);
				screenAssignTask.setVisible(false);
				listAssignUser.removeAll();
				listAssignTask.removeAll();
			}
		});
		btnAssignSubmit.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				if (listAssignUser.getSelectionCount() < 1)
					lblError5.setText("No user selected");
				else if (listAssignTask.getSelectionCount() < 1)
					lblError5.setText("No task selected");
				else {
					try {
						assignTask(listAssignUser.getSelection()[0], listAssignTask.getSelection()[0]);
					
						lblError5.setText("");
						screenMain.setVisible(true);
						screenAssignTask.setVisible(false);
						listAssignUser.removeAll();
						listAssignTask.removeAll();
					}
					catch (Exception e) {
						lblError5.setVisible(true);
						lblError5.setText("Could not assign task");
						listAssignUser.removeAll();
						listAssignTask.removeAll();
					}
				}
			}
		});
		btnUnassignTask.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {				
				lblError6.setText("");
				screenMain.setVisible(false);
				screenUnassignTask.setVisible(true);

				for (int i = 0; i < users.size(); i++) {
					listUnassignUser.add(users.get(i).firstname + " " + users.get(i).lastname);
				}
			}
		});
		btnUnassignBack.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				lblError6.setText("");
				screenMain.setVisible(true);
				screenUnassignTask.setVisible(false);
				listUnassignUser.removeAll();
				listUnassignTask.removeAll();
			}
		});
		listUnassignUser.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				String fullname = "";
				
				listUnassignTask.removeAll();
				
				if (listUnassignUser.getSelectionCount() > 0)
					fullname = listUnassignUser.getSelection()[0];
				
				for (int i = 0; i < tasks.size(); i++) {
					if (tasks.get(i).assignee.compareTo(fullname) == 0) {
						listUnassignTask.add(tasks.get(i).name);
					}
				}
			}
		});
		btnUnassignSubmit.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				if (listUnassignUser.getSelectionCount() < 1)
					lblError6.setText("No user selected");
				else if (listUnassignTask.getSelectionCount() < 1)
					lblError6.setText("No task selected");
				else {
					try {
						unassignTask(listUnassignUser.getSelection()[0], listUnassignTask.getSelection()[0]);
					
						lblError6.setText("");
						screenMain.setVisible(true);
						screenUnassignTask.setVisible(false);
						listUnassignUser.removeAll();
						listUnassignTask.removeAll();
					}
					catch (Exception e) {
						lblError6.setVisible(true);
						lblError6.setText("Could not unassign task");
						listUnassignUser.removeAll();
						listUnassignTask.removeAll();
					}
				}
			}
		});
	}
}
