package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
  private AccountDAO accountDAO;

  public AccountService() {
    accountDAO = new AccountDAO();
  }

  public AccountService(AccountDAO accountDAO) {
    this.accountDAO = accountDAO;
  }

  /**
   * username is provided and cannot be blank
   * password is provided and must be at least 4 characters long
   * @param account an account object
   * @return account if it was successfully persisted, null otherwise
   */
  public Account registerAccount(Account account) {
    // Validate inputs
    if (account.getUsername().equals("") || account.getPassword().length() < 4)
      return null;
    
    // Ensure account does not already exist
    if (accountDAO.getAccountByUsername(account.getUsername()) != null)
      return null;

    // Return inserted account
    return accountDAO.insertAccount(account);
  }

  public Account authenticateAccount(Account account) {
    return accountDAO.authenticateAccount(account);
  }
}
