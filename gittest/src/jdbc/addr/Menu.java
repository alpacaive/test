package jdbc.addr;

import java.util.Scanner;

public class Menu {
	private Service service;
	
	public Menu() {
		service = new Service();
	}
	
	public void run(Scanner sc) {
		boolean flag = true;
		int menu = 0;
		
		while(flag) {
			System.out.println("1.등록 2.번호로검색 3.이름으로검색 4.전화로검색 5.수정 6.삭제 7.전체목록 8.종료");
			System.out.print("메뉴 : ");
			menu = sc.nextInt();
			
			switch(menu) {
			case 1:
				service.addAddr(sc);
				break;
			case 2:
				service.printAddr(sc);
				break;
			case 3:
				service.printByName(sc);
				break;
			case 4:
				service.printByTel(sc);
				break;
			case 5:
				service.editAddr(sc);
				break;
			case 6:
				service.delAddr(sc);
				break;
			case 7:
				service.printAll();
				break;
			case 8:
				flag = false;
				break;
			}
		}
	}
}
