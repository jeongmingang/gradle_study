package gradle_study.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import gradle_study.dao.impl.TitleDaoImpl;
import gradle_study.dto.Title;

public class TitleUI extends JFrame {

	private JPanel contentPane;
	private JTextField tftno;
	private JTextField tftname;
	private JTable table;

	public TitleUI() {
		setTitle("직책정보");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel pTitle = new JPanel();
		contentPane.add(pTitle);
		pTitle.setLayout(new GridLayout(0, 2, 10, 0));
		
		JLabel lbltno = new JLabel("직책번호");
		lbltno.setHorizontalAlignment(SwingConstants.RIGHT);
		pTitle.add(lbltno);
		
		tftno = new JTextField();
		pTitle.add(tftno);
		tftno.setColumns(10);
		
		JLabel lbltname = new JLabel("직책명");
		lbltname.setHorizontalAlignment(SwingConstants.RIGHT);
		pTitle.add(lbltname);
		
		tftname = new JTextField();
		tftname.setColumns(10);
		pTitle.add(tftname);
		
		JPanel pBtn = new JPanel();
		contentPane.add(pBtn);
		
		JButton btnAdd = new JButton("추가");
		btnAdd.addActionListener(insert());
		pBtn.add(btnAdd);
		
		JButton btnSub = new JButton("삭제");
		btnSub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int titleNo = Integer.parseInt(tftno.getText().trim());
				Title title = new Title(titleNo);
				TitleDaoImpl.getInstance().deleteTitle(title);
				table.setModel(getModel());
			}
		});
		pBtn.add(btnSub);
		
		JButton btnExit = new JButton("나가기");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		pBtn.add(btnExit);
		
		JPanel pList = new JPanel();
		contentPane.add(pList);
		pList.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		pList.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(getModel());
		scrollPane.setViewportView(table);
	}

	public ActionListener insert() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int tno = Integer.parseInt(tftno.getText().trim());
				String tname = tftname.getText().trim();

				Title title = new Title(tno, tname);
				TitleDaoImpl.getInstance().insertTitle(title);
				table.setModel(getModel());
			}

		};
	}

	public DefaultTableModel getModel() {
		return new DefaultTableModel(getTitleList(), getColumn());
	}

	public String[] getColumn() {
		return new String[] { "직책번호", "직책명"};
	}

	public Object[][] getTitleList() {
		List<Title> list = TitleDaoImpl.getInstance().selectTitleByAll();
		Object[][] arr = new Object[list.size()][2];
		for (int i = 0; i<list.size(); i++) {
			arr[i][0] = list.get(i).gettNo();
			arr[i][1] = list.get(i).gettName();
		}
		return arr;
	}

}
