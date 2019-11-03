package main.java.quemepongo2.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.quemepongo2.api.requests.PreferenciaRq;
import main.java.quemepongo2.model.Usuario;
import main.java.quemepongo2.persistence.UsuarioRepository;

@Service
public class PreferenciaService {
	
	@Autowired
	UsuarioRepository repo;

	public void actualizarPreferencias(PreferenciaRq preferenciaRq) {
		boolean bufanda=preferenciaRq.isBufanda();
		boolean lentes=preferenciaRq.isLentes();
		boolean paraguas=preferenciaRq.isParaguas();
		boolean protectorSolar=preferenciaRq.isProtectorSolar();
		
		Usuario user=repo.findById(preferenciaRq.getUserId()).get();
		
		user.setTieneBufanda(bufanda);
		user.setTieneLentes(lentes);
		user.setTieneParaguas(paraguas);
		user.setTieneProtectorSolar(protectorSolar);
		repo.save(user);
	}
	
	
	
}
