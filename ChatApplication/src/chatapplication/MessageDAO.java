package chatapplication;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MessageDAO {
	DbConnection dbconnection = new DbConnection();
	 public List<register> getAllContacts() throws SQLException {
		List<register> list = new ArrayList<>();
		register Reg = null;
		String sql = "SELECT * FROM register";

		try (Connection connection = dbconnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				Reg = new register();
				Reg.setUsername(resultSet.getString("username"));
				Reg.setMobileno(resultSet.getInt("mobileno"));
				list.add(Reg);
			}
		}

		return list;
	}

	public void sendMessage(String senderUsername, String receiverUsername, String messageText) throws SQLException {
		String query = "INSERT INTO messages (sender_username, receiver_username, message_text) VALUES (?, ?, ?)";
		try (Connection connection = dbconnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query);) {
			statement.setString(1, senderUsername);
			statement.setString(2, receiverUsername);
			statement.setString(3, messageText);
			int rs = statement.executeUpdate();
			if (rs > 0) {
				System.out.println("MESSAGE SENT SUCCESSFULLY-----!!!!");
			} else {
				System.out.println("MESSAGE NOT SENT ENTER CORRECT DETAILS----!!!!");
			}
		}
	}

	public void receiveMessages(String reciever_username1) throws SQLException {
		String sql = "SELECT * FROM messages WHERE receiver_username = ? ORDER BY timestamp";
		try (Connection connection = dbconnection.getConnection();
				PreparedStatement preparedstatement = connection.prepareStatement(sql);) {
			preparedstatement.setString(1, reciever_username1);
			ResultSet resultSet = preparedstatement.executeQuery();
			if (resultSet.next()) {
				String sender = resultSet.getString("sender_username");
				String message = resultSet.getString("message_text");
				System.out.println(reciever_username1 + " recieved " + message + " from " + sender);
			}
		}
	}
}





