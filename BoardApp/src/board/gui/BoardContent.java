package board.gui;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import board.model.Notice;
import board.model.NoticeDAO;

public class BoardContent extends Page {
	JTextField t_author;
	JTextField t_title;
	JTextArea area;
	JScrollPane scroll;
	JButton bt;
	JButton bt_list, bt_edit, bt_del;
	NoticeDAO noticeDAO;
	Notice notice;

	public BoardContent(BoardMain boardMain) {
		super(boardMain);

//생성
		t_author = new JTextField();
		t_title = new JTextField();
		area = new JTextArea();
		scroll = new JScrollPane(area);

		bt_list = new JButton("목록으로");
		bt_edit = new JButton("수정");
		bt_del = new JButton("삭제");
		noticeDAO = new NoticeDAO();// DAO생성하자

//스타일
		t_author.setPreferredSize(new Dimension((int) this.getPreferredSize().getWidth() - 10, 25));
		t_title.setPreferredSize(new Dimension((int) this.getPreferredSize().getWidth() - 10, 25));
		scroll.setPreferredSize(new Dimension((int) this.getPreferredSize().getWidth() - 10, 500));
//조립
		add(t_author);
		add(t_title);
		add(scroll);
		add(bt_list);
		add(bt_edit);
		add(bt_del);

		bt_list.addActionListener((e) -> { // 리스트로 돌아갈때!
			boardMain.showPage(Pages.valueOf("BoardList").ordinal());
		});

		bt_edit.addActionListener((e) -> {
			// Notice notice = new Notice(); 이미 가져왔으니 안가져와도된다.
			// 한번 물어보고 수정하자!!!
			if (JOptionPane.showConfirmDialog(BoardContent.this, "수정하실래요?") == JOptionPane.OK_OPTION) {
				edit();
			}
		});
		bt_del.addActionListener((e)->{
			if (JOptionPane.showConfirmDialog(BoardContent.this, "삭제하실래요?") == JOptionPane.OK_OPTION) {
				del();
			}
		});

	}

	public void del() {
		//삭제하고 목록보여주기 
		int result=noticeDAO.delete(notice.getNotice_id());
		if(result==0) {
			JOptionPane.showMessageDialog(this, "삭제실패");
		}else {
			JOptionPane.showMessageDialog(this, "삭제성공");
			BoardList boardList=(BoardList)boardMain.pageList[Pages.valueOf("BoardList").ordinal()];
			boardList.getList();//데이터가져오기
			boardList.table.updateUI();//화면갱신
			boardMain.showPage(Pages.valueOf("BoardList").ordinal());
		}
	}

	public void edit() {
		// DAO를 이용하여 수정작업 수행
		// 작성자, 제목, 내용만 교체
		notice.setAuthor(t_author.getText());// 새론값
		notice.setTitle(t_title.getText());// 새론값
		notice.setContent(area.getText());// 새론값

		int result = noticeDAO.update(notice);
		if (result == 0) {
			JOptionPane.showMessageDialog(this, "수정실패");
		} else {
			JOptionPane.showMessageDialog(this, "수정성공");
		}
	}

	// 컴포넌트에 데이터 채워넣기
	// 이 메서드를 호출하는자는 한건의 데이터를 VO에 담아서 호출하면된다.
	public void setData(Notice notice) {
		this.notice = notice;// 나중에 써먹을것을 대비해서 보관해놓기
		t_author.setText(notice.getAuthor());
		t_title.setText(notice.getTitle());
		area.setText(notice.getContent());
	}

}
