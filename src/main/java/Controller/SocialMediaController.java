package Controller;

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
     * Successful (200) if:
     * - username is not blank
     * - password is at least 4 characters long
     * - Account with that username does not already exist
     * Client Error (400) if not successful
     */
    private void registrationHandler(Context ctx) {

    }

    /**
     * Handler for User Login
     * Successful (200) if:
     * - username and password match a database record
     * Response: JSON of account
     * Client Error (401) if unauthorized
     */
    private void loginHandler(Context ctx) {

    }

    /**
     * Handler for new message post
     * Request contains message
     * Successful (200) if message is valid if:
     * - message_text not blank and not over 255 characters
     * - posted_by is an existing user
     * Client Error (400) if not successful
     * 
     */
    private void newMessageHandler(Context ctx) {

    }

    /**
     * Handler for retrieving all messages
     * Successful (200) always
     * Response contains a list of all messages from db, or empty list if no
     * messages
     */
    private void getAllMessagesHandler(Context ctx) {

    }

    /**
     * Handler for retrieving a message by ID
     * Successful (200) always
     * Response contains JSON message or empty if message does not exist
     */
    private void getMessageHandler(Context ctx) {

    }

    /**
     * Handler for deleting a message by ID
     * Successful (200) response always
     * Response contains the deleted message if it existed, or empty if it did not
     * exist
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
     */
    private void updateMessageHandler(Context ctx) {

    }

    /**
     * Handler for retrieving all messages by a user
     * Successful (200) always
     * Response contains a list of messages by user, or empty if there are no
     * messages
     */
    private void getAllMessagesByUser(Context ctx) {

    }

}