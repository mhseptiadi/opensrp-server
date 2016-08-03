/**
 * The MultimediaRegisterService class implements Multimedia support for Elco registry and Household registry. 
 * @author Asifur
 */

package org.opensrp.register.mcare.service;

import static org.opensrp.common.util.EasyMap.create;

import java.util.List;
import java.util.Map;

import org.opensrp.domain.Multimedia;
import org.opensrp.register.mcare.domain.Child;
import org.opensrp.register.mcare.repository.AllChilds;
import org.opensrp.repository.MultimediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MultimediaRegisterService {
	
	private final AllChilds allChilds;
	
	private MultimediaRepository multimediaRepository;
	
	@Autowired
	public MultimediaRegisterService(AllChilds allChilds, MultimediaRepository multimediaRepository) {
		this.allChilds = allChilds;
		this.multimediaRepository = multimediaRepository;
	}
	
	/**
	 * The getMultimedia function retrieves Multimedia for all data stored in Elco registry and
	 * Household registry
	 * 
	 * @param void
	 * @return void
	 * @throws No Exception
	 */
	public void getMultimedia() {
		
		List<Child> hhs = allChilds.findAllChilds();
		
		for (Child hh : hhs) {/*
		                      
		                      hh.multimediaAttachments().clear();
		                      
		                      List<Multimedia> multimediaList = multimediaRepository.findByCaseIdAndFileCategory(hh.caseId(), "dp");
		                      
		                      if(multimediaList.size()>0)
		                      {    		
		                      for (Multimedia multimedia : multimediaList){
		                      
		                      Map<String, String> att = create("contentType", multimedia.getContentType())
		                      .put("filePath", multimedia.getFilePath())
		                      .put("fileCategory", multimedia.getFileCategory())
		                      .map();       		
		                      
		                      hh.multimediaAttachments().add(att);
		                      }
		                      }
		                      
		                      for (int i = 0; i < hh.ELCODETAILS().size(); i++){
		                      
		                      String id = hh.ELCODETAILS().get(i).get("id");
		                      
		                      List<Multimedia> profileImagePath = multimediaRepository.findByCaseIdAndFileCategory(id, "dp");
		                      
		                      if(profileImagePath.size()>0)
		                      {
		                      hh.ELCODETAILS().get(i).put("profileImagePath", profileImagePath.get(0).getFilePath());
		                      }
		                      
		                      List<Multimedia> nidImagePath = multimediaRepository.findByCaseIdAndFileCategory(id, "nidImage");
		                      
		                      if(nidImagePath.size()>0)
		                      {
		                      hh.ELCODETAILS().get(i).put("nidImagePath", nidImagePath.get(0).getFilePath());
		                      }

		                      }
		                       	
		                      allChilds.update(hh);
		                      */}
		
		/*List<Elco> elcos = allElcos.allOpenELCOs();

		for (Elco ec : elcos) {
			
			ec.multimediaAttachments().clear();
			
			List<Multimedia> multimediaList1 = multimediaRepository.findByCaseIdAndFileCategory(ec.caseId(), "dp");
			
			if(multimediaList1.size()>0)
		    {	    		
			    for (Multimedia multimedia : multimediaList1){
			    	   	    	
			    	Map<String, String> att = create("contentType", multimedia.getContentType())
						.put("filePath", multimedia.getFilePath())
						.put("fileCategory", multimedia.getFileCategory())
						.map();       		
			
					 ec.multimediaAttachments().add(att);
			    }
			}
			
			List<Multimedia> multimediaList2 = multimediaRepository.findByCaseIdAndFileCategory(ec.caseId(), "nidImage");
			
			if(multimediaList2.size()>0)
		    { 	    	
			    for (Multimedia multimedia : multimediaList2){
			    	
					 Map<String, String> att = create("contentType", multimedia.getContentType())
						.put("filePath", multimedia.getFilePath())
						.put("fileCategory", multimedia.getFileCategory())
						.map();       		
			
					 ec.multimediaAttachments().add(att);
			    }
			}
			
			allElcos.update(ec);
		}*/
	}
	
	/**
	 * The saveMultimediaFileToRegistry function stores Multimedia file instantly in Elco registry
	 * or Household registry
	 * 
	 * @param Multimedia
	 * @return void
	 * @throws No Exception
	 */
	public void saveMultimediaFileToRegistry(Multimedia multimediaFile) {
		Child hh = allChilds.findByCaseId(multimediaFile.getCaseId());
		if (hh != null) {
			hh.multimediaAttachments().clear();
			
			Map<String, String> att = create("contentType", multimediaFile.getContentType())
			        .put("filePath", multimediaFile.getFilePath()).put("fileCategory", multimediaFile.getFileCategory())
			        .map();
			
			hh.multimediaAttachments().add(att);
			allChilds.update(hh);
		} else {/*
		        Elco elco = allElcos.findByCaseId(multimediaFile.getCaseId());
		        if(elco != null){
		        elco.multimediaAttachments().clear();
		        
		         Map<String, String> att = create("contentType", multimediaFile.getContentType())
		        	.put("filePath", multimediaFile.getFilePath())
		        	.put("fileCategory", multimediaFile.getFileCategory())
		        	.map();       		
		        
		         elco.multimediaAttachments().add(att);
		         
		         allElcos.update(elco);
		         
		         Child hd = allChilds.findByCaseId(elco.getDetail(relationalid));
		         
		         int i;
		         for (i = 0; i < hd.ELCODETAILS().size(); i++){
		        	 if(multimediaFile.getCaseId().equalsIgnoreCase(hd.ELCODETAILS().get(i).get(id)))
		        		 break;
		         }
		         
		         if(multimediaFile.getFileCategory().equalsIgnoreCase("dp"))
		            {
		        	hd.ELCODETAILS().get(i).put("profileImagePath", multimediaFile.getFilePath());
		            }
		        
		         if(multimediaFile.getFileCategory().equalsIgnoreCase("nidImage"))
		            {
		        	hd.ELCODETAILS().get(i).put("nidImagePath", multimediaFile.getFilePath());
		            }
		         
		         allChilds.update(hd);	     		 
		        }	
		        */}
		System.out.println("Image saved in registry");
	}
}
