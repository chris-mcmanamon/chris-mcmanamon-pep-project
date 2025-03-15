package Service;

import java.util.List;

import DAO.AccountDAO;
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

  /**
   * Insert Message
   * message_text cannot be blank and cannot be over 255 characters
   * posted_by must be an existing user
   * 
   * @param message a Message object
   * @return the persisted message if successful
   */
  public Message addMessage(Message message) {
    // Validate message inputs
    if (message.getMessage_text() == null || message.getMessage_text().length() == 0
        || message.getMessage_text().length() > 255)
      return null;

    // Validate user
    AccountDAO accountDAO = new AccountDAO();
    if (accountDAO.getAccountByID(message.getPosted_by()) == null)
      return null;

    // Return persisted message if successful
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
