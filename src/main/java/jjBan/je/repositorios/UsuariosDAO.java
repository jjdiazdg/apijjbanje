package jjBan.je.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import jjBan.je.user.Usuario;

//@Repository
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, 
		RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
@RepositoryRestResource(path = "usuarios",
//						exported=false,
						itemResourceRel="usuario",
						collectionResourceRel="usuarios")
public interface UsuariosDAO extends JpaRepository<Usuario, String> {

	@RestResource(path = "nombre")
	List<Usuario> findByNombreContainingIgnoreCase(String txt);

	@RestResource(path = "mail")
	List<Usuario> findByMailLike(String pattern);

//	@RestResource(exported = false)
//	void delete(Usuario entity);

}
