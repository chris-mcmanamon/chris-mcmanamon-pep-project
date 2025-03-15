package DAO;

import Model.Account;
import Util.ConnectionUtil;

import java.sql.*;

public class AccountDAO {

  public Account insertAccount(Account account) {
    Connection connection = ConnectionUtil.getConnection();
    try {
      String sql = "INSERT INTO account (username, password) VALUES (?, ?)";
      PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

      preparedStatement.setString(1, account.getUsername());
      preparedStatement.setString(2, account.getPassword());
      preparedStatement.executeUpdate();

      ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
      if (pkeyResultSet.next()) {
        int generated_account_id = pkeyResultSet.getInt(1);
        return new Account(generated_account_id, account.getUsername(), account.getPassword());
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }

    return null;
  }

  public Account getAccountByUsername(String username) {
    Connection connection = ConnectionUtil.getConnection();
    try {
      String sql = "SELECT * FROM account WHERE username = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setString(1, username);
      ResultSet rs = preparedStatement.executeQuery();
      if (rs.next()) {
        Account account = new Account(rs.getInt(1),
            rs.getString(2),
            rs.getString(3));
        return account;
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return null;
  }

  public Account authenticateAccount(Account account) {
    Connection connection = ConnectionUtil.getConnection();
    try {
      String sql = "SELECT * FROM account WHERE username = ? AND password = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setString(1, account.getUsername());
      preparedStatement.setString(2, account.getPassword());

      ResultSet rs = preparedStatement.executeQuery();

      if (rs.next()) {
        Account foundAccount = new Account(rs.getInt(1),
            rs.getString(2), rs.getString(3));
        return foundAccount;
      }

    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }

    return null;
  }
}
