package Service;

import DAO.AccountDAO;
import DAO.AccountDAOImpl;
import DAO.MessageDAO;
import DAO.MessageDAOImpl;
import Model.Message;
import java.util.List;

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
   *
   * @param message a Message object; message_text cannot be blank or over 255 char; posted_by must
   *     be an existing user
   * @return the persisted message if successful
   */
  public Message addMessage(Message message) {
    // Reject invalid message inputs
    if (message == null || message.getMessage_text() == null
        || message.getMessage_text().isBlank()
        || message.getMessage_text().length() > 255) return null;

    // Validate user
    AccountDAO accountDAO = new AccountDAOImpl();
    if (accountDAO.getAccountByID(message.getPosted_by()) == null) return null;

    // Return persisted message if successful
    return messageDAO.insertMessage(message);
  }

  /**
   * Get all messages
   *
   * @return a list of all messages, or an empty list if none exist
   */
  public List<Message> getAllMessages() {
    return messageDAO.getAllMessages();
  }

  /**
   * Get message by message ID
   *
   * @param message_id the id of the message
   * @return a message, or null if it does not exist
   */
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
   *
   * @param message_id the id of the Message to update; must exist
   * @param newMessage the text of the new message; cannot be blank or over 255 char
   * @return the updated message if successful, null otherwise
   */
  public Message updateMessageByID(int message_id, String newMessage) {
    // Reject invalid message
    if (newMessage == null || newMessage.isBlank() || newMessage.length() > 255) return null;
    
    // Verify that message exists in permanent storage
    if (messageDAO.getMessageByID(message_id) == null) return null;

    messageDAO.updateMessageByID(message_id, newMessage);
    return messageDAO.getMessageByID(message_id);
  }

  /**
   * Get all messages by user ID
   *
   * @param account_id the user's ID
   * @return a list of Messages or an empty list if none exist
   */
  public List<Message> getAllMessagesByUser(int account_id) {
    return messageDAO.getAllMessagesByUser(account_id);
  }
}
