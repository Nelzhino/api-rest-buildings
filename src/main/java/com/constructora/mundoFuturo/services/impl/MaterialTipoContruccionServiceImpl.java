package com.constructora.mundoFuturo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.constructora.mundoFuturo.dto.SolicitudConstruccionDTO;
import com.constructora.mundoFuturo.models.Material;
import com.constructora.mundoFuturo.models.MaterialTipoConstruccion;
import com.constructora.mundoFuturo.models.Parametro;
import com.constructora.mundoFuturo.models.SolicitudConstruccion;
import com.constructora.mundoFuturo.models.TipoConstruccion;
import com.constructora.mundoFuturo.models.PK.MaterialTipoConstruccionPK;
import com.constructora.mundoFuturo.repository.IMaterialRepository;
import com.constructora.mundoFuturo.repository.IMaterialTipoConstruccionRepository;
import com.constructora.mundoFuturo.repository.IParametroRepository;
import com.constructora.mundoFuturo.repository.ITipoConstruccionRepository;
import com.constructora.mundoFuturo.services.IMaterialTipoContruccionService;

@Service
@Transactional
public class MaterialTipoContruccionServiceImpl implements IMaterialTipoContruccionService {
	
	@Autowired
	private IMaterialTipoConstruccionRepository materialTipoConstruccionRepository;
	
	@Autowired
	private ITipoConstruccionRepository tipoContruccionRepository;
	
	@Autowired
	private IMaterialRepository materialRepository;
	
	@Autowired
	private IParametroRepository parametroRepository;

	@Override
	public void registrar(SolicitudConstruccionDTO solicitudConstruccionDTO) {
		
		SolicitudConstruccion solicitudConstruccion = solicitudConstruccionDTO.getSolicitudConstruccion();
		
		this.materialTipoConstruccionRepository.deleteBySolicitudConstruccion(solicitudConstruccion);
		
		TipoConstruccion tipoConstruccion = this.tipoContruccionRepository.findByNombre(solicitudConstruccionDTO.getTipoContrucccion().name());
		
		List<Material> materiales = this.materialRepository.findAll();
		List<Parametro> cantidades = this.parametroRepository.findByNombreStartsWith(tipoConstruccion.getNombre());
		materiales.forEach( material -> {
			MaterialTipoConstruccionPK id = new MaterialTipoConstruccionPK();
			id.setIdSolicitud(solicitudConstruccion.getIdSolicitud());
			id.setIdMaterial(material.getIdMaterial());
			id.setIdTipoConstruccion(tipoConstruccion.getIdTipoConstruccion());
			
			MaterialTipoConstruccion materialTipoConstruccion = new MaterialTipoConstruccion();
			
			materialTipoConstruccion.setId(id);
			materialTipoConstruccion.setMaterial(material);
			materialTipoConstruccion.setTipoConstruccion(tipoConstruccion);
			materialTipoConstruccion.setSolicitudConstruccion(solicitudConstruccion);

			Optional<Parametro> cantidadEncontrada = cantidades.stream().filter( cantidad -> cantidad.getNombre().contains(material.getNomenclatura())).findFirst(); 
			if(cantidadEncontrada.isPresent()) {
				materialTipoConstruccion.setCantidad(Integer.parseInt(cantidadEncontrada.get().getDescripcion()));
			}
	
			this.materialTipoConstruccionRepository.save(materialTipoConstruccion);					
		});
	}

}
