package jdbc.addr;

import java.util.ArrayList;
import java.util.Scanner;

public class Service {
	private Dao dao;
	
	public Service() {
		dao = new Dao();
	}
	
	// 1명의 이름, 전화, 주소 입력받아서 db 에 저장
	public void addAddr(Scanner sc) {
		System.out.println("=== 등록 ===");
		
		System.out.print("name : ");
		String name = sc.next();
		System.out.print("tel : ");
		String tel = sc.next();
		System.out.print("addr : ");
		sc.nextLine(); //버퍼에 남은 엔터 삭제
		String addr = sc.nextLine(); // 공백을 포함한 한 줄 입력
		
		dao.insert(new Vo(0, name, tel, addr));
		
	}
	
	// 수정할 사람 번호와 새 전화, 주소를 입력받아 db에서 수정
	public void editAddr(Scanner sc) {
		System.out.println("=== 수정 ===");
		System.out.print("수정할 num : ");
		int num = sc.nextInt();
		
		System.out.print("new tel : ");
		String tel = sc.next();
		System.out.print("new addr : ");
		sc.nextLine();
		String addr = sc.nextLine();
		
		int cnt = dao.update(new Vo(num, "", tel, addr));
		if(cnt > 0) {
			System.out.println("수정 완료");
		} else {
			System.out.println("수정 실패");
		}
	}
	
	// 삭제할 사람 번호를 입력받아 db 에서 삭제
	public void delAddr(Scanner sc) {
		System.out.println("=== 삭제 ===");
		System.out.print("삭제할 num : ");
		int num = sc.nextInt();
		int cnt = dao.delete(num);
		if(cnt > 0) {
			System.out.println("삭제 완료");
		} else {
			System.out.println("삭제 실패");
		}
	}
	
	// 검색할 사람 번호를 입력받아 db 에서 검색하고, null 이 아니면 출력
	public void printAddr(Scanner sc) {
		System.out.println("=== 번호로 검색 ===");
		System.out.print("검색할 num : ");
		int num = sc.nextInt();
		Vo v = dao.select(num);
		if(v == null) {
			System.out.println("없는 번호");
		} else {
			System.out.println(v);
		}
	}
	
	// 전체 주소 목록 출력
	public void printAll() {
		ArrayList<Vo> list = dao.selectAll();
		System.out.println("전체 목록");
		for(Vo v : list) {
			System.out.println(v);
		}
	}
	
	// 이름으로 검색. 검색할 사람 이름 입력 받아서 db애서 이름으로 검색한 결과 출력
	public void printByName(Scanner sc) {
		System.out.println("=== 이름으로 검색 ===");
		System.out.print("검색할 name : ");
		String name = sc.next();
		
		ArrayList<Vo> list = dao.selectByName(name);
		System.out.println("=== 검색 결과 ===");
		for(Vo v : list) {
			System.out.println(v);
		}
		
	}
	
	// 검색할 사람 전화 입력 받아서 db 에서 전화로 검색하고 결과를 출력
	public void printByTel(Scanner sc) {
		System.out.println("=== 전화번호로 검색 ===");
		System.out.print("검색할 tel : ");
		String tel = sc.next();
		
		ArrayList<Vo> list = dao.selectByTel(tel);
		System.out.println("=== 검색 결과 ===");
		for(Vo v : list) {
			System.out.println(v);
		}
	}
}
