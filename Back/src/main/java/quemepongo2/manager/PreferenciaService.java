package main.java.quemepongo2.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.quemepongo2.api.requests.PreferenciaRq;
import main.java.quemepongo2.api.responses.PreferenciaRs;
import main.java.quemepongo2.model.Usuario;
import main.java.quemepongo2.persistence.UsuarioRepository;

@Service
public class PreferenciaService {
	
	@Autowired
	UsuarioRepository repo;

	public PreferenciaRs actualizarPreferencias(PreferenciaRq preferenciaRq, int userId) {
		boolean bufanda=preferenciaRq.isBufanda();
		boolean lentes=preferenciaRq.isLentes();
		boolean paraguas=preferenciaRq.isParaguas();
		boolean protectorSolar=preferenciaRq.isProtectorSolar();
		boolean gorra=preferenciaRq.isGorra();
		
		Usuario user=repo.findById(userId).get();
		
		user.setTieneBufanda(bufanda);
		user.setTieneLentes(lentes);
		user.setTieneParaguas(paraguas);
		user.setTieneProtectorSolar(protectorSolar);
		user.setTieneGorra(gorra);
		repo.save(user);
		
		return new PreferenciaRs(bufanda, lentes, paraguas, protectorSolar, gorra);
	}
	
	public PreferenciaRs obtenerPreferncias(int userId) {
		Usuario user=repo.findById(userId).get();
		return new PreferenciaRs(user);
	}
	
	
}
