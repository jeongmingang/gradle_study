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

import gradle_study.dao.impl.DepartmentDaoImpl;
import gradle_study.dto.Department;

public class DepartmentUI extends JFrame {

	private JPanel contentPane;
	private JPanel pDept;
	private JPanel pBtn;
	private JPanel pList;
	private JLabel lblDeptNo;
	private JTextField tfDeptNo;
	private JLabel lblDeptName;
	private JTextField tfDeptName;
	private JLabel lblFloor;
	private JTextField tfFloor;
	private JButton btnAdd;
	private JButton btnSub;
	private JButton btnExit;
	private JScrollPane scrollPane;
	private JTable table;

	
	public DepartmentUI() {
		setTitle("부서정보");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 489, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		pDept = new JPanel();
		contentPane.add(pDept);
		pDept.setLayout(new GridLayout(0, 2, 10, 0));

		lblDeptNo = new JLabel("부서번호");
		lblDeptNo.setHorizontalAlignment(SwingConstants.RIGHT);
		pDept.add(lblDeptNo);

		tfDeptNo = new JTextField();
		pDept.add(tfDeptNo);
		tfDeptNo.setColumns(10);

		lblDeptName = new JLabel("부서명");
		lblDeptName.setHorizontalAlignment(SwingConstants.RIGHT);
		pDept.add(lblDeptName);

		tfDeptName = new JTextField();
		tfDeptName.setColumns(10);
		pDept.add(tfDeptName);

		lblFloor = new JLabel("위치");
		lblFloor.setHorizontalAlignment(SwingConstants.RIGHT);
		pDept.add(lblFloor);

		tfFloor = new JTextField();
		tfFloor.setColumns(10);
		pDept.add(tfFloor);

		pBtn = new JPanel();
		contentPane.add(pBtn);

		btnAdd = new JButton("추가");
		btnAdd.addActionListener(insert());
		pBtn.add(btnAdd);

		btnSub = new JButton("삭제");
		btnSub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int deptNo = Integer.parseInt(tfDeptNo.getText().trim());
				Department department = new Department(deptNo);
				DepartmentDaoImpl.getInstance().deleteDepartment(department);
				table.setModel(getModel());
			}
		});
		pBtn.add(btnSub);

		btnExit = new JButton("나가기");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		pBtn.add(btnExit);

		pList = new JPanel();
		contentPane.add(pList);
		pList.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		pList.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(getModel());
		scrollPane.setViewportView(table);
	}

	private ActionListener insert() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int deptNo = Integer.parseInt(tfDeptNo.getText().trim());
				String deptName = tfDeptName.getText().trim();
				int floor = Integer.parseInt(tfFloor.getText().trim());

				Department department = new Department(deptNo, deptName, floor);
				DepartmentDaoImpl.getInstance().insertDepartment(department);
				table.setModel(getModel());
			}

		};
	}

	public DefaultTableModel getModel() {
		return new DefaultTableModel(getDeparmentList(), getColumn());
	}

	public String[] getColumn() {
		return new String[] { "부서정보", "부서명", "위치" };
	}

	public Object[][] getDeparmentList() {
		List<Department> list = DepartmentDaoImpl.getInstance().selectDepartmentByAll();
		Object[][] arr = new Object[list.size()][3];
		for (int i = 0; i<list.size(); i++) {
			arr[i][0] = list.get(i).getDeptNo();
			arr[i][1] = list.get(i).getDeptName();
			arr[i][2] = list.get(i).getFloor();
		}
		return arr;
	}

}
