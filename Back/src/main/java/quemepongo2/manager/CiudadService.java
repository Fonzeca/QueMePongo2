package main.java.quemepongo2.manager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.quemepongo2.api.responses.CiudadRs;
import main.java.quemepongo2.model.Ciudad;
import main.java.quemepongo2.model.CiudadUsuario;
import main.java.quemepongo2.model.Usuario;
import main.java.quemepongo2.persistence.CiudadRepository;
import main.java.quemepongo2.persistence.CiudadUsuarioRepository;
import main.java.quemepongo2.persistence.UsuarioRepository;

@Service
public class CiudadService{

	@Autowired
	CiudadRepository repo;
	
	@Autowired
	CiudadUsuarioRepository repoCiudadUsuario;
	
	@Autowired
	UsuarioRepository repoUsuario;

	public List<CiudadRs> findAll() {
		List <CiudadRs> ciudadesRs = new ArrayList<CiudadRs>();
		List <Ciudad> ciudades=repo.findAll();
		for (Ciudad ciudad:ciudades) {
			ciudadesRs.add(new CiudadRs(ciudad));
		}
		return ciudadesRs;
	}

	public Ciudad getById(int id) {
		return repo.getOne(id);
	}
	
	public List<CiudadRs> getByLikeNombre(String q){
		List<CiudadRs> ciudadesRs = new ArrayList<>();
		
		List<Ciudad> ciudades = repo.searchCiudadLikeName(q); 
		
		for (Ciudad ciudad : ciudades) {
			ciudadesRs.add(new CiudadRs(ciudad));
		}
		
		return ciudadesRs;
	}
	
	public List<CiudadRs> getAllByUsuario(int userId){
		Usuario usuario = repoUsuario.findById(userId).get();
		
		List <CiudadRs> ciudadesRs = new ArrayList<CiudadRs>();
		
		List<CiudadUsuario> ciudadUsuarioOrdeanada = new ArrayList<>(usuario.getCiudadUsuarios());
		
		ciudadUsuarioOrdeanada.sort(new Comparator<CiudadUsuario>() {
			public int compare(CiudadUsuario o1, CiudadUsuario o2) {
				if(!o1.isPredeterminado()) {
					return o1.getIndiceOrdenado() < o2.getIndiceOrdenado() ? -1 : 1;
				}else {
					return -1;
				}
			}
		});
		
		for (CiudadUsuario ciudadUsuario : ciudadUsuarioOrdeanada) {
			ciudadesRs.add(new CiudadRs(ciudadUsuario.getCiudad()));
		}
		
		return ciudadesRs;
	}
	
	public void setCiudadPredeterminada(int ciudadId, int userId) {
		Usuario usuario = repoUsuario.findById(userId).get();
		boolean existe = false;
		
		for (CiudadUsuario ciudadUsuario : usuario.getCiudadUsuarios()) {
			if(ciudadUsuario.getId().getCiudadId() == ciudadId) {
				existe = true;
			}
		}
		
		if(existe) {
			for (CiudadUsuario ciudadUsuario : usuario.getCiudadUsuarios()) {
				ciudadUsuario.setPredeterminado(false);
				if(ciudadUsuario.getId().getCiudadId() == ciudadId) {
					ciudadUsuario.setPredeterminado(true);
				}
			}
		}
		
		repoUsuario.flush();
	}
	
}
