package addressbook;

import java.io.FileInputStream;
import java.util.Scanner;

import javazoom.jl.player.Player;

public class MediaPlayer extends Thread{
	private static Player player;
	private FileInputStream fis;
	String filePath;
	public MediaPlayer(String filePath) {
		this.filePath = filePath;
	}////MediaPlayer()
	
	//음악 시작
	void playMusic() {
		try {
			fis = new FileInputStream(filePath);
			player = new Player(fis);
			player.play();
		}
		catch (Exception e) {System.out.println("음악 재생 중 오류가 발생했습니다.");}
	}////play
	
	//음악 중지
	static void stopMusic() {
		if(player != null) {
			player.close();
			player=null;
		}
	}
	
	@Override
	public void run() {
		playMusic();
	}
	
	//메뉴 분기용
	public static void musicPlayer(int insertmenu) {
		MediaPlayer player = null;
		switch(insertmenu) {
			case 1: 
				player = new MediaPlayer("music/Lawrence.mp3");
				player.setDaemon(true);
				player.start();
				break;
			case 2: stopMusic(); break;
			default : System.out.println("메뉴에 없는 번호입니다.");
		}
	}////musicPlayer
	
}//////MediaPlayerClass
