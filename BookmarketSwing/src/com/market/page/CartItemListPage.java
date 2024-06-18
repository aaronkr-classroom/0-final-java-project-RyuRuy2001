package com.market.page;

import javax.swing.*;
import com.market.cart.Cart;
import com.market.cart.CartItem;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CartItemListPage extends JPanel{
	
	private static final String totalprice = null;
	JTable cartTable;
	Object[] tableHeader = {"도서ID", "도서명", "단가", "수량", "총가격"};
	
	Cart mCart = new Cart();
	public static int mSelectRow = -1;
	
	public CartItemListPage(JPanel panel, Cart cart) {
		Font ft;
		ft = new Font("함초롬돋움", Font.BOLD, 15);
		this.mCart = cart;
		this.setLayout(null);
		
		Rectangle rect = panel.getBounds();
		System.out.println(rect);
		this.setPreferredSize(rect.getSize());
		
		JPanel bookPanel = new JPanel();
		bookPanel.setBounds(0, 0, 1000, 400);
		add(bookPanel);
	
		Object[][] content = null;
		cartTable = new JTable(content, tableHeader);
		JScrollPane jScrollPane = new JScrollPane();
		JScrollPane jScrollPane2 = new JScrollPane();
		jScrollPane2.setPreferredSize(new Dimension(600, 350));
		
		
		JPanel totalPrciepanel = new JPanel();
		totalPrciepanel.setBounds(0, 400, 1000, 50);
		
		JLabel totalPricelabel = new JLabel("총금액: " + totalprice + " 원");
		totalPricelabel.setForeground(Color.red);
		totalPricelabel.setFont(ft);
		totalPrciepanel.add(totalPricelabel);
		add(totalPrciepanel);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setBounds(0, 450, 1000, 50);
		add(buttonPanel);
		
		JLabel buttonLabel = new JLabel("장바구니 비우기");
		buttonLabel.setFont(ft);
		JButton clearButton = new JButton();
		clearButton.add(buttonLabel);
		buttonPanel.add(clearButton);
		
		clearButton.addActionListener(new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<CartItem> cartItem = cart.getmCartItem();
				if (Cart.mCartCount == 0)
					JOptionPane.showMessageDialog(clearButton, "장바구나에 항목이 없습니다.");
				else {
					int select = JOptionPane.showConfirmDialog(clearButton, "장바구니의 모든 항목을 삭제하겠습니까?");
					if (select == 0) {
						TableModel tableModel = new DefaultTableModel(new Object[0][0], tableHeader);
						totalPricelabel.setText("총금액: " + 0 + " 원");
						JOptionPane.showMessageDialog(clearButton, "장바구니에 있는 모든 항목을 삭제 했습니다.");
						cart.deleteBook();
					}
				}
			}
		});
		
		JLabel removeLabel = new JLabel("장바구니의 항목 삭제하기");
		removeLabel.setFont(ft);
		JButton removeButton = new JButton();
		removeButton.add(removeLabel);
		buttonPanel.add(removeButton);
		
		removeButton.addActionListener(new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				if (Cart.mCartCount == 0)
					JOptionPane.showMessageDialog(clearButton, "장바구니 항목이 없습니다.");
				else if (mSelectRow == -1)
				JOptionPane.showMessageDialog(clearButton, "장바구니에서 삭제할 항목을 선택하세요.");
				else {
					ArrayList<CartItem> cartItem = cart.getmCartItem();
					cartItem.remove(mSelectRow);
					Cart.mCartCount -= -1;
					Object[][] content = new Object[cartItem.size()][tableHeader.length];
					Integer totalprice = 0;
					for (int i = 0; i < cartItem.size(); i++) {
						CartItem item = CartItem.get(i);
						content[i][0] = item.getBookID();
						content[i][1] = item.getItemBook().getName();
						content[i][2] = item.getItemBook().getUnitPrice();
						content[i][3] = item.getQuantity();
						content[i][4] = item.getTotalPrice();
						totalprice += item.getQuantity() * item.getItemBook().getUnitPrice();
						
					}
					TableModel tableModel = new DefaultTableModel(content, tableHeader);
					totalPricelabel.setText("총금액: " + totalprice + "원");
					cartTable.setModel(tableModel);
					mSelectRow = -1;
				}
			}
	});
	
	cartTable.addMouseListener(new MouseListener() {
		
		public void mouseClicked(MouseEvent e) {
			
		int row = cartTable.getSelectedRow();
		mSelectRow = row;
	}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	
	
	});
	
	
	
	JLabel refreshLabel = new JLabel("장바구니 새로 고침");
	refreshLabel.setFont(ft);
	JButton refreshBtn = new JButton();
	refreshBtn.add(refreshLabel);
	buttonPanel.add(refreshBtn);
	
	//문제가있어서 찾는 중
/*	refreshBtn.addActionListener(new AbstractAction() {
		public void actionPerformed(ActionEvent e) {
			ArrayList<CartItem> cartItem = mCart.getmCartItem();
			Object[][] content = new Object[cartItem.size()] [tableHeader.length];
			Integer totalPrice = 0;
			for (int i = 0; i < cartItem.size(); i++) {
				CartItem item = cartItem.get(i);
				content[i][0] = item.getBookID();
				content[i][1] = item.getItemBook().getName();			
				content[i][2] = item.getItemBook().getUnitPrice();
				content[i][3] = item.getQuantity();
				content[i][4] = item.getTotalPrice();
			totalPrice += item.getQuantity() * item.getItemBook().getUnitPrice();		}
		}
		TableModel tableModel = new DefaultTableModel(content, tableHeader);
		totalPricelabel.setText("총금액: " + totalprice + "원");
		cartTable.setModel(tableModel); 
	
	}*/ 
	
	}// constructor
} // class
