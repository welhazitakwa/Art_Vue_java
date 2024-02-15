package services.utilisateur;

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

    public void validateLogin (String loginFourni, String mdpFourni )throws SQLException{

        String reqVerif = "SELECT count(*) from utilisateur WHERE login = '"+ loginFourni +"'";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(reqVerif) ;
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
                }
            }else{
                System.out.println("verifiez vos parametres d'authentification !! ");
            }
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
            user.setMdp(rs.getString("mdp"));
            user.setImage(rs.getString("image"));
            user.setGenre(rs.getString("genre"));
            user.setDateNaissance(rs.getDate("dateNaissance"));
            user.setAdresse(rs.getString("adresse"));
            list.add(user) ;
        }
        return list;
    }

    public static boolean checkExistingUser(String enteredPassword, String hashedPasswordFromDatabase) {
        return  BCrypt.checkpw(enteredPassword, hashedPasswordFromDatabase);
    }
}
