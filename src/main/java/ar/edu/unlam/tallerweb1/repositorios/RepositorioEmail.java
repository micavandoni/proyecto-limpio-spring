package ar.edu.unlam.tallerweb1.repositorios;
import ar.edu.unlam.tallerweb1.modelo.Mail;
import java.util.List;

public interface RepositorioEmail {

	  void guardarMail(Mail mail);

	  List<Mail> obtenerEmailsPorUsuario(Integer userId);

	  void borrarEmails(List<Mail> mails);
	
}
