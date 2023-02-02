package com.KoreaIT.java.BasicAM;

//날짜 시간
import java.util.Date;
import java.text.SimpleDateFormat;

//어레이 리스트
import java.util.ArrayList;
import java.util.List;

//입력
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);

		int lastArticleId = 0;

		List<Article> articles = new ArrayList<>();

		while (true) {
			System.out.printf("명령어 ) ");
			String command = sc.nextLine().trim(); // trum() -> 양쪽 끝 공백을 없앰

			if (command.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}

			if (command.equals("system exit")) {
				break;
			}

			// 1. 게시글 확인
			if (command.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시글이 없습니다");
					continue;
				}

				System.out.println("번호 |  제목");
				String tempTitle = null;

				for (int i = articles.size() - 1; i >= 0; i--) {
					Article Get = articles.get(i);
					if (Get.title.length() > 4) {
						tempTitle = Get.title.substring(0, 5);
						System.out.printf("  %d  |  %s...\n", Get.id, tempTitle);
						continue;
					}
					System.out.printf("  %d  |  %s\n", Get.id, Get.title);

				}
			}

			// 2. 글 조회
			// 명령어 입력시에 번호가 없을 때
			else if (command.equals("article detail")) {
				System.out.println("명령어 뒤에 정수를 추가하세요. -> article detail \"int\"");
			}

			// 제대로 입력시 실행
			// startsWith(String) 은 boolean형임, 입력한 문자열과 (String)안의
			// 문자열이 같으면 true를 리턴, 다르면 false 리턴
			// command문자열의 시작 부분이 "article detail "인지 검사하고, boolean값을 반환할때 true이면 실행
			else if (command.startsWith("article detail ")) {

				String[] ID = command.split("article detail ");
				// split을 사용하여 command에 저장되어 있는 문자열에서 "article deatil "을 기준으로 자르고
				// "article detail "은 ID[0], 그 뒤에 있는 문자열은 ID[1]에 저장

				String checkId = command.substring(15);
				// command에 저장되어있 는 문자열을 15번째까지 자르고 남은 문자열 저장
				// "article detail "을 자르고 남은 문자열을 저장

				boolean isID = true; // 남아있는 문자열이 숫자면 true, 문자면 false, 기본값 true

				// 오류 검사 -> 잘못 입력했을때 오류가 실행되지 않기위해 사용
				for (int i = 0; i < checkId.length(); i++) { // checkId의 길이까지 검사
					int check = (int) checkId.charAt(i); // checkID의 문자열을 문자 하나씩 검사하기 위해 정수로 형변환
					if (!(check >= 48 && check <= 57)) { // 변환된 문자의 아스키코드값이 48~57(0~9)사이가 아닐때
						isID = false; // isID에 false를 저장하고
						break; // 종료
					}
				}
				if (isID == true) { // 문자열이 숫자일때 실행
					Article Id; // Article타입의 Id변수를 선언
					int StoInt = Integer.parseInt(ID[1]); // parseInt -> 문자열을 기본 정수타입으로 변환 후 반환
					// 해당 번호의 게시글이 있는 경우
					if (articles.size() > 0 || articles.size() == 0) { // arrayList의 크기가 0 초과 or 0 일때 실행
						// 해당 번호의 게시글이 없는 경우
						if (articles.size() < StoInt) {
							System.out.printf("%s번 게시물은 존재하지 않습니다.\n", ID[1]);
							continue;
						}

						// 해당 번호의 게시글이 있는 경우
						Id = articles.get(StoInt - 1); // Id변수에 arrayList의 인덱스 -1의 객체를 연결한다.
						// System.out.printf("%d\n", Id.id);

						// 해당 게시물 출력
						System.out.printf("번호 : %d\n날짜 : %s\n", Id.id, Id.DateTimelog);
						System.out.printf("제목 : %s\n내용 : %s\n", Id.title, Id.body);
						continue;
					}
				}
				// 숫자가 아닌 문자가 있을경우
				System.out.println("명령어 뒤에 문자가 있으면 안됩니다. -> article detail \"int\"");

			}

			// 3. 글 생성
			else if (command.equals("article write")) {
				int id = lastArticleId + 1;
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				// 글 생성 시 날짜, 시간 저장
				// 날짜 시간 저장 Date 객체 활용 -> "년-월-일 시:분:초" 형태로 출력
				SimpleDateFormat Sformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date now = new Date(); // 현재 시간을 저장하기위한 Date 객체 생성
				String D_T_log = Sformat.format(now); // 인자로 사용하기위해 D_T_log변수에 현재 날짜, 시간 저장

				Article article = new Article(id, title, body, D_T_log);
				articles.add(article);

				System.out.printf("%d번 글이 생성 되었습니다\n", id);
				lastArticleId++;
			}

			// 글 삭제
			else if (command.startsWith("article delete ")) {
				String[] check = command.split("article delete ");
				String cut = command.substring(15);
				boolean checkId = true;

				for (int i = 0; i < cut.length(); i++) {
					int cId = (int) cut.charAt(i);
					if (!(cId >= 48 && cId <= 57)) {
						checkId = false;
						break;
					}
				}
				if (checkId == true) {
					int StoInt = Integer.parseInt(check[1]);
					if (articles.size() > 0 || articles.size() == 0) {
						if (articles.size() < StoInt) {
							System.out.printf("%s번 게시물은 존재하지 않습니다.\n", check[1]);
							continue;
						}
						
						//글이 삭제되면 번호 바뀜
						Article save;
						for (int j = 0; j < articles.size(); j++) {
							save = articles.get(j);
							if (save.id > StoInt - 1) {
								save.id--;
							}
						}
						
						lastArticleId--;
						System.out.printf("%d번 게시물이 삭제 되었습니다.\n", StoInt);
						articles.remove(StoInt - 1);
						continue;
					}
				}
				// 숫자가 아닌 문자가 있을경우
				System.out.println("명령어 뒤에 문자가 있으면 안됩니다. -> article delete \"int\"");
			}

			else {
				System.out.println("존재하지 않는 명령어입니다");
			}
		}

		System.out.println("==프로그램 끝==");

		sc.close();
	}
}

class Article {
	int id; // 번호
	String title; // 제목
	String body; // 내용
	String DateTimelog; // 날짜 시간 저장

	Article(int id, String title, String body, String DateTimelog) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.DateTimelog = DateTimelog;
	}
}