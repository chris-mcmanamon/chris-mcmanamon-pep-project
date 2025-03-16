package Service;

import java.util.List;

import DAO.AccountDAO;
import DAO.AccountDAOImpl;
import DAO.MessageDAO;
import DAO.MessageDAOImpl;
import Model.Message;

public class MessageService {
  private MessageDAO messageDAO;

  public MessageService() {
    messageDAO = new MessageDAOImpl();
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
    AccountDAO accountDAO = new AccountDAOImpl();
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

  /**
   * Delete message
   * 
   * @param message_id ID of message to delete
   * @return deleted message, or null if it did not exist
   */
  public Message deleteMessageByID(int message_id) {
    Message message = messageDAO.getMessageByID(message_id);
    if (message != null) {
      messageDAO.deleteMessageByID(message_id);
    }

    return message;
  }

  /**
   * Update message
   * message_id must exist
   * new message_text cannot be blank and cannot be over 255 characters
   * @param message_id the id of the Message to update
   * @param newMessage the text of the new message
   * @return
   */
  public Message updateMessageByID(int message_id, String newMessage) {
    // Validate new message
    if (newMessage == null || newMessage.length() == 0 || newMessage.length() > 255) return null;
    // Verify that message exists in permanent storage
    if (messageDAO.getMessageByID(message_id) == null) return null;

    messageDAO.updateMessageByID(message_id, newMessage);
    return messageDAO.getMessageByID(message_id);
  }

  public List<Message> getAllMessagesByUser(int account_id) {
    return messageDAO.getAllMessagesByUser(account_id);
  }
}
