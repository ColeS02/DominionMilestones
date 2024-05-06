package com.unclecole.dominionmilestones.database;

import java.nio.charset.Charset;
import java.sql.*;
import java.util.UUID;

public class MariaDB {

    private Connection connection;
    private String url;
    private String host;
    private String database;
    private String user;
    private String pass;
    private final Charset UTF_8 = Charset.forName("UTF-8");

    public MariaDB(String url, String host, String database, String user, String pass) {
        this.url = url;
        this.host = host;
        this.database = database;
        this.user = user;
        this.pass = pass;
    }

    private boolean isConnected() {
        return this.connection != null;
    }

    public void connect() throws ClassNotFoundException {
        connection = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            this.connection = DriverManager.getConnection(this.url + this.host + "/" + this.database, this.user, this.pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        if (this.isConnected()) {
            try {
                this.connection.close();
            } catch (SQLException var2) {
                var2.printStackTrace();
            }
        }

    }

    public void registerTable() throws ClassNotFoundException {
        try {
            connect();
            PreparedStatement registerPlayerTable = this.connection.prepareStatement("CREATE TABLE IF NOT EXISTS all_players(uuid varchar(255) primary key, user_name varchar(255), discord_id varchar(255), user_verified TINYTEXT)");
            registerPlayerTable.execute();
            registerPlayerTable.close();
            disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addNewUser(UUID uuid, String username) throws ClassNotFoundException {
        try {
            connect();
            PreparedStatement createUser = this.connection.prepareStatement("INSERT INTO all_players(uuid, user_name, discord_id, user_verified) VALUES (?,?,?,?)");
            createUser.setString(1, uuid.toString());
            createUser.setString(2, username);
            createUser.setString(3, null);
            createUser.setString(4, "0");
            createUser.execute();
            createUser.close();
            disconnect();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(UUID uuid, String username) throws ClassNotFoundException {
        try {
            connect();
            PreparedStatement update = this.connection.prepareStatement("UPDATE all_players SET user_name='" + username + "' WHERE uuid='" + uuid.toString() + "'");
            update.executeUpdate();
            update.close();
            disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void updateUser(UUID uuid, String discord_id, String user_verified) throws ClassNotFoundException {
        try {
            connect();
            PreparedStatement update = this.connection.prepareStatement("UPDATE all_players SET discord_id='" + discord_id + "', user_verified='" + user_verified + "'  WHERE uuid='" + uuid.toString() + "'");
            update.executeUpdate();
            update.close();
            disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void checkUser(UUID uuid, String username) {
        try {
            connect();
            PreparedStatement checkUser = this.connection.prepareStatement("SELECT * FROM all_players WHERE uuid='" + uuid.toString() + "'");

            ResultSet rs = checkUser.executeQuery();
            checkUser.close();
            disconnect();

            if(rs.next()) {
                if(!rs.getString("user_name").equals(username))
                    updateUser(uuid,username);;

            } else {
                addNewUser(uuid, username);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Boolean isVerified(UUID uuid) {
        try {
            connect();
            PreparedStatement verifiedQuery = this.connection.prepareStatement("SELECT * FROM all_players WHERE uuid='" + uuid.toString() + "'");

            ResultSet rs = verifiedQuery.executeQuery();

            verifiedQuery.close();
            disconnect();

            if(rs.next()) {
                return rs.getString("user_verified").equals("1");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }

    public String fetchStringUUID(UUID uuid, String string) {
        try {
            connect();
            PreparedStatement verifiedQuery = this.connection.prepareStatement("SELECT * FROM all_players WHERE uuid='" + uuid.toString() + "'");

            ResultSet rs = verifiedQuery.executeQuery();

            verifiedQuery.close();
            disconnect();

            switch (string) {
                case "discord_id":
                    if(rs.next()) return rs.getString("discord_id");
                    break;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String fetchStringDiscordID(String ID, String string) {
        try {
            connect();
            PreparedStatement verifiedQuery = this.connection.prepareStatement("SELECT * FROM all_players WHERE discord_id='" + ID + "'");

            ResultSet rs = verifiedQuery.executeQuery();

            verifiedQuery.close();
            disconnect();

            switch (string) {
                case "uuid":
                    if (rs.next()) return rs.getString("uuid");
                    break;
                case "user_name":
                    if (rs.next()) return rs.getString("user_name");
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String fetchStringUsername(String IGN, String string) {
        try {
            connect();
            PreparedStatement verifiedQuery = this.connection.prepareStatement("SELECT * FROM all_players WHERE user_name='" + IGN + "'");

            ResultSet rs = verifiedQuery.executeQuery();

            verifiedQuery.close();
            disconnect();

            switch (string) {
                case "uuid":
                    if (rs.next()) return rs.getString("uuid");
                    break;
                case "discord_id":
                    if(rs.next()) return rs.getString("discord_id");
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

}
