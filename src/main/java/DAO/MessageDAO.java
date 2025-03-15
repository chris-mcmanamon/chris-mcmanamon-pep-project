package DAO;

import java.util.List;
import java.sql.*;

import Model.Message;
import Util.ConnectionUtil;

public class MessageDAO {

  public Message insertMessage(Message message) {
    Connection connection = ConnectionUtil.getConnection();
    try {
      String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?,?,?)";
      PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

      preparedStatement.setInt(1, message.getPosted_by());
      preparedStatement.setString(2, message.getMessage_text());
      preparedStatement.setLong(3, message.getTime_posted_epoch());
      preparedStatement.executeUpdate();

      ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
      if (pkeyResultSet.next()) {
        int generated_message_id = pkeyResultSet.getInt(1);
        return new Message(generated_message_id, 
            message.getPosted_by(), 
            message.getMessage_text(),
            message.getTime_posted_epoch());
      }

    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }

    return null;
  }

  public List<Message> getAllMessages() {
    return null;
  }

  public Message getMessageByID(int message_id) {
    return null;
  }

  public Message deleteMessageByID(int message_id) {
    return null;
  }

  public Message updateMessageByID(int message_id) {
    return null;
  }

  public List<Message> getAllMessagesByUser(int account_id) {
    return null;
  }
}
