package addressbook;

public class AddrApp{

	public static void main(String[] args) {

		AddrLogic logic = new AddrLogic();
		while(true) {
			logic.mainMenu();
			int insertMenu = logic.getMenu();
			logic.devideByMenuNumber(insertMenu);
		}/////while
		
	}/////main

}/////class
