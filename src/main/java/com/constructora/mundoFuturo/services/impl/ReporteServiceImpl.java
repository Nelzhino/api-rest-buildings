
package com.constructora.mundoFuturo.services.impl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.constructora.mundoFuturo.models.view.VistaReporte;
import com.constructora.mundoFuturo.repository.VistaReporteRepository;
import com.constructora.mundoFuturo.services.IReporteService;

@Service
@Transactional
public class ReporteServiceImpl implements IReporteService {
	
	private static final Logger logger = LogManager.getLogger(ReporteServiceImpl.class);

	@Autowired
	private VistaReporteRepository vistaReporteRepository;
	
	@Override
	public void generarReporteSolicitudConstruccion(HttpServletResponse response) {
		
	       response.setContentType("text/csv");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
	        String currentDateTime = dateFormatter.format(new Date());
	         
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=solicitud_" + currentDateTime + ".csv";
	        response.setHeader(headerKey, headerValue);
	         
	        List<VistaReporte> solicitudes = this.vistaReporteRepository.findAll();	        
	        
	        if(solicitudes!= null && !solicitudes.isEmpty()) {
	            ICsvBeanWriter csvWriter;
				try {
					csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
					String[] csvHeader = {"Numero-Solicitud", "Construccion", "Fecha Inicio", "Fecha Fin", "Estado"};
					String[] nameMapping = {"numeroSolicitud", "construccion", "fechaInicio", "fechaFin", "estado"};
					
					csvWriter.writeHeader(csvHeader);
			        for (VistaReporte user : solicitudes) {
			            csvWriter.write(user, nameMapping);
			        }
					csvWriter.close();
				} catch (IOException e) {
					logger.warn("Hubo un problema al generar el reporte.");
				}
		
	        } else {
				logger.warn("No existe la solcitud.");
	        }
	        
	}

}
