package DAO;

import java.util.List;

import Model.Message;

public interface MessageDAO {
  public Message insertMessage(Message message);

  public List<Message> getAllMessages();

  public Message getMessageByID(int message_id);

  public void deleteMessageByID(int message_id);

  public void updateMessageByID(int message_id, String newMessage);

  public List<Message> getAllMessagesByUser(int account_id);
}
