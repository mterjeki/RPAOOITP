
package hu.bme.RPAOOITP.components;

import hu.bme.RPAOOITP.domain.io.AbstractRPAOOITPDTO;
import lombok.Getter;

import com.vaadin.ui.FormLayout;

public class GenericFormLayout<DTO extends AbstractRPAOOITPDTO> extends FormLayout implements GenericLayout<DTO> {
	
	@Getter
	private final DTO data;
	
	public GenericFormLayout( final DTO dto ) {
		super();
		this.data = dto;
	}
	
}
