package model;

//import net.proteanit.sql.DbUtils;

import java.sql.*;

public class Login 
{

	public void actionPerformed(ActionEvent arg0) {
		if(viewRec.isSelected()){
			try{
				String query = "SELECT * FROM  WHERE username = ? and password = ?";
				PreparedStatement pst = connection.prepareStatement(query);
				pst.setString(1, Username.getText() );
				pst.setString(2, Password.getText() );
				
				ResultSet rs = pst.executeQuery();
				int count = 0;
				while(rs.next()){
					count++;
				}
				if(count == 1){
					try{
						String query2 = "SELECT userid from customers where username = '"+ Username.getText() +"'";
						PreparedStatement pst2 = connection.prepareStatement(query2);
						ResultSet rs2 = pst2.executeQuery();
							
						tableCurrentIDCust.setModel(DbUtils.resultSetToTableModel(rs2));
						int row =0;
						int column = 0;
						str = (tableCurrentIDCust.getModel().getValueAt(row,column).toString());
						
					}catch (Exception ex) { 
						JOptionPane.showMessageDialog(null, ex);
					}
					
					JOptionPane.showMessageDialog(null, "Username and Password is correct");
					Main_Cust window = new Main_Cust();
					Main_Cust.lbCurrentUserIDCust.setText(str);
					Main_Cust.lbCurrentUsernameCust.setText(Username.getText());
					window.setVisible(true);
					frmLogin.dispose();
				}
				else if(count > 1){
					JOptionPane.showMessageDialog(null, "Duplicate Username and Password");
				}
				else{
					JOptionPane.showMessageDialog(null, "Username/Password is not correct. Try Again.");
				}
				
				rs.close();
				pst.close();
			}catch(Exception ex){
				ex.printStackTrace();
		}
		}
}
}
