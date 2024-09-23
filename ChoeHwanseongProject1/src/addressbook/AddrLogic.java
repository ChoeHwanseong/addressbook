package addressbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddrLogic {
	List<Person> person;
	CreatorGreeting cg = new CreatorGreeting();
	
	public AddrLogic() {
		person = new Vector<Person>();
		MediaPlayer.musicPlayer(1); 
		cg.loading();
		loadPerson();
	}
	
	//메인메뉴 출력
	public void mainMenu() {
		System.out.println("　　　　　　　　　　　　　　메인 메뉴　　　　　　　　　　　　　	 ");
		System.out.println("┌───────┬───────┬───────┬───────┬───────┬───────┬───────┬───────┐");
		System.out.println("| 1.등록 	| 2.조회 	| 3.수정 	| 4.삭제 	| 5.검색 	| 6.저장 	| 7.음악 	| 0.종료 	|");
		System.out.println("└───────┴───────┴───────┴───────┴───────┴───────┴───────┴───────┘");
	}/////////MainMenu()

	//수정시 변경메뉴
	public void SubMenu(Person person) {
		System.out.printf("현재 정보: %s%n",person.getInfo());
		System.out.println("　　           　　변경할 정보를 선택해주세요.");
		System.out.println("┌───────────┬───────────┬───────────┬───────────┬───────────┐");
		System.out.println("|   1.이름   |   2.나이 	|   3.주소   |  4.연락처  	|  0.메인메뉴 |");
		System.out.println("└───────────┴───────────┴───────────┴───────────┴───────────┘");
	}/////SubMenu()
	
	//메뉴 선택용 메소드
	public int getMenu() {
		Scanner sc = new Scanner(System.in);
		String putMenu = sc.nextLine().trim();
		int menu = -99;
		try {
			menu = Integer.parseInt(putMenu);		
		}
		catch (NumberFormatException e) {System.out.println("메뉴는 숫자만 입력해주세요.");}
		return menu;
	}/////////getMenu()

	//메인메뉴 분기
	public void devideByMenuNumber(int insertMenu) {
		switch(insertMenu) {
			case 1:	registerToAddrBook(); break;
			case 2:	printAddrBook(); 	  break;
			case 3: updateAddrBook();     break;
			case 4: deleteAddrBook();     break;
			case 5: Person findperson = findPerson("검색");
				if(findperson!=null) findperson.printInfo();
				break;
			case 6: saveAddrBook(); 	  break;
			case 7:
				System.out.println("┌─────음악 플레이어────┐");
				System.out.println("|  1.재생  |  2.중지  |");
				System.out.println("└─────────┴─────────┘");
				MediaPlayer.musicPlayer(getMenu()); 
				break;
			case 0:	cg.ending();		  break;
			default : System.out.println("메뉴에 없는 번호입니다.");
		}//switch절
	}/////devideByMenuNumber()

	//등록용 메소드
	private void registerToAddrBook() {
		Scanner sc = new Scanner(System.in);
		System.out.println("이름을 입력해 주세요.");
		String name = sc.nextLine().trim();
		System.out.println("나이를 입력해 주세요.(사람이 아닌 경우 0을 입력해주세요.)");
		int age = -1;
		while(true) {
			try {
				age = Integer.parseInt(sc.nextLine().trim()); break;
			}
			catch(NumberFormatException e) {System.out.println("나이는 숫자만 입력해주세요.");}
		}
		System.out.println("주소를 입력해 주세요.");
		String address = sc.nextLine().trim();
		String contact;
		while(true) {
			System.out.println("연락처를 다음과 같이 입력해 주세요.(형식: 000-0000-0000)");
			String regex = "^\\d{1,}-\\d{1,}-\\d{1,}$";
			Pattern pattern = Pattern.compile(regex);
			contact = sc.nextLine().trim();
			Matcher matcher = pattern.matcher(contact);
			if(matcher.matches()) break;
		}///while
		person.add(new Person(name,age,address,contact));
	}/////registerToAddrBook
	
	//조회용 메소드
	private void printAddrBook() {
		StringBuffer giyeok	   = new StringBuffer("[ㄱ으로 시작하는 명단]\r\n");
		StringBuffer nieun	   = new StringBuffer("[ㄴ으로 시작하는 명단]\r\n");
		StringBuffer digeut	   = new StringBuffer("[ㄷ으로 시작하는 명단]\r\n");
		StringBuffer rieul	   = new StringBuffer("[ㄹ로 시작하는 명단]\r\n");
		StringBuffer mieum	   = new StringBuffer("[ㅁ으로 시작하는 명단]\r\n");
		StringBuffer bieup	   = new StringBuffer("[ㅂ으로 시작하는 명단]\r\n");
		StringBuffer siot	   = new StringBuffer("[ㅅ으로 시작하는 명단]\r\n");
		StringBuffer ieung	   = new StringBuffer("[ㅇ으로 시작하는 명단]\r\n");
		StringBuffer jieut	   = new StringBuffer("[ㅈ으로 시작하는 명단]\r\n");
		StringBuffer chieut	   = new StringBuffer("[ㅊ으로 시작하는 명단]\r\n");
		StringBuffer kieuk	   = new StringBuffer("[ㅋ으로 시작하는 명단]\r\n");
		StringBuffer tieut	   = new StringBuffer("[ㅌ으로 시작하는 명단]\r\n");
		StringBuffer pieup	   = new StringBuffer("[ㅍ으로 시작하는 명단]\r\n");
		StringBuffer hieut	   = new StringBuffer("[ㅎ으로 시작하는 명단]\r\n");
		StringBuffer anonymous = new StringBuffer("[분류되지 않은 명단]\r\n");
		if(person != null) {
			for(Person p : person) {
				if(p.getName().charAt(0)>='가'&&p.getName().charAt(0)<'나')
					giyeok.append(p.getInfo()+"\r\n");
				else if (p.getName().charAt(0)>='나'&&p.getName().charAt(0)<'다')
					nieun.append(p.getInfo()+"\r\n");
				else if (p.getName().charAt(0)>='다'&&p.getName().charAt(0)<'라')
					digeut.append(p.getInfo()+"\r\n");
				else if (p.getName().charAt(0)>='라'&&p.getName().charAt(0)<'마')
					rieul.append(p.getInfo()+"\r\n");
				else if (p.getName().charAt(0)>='마'&&p.getName().charAt(0)<'바')
					mieum.append(p.getInfo()+"\r\n");
				else if (p.getName().charAt(0)>='바'&&p.getName().charAt(0)<'사')
					bieup.append(p.getInfo()+"\r\n");
				else if (p.getName().charAt(0)>='사'&&p.getName().charAt(0)<'아')
					siot.append(p.getInfo()+"\r\n");
				else if (p.getName().charAt(0)>='아'&&p.getName().charAt(0)<'자')
					ieung.append(p.getInfo()+"\r\n");
				else if (p.getName().charAt(0)>='자'&&p.getName().charAt(0)<'차')
					jieut.append(p.getInfo()+"\r\n");
				else if (p.getName().charAt(0)>='차'&&p.getName().charAt(0)<'카')
					chieut.append(p.getInfo()+"\r\n");
				else if (p.getName().charAt(0)>='카'&&p.getName().charAt(0)<'타')
					kieuk.append(p.getInfo()+"\r\n");
				else if (p.getName().charAt(0)>='타'&&p.getName().charAt(0)<'파')
					tieut.append(p.getInfo()+"\r\n");
				else if (p.getName().charAt(0)>='파'&&p.getName().charAt(0)<'하')
					pieup.append(p.getInfo()+"\r\n");
				else if (p.getName().charAt(0)>='하'&&p.getName().charAt(0)<='힣')
					hieut.append(p.getInfo()+"\r\n");
				else anonymous.append(p.getInfo()+"\r\n");
			}//for
			if(giyeok.length()>15)	  System.out.println(giyeok);
			if(nieun.length()>15) 	  System.out.println(nieun);
			if(digeut.length()>15)	  System.out.println(digeut);
			if(rieul.length()>14) 	  System.out.println(rieul);
			if(mieum.length()>15)	  System.out.println(mieum);
			if(bieup.length()>15)	  System.out.println(bieup);
			if(siot.length()>15) 	  System.out.println(siot);
			if(ieung.length()>15)	  System.out.println(ieung);
			if(jieut.length()>15) 	  System.out.println(jieut);
			if(chieut.length()>15)	  System.out.println(chieut);
			if(kieuk.length()>15) 	  System.out.println(kieuk);
			if(tieut.length()>15)	  System.out.println(tieut);
			if(pieup.length()>15) 	  System.out.println(pieup);
			if(hieut.length()>15) 	  System.out.println(hieut);
			if(anonymous.length()>14) System.out.println(anonymous);
		}//if
		else System.out.println("명단이 없습니다.");
	}/////printAddrBook
	
	//검색용 메소드
	private Person findPerson(String message) {
		System.out.println(message+"할 사람의 이름을 입력해주세요.");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine().trim();
		List<Person> dup = new Vector<Person>();
		for(Person p : person) {
			if(p.name.equalsIgnoreCase(name)) dup.add(p);
		}//for
		if(dup.size()>1) System.out.println("동명이인이 존재합니다. 주의해주세요.");
		for(Person p : person) {
			if(p.name.equalsIgnoreCase(name))
				return p;
		}//for
		System.out.println(name+"으로 검색된 정보가 없습니다.");
		return null;
	}///findPerson()
	
	//수정용 메소드
	private void updateAddrBook() {
		Person findPerson = findPerson("수정");
		if(findPerson!=null) {
			SubMenu(findPerson);
			Scanner sc = new Scanner(System.in);
			int insertMenu = getMenu();
			switch(insertMenu) {
			case 1:
				System.out.println("변경할 이름을 입력해주세요.");
				findPerson.name = sc.nextLine().trim();
				break;
			case 2:
				System.out.println("변경할 나이를 입력해 주세요.");
				while(true) {
					try {
						findPerson.age = Integer.parseInt(sc.nextLine().trim()); break;
					}
					catch(NumberFormatException e) {System.out.println("나이는 숫자만 입력해주세요.");}
				}
				break;
				
			case 3: 
				System.out.println("변경할 주소를 입력해 주세요.");
				findPerson.address = sc.nextLine().trim();
				break;
			case 4:
				String contact;
				while(true) {
					System.out.println("변경할 연락처를 다음과 같이 입력해 주세요.(형식: 000-0000-0000)");
					String regex = "^\\d{1,}-\\d{1,}-\\d{1,}$";
					Pattern pattern = Pattern.compile(regex);
					contact = sc.nextLine().trim();
					Matcher matcher = pattern.matcher(contact);
					if(matcher.matches()) break;
				}///while
				findPerson.contact = contact;
				break;
			case 0: break;
			default : System.out.println("메뉴에 없는 번호입니다.");
			}//switch
		}//if
	}/////updateAddrBook()
	
	//삭제용 메소드
	private void deleteAddrBook() {
		Person findPerson = findPerson("삭제");
		Scanner sc = new Scanner(System.in);
		if(findPerson!=null) {
			System.out.println("=============현재 연락처 정보================");
			findPerson.printInfo();
			System.out.println("===========================================");
			System.out.println("┌───정말 삭제하시겠습니까?───┐");
			System.out.println("|   1.삭제   |  0.메인메뉴 	|");
			System.out.println("└───────────────────────┘");
			int insertMenu = getMenu();
			switch(insertMenu) {
			case 1:
				for(Person p:person) {
					if(findPerson.equals(p)) {
						person.remove(p);
						break;
					}//if
				}//for
			case 0: break;
			default : System.out.println("메뉴에 없는 번호입니다.");
			}
		}//if
	}/////deleteAddrBook()
	
	//저장용
	private void saveAddrBook() {
		if(person.isEmpty()) {
			System.out.println("저장할 명단이 없습니다.");
		}
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("src/addressbook/AddressBook.dat"));
			oos.writeObject(person);
			System.out.println("저장이 완료되었습니다.");
		}//try
		catch (IOException e) {System.out.println("저장 중 오류가 발생했습니다."+e.getMessage());}
		finally {
			try {
				if(oos != null) oos.close();
			}//try
			catch(Exception e) {}
		}//finally
	}/////saveAddrBook()
	
	//로딩용 메소드 
	private void loadPerson() {
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream("src/addressbook/AddressBook.dat"));
			person = (List<Person>)ois.readObject();
		}//try
		catch(Exception e) {}
		finally {
			try {
				if(ois != null) ois.close();
			}//try
			catch(Exception e) {}
		}
	}/////loadPerson
	
}/////////AddrLogic_class
