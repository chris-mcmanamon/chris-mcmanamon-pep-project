package Service;

import DAO.AccountDAO;
import DAO.AccountDAOImpl;
import Model.Account;

public class AccountService {
  private AccountDAO accountDAO;

  public AccountService() {
    accountDAO = new AccountDAOImpl();
  }

  public AccountService(AccountDAO accountDAO) {
    this.accountDAO = accountDAO;
  }

  /**
   * Register an account
   *
   * @param account an account object; username cannot be blank; password must be at least 4 chars.
   * Additional checks added: username and pw <= 255 chars to match column constraints.
   * @return account if it was successfully persisted, null otherwise
   */
  public Account registerAccount(Account account) {
    // Reject invalid username or password
    if (account == null || account.getUsername() == null || account.getPassword() == null
        || account.getUsername().isBlank() 
        || account.getUsername().length() > 255 
        || account.getPassword().length() < 4 
        || account.getPassword().length() > 255)
        {
          return null;
        }

    // Reject if username already exists
    if (accountDAO.getAccountByUsername(account.getUsername()) != null) return null;

    // Return inserted account
    return accountDAO.insertAccount(account);
  }

  /**
   * Verify that account exists in permanent storage
   *
   * @param account an account object
   * @return the account including account_id if it exists
   */
  public Account authenticateAccount(Account account) {
    return accountDAO.authenticateAccount(account);
  }
}
