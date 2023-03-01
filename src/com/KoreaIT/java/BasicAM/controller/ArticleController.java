package com.KoreaIT.java.BasicAM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.BasicAM.container.Container;
import com.KoreaIT.java.BasicAM.dto.Article;
import com.KoreaIT.java.BasicAM.dto.Member;
import com.KoreaIT.java.BasicAM.dto.Reply;
import com.KoreaIT.java.BasicAM.util.Util;

public class ArticleController extends Controller {
	private List<Article> articles;
	private List<Reply> replies = new ArrayList<>();
	private Scanner sc;
	private String command;
	private String actionMethodName;
	private int lastArticleId;
	private int lastReplyId;

	public ArticleController(Scanner sc) {
		this.articles = Container.articleService.getArticles();
		this.lastArticleId = 0;
		this.lastReplyId = 0;
		this.sc = sc;
	}

	public void doAction(String command, String actionMethodName) {
		this.command = command;
		this.actionMethodName = actionMethodName;

		switch (actionMethodName) {
		case "list":
			showList();
			break;
		case "write":
			doWrite();
			break;
		case "detail":
			showDetail();
			break;
		case "modify":
			doModify();
			break;
		case "delete":
			doDelete();
			break;
		default:
			System.out.println("존재하지 않는 명령어 입니다.");
			break;
		}
	}

	private void showList() {
		if (articles.size() == 0) {
			System.out.println("게시글이 없습니다");
			return;
		}
		System.out.println("원하는 게시물의 제목을 검색하세요");
		System.out.println("게시글 모두 보기 : Enter Click");
		System.out.printf("검색 ) ");
		String search = sc.nextLine().trim();

		System.out.println("+------+---------------+--------+------------+-----------------------+");
		System.out.println("| 번호 |   제     목   |  조회  |  작 성 자  |   게     시     일    |");
		System.out.println("+------+---------------+--------+------------+-----------------------+");
		String tempTitle = null;
		Article article = null;
		String writeName = null;
		
		for (int i = articles.size() - 1; i >= 0; i--) {
			article = articles.get(i);
			writeName = Container.articleService.foundNameInMember(article);
			if (writeName == null) {
				article.title = "X";
				article.hit = 0;
				writeName = "X";
				Container.articleService.remove(article);
			}
			if (article.title.contains(search)) {
				if (article.title.length() > 10) {
					tempTitle = article.title.substring(0, 7) + "...";
					System.out.printf("|  %2d  |   %10s  |  %3d   |  %8s  |  %s  |\n", article.id, tempTitle, article.hit,
							writeName, article.regDate);
					continue;
				}
				System.out.printf("|  %2d  |   %10s  |  %3d   |  %8s  |  %s  |\n", article.id, article.title, article.hit,
						writeName, article.regDate);
			}
		}
		System.out.println("+------+---------------+--------+------------+-----------------------+");
	}

	private void doWrite() {
		int id = lastArticleId + 1;
		String title = null;
		String body = null;

		while (true) {
			System.out.printf("제목 : ");
			title = sc.nextLine().trim();
			if (title.equals("")) {
				System.out.println("제목을 입력하세요.");
				continue;
			}
			if (title.length() > 42) {
				System.out.println("제목 최대길이는 42입니다.");
				continue;
			}
			break;
		}
		while (true) {
			System.out.printf("내용 : ");
			body = sc.nextLine().trim();
			if (body.equals("")) {
				System.out.println("내용을 입력하세요.");
				continue;
			}
			break;
		}

		Container.articleService
				.add(new Article(id, loginedMember.id, title, body, Util.getNowDateStr(), Util.getNowDateStr()));

		System.out.printf("%d번 글이 생성 되었습니다\n", id);
		lastArticleId++;
	}

	private void showDetail() {
		Article foundArticle = null;

		foundArticle = Container.articleService.getArticle(command, foundArticle);

		if (foundArticle != null) {
			String writeName = Container.articleService.foundNameInMember(foundArticle);
			System.out.println("+--------+--------------------------------------------+--------+-----+");
			System.out.printf("| 제  목 | %-42s | 조회수 | %-4d|\n", foundArticle.title, foundArticle.hit++ + 1);
			System.out.println("+--------+--------------------------------------------+--------+-----+");
			System.out.printf("| 작성자 | %-58s|\n", writeName);
			if (foundArticle.updateDate != null) {
				System.out.println("+--------+----------------------------+--------+---------------------+");
				System.out.printf("| 작성일 | %-27s| 수정일 | %-18s |\n", foundArticle.regDate, foundArticle.updateDate);
				System.out.println("+--------+----------------------------+--------+---------------------+");
			} else {
				System.out.println("+--------+-----------------------------------------------------------+");
				System.out.printf("| 작성일 | %-58s|\n", foundArticle.regDate);
				System.out.println("+--------+-----------------------------------------------------------+");
			}
			if (foundArticle.body.length() >= 58) {
				System.out.printf("| 내  용 | %-58s|\n", foundArticle.body.substring(0, 57));

				for (int i = 57; i < foundArticle.body.length(); i = i + 57) {
					if (foundArticle.body.length() >= i + 57) {
						System.out.printf("|        | %-58s|\n", foundArticle.body.substring(i, i + 57));
					} else {
						System.out.printf("|        | %-58s|\n", foundArticle.body.substring(i));
					}
				}
			} else {
				System.out.printf("| 내  용 | %-58s|\n", foundArticle.body);
			}
			if (!(isLogined())) {
				System.out.println("+--------+-----------------------------------------------------------+");
				System.out.printf("| 댓  글 |                 로 그 인  후  이 용 가 능                 |\n");
				System.out.println("+--------+-----------------------------------------------------------+");
				return;
			}

			System.out.println("+--------------------------------------------------------------------+");
			System.out.printf("|        댓 글 쓰 기 : reply write  |  나 가 기 : exit               |\n");

			Reply re = null;
			String replyWriteName = null;
			
			for (Reply reply : replies) {
				if (reply.articleId == foundArticle.id) {
					re = reply;
					break;
				}
			}
			
			List<Member> members = Container.memberDao.members;
			
			if (re != null) {
				for(int k = 0; k < replies.size(); k++) {
					for (Reply reply : replies) {
						if (reply.id == k) {
							re = replies.get(k);
							break;
						}
					}
					
					for(Member member : members) {
						if(member.id == re.memberId) {
							replyWriteName = member.name;
							break;
						}
					}
					if (re.replyBody.length() >= 57) {
						System.out.printf("| %-8s| %-56s |\n", replyWriteName, re.replyBody.substring(0, 56));
						for (int j = 56; j < re.replyBody.length(); j = j + 56) {
							if (re.replyBody.length() >= j + 56) {
								System.out.printf("|         | %-57s|\n", re.replyBody.substring(j, j + 56));
							} else {
								System.out.printf("|         | %-57s|\n", re.replyBody.substring(j));
							}
						}
					} else {
						System.out.printf("| %-8s| %-57s|\n", replyWriteName, re.replyBody);
					}
				}
			}

			System.out.println("+--------------------------------------------------------------------+");

			System.out.printf("댓글 작성 여부 : ");
			String replyWrite = sc.nextLine().trim();
			if (replyWrite.equals("reply write")) {
				System.out.printf("댓글 : ");
				String replyBody = sc.nextLine();
				replies.add(new Reply(lastReplyId++ + 1, loginedMember.id, foundArticle.id, replyBody, Util.getNowDateStr(),
						Util.getNowDateStr()));
			}else if(replyWrite.equals("exit")) {
				System.out.println("게시글 나가기");
			}else {
				System.out.println("\"reply write\" 또는 \"exit\"를 입력해주세요.");
			}
		}
	}

	private void doModify() {
		Article foundArticle = null;
		foundArticle = Container.articleService.getArticle(command, foundArticle);

		if (foundArticle != null && foundArticle.memberId != loginedMember.id) {
			System.out.println("해당 글에 권한이 없습니다.");
			return;
		}

		if (foundArticle != null) {
			System.out.printf("%d번 게시물을 수정합니다.\n", foundArticle.id);
			while (true) {
				System.out.printf("제목 : ");
				foundArticle.title = sc.nextLine().trim();
				if (foundArticle.title.equals("")) {
					System.out.println("제목을 입력하세요.");
					continue;
				}
				break;
			}
			while (true) {
				System.out.printf("내용 : ");
				foundArticle.body = sc.nextLine().trim();
				if (foundArticle.body.equals("")) {
					System.out.println("내용을 입력하세요.");
					continue;
				}
				break;
			}
			foundArticle.updateDate = Util.getNowDateStr();
			System.out.println("글이 수정 되었습니다.");
		}
	}

	private void doDelete() {
		Article foundArticle = null;
		foundArticle = Container.articleService.getArticle(command, foundArticle);

		if (foundArticle != null && foundArticle.memberId != loginedMember.id) {
			System.out.println("해당 글에 권한이 없습니다.");
			return;
		}

		if (foundArticle != null) {
			System.out.printf("%d번 게시물이 삭제 되었습니다.\n", foundArticle.id);
			Container.articleService.remove(foundArticle);
		}
	}

	public void makeTestDate() {
		System.out.println("테스트를 위한 글 데이터를 생성합니다");
		for (int i = 1; i <= maxTestId; i++) {
			Container.articleService.add(new Article(lastArticleId++ + 1, i * 11, i, "t" + i * i * i * i * i * i * i * i * i,
					"test" + i, Util.getNowDateStr(), null));
		}
	}
}
