package PhamMinhHien;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class GiaoDien {

	private JFrame frame;
	private JTextField product_id;
	private JTextField color;
	private JTextField product_total;
	private JTextField product_name;
	private JTextField max_speed;
	private JTextField product_price;
	private JTable table;
	private Car car = new Car();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GiaoDien window = new GiaoDien();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GiaoDien() {
		initialize();
		//DocFile.ghiFile(null);
		LoadTable();
	}
	
	public void LoadTable() {
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		table.setModel(defaultTableModel);
		
		defaultTableModel.addColumn("Product ID");
		defaultTableModel.addColumn("Product Name");
		defaultTableModel.addColumn("Color");
		defaultTableModel.addColumn("Max Speed");
		defaultTableModel.addColumn("Product Total");
		defaultTableModel.addColumn("Product Price");
				
		try {
			car.setListCar(DocFile.docFile());
			if(car.getListCar() != null) {
				for(Car item : car.getListCar()) {
					defaultTableModel.addRow(new Object[] {
							item.getProduct_id(), item.getProduct_name(), item.getColor(), item.getMax_speed(), item.getProduct_total(), item.getProduct_price()
					});
				}
			}
		} catch (Exception e) {			
			JOptionPane.showMessageDialog(frame, "File null", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		}	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		car.setListCar(DocFile.docFile());
		frame = new JFrame();
		frame.setBounds(100, 100, 757, 510);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Product ID");
		lblNewLabel.setBounds(38, 43, 68, 14);
		frame.getContentPane().add(lblNewLabel);
		
		product_id = new JTextField();
		product_id.setBounds(127, 40, 117, 20);
		frame.getContentPane().add(product_id);
		product_id.setColumns(10);
		
		JLabel lblColor = new JLabel("Color");
		lblColor.setBounds(38, 76, 68, 14);
		frame.getContentPane().add(lblColor);
		
		color = new JTextField();
		color.setColumns(10);
		color.setBounds(127, 73, 117, 20);
		frame.getContentPane().add(color);
		
		JLabel lblProductTotal = new JLabel("Product Total");
		lblProductTotal.setBounds(38, 107, 79, 14);
		frame.getContentPane().add(lblProductTotal);
		
		product_total = new JTextField();
		product_total.setColumns(10);
		product_total.setBounds(127, 104, 117, 20);
		frame.getContentPane().add(product_total);
		
		JLabel lblProductName = new JLabel("Product Name");
		lblProductName.setBounds(337, 46, 82, 14);
		frame.getContentPane().add(lblProductName);
		
		product_name = new JTextField();
		product_name.setColumns(10);
		product_name.setBounds(440, 43, 117, 20);
		frame.getContentPane().add(product_name);
		
		JLabel lblMaxSp = new JLabel("Max Speed");
		lblMaxSp.setBounds(337, 76, 82, 14);
		frame.getContentPane().add(lblMaxSp);
		
		max_speed = new JTextField();
		max_speed.setColumns(10);
		max_speed.setBounds(440, 73, 117, 20);
		frame.getContentPane().add(max_speed);
		
		JLabel lblProductPrice = new JLabel("Product Price");
		lblProductPrice.setBounds(337, 107, 82, 14);
		frame.getContentPane().add(lblProductPrice);
		
		product_price = new JTextField();
		product_price.setColumns(10);
		product_price.setBounds(440, 104, 117, 20);
		frame.getContentPane().add(product_price);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				if(row >= 0) {
					product_id.setText(table.getValueAt(row, 0).toString());
					product_name.setText(table.getValueAt(row, 1).toString());
					color.setText(table.getValueAt(row, 2).toString());
					max_speed.setText(table.getValueAt(row, 3).toString());
					product_total.setText(table.getValueAt(row, 4).toString());
					product_price.setText(table.getValueAt(row, 5).toString());
				}
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
			},
			new String[] {
				"Product ID", "Product Name", "Color", "Max Speed", "Product Total", "Product Price"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, Integer.class, Integer.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.setBounds(49, 206, 631, 182);
		frame.getContentPane().add(table);
		
		JButton btn_add = new JButton("Thêm");
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try { 
					String id = product_id.getText();
					String name = product_name.getText();
					double price = Double.valueOf(product_price.getText());
					int total = Integer.valueOf(product_total.getText());
					String colors = color.getText();
					int maxspeed = Integer.valueOf(max_speed.getText());
					
					Car cars = new Car(id, name, price, total, colors, maxspeed);
					if(car.addCar(cars)) {
						product_id.setText("");
						product_name.setText("");
						product_total.setText("");
						product_price.setText("");
						color.setText("");
						max_speed.setText("");
						JOptionPane.showMessageDialog(frame, "Thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);						
					} else {
						JOptionPane.showMessageDialog(frame, "Trùng ID", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
					}
					DocFile.ghiFile(car.getListCar());
					LoadTable();
					
				} catch(Exception err) {							
					JOptionPane.showMessageDialog(frame, "Chưa điền đủ dữ liệu", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btn_add.setBounds(49, 410, 89, 23);
		frame.getContentPane().add(btn_add);
		
		JButton btn_edit = new JButton("Sửa");
		btn_edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String id = product_id.getText();
					String name = product_name.getText();
					double price = Double.valueOf(product_price.getText());
					int total = Integer.valueOf(product_total.getText());
					String colors = color.getText();
					int maxspeed = Integer.valueOf(max_speed.getText());
					
					Car cars = new Car(id, name, price, total, colors, maxspeed);
					if(car.editCar(cars)) {
						product_id.setText("");
						product_name.setText("");
						product_total.setText("");
						product_price.setText("");
						color.setText("");
						max_speed.setText("");
						JOptionPane.showMessageDialog(frame, "Sửa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);						
					} else {
						JOptionPane.showMessageDialog(frame, "Không tìm thấy ID", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
					}
					DocFile.ghiFile(car.getListCar());
					LoadTable();
					
				} catch(Exception err) {							
					JOptionPane.showMessageDialog(frame, "Chưa điền đủ dữ liệu", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btn_edit.setBounds(148, 410, 89, 23);
		frame.getContentPane().add(btn_edit);
		
		JButton btn_delete = new JButton("Xoá");
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String id = product_id.getText();
					String name = product_name.getText();
					double price = Double.valueOf(product_price.getText());
					int total = Integer.valueOf(product_total.getText());
					String colors = color.getText();
					int maxspeed = Integer.valueOf(max_speed.getText());
					
					Car cars = new Car(id, name, price, total, colors, maxspeed);
					if(car.delCar(cars)) {
						product_id.setText("");
						product_name.setText("");
						product_total.setText("");
						product_price.setText("");
						color.setText("");
						max_speed.setText("");
						JOptionPane.showMessageDialog(frame, "Xoá thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);						
					} else {
						JOptionPane.showMessageDialog(frame, "Không tìm thấy ID", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
					}
					DocFile.ghiFile(car.getListCar());
					LoadTable();
					
				} catch(Exception err) {							
					JOptionPane.showMessageDialog(frame, "Chưa điền đủ dữ liệu", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btn_delete.setBounds(246, 410, 89, 23);
		frame.getContentPane().add(btn_delete);
		
		JButton btn_search = new JButton("Tìm kiếm");
		btn_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {				
					String name = product_name.getText().toString().trim();
					if(name.equals("")) {
						JOptionPane.showMessageDialog(frame, "Chưa Nhập Tên Cần Tìm Kiếm", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
						return;
					} else {
						DefaultTableModel defaultTableModel = new DefaultTableModel();
						table.setModel(defaultTableModel);
						
						defaultTableModel.addColumn("Product ID"); 
						defaultTableModel.addColumn("Product Name");
						defaultTableModel.addColumn("Color");
						defaultTableModel.addColumn("Max Speed");
						defaultTableModel.addColumn("Product Total");
						defaultTableModel.addColumn("Product Price");
						car.setListCar(DocFile.docFile());						
					
						List<Car> lists = car.searchCar(name);
						
						if(lists.size() != 0) {
							for(Car item : lists) {
								defaultTableModel.addRow(new Object[] {
										item.getProduct_id(), item.getProduct_name(), item.getColor(), item.getMax_speed(), item.getProduct_total(), item.getProduct_price()
								});
							}
						}
					}
					
					
				} catch (Exception e2) {					
					JOptionPane.showMessageDialog(frame, "Lỗi tìm kiếm", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btn_search.setBounds(351, 410, 89, 23);
		frame.getContentPane().add(btn_search);
		
		JButton btn_sort = new JButton("Sắp xếp");
		btn_sort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel defaultTableModel = new DefaultTableModel();
				table.setModel(defaultTableModel);
				
				defaultTableModel.addColumn("Product ID");
				defaultTableModel.addColumn("Product Name");
				defaultTableModel.addColumn("Color");
				defaultTableModel.addColumn("Max Speed");
				defaultTableModel.addColumn("Product Total");
				defaultTableModel.addColumn("Product Price");
				car.setListCar(DocFile.docFile());				
				List<Car> lists = car.sortedCar(0);
				if(lists.size() != 0) {
					for(Car item : lists) {
						defaultTableModel.addRow(new Object[] {
								item.getProduct_id(), item.getProduct_name(), item.getColor(), item.getMax_speed(), item.getProduct_total(), item.getProduct_price()
						});
					}
				}
			}
		});
		btn_sort.setBounds(450, 410, 89, 23);
		frame.getContentPane().add(btn_sort);
		
		JButton btn_load = new JButton("Hiển thị dữ liệu");
		btn_load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoadTable();
			}
		});
		btn_load.setBounds(549, 410, 133, 23);
		frame.getContentPane().add(btn_load);
	}
}
