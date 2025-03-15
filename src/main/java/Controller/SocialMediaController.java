package Controller;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your
 * controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a
 * controller may be built.
 */
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;

    public SocialMediaController() {
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }

    /**
     * In order for the test cases to work, you will need to write the endpoints in
     * the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * 
     * @return a Javalin app object which defines the behavior of the Javalin
     *         controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::registrationHandler);
        app.post("/login", this::loginHandler);
        app.post("/messages", this::newMessageHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getMessageHandler);
        app.delete("/messages/{message_id}", this::deleteMessageHandler);
        app.patch("/messages/{message_id}", this::updateMessageHandler);
        app.get("/accounts/{account_id}/messages", this::getAllMessagesByUser);

        return app;
    }

    /**
     * Handler for Account registration. Request contains username and password
     * Response status (200) contains JSON of account if successful
     * Client Error (400) if registration fails
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     * @throws JsonProcessingException 
     * @throws JsonMappingException 
     */
    private void registrationHandler(Context ctx) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account addedAccount = accountService.registerAccount(account);
        if (addedAccount != null) {
            ctx.json(mapper.writeValueAsString(addedAccount)).status(200);
        }
        else {
            ctx.status(400);
        }
        
    }

    /**
     * Handler for User Login
     * Successful (200) response contains JSON of account
     * Client Error (401) if unauthorized
     * @param context The Javalin Context object manages information about both the HTTP request and response.
          * @throws JsonProcessingException 
          * @throws JsonMappingException 
          */
         private void loginHandler(Context ctx) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account authenticatedAccount = accountService.authenticateAccount(account);
        if (authenticatedAccount != null) {
            ctx.json(authenticatedAccount).status(200);
        }
        else {
            ctx.status(401);
        }
    }

    /**
     * Handler for new message post
     * Request contains message
     * Successful (200) response contains JSON of message
     * Client Error (400) if not successful
     * @param context The Javalin Context object manages information about both the HTTP request and response.
          * @throws JsonProcessingException 
          * @throws JsonMappingException 
          */
         private void newMessageHandler(Context ctx) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        Message addedMessage = messageService.addMessage(message);
        if (addedMessage != null) {
            ctx.json(addedMessage).status(200);
        }
        else {
            ctx.status(400);
        }
    }

    /**
     * Handler for retrieving all messages
     * Successful (200) always
     * Response contains a list of all messages, or empty list if no messages
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void getAllMessagesHandler(Context ctx) {
        List<Message> messages = messageService.getAllMessages();
        ctx.json(messages).status(200);
    }

    /**
     * Handler for retrieving a message by ID
     * Successful (200) always
     * Response contains JSON message or empty if message does not exist
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void getMessageHandler(Context ctx) {

    }

    /**
     * Handler for deleting a message by ID
     * Successful (200) response always
     * Response contains the deleted message if it existed, or empty if it did not
     * exist
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void deleteMessageHandler(Context ctx) {

    }

    /**
     * Handler for updating a message text by ID
     * Request body contains new message_text and message_id
     * Successful (200) if:
     * - message_id exists
     * - new message_text is not blank and not over 255 characters
     * Response contains full updated message
     * Client Error (400) if update is not successful for any reason
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void updateMessageHandler(Context ctx) {

    }

    /**
     * Handler for retrieving all messages by a user
     * Successful (200) always
     * Response contains a list of messages by user, or empty if there are no
     * messages
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void getAllMessagesByUser(Context ctx) {

    }

}