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
   * @param account an account object; username cannot be blank; password must be at least 4 chars
   * @return account if it was successfully persisted, null otherwise
   */
  public Account registerAccount(Account account) {
    // Validate inputs
    if (account.getUsername().equals("") || account.getPassword().length() < 4) return null;

    // Ensure account does not already exist
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
