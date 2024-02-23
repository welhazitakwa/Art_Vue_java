package services.utilisateur;

import models.Categorie;
import models.Utilisateur;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import utils.MyDataBase;
import org.mindrot.jbcrypt.BCrypt;

public class UtilisateurService implements IUtilisateur<Utilisateur>{

    private Connection connection;
    public UtilisateurService (){
        connection = MyDataBase.getInstance().getConnection();
    }
    @Override
    public void ajouter(Utilisateur utilisateur) throws SQLException {
            String req= "INSERT INTO utilisateur(nom, prenom, email, numTel, login,cin, mdp,profil, image, genre, dateNaissance, " +
                    "adresse,date_inscription, etat_compte)" +
                    "values('" + utilisateur.getNom() + "'," +
                    "'" + utilisateur.getPrenom() + "'," +
                    "'" + utilisateur.getEmail() + "'," +
                    "'" + utilisateur.getNumTel() + "'," +
                    "'" + utilisateur.getLogin() + "'," +
                    "'" + utilisateur.getCin() + "'," +
                    "'" +  BCrypt.hashpw(utilisateur.getMdp() , BCrypt.gensalt())+ "'," +
                    "'" + utilisateur.getProfil() + "'," +
                    "'" + utilisateur.getImage() + "'," +
                    "'" + utilisateur.getGenre() + "'," +
                    "'" + utilisateur.getDateNaissance() + "'," +
                    "'" + utilisateur.getAdresse() + "'," +
                    "'" + java.sql.Date.valueOf(LocalDate.now()) + "'," +
                    "'" + utilisateur.getEtat_compte() + "')";
        Statement satatement = connection.createStatement();
        satatement.executeUpdate(req);
    }
    public int validateRegisterLoginMail(String login,String mail) throws SQLException {
        String reqVerif = "SELECT count(*) from utilisateur WHERE login = '" + login + "'";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(reqVerif);
        int existinlogin = 0;
        while (rs.next()) {
            if (rs.getInt(1) == 1) {
                existinlogin = 1;
            } }


        String reqVerif2 = "SELECT count(*) from utilisateur WHERE email = '" + mail + "'";
        Statement statement2 = connection.createStatement();
        ResultSet rs2 = statement2.executeQuery(reqVerif2);
        while (rs2.next()) {
            if (rs2.getInt(1) == 1) {
                existinlogin = 2;
            } }
        return existinlogin ;
    }

    public int getIdUserConnected(String login, String pwd) throws SQLException {
        int userLogin = 0;
        if (validateLogin(login,pwd)==0 || validateLogin(login,pwd)==1 ||validateLogin(login,pwd)==2) {


            String getID = "SELECT id from utilisateur WHERE login = '" + login + "'";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(getID);

            while (rs.next()) {
                userLogin = rs.getInt("id");
            }
        }
        return userLogin ;
    }
    public int validateLogin (String loginFourni, String mdpFourni )throws SQLException{

        String reqVerif = "SELECT count(*) from utilisateur WHERE login = '"+ loginFourni +"'";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(reqVerif) ;
        int profilUser = 3 ;


        while (rs.next()) {
            if ( rs.getInt(1) == 1 ){
                String reqmdp = "SELECT mdp from utilisateur WHERE login = '"+ loginFourni +"'";
                Statement statement1 = connection.createStatement();
                ResultSet rs1 = statement1.executeQuery(reqmdp) ;
                String mdpfromdatabase = "";
                while (rs1.next()) {
                    mdpfromdatabase = rs1.getString("mdp");
                }
                boolean mdphashed = checkExistingUser(mdpFourni , mdpfromdatabase );
                if (mdphashed == true ) {
                    System.out.println("welcome !!" );
                    String reqprofil = "SELECT profil from utilisateur WHERE login = '"+ loginFourni +"'";
                    Statement statement3 = connection.createStatement();
                    ResultSet rs3 = statement3.executeQuery(reqprofil) ;

                    while (rs3.next()) {
                        profilUser = rs3.getInt(1);
                    }
                }
                if (mdphashed == false ) {
                    System.out.println("mawelcomch !!" );
                    profilUser = 3 ;
                }



            }else{
                System.out.println("verifiez vos parametres d'authentification !! ");
                profilUser = 3;
            }
        }
        return profilUser ;

    }
    public void register(Utilisateur utilisateur, String role) throws SQLException {
         if (role.equals("Je suis un artiste")) {
             String req= "INSERT INTO utilisateur(nom, prenom, email,login, mdp,profil,date_inscription, etat_compte)" +
                     "values('" + utilisateur.getNom() + "'," +
                     "'" + utilisateur.getPrenom() + "'," +
                     "'" + utilisateur.getEmail() + "'," +
                     "'" + utilisateur.getLogin() + "'," +
                     "'" +  BCrypt.hashpw(utilisateur.getMdp() , BCrypt.gensalt())+ "'," +
                     "'" + 1 + "'," +
                     "'" + java.sql.Date.valueOf(LocalDate.now()) + "'," +
                     "'" + 0 + "')";
             Statement statement = connection.createStatement();
             statement.executeUpdate(req) ;
         }
         if (role.equals("Je suis un client")) {
             String req= "INSERT INTO utilisateur(nom, prenom, email,login, mdp,profil,date_inscription, etat_compte)" +
                     "values('" + utilisateur.getNom() + "'," +
                     "'" + utilisateur.getPrenom() + "'," +
                     "'" + utilisateur.getEmail() + "'," +
                     "'" + utilisateur.getLogin() + "'," +
                     "'" +  BCrypt.hashpw(utilisateur.getMdp() , BCrypt.gensalt())+ "'," +
                     "'" + 2 + "'," +
                     "'" + java.sql.Date.valueOf(LocalDate.now()) + "'," +
                     "'" + 0 + "')";
             Statement statement = connection.createStatement();
             statement.executeUpdate(req) ;
         }


    }
    @Override
    public void modifier(Utilisateur utilisateur) throws SQLException {
        int varrr = utilisateur.getId();
        String sql= "update utilisateur set nom= ?, prenom = ? ,email = ? , numTel = ?, login = ? , " +
                "mdp = ? ,image = ?, genre = ?, dateNaissance = ?, adresse = ? where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, utilisateur.getNom());
        preparedStatement.setString(2, utilisateur.getPrenom());
        preparedStatement.setString(3, utilisateur.getEmail());
        preparedStatement.setInt(4, utilisateur.getNumTel());
        preparedStatement.setString(5, utilisateur.getLogin());
        preparedStatement.setString(6, BCrypt.hashpw(utilisateur.getMdp() , BCrypt.gensalt()));
        preparedStatement.setString(7, utilisateur.getImage());
        preparedStatement.setString(8, utilisateur.getGenre());
        preparedStatement.setDate(9, utilisateur.getDateNaissance());
        preparedStatement.setString(10, utilisateur.getAdresse());
        preparedStatement.setInt(11, utilisateur.getId());
        preparedStatement.executeUpdate();


    }
    @Override
    public void supprimer(int id) throws SQLException {
    String sql = "DELETE FROM `utilisateur` WHERE id=?";
    PreparedStatement preparedStatement = connection.prepareStatement(sql) ;
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }
    @Override
    public List<Utilisateur> listAll() throws SQLException {
        String sql = "select * from utilisateur " ;
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql) ;
        List <Utilisateur> list = new ArrayList<>();
        while (rs.next()) {
            Utilisateur user = new Utilisateur();
            user.setId(rs.getInt("id"));
            user.setNom(rs.getString("nom"));
            user.setPrenom(rs.getString("prenom"));
            user.setEmail(rs.getString("email"));
            user.setNumTel(rs.getInt("numTel"));
            user.setLogin(rs.getString("login"));
            user.setCin(rs.getInt("cin"));
            user.setMdp(rs.getString("mdp"));
            user.setProfil(rs.getInt("profil"));
            user.setImage(rs.getString("image"));
            user.setGenre(rs.getString("genre"));
            user.setDateNaissance(rs.getDate("dateNaissance"));
            user.setAdresse(rs.getString("adresse"));
            user.setDate_inscription(rs.getDate("date_inscription"));
            list.add(user) ;
        }
        return list;
    }

    @Override
    public  Utilisateur getUtilisateurById(int id) throws SQLException {
        String sql = "SELECT * FROM utilisateur WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setId(rs.getInt("id"));
            utilisateur.setNom(rs.getString("nom"));
            utilisateur.setPrenom(rs.getString("prenom"));
            utilisateur.setEmail(rs.getString("email"));
            utilisateur.setNumTel(rs.getInt("numTel"));
            utilisateur.setLogin(rs.getString("login"));
            utilisateur.setMdp(rs.getString("mdp"));
            utilisateur.setImage(rs.getString("image"));
            utilisateur.setGenre(rs.getString("genre"));
            utilisateur.setDateNaissance(rs.getDate("dateNaissance"));
            utilisateur.setAdresse(rs.getString("adresse"));
            utilisateur.setDate_inscription(rs.getDate("date_inscription"));
            utilisateur.setEtat_compte(rs.getInt("etat_compte"));

            return utilisateur;
        } else {
            return null;
        }
    }


    public static boolean checkExistingUser(String enteredPassword, String hashedPasswordFromDatabase) {
        return  BCrypt.checkpw(enteredPassword, hashedPasswordFromDatabase);
    }
}
