
package hu.bme.RPAOOITP.components;

import hu.bme.RPAOOITP.dto.RPAOOITPDTO;

import com.vaadin.ui.FormLayout;

public class GenericFormLayout<DTO extends RPAOOITPDTO> extends FormLayout {
	
	private DTO dto;
	
	public GenericFormLayout( final DTO dto ) {
		super();
		this.dto = dto;
	}
	
}
