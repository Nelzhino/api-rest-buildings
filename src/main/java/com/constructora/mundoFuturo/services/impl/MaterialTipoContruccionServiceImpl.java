package com.constructora.mundoFuturo.services.impl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.constructora.mundoFuturo.dto.MaterialDTO;
import com.constructora.mundoFuturo.dto.MaterialTipoConstruccionDTO;
import com.constructora.mundoFuturo.dto.ResponseDTO;
import com.constructora.mundoFuturo.dto.SolicitudConstruccionDTO;
import com.constructora.mundoFuturo.exceptions.AplicacionException;
import com.constructora.mundoFuturo.models.Material;
import com.constructora.mundoFuturo.models.MaterialTipoConstruccion;
import com.constructora.mundoFuturo.models.Parametro;
import com.constructora.mundoFuturo.models.SolicitudConstruccion;
import com.constructora.mundoFuturo.models.TipoConstruccion;
import com.constructora.mundoFuturo.models.PK.MaterialTipoConstruccionPK;
import com.constructora.mundoFuturo.repository.IMaterialRepository;
import com.constructora.mundoFuturo.repository.IMaterialTipoConstruccionRepository;
import com.constructora.mundoFuturo.repository.IParametroRepository;
import com.constructora.mundoFuturo.repository.ISolicitudContruccionRepository;
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
	private IMaterialRepository materialRepository;;

	@Autowired
	private ISolicitudContruccionRepository solicitudConstruccionRepository;
	
	@Autowired
	private IParametroRepository parametroRepository;

	private static final Logger logger = LogManager.getLogger(MaterialTipoContruccionServiceImpl.class);

	@Transactional(rollbackFor = { AplicacionException.class })
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

	@Transactional(rollbackFor = { AplicacionException.class })
	@Override
	public ResponseDTO registrar(MaterialTipoConstruccionDTO materialTipoConstruccionDTO) throws AplicacionException {
		int codigo = 0;
		String mensaje = "";
		if (materialTipoConstruccionDTO != null
				&& materialTipoConstruccionDTO.getIdSolicitud() != null
				&& materialTipoConstruccionDTO.getMateriales() != null
				&& !materialTipoConstruccionDTO.getMateriales().isEmpty()) {
			Optional<SolicitudConstruccion> solicitudConstruccion = this.solicitudConstruccionRepository
					.findById(materialTipoConstruccionDTO.getIdSolicitud());
			
			if (solicitudConstruccion != null && solicitudConstruccion.isPresent()) {
				
				List<MaterialTipoConstruccion> materialTipoConstruccions =  
						this.materialTipoConstruccionRepository.findBySolicitudConstruccion(solicitudConstruccion.get());
				
				TipoConstruccion tipoConstruccion = materialTipoConstruccions.get(0).getTipoConstruccion();
				
				if (tipoConstruccion != null) {

					List<MaterialDTO> materialDTOs = materialTipoConstruccionDTO.getMateriales();

					materialDTOs.forEach(material -> {
						try {
							List<Material> listadoMaterial = this.materialRepository
									.findByNomenclatura(material.getMaterial());

							MaterialTipoConstruccionPK id = new MaterialTipoConstruccionPK();
							id.setIdMaterial(listadoMaterial.get(0).getIdMaterial());
							id.setIdSolicitud(solicitudConstruccion.get().getIdSolicitud());
							id.setIdTipoConstruccion(tipoConstruccion.getIdTipoConstruccion());

							MaterialTipoConstruccion materialTipoConstruccion = new MaterialTipoConstruccion();
							materialTipoConstruccion.setId(id);
							materialTipoConstruccion.setCantidad(material.getCantidad());
							materialTipoConstruccion.setMaterial(listadoMaterial.get(0));
							materialTipoConstruccion.setSolicitudConstruccion(solicitudConstruccion.get());
							materialTipoConstruccion.setTipoConstruccion(tipoConstruccion);

							materialTipoConstruccionRepository.save(materialTipoConstruccion);

						} catch (Exception ex) {
							logger.error(
									"Ha ocurrido un error mientras se realizaba guardado de informacion. SOLICITUD:"
											+ solicitudConstruccion.get().getIdSolicitud());
						}

					});

					codigo = 1;
					mensaje = "Se realiza registro de materiales.";
				} else {
					codigo = 0;
					mensaje = "No existe la tipo construccion "+ materialTipoConstruccionDTO.getContruccion() ;
				}

			} else {
				codigo = 0;
				mensaje = "No existe la solicitud "+ materialTipoConstruccionDTO.getIdSolicitud();
			}

		} else {
			mensaje = "Faltan parametros para adicionar materiales.";
		}
		return new ResponseDTO(codigo, mensaje);
	}

}
