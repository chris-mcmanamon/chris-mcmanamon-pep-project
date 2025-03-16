package DAO;

import Model.Account;

public interface AccountDAO {
  public Account insertAccount(Account account);

  public Account getAccountByUsername(String username);

  public Account getAccountByID(int userID);

  public Account authenticateAccount(Account account);
}
