package board.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import board.model.Notice;
import board.model.NoticeDAO;

//게시판 목록페이지
public class BoardList extends Page {
	JTable table;
	JScrollPane scroll;
	JButton bt; // 글쓰기폼이동버튼
	BoardModel model;
	NoticeDAO noticeDAO;
	ArrayList<Notice>boardList;
	public BoardList(BoardMain boardMain) {
		super(boardMain);
		// 생성
		table = new JTable(model = new BoardModel());// jtable과 모델객체와 연결
		scroll = new JScrollPane(table);
		bt = new JButton("글등록");
		noticeDAO = new NoticeDAO();

		// System.out.println(boardMain.getWidth());
		// 스타일
		scroll.setPreferredSize(new Dimension((int) this.getPreferredSize().getWidth(), 600));
		this.setBackground(Color.GREEN);
		// 조립
		add(scroll);
		add(bt);

		getList();
		table.updateUI();

		bt.addActionListener((e) -> {
			boardMain.showPage(Pages.valueOf("BoardWrite").ordinal());
			// 보드라이트 보여줘라
		});

		table.addMouseListener(new MouseAdapter() {

			public void mouseReleased(MouseEvent e) {
				//상세보기로 가기전에 notice_id를 추출하자 
				int col=0;// 프라이머리키가있는곳 
				int row=table.getSelectedRow();
				Notice notice=boardList.get(row);
				BoardContent boardContent = (BoardContent)boardMain.pageList[Pages.valueOf("BoardContent").ordinal()];
				boardContent.setData(notice);
				boardMain.showPage(Pages.valueOf("BoardContent").ordinal());//화면전환
				
//				System.out.println("당신이 선택학 게시문의 notice_id는? "+notice_id);
				
			}
		});
	}

	// DAO를 이용해서 데이터 가져오기!
	public void getList() {
		this.boardList=noticeDAO.selectAll();
		model.list = boardList;
	}
}
