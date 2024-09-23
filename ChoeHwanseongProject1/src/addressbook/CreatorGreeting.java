package addressbook;

public class CreatorGreeting extends Thread{
	//시작시 안내문
	static void loading() {
		try {
			System.out.println("                  안녕하세요. 제작자입니다.");
			sleep(1000);
			System.out.println("              본 프로그램은 노래가 삽입되어있습니다.");
			sleep(1000);
			System.out.println("         프로그램을 종료하시기 전에 저장했는지 확인하여 주세요.");
			sleep(1000);
			System.out.println("         저장된 파일을 받아오고 있습니다. 잠시만 기다려주세요.");
			sleep(1000);
		} 
		catch (InterruptedException e) {e.printStackTrace();}
	}///loading
	
	//종료시 안내문
	static void ending() {
		try {
			System.out.println("                    잠깐! 저장은 하셨나요?");
			sleep(1000);
			System.out.println("                    곧 프로그램이 종료됩니다.");
			sleep(1000);
			System.out.println("             개선할 사항이 있다면 연락주시면 감사드립니다. ");
			sleep(1000);
			System.out.println("                    이용해 주셔서 감사합니다.");
			sleep(1000);
			System.exit(0);
			
		} 
		catch (InterruptedException e) {e.printStackTrace();}
	}////ending

}
