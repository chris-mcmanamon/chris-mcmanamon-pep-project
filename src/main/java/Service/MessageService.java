package Service;

import java.util.List;

import DAO.MessageDAO;
import Model.Message;

public class MessageService {
  private MessageDAO messageDAO;

  public MessageService() {
    messageDAO = new MessageDAO();
  }

  public MessageService(MessageDAO messageDAO) {
    this.messageDAO = messageDAO;
  }

  public Message insertMessage(Message message) {
    return messageDAO.insertMessage(message);
  }

  public List<Message> getAllMessages() {
    return messageDAO.getAllMessages();
  }

  public Message getMessageByID(int message_id) {
    return messageDAO.getMessageByID(message_id);
  }

  public Message deleteMessageByID(int message_id) {
    return messageDAO.deleteMessageByID(message_id);
  }

  public Message updateMessageByID(int message_id) {
    return messageDAO.updateMessageByID(message_id);
  }

  public List<Message> getAllMessagesByUser(int account_id) {
    return messageDAO.getAllMessagesByUser(account_id);
  }
}
